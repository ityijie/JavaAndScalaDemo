package general

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Administrator on 2016/10/28.
  *
  * 端口,实时处理.
  */
object socket {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建SparkConf对象
    conf.set("spark.executor.memory", "1g");
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("Wow,TopNGroup App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息

    val ssc = new StreamingContext("local[2]","aa",Seconds(300))

    println("-------")
    //lines1 = flat,map,transformation
    //lines2 = flat,mmm,map
    val  lines = ssc.socketTextStream("127.0.0.1",9999);
    //flatMap ->> words = flas map transformation
    val words = lines.flatMap(_.split(","))

    //时间间隔操作
    // map  ->> 每个做key 做累加,  reduceBykey ->> 相同的key的Value相加
    val wc = words.map((_,1)).reduceByKey(_+_)
    //序列化到内存
    wc.persist()
    //打印
    wc.print()
    //开始工作
    ssc.start()
    //?
    ssc.awaitTermination()
  }
}
