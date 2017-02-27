import java.util

/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 数组
  */
object test31 extends App{

  var arr = new Array[Int](5) //如果没有赋值，int-->则值为0  string-->则值为null
  arr(1) = 2
  for (i <- arr)
    println(i)

  var arr2 = Array(1,2,3,4,5,"hehe") //不指定数据类型,不过看数据类型的而决定由哪些api
  for(i <- arr2)
    println(i)


  var arr3 = Array(1,2,3,4,5)
  println(arr3.max)
  println(arr3.min)
  println(arr3.mkString("-")) //按一定格式打印 1-2-3-4-5


  //使用for的推倒式产生新的数组 重新赋值给arr4 数组
  var arr4 = for (i<-arr3) yield i*2
  println(arr4.mkString("-"))

  //与java API交互,  将list转换成java的list ，才能用java的add方法，get方法
  var list = new util.ArrayList[Int]
  list.add(1)
  list.add(3)
  for (i <- 0 to list.size() -1){
    println(list.get(i))
  }


  var arr7 = Array(1,2,3,4,5)
  //filtet 过滤， _ （下划线） 代表所有元素   map ,对数组中素有元素*2
  var arr8  = arr7.filter(_ % 2 == 0).map(_ * 2)
  print(arr8.mkString("-"))



}