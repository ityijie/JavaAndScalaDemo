package Rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

/**
  * Created by YJ on 2017/8/17.
  */


/*
优点:

1.DataSet可以在编译时检查类型
2.并且是面向对象的编程接口
3.只需要序列化数据,不需要序列化结构
4.不受GC影响
DataSet结合了RDD和DataFrame的优点, 并带来的一个新的概念Encoder当序列化数据时,
Encoder产生字节码与off-heap进行交互, 能够达到按需访问数据的效果,而不用反序列化整个对象.
Spark还没有提供自定义Encoder的API, 但是未来会加入.
* */
//定义的class 必须放在类的外面,填坑,不知道为什么
case class Persons(name: String, age: Long)

object DataSet {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._

    // 目前支持String, Integer, Long等类型直接创建Dataset
    //Creating Datasets

    //第一种
    Seq(1, 2, 3).toDS().show()

    //第二种
    val ds = Seq(Persons("Andy", 32)).toDS()
    ds.show()

    //第三种
    val idAgeRDDRow = sc.parallelize(Array(Row(1, 30), Row(2, 29), Row(4, 21)))
    val schema = StructType(Array(StructField("id", DataTypes.IntegerType), StructField("age", DataTypes.IntegerType)))
    // 在2.0.0-preview中这行代码创建出的DataFrame, 其实是DataSet[Row]

    val idAgeDS = sqlContext.createDataFrame(idAgeRDDRow, schema)
    idAgeDS.show()

    //第四种
    import sqlContext.implicits._    //隐式函数
    /*  1.序列化
        2. 序列化成对象，调用对象属性*/
    val PersionDF = sqlContext.read.json("d://SparkProject//demo1//testdata//person.json").as[Persons]
    PersionDF.show()
    //第五种
    sqlContext.createDataset(sc.parallelize(Array(1, 2, 3))).show()
  }

}
