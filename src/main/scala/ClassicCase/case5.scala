/*
数据准备
eightteen_a.txt
102
10
39
109
200
11
3
90
28

eightteen_b.txt
5
2
30
838
10005*/

package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：求最大最小值
  * Created by YJ on 2017/2/8.
  */


object case5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val fifth = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case5/*", 2)
    val res = fifth.filter(_.trim().length>0).map(line => ("key",line.trim.toInt)).groupByKey().map(x => {
      var min = Integer.MAX_VALUE
      var max = Integer.MIN_VALUE
      for(num <- x._2){
        if(num>max){
          max = num
        }
        if(num<min){
          min = num
        }
      }
      (max,min)
    }).collect.foreach(x => {
      println("max\t"+x._1)
      println("min\t"+x._2)
    })
  }

}
