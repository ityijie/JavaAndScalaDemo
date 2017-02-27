package general

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Administrator on 2016/11/22.
  * 黑名单分析案例
  */
object fenxi {

  def main(args: Array[String]): Unit = {
    //Logger logger = Logger.getLogger(log4j3.class);
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf() //创建SparkConf对象
    conf.set("spark.executor.memory", "1g");
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("Wow,TopNGroup App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息

    val ssc = new StreamingContext("local[2]","aa",Seconds(10))

    val  adsClickStream  = ssc.socketTextStream("127.0.0.1",9999);

    /*----------------------------------业务处理代码-Start---------------------------------*/

    val blackList = Array(("Spy", true),("Cheater", true))
    //将Array 序列化成RDD (Spy,true)
    val blackListRDD = ssc.sparkContext.parallelize(blackList, 8)
    //将输入的内容 用Map遍历变化RDD 输入:  333 hadoop =>  输出:(hadoop,(333 hadoop))
    val adsClickStreamFormatted = adsClickStream.map { ads => (ads.split(" ")(1), ads) }
    adsClickStreamFormatted.transform( user => {
      user.leftOuterJoin(blackListRDD)
    }).print


    //将两个RDD对比,排除黑名单
    adsClickStreamFormatted.transform(userClickRDD => {
      // 通过leftOuterJoin操作既保留了左侧用户广告点击内容的RDD的所有内容，
      // 又获得了相应点击内容是否在黑名单中
      // hadoop,((333,hadoop),null)
      // Spy,((111,Spy),true)
      val joinedBlackListRDD = userClickRDD.leftOuterJoin(blackListRDD)


      /**
        * 进行filter过滤的时候，其输入元素是一个Tuple：（name,((time,name), boolean)）
        * 其中第一个元素是黑名单的名称，第二元素的第二个元素是进行leftOuterJoin的时候是否存在的值。
        * 如果存在的话，表面当前广告点击是黑名单，需要过滤掉，否则的话是有效点击内容；
        */
      val validClicked = joinedBlackListRDD.filter(joinedItem => {
        if(joinedItem._2._2.getOrElse(false))
        {
          false
        } else {
          true
        }
      })
      //  hadoop,((333,hadoop),null)  => 333,hadoop
      validClicked.map(validClick => {validClick._2._1})
    }).print



    /*----------------------------------业务处理代码-End---------------------------------*/

    //开始工作
    ssc.start()
    //?
    ssc.awaitTermination()
  }

}
