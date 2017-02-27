package SecondarySort

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/24.
  */
object secondarySort {

  /**
    *
    * data.txt
  2015,1,24
  2015,3,56
  2015,1,3
  2015,2,-43
  2015,4,5
  2015,3,46
  2014,2,64
  2015,1,4
  2015,1,21
  2015,2,35
  2015,2,0
    */
  def main(args: Array[String]): Unit = {
    println("---")
    val conf = new SparkConf() //创建SparkConf对象
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("Wow,TopNGroup App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息
    val sc = new SparkContext(conf)

    sc.setLogLevel("WARN")
    val lines = sc.textFile("d://data.txt", 1)

    val pairs=lines.map { line => (line.split(",")(0) + "-" +line.split(",")(1),line.split(",")(2).toInt) }
    pairs.collect().foreach(println)

    val grouped=pairs.groupByKey
    grouped.collect().foreach(println)

    val result = grouped.map(item => (item._1,item._2.toList.sortWith(_<_)))
    result.collect().foreach(println)

    sc.stop()
  }


}
