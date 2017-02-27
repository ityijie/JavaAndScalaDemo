import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/7.
  */

object Sparktest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    println("\n-----------RDD基本转换-----------")

    val Rdd = sc.parallelize(1 to 10) //创建RDD ,一个分区
    val map = Rdd.map(_ * 2) //对RDD中的每个元素都乘于2
    map.foreach(x => print(x + " "))

    println("\n去重")
    val list = List(1, 1, 2, 5, 2, 9, 6, 1)
    val distinctRDD = sc.parallelize(list)
    val unionRDD = distinctRDD.distinct()
    unionRDD.collect.foreach(x => print(x + " "))


    println("\n-----------键值K-V基本转换-----------")
    println("mapValus(fun):对[K,V]型数据中的V值map操作")
    val list1 = List(("mobin", 22), ("kpop", 20), ("lufei", 23))
    val listrdd = sc.parallelize(list1)
    val mapValuesRDD = listrdd.mapValues(_ + 2)
    mapValuesRDD.foreach(println)


    //reduceByKey和groupByKey 区别，前者，按key值分区,并统计,后者,只按key值分区,不统计
    println("reduceByKey(func,numPartitions):按Key进行分组，使用给定的func函数聚合value值, numPartitions设置分区数，提高作业并行度")
    val arr2 = List(("A", 3), ("A", 2), ("B", 1), ("B", 3))
    val Rdd2 = sc.parallelize(arr2)
    //val reduceByKeyRDD = Rdd2.reduceByKey(_ + _ )
    val reduceByKeyRDD = Rdd2.reduceByKey(_ + _, 2)
    reduceByKeyRDD.foreach(println)

    println("groupByKey(numPartitions):按Key进行分组，返回[K,Iterable[V]]，numPartitions设置分区数，提高作业并行度")
    val arr3 = List(("A", 1), ("B", 3), ("A", 2), ("B", 2))
    val Rdd3 = sc.parallelize(arr3)
    val groupByKeyRDD = Rdd3.groupByKey()
    groupByKeyRDD.foreach(println)
    //sortWith 自定义函数排序
    groupByKeyRDD.map(rdd => {
      (rdd._1, rdd._2.toList.sortWith(_ > _).take(2))
    }).foreach(println)
    //sorted 自身元素排序
    groupByKeyRDD.map(rdd => {
      (rdd._1, rdd._2.toList.sorted.take(2))
    }).foreach(println)


    //PS:按照Key scall的顺序排序
    println("sortByKey(accending，numPartitions):返回以Key排序的（K,V）键值对组成的RDD，accending为true时表示升序，为false时表示降序，numPartitions设置分区数，提高作业并行度")
    val arr4 = List(("A", 2), ("B", 1), ("A", 1), ("B", 3))
    val rdd4 = sc.parallelize(arr4)
    //val sortByKeyRDD = rdd4.sortByKey()
    val sortByKeyRDD = rdd4.sortByKey(false)
    sortByKeyRDD.foreach(println)


    println("LeftOutJoin(otherDataSet，numPartitions):左外连接，包含左RDD的所有数据，如果右边没有与之匹配的用None表示,numPartitions设置分区数，提高作业并行度")
    val arr5 = Array(("A", 1), ("B", 2), ("A", 2), ("B", 3), ("C", 1))
    val arr15 = Array(("A", true), ("B", false))
    val rdd5 = sc.parallelize(arr5, 3)
    val rdd15 = sc.parallelize(arr15, 3)
    val leftOutJoinRDD = rdd5.leftOuterJoin(rdd15)
    leftOutJoinRDD.map { as => (as._1 + "-" + as._2._1 + "-" + as._2._2) }.foreach(println)
    leftOutJoinRDD.foreach(println)
    leftOutJoinRDD.foreach(a => println(a._2._2))


    val arr = List(("A", 1), ("B", 2), ("A", 2), ("B", 3))
    val rdd1 = sc.parallelize(arr, 2)
    val countByKeyRDD = rdd1.countByKey()
    val collectAsMapRDD = rdd1.collectAsMap()

    println("countByKey: 作用于K-V类型的RDD上，统计每个key的个数，返回(K,K的个数)")
    countByKeyRDD.foreach(print)

    println("\ncollectAsMap:作用于K-V类型的RDD上，作用与collect不同的是collectAsMap函数不包含重复的key，对于重复的key。后面的元素覆盖前面的元素")
    collectAsMapRDD.foreach(print)

    val data = sc.parallelize(List(("www", 1), ("iteblog", 1), ("com", 1), ("bbs", 2), ("iteblog", 1), ("com", 4), ("good", 1),
      ("www", 1), ("iteblog", 1), ("com", 1), ("bbs", 2), ("iteblog", 1), ("com", 4), ("good", 1),
      ("www", 1), ("iteblog", 1), ("com", 1), ("bbs", 2), ("iteblog", 1), ("com", 4), ("good", 1)),
      3)
    val result = data.combineByKey(x => x,
      (x : Int, y : Int) => x + y,
      (x: Int, y: Int) => x +y)
   result.collect().foreach(println)
    data.reduceByKey(_+_).foreach(println)



    println("\n-----------Action操作-----------")
    println("\n注意是两个分区-->")
    val rdd = sc.parallelize(1 to 10, 2)
    val filterRDD = rdd.filter(_ > 5)
    val reduceRDD = rdd.reduce(_ + _) //两个分区数据,相加后,第一个分区加上第二分区,
    rdd.map { as => (as) }.reduce(_ + _) //reduce(_ + "-" + _)
    val reduceRDD1 = rdd.reduce(_ - _) //如果分区数据为1结果为 -53 两个分区数据,相减后,第一个分区减去第二分区,
    val countRDD = rdd.count()
    val firstRDD = rdd.first()
    val takeRDD = rdd.take(5) //输出前个元素
    val topRDD = rdd.top(3) //从高到底输出前三个元素
    val takeOrderedRDD = rdd.takeOrdered(3) //按自然顺序从底到高输出前三个元素
    println("Filter:过滤")
    filterRDD.foreach(x => print(x + " "))

    println("\nreduce:通过函数func先聚集各分区的数据集，再聚集分区之间的数据，func接收两个参数，返回一个新值，新值再做为参数继续传递给函数func，直到最后一个元素")
    println("func +: " + reduceRDD)
    println("func -: " + reduceRDD1)

    println("count:返回数据集元素个数 ")
    println("count: " + countRDD)

    println("first:返回数据集的第一个元素 ")
    println("first: " + firstRDD)

    println("take:以数组的形式返回数据集上的前n个元素")
    println("take:")
    takeRDD.foreach(x => print(x + " "))

    println("\ntop:")
    topRDD.foreach(x => print(x + " "))

    println("\ntakeOrdered:")
    takeOrderedRDD.foreach(x => print(x + " "))


    sc.stop
  }

}
