/*一、案例分析：
1、需求分析
对输入文件中数据进行就算学生平均成绩。输入文件中的每行内容均为一个学生的姓名和他相应的成绩，如果有多门学科，则每门学科为一个文件。
要求在输出中每行有两个间隔的数据，其中，第一个代表学生的姓名，第二个代表其平均成绩。


2、原始数据
1）math：
张三,88
李四,99
王五,66
赵六,77

2）china：
张三,78
李四,89
王五,96
赵六,67

3）english：
张三,80
李四,82
王五,84
赵六,86

样本输出：
张三,82
李四,90
王五,82
赵六,76

*/


package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：求平局值
  * Created by YJ on 2017/2/8.
  */


object case4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val four = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case4/*", 3)

    val a = four.filter(_.trim.length > 0) //数据过滤
      .map(line => //数据整理
      (line.trim().split(",")(0), line.trim.split(",")(1).toInt)
    )
      .groupByKey() //按key分组 (张三,CompactBuffer(78, 80, 88))
      .map(x => {
      var num = 0.0
      var sum = 0
      for (i <- x._2) {
        //遍历该值
        sum = sum + i
        num = num + 1
      }
      val avg = sum / num
      val fm = f"$avg%1.2f"   //1.2->取后面两位小数,格式化数据
      println("fm:"+fm)
      (x._1, fm)
    }
    ).collect.foreach(x => println(x._1+"\t"+x._2))

    //资源学习
    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "资源学习!"
    var fs = printf(
      "浮点型变量为 " + "%1.2f, " +
        "整型变量为  " + "%d,"+
        "字符串为 " + " %s", floatVar, intVar, stringVar)
    println(fs)


  }

}
