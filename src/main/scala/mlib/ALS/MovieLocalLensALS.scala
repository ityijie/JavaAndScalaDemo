package mlib.ALS

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source

/**
  * Created by Administrator on 2016/11/7.
  */
object MovieLocalLensALS {
  def main(args: Array[String]): Unit = {
    val dataDirRating = "d://SparkProject//demo1//testdata//ratings.dat"
    val dataDirMovies = "d://SparkProject//demo1//testdata//movies.dat"
    val dataDirPeople = "d://SparkProject//demo1//testdata//personalRatings.txt"
    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.eclipse.jetty.server").setLevel(Level.OFF)

    //设置运行环境
    val sparkConf = new SparkConf().setAppName("MovieLensALS").setMaster("local[5]")
    //val sc = new StreamingContext(sparkConf,Seconds(5))
    val sc = new SparkContext(sparkConf)

    //jie- 装载样本评分数据 -ratings.dat- 用户ID::电影ID::评分::时间
    //装载样本评分数据，其中最后一列Timestamp取除10的余数作为key，Rating为值，即(Int，Rating)
    val myRatings = loadRatings(dataDirPeople)
    val myRatingsRDD = sc.parallelize(myRatings, 1)
    val data = sc.textFile(dataDirRating)

    val ratings = data.map {
      line =>
        val fields = line.split("::")
        // format: (timestamp % 10, Rating(userId, movieId, rating))   ??--Rating 已经定好了是3种数据--??
        (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
    }

    //jie- 装载电影目录数据. -movies.dat-   电影ID::电影名称::电影种类
    //装载电影目录对照表(电影ID->电影标题)
    val movies = sc.textFile(dataDirMovies).map {
      line =>
        val fields = line.split("::")
        // format: (movieId, movieName)
        (fields(0).toInt, fields(1))
    }.collect().toMap

    //统计有用户数量和电影数量以及用户对电影的评分数目
    val numRatings = ratings.count()
    val numUsers = ratings.map(_._2.user).distinct().count()
    val numMovies = ratings.map(_._2.product).distinct().count()
    println("Got " + numRatings + " ratings from " + numUsers + " users " + numMovies + " movies")

    //将样本评分表以key值切分成3个部分，分别用于训练 (60%，并加入用户评分), 校验 (20%), and 测试 (20%)
    //该数据在计算过程中要多次应用到，所以cache到内存
    val numPartitions = 4
    //jie-训练数据
    val training = ratings.filter(x => x._1 < 6).values.union(myRatingsRDD).repartition(numPartitions).persist()
    //jie-检验数据
    val validation = ratings.filter(x => x._1 >= 6 && x._1 < 8).values.repartition(numPartitions).persist()
    //jie-测试数据
    val test = ratings.filter(x => x._1 >= 8).values.persist()

    val numTraining = training.count()
    val numValidation = validation.count()
    val numTest = test.count()
    println("Training: " + numTraining + " validation: " + numValidation + " test: " + numTest)


    //训练不同参数下的模型，并在校验集中验证，获取最佳参数下的模型
    val ranks = List(8, 12)
    val lambdas = List(0.1, 10.0)
    val numIters = List(10, 20)
    var bestModel: Option[MatrixFactorizationModel] = None
    var bestValidationRmse = Double.MaxValue
    var bestRank = 0
    var bestLambda = -1.0
    var bestNumIter = -1


    //寻找最佳模型
    for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
      val model = ALS.train(training, rank, numIter, lambda)
      //寻找最佳值:validationRmse
      val validationRmse = computeRmse(model, validation, numValidation)
      println("RMSE(validation) = " + validationRmse + " for the model trained with rank = "
        + rank + ",lambda = " + lambda + ",and numIter = " + numIter + ".")

      if (validationRmse < bestValidationRmse) {
        bestModel = Some(model)
        bestValidationRmse = validationRmse
        bestRank = rank
        bestLambda = lambda
        bestNumIter = numIter
      }
    }

    //用最佳模型预测测试集的评分，并计算和实际评分之间的均方根误差（RMSE）
    val testRmse = computeRmse(bestModel.get, test, numTest)
    println("The best model was trained with rank = " + bestRank + " and lambda = " + bestLambda
      + ", and numIter = " + bestNumIter + ", and its RMSE on the test set is " + testRmse + ".")

    //create a naive baseline and compare it with the best model
    val meanRating = training.union(validation).map(_.rating).mean
    val baselineRmse = math.sqrt(test.map(x => (meanRating - x.rating) * (meanRating - x.rating)).reduce(_ + _) / numTest)
    val improvement = (baselineRmse - testRmse) / baselineRmse * 100
    println("The best model improves the baseline by " + "%1.2f".format(improvement) + "%.")

    //推荐前十部最感兴趣的电影，注意要剔除用户已经评分的电影
    val myRatedMovieIds = myRatings.map(_.product).toSet
    val candidates = sc.parallelize(movies.keys.filter(!myRatedMovieIds.contains(_)).toSeq)
    //模型推荐bestModel
    val recommendations = bestModel.get
      .predict(candidates.map((0, _)))
      .collect
      .sortBy(-_.rating)
      .take(10)
    var i = 1
    println("Movies recommended for you:")
    recommendations.foreach { r =>
      println("%2d".format(i) + ": " + movies(r.product))
      i += 1
    }





    //val ssc = new StreamingContext("local[2]","aa",Seconds(300))
    val ssc = new StreamingContext(sc,Seconds(10))
    val  lines = ssc.socketTextStream("127.0.0.1",9999);
    //flatMap ->> words = flas map transformation
    val words = lines.flatMap(_.split(","))
    val wc = words.map((_,1)).reduceByKey(_+_)
    //序列化到内存
    wc.persist()
    //打印
    wc.print()
    //开始工作
    ssc.start()
    //开始监听
    ssc.awaitTermination()
  }

  /** 校验集预测数据和实际数据之间的均方根误差 **/
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {

    val predictions: RDD[Rating] = model.predict((data.map(x => (x.user, x.product))))
    val predictionsAndRatings = predictions.map { x => ((x.user, x.product), x.rating) }
      .join(data.map(x => ((x.user, x.product), x.rating))).values
    math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
  }

  /** 装载用户评分文件 personalRatings.txt **/
  def loadRatings(path: String): Seq[Rating] = {
    val lines = Source.fromFile(path).getLines()
    val ratings = lines.map {
      line =>
        val fields = line.split("::")
        Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
    }.filter(_.rating > 0.0)
    if (ratings.isEmpty) {
      sys.error("No ratings provided.")
    } else {
      ratings.toSeq
    }
  }


}
