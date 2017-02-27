package Sql

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by Administrator on 2016/12/6.
  *  textFlie -> table
    {"name":"Michael","age":20}
    {"name":"Andy", "age":30}
    {"name":"Justin", "age":19}
  */
object Sqltest2 {
  case class user( name:String,age:Int)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建SparkConf对象
    conf.set("spark.executor.memory", "1g");
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("Wow,TopNGroup App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local[2]")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息

    val ssc = new SparkContext(conf)
    ssc.setLogLevel("ERROR")
    //加载SQL对象
    val sqlContext = new SQLContext(ssc)
    //加载数据
    var dr =  ssc.textFile("d://sparktest/people2.txt")
    val RowRDD = dr.map(_.split(",")).map(p => Row(p(0),p(1)))
    val schemaString = "name age"
    val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))
    val rowRDD = dr.map(_.split(",")).map(p => Row(p(0), p(1)))
    val df = sqlContext.createDataFrame(rowRDD, schema)

    //注册到临时表
    df.registerTempTable("user")
    //输出, df 的数据类型
    df.printSchema()
    //输出所有名字
    df.select("name").show()
    df.filter(df("age") > 21).show()
    df.groupBy("age").count().show()
    val teenagers = sqlContext.sql("SELECT name, age FROM user WHERE age >= 13 AND age <= 19")
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)

    println("-------")
    ssc.stop()
  }
}
