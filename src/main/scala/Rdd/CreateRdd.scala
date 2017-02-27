package Rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * jie:创建rdd
  * Rdd是由sparkContext创建并引用，与scala无关
  * Created by YJ on 2017/2/7.
  */
object CreateRdd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    // CreateRddFromList(sc)
    // CreateRddFromLocal(sc)
    // CreateRddFromHdfs(sc)
    // RddToPairRdd(sc)
    ReadFromRdd(sc)
    sc.stop()
  }

  /**
    * 从集合中创建RDD
    */
  def CreateRddFromList(sc: SparkContext): Unit = {
    val nums = 1 to 10
    val rdd = sc.parallelize(nums)
    val sum = rdd.reduce(_ + _)
    println("sum:" + sum)
  }

  /**
    * 从本地加载成RDD
    *
    * @param sc
    */
  def CreateRddFromLocal(sc: SparkContext): Unit = {
    val rows = sc.textFile("G://sparktest/peopl.json")
    println(rows.first())
    val length = rows.map(row => row.length()).reduce(_ + _)
    println("local total chars length:" + length)
  }

  /**
    * 将HDFS中加载成为RDD
    *
    * @param sc
    */
  def CreateRddFromHdfs(sc: SparkContext): Unit = {
    val lines = sc.textFile("hdfs://192.168.109.130:8020//user/flume/20170207/*")
    println("hdfs total chars length:" + lines.map(row => row.length()).reduce(_ + _))
  }

  /** *
    * 将Rdd转换成PairRdd（mapRdd）
    *
    * @param sc
    */
  def RddToPairRdd(sc: SparkContext): Unit = {
    val nums = 1 to 10
    val rdd = sc.parallelize(nums)
    val RddPair = rdd.map(x => (x, 1))
    RddPair.reduceByKey(_ + _).foreach(println)
  }

  /**
    * 读取PairRdd中的值,并统计
    *  ps:
    *     flatMap, List("A,B,C","D,E,F") ->List("A,B,C,D,E,F")
    *     take，前期是list
    * @param sc
    */
  def ReadFromRdd(sc: SparkContext): Unit = {
    val inFile = sc.textFile("G://sparktest/log.txt")
    val pirdd = inFile.flatMap{line => {line.split(",").toList.take(5)}.map(x => (x,1))}
    pirdd.reduceByKey(_+_).foreach(println)
    println("-------")
  }
}
