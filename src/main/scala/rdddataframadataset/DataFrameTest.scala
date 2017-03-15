package rdddataframadataset

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

/**
  * Created by Administrator on 2017/3/15.
  */


/**
  * 优点:
  *     1.只需要序列化数据,不需要序列化结构
  *     2.不受GC影响
  * 缺点:
  *     1.不是面对对象风格 ( _.age )
  *     2.运行时检查错误   ( idAgeDF.col("aage") )  没有aage这个列
  * DataFrame引入了schema和off-heap
  *
  * schema : RDD每一行的数据,结构都是一样的,这个结构就存储在schema中.Spark通过schema就能够读懂
  *          数据,因此在通讯和IO时就只需要序列化和反序列化数据,   而    结构部分  就可以   省略
  * off-heap : 意味着JVM对以外的内存,这些内存直接受操作系统管理(而不是Jvm),Spark能够以二进制的形式
  *            序列化数据(不包括结构)到off-heap中,当要操作系统时,就直接操作off-heap内存,由于spark
  *            理解schema,所以知道怎么做
  *
  * off-heap就像地盘, schema就像地图, Spark有地图又有自己地盘了, 就可以自己说了算了, 不再受JVM的限制, 也就不再收GC的困扰了.
  * 通过schema和off-heap, DataFrame解决了RDD的缺点, 但是却丢了RDD的优点. DataFrame不是类型安全的, API也不是面向对象风格的.
  *
  */
object DataFrameTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val sqlContext = new SQLContext(sc)
    /**
      * id      age
      * 1       30
      * 2       29
      * 3       21
      */
    val idAgeRDDRow = sc.parallelize(Array(Row(1, 30), Row(2, 29), Row(4, 21)))

    val schema = StructType(Array(StructField("id", DataTypes.IntegerType), StructField("age", DataTypes.IntegerType)))

    val idAgeDF = sqlContext.createDataFrame(idAgeRDDRow, schema)
    // API不是面向对象的
    idAgeDF.filter(idAgeDF.col("age") > 25).show()
    // 不会报错, DataFrame不是编译时类型安全的
    idAgeDF.filter(idAgeDF.col("age") > "").show()
  }

}
