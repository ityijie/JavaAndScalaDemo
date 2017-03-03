package Sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/12/6.
  *  textFlie -> table
    Michael,20
    Andy,30
    Justin,19
    需要添加import sqlContext.implicits._,才可以调用.toDF
    定义People格式
    testFile->map(p=>People(p(1),p(2))) ->DataFrame->Table
    可以是用Sql.Context.sql("Sql语句")
  */
object Sqltest3 {
  case class User(name:String,age:Int)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建SparkConf对象
    //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setAppName("SparkSql App!")
    //此时，程序在本地运行，不需要安装Spark集群
    conf.setMaster("local[2]")
    //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息
    val ssc = new SparkContext(conf)
    ssc.setLogLevel("ERROR")
    //加载SQL对象
    val sqlContext = new SQLContext(ssc)

    //加载数据
    val dr =  ssc.textFile("d://sparktest/people2.txt")
    import sqlContext.implicits._
    val df = dr.map(_.split(","))
               .map(p => User(p(0), p(1).trim.toInt)).toDF()
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
