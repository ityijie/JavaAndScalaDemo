package mlib.ALS

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/7.
  */
object Socketchuji {


  /**
    *
    *u.data
    * userId,moiveId,评分rate,时间戳
  196	242	3	881250949
  186	302	3	891717742
  22	377	1	878887116
  244	51	2	880606923
  166	346	1	886397596
    u.user
    */
  def main(args: Array[String]): Unit = {

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.eclipse.jetty.server").setLevel(Level.OFF)

    //设置运行环境
    val sparkConf = new SparkConf().setAppName("MovieLensALS").setMaster("local[5]")
    val sc = new SparkContext(sparkConf)
   // sc.setLogLevel("INFO")
    //jie -加载数据,所有的历史数据.所有用户对电影的评分(u.data)
    println("---加载所有用户对电影的评分---")
    //val rawData = sc.textFile("d://u.data")
    val rawData = sc.textFile("d://SparkProject//demo1//testdata//u.data")

    //jie -查看第一条数据
    rawData.first()

    //jie -只需要前3个字段 -- 用户ID，影片ID，评分，时间
    val rawRatings = rawData.map(_.split("\t").take(3)).cache()
    //jie -查看第一条数据
     rawRatings.first()

    /*val a = rawData.map{adc => (adc.split("\t")(1),adc.split("\t")(2),adc.split("\t")(3))}.take(3)
    a.foreach(print)*/

    //jie -将数据转换成Rating数据集
    val ratings = rawRatings.map { case Array(user, movie, rating) => Rating(user.toInt, movie.toInt, rating.toDouble) }
    ratings.first()

    //统计数据
    val user =  ratings.map(_.user).distinct()
    val product = ratings.map(_.product).distinct()

    println("数据容量: Got "+ratings.count() + " ratings from " + user.count() + " users on " +product.count() + " products")





    //jie -根据数据集训练模型
    //参数1- 数据集-类是对用户ID，影片ID,评分的封装
    //参数2- 对应ALS模式中的因子个数,即 两个小矩阵,U(m*k)和V(n*K)中的K ?   --模型中隐性因子的个数?
    //参数3- 对应运行时的迭代次数
    //参数4- 控制模型的正则化过程，从而控制模型的过拟合情况。-什么鬼?
    println("---训练模型---")
    val model = ALS.train(ratings, 50, 10, 0.01)

    //用户特征向量  -- for what ?
    model.userFeatures
    println("userFeatures--"+model.userFeatures.count())
    //产品特征向量   -- for what  ?
    model.productFeatures
    println("productFeatures--"+model.productFeatures.count())



    //jie-  ------ 预测数据-----
    println("-------- 预测数据-------")
    //参数1- 用户
    //参数2- 产品
    //场景,用户789,选了产品123, 之后该推荐什么产品
    val predictedRating = model.predict(789, 123)

    println("---加载所有电影资源---")
    //jie- 加载产品集合(u.item)
    //用户推荐，向给定用户推荐物品。这里，我们给用户789推荐前10个他可能喜欢的电影。我们可以先解析下电影资料数据集
    val movies = sc.textFile("d://SparkProject//demo1//testdata//u.item")
    //取两个数据,产品编号和产品名字
    val titles = movies.map(line => line.split("\\|").take(2)).map(array => (array(0).toInt, array(1))).collectAsMap()


    //jie- 用户789,推荐10个产品
    val userId = 789
    val K = 10
    val topKRecs = model.recommendProducts(userId, K)
    println("---结果预测---")
    println("---用户ID,推荐ID,评分相关度---")
    println(topKRecs.mkString("\n"))

    println("---推荐前10个可能喜欢的电影---")
    println("---推荐ID的书名,评分---")
    topKRecs.map(rating => (titles(rating.product), rating.rating)).foreach(println)


    val moviesForUser = ratings.keyBy(_.user).lookup(789)
    println("---总共有多少个相关的电影---")
    println(moviesForUser.size)


    println("---电影推荐排序--")
    moviesForUser.sortBy(-_.rating).take(10).map(rating => (titles(rating.product), rating.rating)).foreach(println)

    sc.stop()


    val ssc = new StreamingContext(sc,Seconds(2))
    val  lines = ssc.socketTextStream("127.0.0.1",9999);
    //flatMap ->> words = flas map transformation
    val words = lines.flatMap(_.split(","))

    val suserId = words.map(r => r.take(0))

    //println("id "+suserId )
   // val stopKRecs = model.recommendProducts(suserId, 10)
    println("---端口结果预测---")
    println("---用户ID,推荐ID,评分相关度---")
    //println(stopKRecs.mkString("\n"))
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



}
