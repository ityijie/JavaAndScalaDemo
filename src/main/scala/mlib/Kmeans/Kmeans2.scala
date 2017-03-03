package mlib.Kmeans

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/8.
  *
  * //机场人员分类
  */
object Kmeans2 {
  def main(args: Array[String]): Unit = {
    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)


    // 设置运行环境
    val conf = new SparkConf().setAppName("mlib/Kmeans").setMaster("local[5]")
    val sc = new SparkContext(conf)

    println("----装载数据----")
    // 装载数据集
    val data = sc.textFile("d://kmeans_data2.txt", 1)
    val parsedData = data.map(s => Vectors.dense((s.split(","))(1).split(' ').map(_.toDouble)))

    //将数据集聚类，5个类，20次迭代，形成数据模型
    val numClusters = 5
    val numIterations = 20
    val model = KMeans.train(parsedData, numClusters, numIterations)

    // 打印数据模型的中心点
    println("Cluster centers:--模型中心点--")
    for (c <- model.clusterCenters) {
      println("  " + c.toString)
    }

    //使用误差平方之和来评估数据模型
    //该值越小越好
    println("----训练模型----")
    val cost = model.computeCost(parsedData)
    println("Within Set Sum of Squared Errors =" + cost)



    //交叉评估2，返回数据集和结果
    val result2 = data.map {
      line =>
        val linevectore = Vectors.dense((line.split(","))(1).split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        line + " " + prediction
    }.foreach(println)//.saveAsTextFile("e:\\result")
    sc.stop()

  }

}
