/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 元组
  */

object test33 extends App{
  //下标是从1开始
  var t = (1,2,3,4)   //元组的定义
  println(t._1)       //使用._(下标)
  println(t _1)       //省略.

  //业务场景？ --
  var (first,second,third,forth) = t //利用模式匹配范文元组元素
  println(first)


  var s = "Hello World"
  println(s.partition(_.isUpper))
  //(HW,ello orld)


  //拉链操作
  var arr1 = Array(1,2,3,4)
  var arr2 = Array("a","b","c","d")
  var arr3 = arr1.zip(arr2)    //元组，，，利用两个数组，组合成元组,元组个数取决于最小数量数组

  for (i <- arr3){
    println(i)
  }

  var arr4 = arr3.toMap
  for(i <- arr4)
    println(i)
}
