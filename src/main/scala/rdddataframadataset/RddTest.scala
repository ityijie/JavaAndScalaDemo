package rdddataframadataset

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/3/15.
  */
/**
  * 优点:
  *   1.编译时类型安全
  *     编译时就能检查出类型错误  (_.age > "")  能检测"" 空字符串
  *   2.面向对象的编程风格       ( _.age )
  *     直接通过类名点的方式来操作数据
  *
  * 缺点:
  *   1.序列化和反序列化的性能开销
  *     无论是集群间的通讯,还是IO操作都需要对对象的结构和数据进行序列化和反序列化
  *   2.GC的性能开销
  *     频繁的创建和销毁对象,势必会增加GC
  *
  */
object RddTest {
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
    case class Person(id: Int, age: Int)
    val idAgeRDDPerson = sc.parallelize(Array(Person(1, 30), Person(2, 29), Person(3, 21)))

    // 优点1
    // idAge.filter(_.age > "") // 编译时报错, int不能跟String比

    // 优点2
    idAgeRDDPerson.filter(_.age > 25) // 直接操作一个个的person对象
  }

}
