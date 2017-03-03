package Sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/12/6.
  * json -> table
  * {"name":"Michael"s,"age":20}
  * {"name":"Andy", "age":30}
  * {"name":"Justin", "age":19}
  */
object Sqltest1 {


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
    println("------------方式一:json->加载到临时表---------------")
   // RJsonToTemp(sqlContext)


    println("------------方式二:json->直接显示---------------")
   // RJson(sqlContext)


    println("------------方式三:读取数据库->直接显示--------------")
    Rmysql(sqlContext)
    ssc.stop()
  }

  def ceshi(a: Int): Unit = {
    print(a)
  }

  /**
    * 读取json文件(返回的的DataFrame)加载到临时table,使用selete语句查询
    * @param scontext
    *
    * json->DataFrame->Table
      可以是用Sql.Context.sql("Sql语句")
    *
    */
  def RJsonToTemp(scontext: SQLContext): Unit ={
    val df = scontext.read.json("d://sparktest/peopl.json")
    //注册到临时表
    df.registerTempTable("user")
    //输出, df 的数据类型
    df.printSchema()
    //输出所有名字
    df.select("name").show()
    df.filter(df("age") > 21).show()
    df.groupBy("age").count().show()
    val teenagers = scontext.sql("SELECT name, age FROM user WHERE age >= 13 AND age <= 19")
    //val rowRDD = dr.map(_.split(",")).map(p => Row(p(0), p(1)))
    teenagers.map(t => "Name: " + t(0) + " age :" + t(1)).collect().foreach(println)
    scontext.sql("SELECT name, age FROM user").map(t => "Name: " + t(0) + " age :" + t(1)).collect().foreach(println)
  }

  /**
    * 读取json文件(返回的是DataFrame)直接展示,通过name(已有)查看数据
    *
    * json->DataFrame
      不能使用SQL语句,但是可以使用.DataFrame.sql的API
    *
    * @param scontext
    */
  def RJson(scontext: SQLContext): Unit ={
    //val sc = scontext
    val df2 = scontext.read.json("d://sparktest/peopl.json");
    df2.persist()
    df2.show()
    df2.printSchema()
    df2.select("name").show()
    df2.select(df2("name"), df2("age") + 10).show()
    df2.filter(df2("age") > 10).show()
  }

  /**
    * 读取mysql数据(返回的是DataFrame),通过item_id(已有)查看数据
    *
    * mysql->Dataframe
      不能使用SQL语句,但是可以使用.DataFrame.sql的API,查询的列祖必须是已有
    *
    * @param scontext
    */
  def Rmysql(scontext: SQLContext): Unit = {
    val sc = scontext
    val driverUrl = "jdbc:mysql://192.168.200.30:3306/vip?user=root&password=abcd1234&zeroDateTimeBehavior=convertToNull&amp;characterEncoding=utf-8"
    val tableName = "dict_score_trade_item"
    val jdbcDF = sc.read
      .options(Map("url" -> driverUrl,
        "dbtable" -> tableName))
      .format("jdbc")
      .load()
    jdbcDF.printSchema()
    //ps显示的item_id 必须是要在表中存在的
    jdbcDF.select(jdbcDF("item_id")).show()
    println(jdbcDF.count())
    jdbcDF.collect().map { row => {
      //   println(row.split(",")(0))
      println(row.toString())
    }
    }
/*
    val sqlcommand="select * from memberbaseinfo"
    val sel = sc.sql(sqlcommand)
    prop.setProperty("user","root")
    prop.setProperty("password","123456")

    // 调用DataFrameWriter将数据写入mysql
    val dataResult = sc.sql(sqlcommand).write.mode(SaveMode.Append).jdbc("jdbc:mysql://192.168.200.30:3306/vip","dict_score_trade_item",prop) // 表可以不存在
    println(ac.name.get+"  "+ac.value)*/
  }
}
