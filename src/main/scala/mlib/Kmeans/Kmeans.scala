package mlib.Kmeans

/**
  * Created by Administrator on 2016/11/7.
  */
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 普通数据分类
  */
object Kmeans {
  def main(args: Array[String]): Unit = {
    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    // 设置运行环境
    val conf = new SparkConf().setAppName("mlib/Kmeans").setMaster("local[5]")
    val sc = new SparkContext(conf)

    // 装载数据集
    val data = sc.textFile("d://SparkProject//demo1//src//testdata//kmeans_data.txt", 1)
    // jie- 向量矩阵类Vectors 这个是标配呀
    // jie- 将数据矩阵化
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.trim.toDouble))).cache()
    //val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))

    // 将数据集聚类，2个类，20次迭代，进行模型训练形成数据模型
    // 或者用循环取得最佳的误差值  cost
    val numClusters = 2
    val numIterations = 20
    //  模型参数(矩形化数据集,训练类个数,训练迭代次数)
    val model = KMeans.train(parsedData, numClusters, numIterations)

    // 获取数据模型的中心点
    println("Cluster centers:--模型中心点--")
    for (c <- model.clusterCenters) {
      println("  " + c.toString)
    }

    // 使用误差平方之和来评估数据模型
    // 该值越小越好
    val cost = model.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + cost)

    //使用模型测试单点数据
    //用模型去测量每一个数字,分布在类别
    println("Vectors 0.2 0.2 0.2 is belongs to clusters:" + model.predict(Vectors.dense("0.2 0.2 0.2".split(' ').map(_.toDouble))))
    println("Vectors 0.25 0.25 0.25 is belongs to clusters:" + model.predict(Vectors.dense("0.25 0.25 0.25".split(' ').map(_.toDouble))))
    println("Vectors 8 8 8 is belongs to clusters:" + model.predict(Vectors.dense("8 8 8".split(' ').map(_.toDouble))))


    //使用全部数据分类.
    println("--交叉评估1--")
    // 交叉评估1，只返回结果
    val testdata = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))
    //将原始数据放进模型判断.将所有数据分类
    val result1 = model.predict(testdata)
    result1.foreach(println)
    //result1.saveAsTextFile("d://SparkProject//demo1//src//testdata//KmeansDataResult/result1")


    println("--交叉评估2--")
    // 交叉评估2，将每一个元素放进去评估,格式化输出,交叉评估1的强化版
    val result2 = data.map {
      line =>
        val linevectore = Vectors.dense(line.split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        line + " " + prediction
    }
    result2.foreach(println)
    //result2.saveAsTextFile("d://SparkProject//demo1//src//testdata//KmeansDataResult/result2")

    sc.stop()



  }

}
