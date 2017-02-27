package topn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/21.
  *
  * 离线,批处理.->>求topNs--Standalone模式
  */
object StandaloneTopN {
  def main(args: Array[String]): Unit = {
    println("---")
    val conf = new SparkConf() //创建SparkConf对象
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("TopNGroup App")
    //此时，程序在本地运行，不需要安装Spark集群
    //conf.setMaster("local")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息
    val sc = new SparkContext(conf)

    sc.setLogLevel("WARN")
    //读取本地文件并设置为一个Partion
    val lines = sc.textFile(args(0), 1)
    //返回一个map集合
    val pairs=lines.map { line => (line.split(",")(0),line.split(",")(1).toInt) }

    /*-----------------------数量统计-->OK-------------------------*/
    //pairs.reduceByKey(_ + _).saveAsTextFile(args(1))
    /*-----------------------数量统计-->OK-------------------------*/


    //统计数据,尽量用reduceByKey,不要用groupByKey,优化点
    //reduceByKey,在本机suffle后,再发送一个总map
    //groupByKey,发送本机所有的map,安装本机的的
    val grouped=pairs.groupByKey
    //取元素最大的五个
    val groupedTop5=grouped.map(grouped=>
    {
      (grouped._1,grouped._2.toList.sortWith(_<_).take(5))
    })
    //五个元素排序
    val groupedKeySorted=groupedTop5.sortByKey()

    //groupedKeySorted.saveAsHadoopFile(args(1))
    /*----------------------排序统计结果输出-->OK-------------------------*/
    groupedKeySorted.saveAsTextFile(args(1))

    val result = groupedKeySorted.collect().foreach(pair=>{
      println(pair._1+":")
      pair._2.foreach { println }
    })

    sc.stop()
  }

}
