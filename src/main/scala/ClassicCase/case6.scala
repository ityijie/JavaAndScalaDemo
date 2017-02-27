/*
一、需求分析
#orderid,userid,payment,productid
求topN的payment值
a.txt
1,9819,100,121
2,8918,2000,111
3,2813,1234,22
4,9100,10,1101
5,3210,490,111
6,1298,28,1211
7,1010,281,90
8,1818,9000,20

b.txt
100,3333,10,100
101,9321,1000,293
102,3881,701,20
103,6791,910,30
104,8888,11,39

*/

package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：求top值
  * Created by YJ on 2017/2/8.
  */


object case6 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val six = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case6/*", 2)
    var idx = 0;
    val res = six.filter(x => (x.trim().length > 0) && (x.split(",").length == 4))
      .map(_.split(",")(2))
      .map(x => (x.toInt, ""))
      .sortByKey(false)    //fasle ->倒序
      .map(x => x._1).take(5)
      .foreach(x => {
        idx = idx + 1
        println(idx + "\t" + x)
      })
  }

}
