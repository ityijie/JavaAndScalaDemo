package Kmeans

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/21.
  * 输出到不同的文件
  */
object StandaloneAirport {

  def main(args: Array[String]): Unit = {
    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    // 设置运行环境
    val conf = new SparkConf().setAppName("Kmeans")
    val sc = new SparkContext(conf)

    println("----装载数据----")
    // 装载数据集
    val data = sc.textFile(args(0), 1)
    val parsedData = data.map(s => Vectors.dense((s.split(",")) (1).split(' ').map(_.toDouble)))

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
        val linevectore = Vectors.dense((line.split(",")) (1).split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        line + " " + prediction
    }.foreach(println) //.saveAsTextFile("e:\\result")

    /*-----------------------id+分类id-->OK-------------------------*/
    //保存文件内容为 id+分类id
   /* val result = data.map {
      line =>
        val linevectore = Vectors.dense((line.split(",")) (1).split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        //定义输出格式
        line.split(",")(0) + " " + prediction
    }.saveAsTextFile(args(1))*/
    /*-----------------------id+分类id-->OK-------------------------*/


    /*-----------------------根据key值输出到不同的文件-->OK-------------------------*/
    //根据分类id,存储到不同的文件中.
    val result = data.map {
      line =>
        val linevectore = Vectors.dense((line.split(",")) (1).split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        //定义输出格式
        prediction + " " + line
    }
    // val pairs=lines.map { line => (line.split(",")(0),line.split(",")(1).toInt) }
    result
      .map{line => (line.split(" ")(0),line.split(" ")(1))}
      /*-----------------------根据key值输出到key值命名的文件-->OK-------------------------*/
          .partitionBy(new HashPartitioner(numClusters))
              .saveAsHadoopFile(args(1),classOf[String], classOf[String], classOf[RDDMultipleTextOutFormat])
    /*-----------------------根据key值输出到相同的临时文件-->OK-------------------------*/
        //.partitionBy(new HashPartitioner(numClusters))
            //.saveAsTextFile(args(1))
    /*-----------------------根据key值输出到不同的文件-->OK-------------------------*/

    sc.stop()
  }
}


class RDDMultipleTextOutFormat extends MultipleTextOutputFormat[Any,Any]{
  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String =
    key.asInstanceOf[String]

  //指定输出,不包括key
/*   override def generateActualKey(key: Any, value: Any): AnyRef =
     NullWritable.get()*/
}