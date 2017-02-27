package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：数据去重问题
  * Created by YJ on 2017/2/7.
  * 统计数据,尽量用reduceByKey,不要用groupByKey,优化点
  * reduceByKey,在本机suffle后,再发送一个总map，发送到一个总机器上汇总，（汇总要压力小）
  * groupByKey,发送本机所有的map,在一个机器上汇总（汇总压力大）
  */
/*

数据格式
flie1:
2012-3-1 a
2012-3-2 b
2012-3-3 c
2012-3-4 d
2012-3-5 a
2012-3-6 b
2012-3-7 c
2012-3-3 c
flie2:
2012-3-1 b
2012-3-2 a
2012-3-3 b
2012-3-4 d
2012-3-5 a
2012-3-6 c
2012-3-7 d
2012-3-3 c
*/


object case2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //获取数据
    val two = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case2/*")
    two.filter(_.trim().length>0) //需要有空格。
        .map(line=>(line.trim,""))//全部值当key，(key value,"")
          .groupByKey()//groupByKey,过滤重复的key value ，发送到总机器上汇总
              .sortByKey() //按key value的自然顺序排序
                  .keys.collect().foreach(println) //所有的keys变成数组再输出
    //第二种有风险
    two.filter(_.trim().length>0)
          .map(line=>(line.trim,""))
            .distinct()
                .reduceByKey(_+_)
                    .sortByKey()
                        .foreach(println)

    //reduceByKey,在本机suffle后,再发送一个总map，发送到一个总机器上汇总，（汇总要压力小）
    //groupByKey,发送本机所有的map,在一个机器上汇总（汇总压力大）
    //如果数据在不同的机器上，则会出现先重复数据，distinct，reduceBykey，只是在本机上去重，谨慎一点的话，在reduceByKey后面需要加多一个distinct

  }
}
