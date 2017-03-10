package Sql

import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by Administrator on 2017/3/6.
  */
object TestHive {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local", "test")
    val hiveContext = new HiveContext(sc)
    val rdd = hiveContext.sql("select * from test")
    rdd.collect().foreach(println)
  }

}
