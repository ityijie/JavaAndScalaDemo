
/*1、"数据排序"是许多实际任务执行时要完成的第一项工作，
比如学生成绩评比、数据建立索引等。这个实例和数据去重类似，都是先对原始数据进行初步处理，为进一步的数据操作打好基础。
1）、需求描述
对输入文件中数据进行排序。输入文件中的每行内容均为一个数字，即一个数据。
要求在输出中每行有两个间隔的数字，其中，第一个代表原始数据在原始数据集中的位次，第二个代表原始数据。*/

/*
2）输入文件
file1：
2
32
654
32
15
756
65223

file2：
5956
22
650
92

file3：
26
54
6

样例输出：
1    2
2    6
3    15
4    22
5    26
6    32
7    32
8    54
9    92
10    650
11    654
12    756
13    5956
14    65223
*/





package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：数据排序
  * Created by YJ on 2017/2/8.
  */


object case3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val three = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case3")
    var idx = 0

    //由入输入文件有多个，产生不同的分区，为了生产序号，使用HashPartitioner将中间的RDD归约到一起。
    import org.apache.spark.HashPartitioner

    val res = three.filter(_.trim().length>0)                    //清洗数据
               .map(num=>(num.trim.toInt,""))                    //转换数据
                  .partitionBy(new HashPartitioner(1))           //将所有数据放到一个分区
                      .sortByKey()                               //按自然顺序排序
                          .map(t => {                            //整理输出格式
                              idx += 1
                              (idx,t._1)}
                              ).collect.foreach(x =>  println(x._1 +"\t" + x._2) )

  }

}
