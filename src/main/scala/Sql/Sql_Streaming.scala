package Sql
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}
/**
  * Created by Administrator on 2016/12/6.
  * Streaming socket 与  sql
  *
  */
object Sql_Streaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建SparkConf对象
    conf.set("spark.executor.memory", "1g");
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("Wow,TopNGroup App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息

    val ssc = new StreamingContext("local[2]","aa",Seconds(10))

    println("-------")
    //lines1 = flat,map,transformation
    //lines2 = flat,mmm,map
    val  lines = ssc.socketTextStream("127.0.0.1",9999);
    //flatMap ->> words = flas map transformation
    val words = lines.flatMap(_.split(" "))

    //调用foreachRDD方法，遍历DStream中的RDD
    words.foreachRDD((rdd: RDD[String], time: Time) => {
      // Get the singleton instance of SQLContext
      val sqlContext = SQLContextSingleton.getInstance(rdd.sparkContext)
      import sqlContext.implicits._

      // Convert RDD[String] to RDD[case class] to DataFrame
      val wordsDataFrame = rdd.map(w => Record(w)).toDF()

      // Register as table
      wordsDataFrame.registerTempTable("words")

      // Do word count on table using SQL and print it
      val wordCountsDataFrame =
      sqlContext.sql("select word, count(*) as total from words group by word")
      println(s"========= $time =========")
      wordCountsDataFrame.show()
    })
    //开始工作
    ssc.start()
    //?
    ssc.awaitTermination()
  }

}
/** Case class for converting RDD to DataFrame */
case class Record(word: String)

object SQLContextSingleton {

  @transient  private var instance: SQLContext = _

  def getInstance(sparkContext: SparkContext): SQLContext = {
    if (instance == null) {
      instance = new SQLContext(sparkContext)
    }
    instance
  }
}
