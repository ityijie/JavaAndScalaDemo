package Kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Administrator on 2016/11/18.
  */
/***
  * 对接kafka ,有两种方式
  * 1.基于Receiver的模式:createStream
  * 2.直接模式(无Receiver)模式 :createDirectStream
  */
object UserClickCountAnalyticstest {
  def main(args: Array[String]): Unit = {
    var masterUrl = "local[2]"
    if (args.length > 0) {
      masterUrl = args(0)
    }

    // Create a StreamingContext with the given master URL
    val conf = new SparkConf().setMaster(masterUrl).setAppName("UserClickCountStat")
    val ssc = new StreamingContext(conf, Seconds(5))

    // Kafka configurations
    val topics = Set("lijietest")
    val brokers = "172.20.1.104:9092"
    val kafkaParams = Map[String, String](
    "metadata.broker.list" -> brokers,
    "group.id" -> "ceshi",
      "serializer.class" -> "kafka.serializer.StringEncoder")

    val dbIndex = 1
    val clickHashKey = "app::users::click"

    // Create a direct stream
    //val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
    for (i <- 1 to 4){
      val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

      val events = kafkaStream.map( line => line._2)
      events.print()
    }




   /* val events = kafkaStream.flatMap(line => {
      //{"uid":"97edfc08311c70143401745a03a50706","event_time":"1479458955275","os_type":"Android","click_count":6}
      val data = JSONObject.fromObject(line._2)
      Some(data)
    })*/




    // Compute user click times
/*    val userClicks = events.map(x => (x.getString("uid"), x.getInt("click_count"))).reduceByKey(_ + _)
    userClicks.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords => {
        partitionOfRecords.foreach(pair => {
          val uid = pair._1
          val clickCount = pair._2
          val jedis = RedisClient.pool.getResource
          jedis.select(dbIndex)
          jedis.hincrBy(clickHashKey, uid, clickCount)
          RedisClient.pool.returnResource(jedis)
        })
      })
    })*/

    ssc.start()
    ssc.awaitTermination()


  }

}
