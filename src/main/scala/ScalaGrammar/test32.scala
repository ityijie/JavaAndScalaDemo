/**
  * Created by Administrator on 2016/9/2.
  */


/**
  * 映射
  * java ->map
  * scala -> 映射
  *
  */
object test32 extends App {

  var map1 = Map[String, Int]("chenyijie" -> 17, "chenyixuan" -> 18)
  //不可变的映射，只能间接改动
  map1 += "chenyijian" -> 19

  for (i <- map1) {
    println(i) //打印键值对，称作为对偶
  }

  //修改值
  map1 += ("chenyijie" -> 177)

  for (i <- map1) {
    println(i)
  }

  //取值
  println(map1("chenyixuan"))

  if (map1.contains("zhaoliu")){
    println(map1("zhaoliu"))
  }

  //以k v 方式去遍历map1
  for((k,v) <- map1){
    println(k + "->" + v)
  }

  //keys 是setl类型
  var keys = map1.keys
  println(keys)

  var value = map1.values
  println(value)

  //利用map的keys取值
  for(i <- keys){
    println(map1(i))
  }


  /*上述的不可变映射*/



  /*下述可变的映射*/
  var map11 = scala.collection.mutable.Map[String,Int]("yijie"->18,"yixuan"->19)
  map11("yijian") ==19
  for(i <- map11){
    println(i)
  }







}
