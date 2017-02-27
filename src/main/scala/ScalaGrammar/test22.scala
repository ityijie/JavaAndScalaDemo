/**
  * Created by Administrator on 2016/9/2.
  */
object test22 extends App{
  /**
    * while for
    */

  /*
  * java -->
  * while (true){
  *   println(hello)
  *   }
  *
  * for (int i=0; i<4;i++){
  *
  * }
  *
  * for(i : list){
  * }
  *
  * */

  //for的基本形态 --从1到10
  //to 说白了就是scala中的一种方法
  //to 闭区间
  println(1.to(10))  //1到10的集合
  //until 开区间
  println(1.until(10))  //1到9的集合

  for(i <- 1 to 10){   //循环迭代给i  省略.
    println(i)
  }

  //不能直接从 10 to 1 加上关键字 reverse
  for(i <- 1 to 10 reverse){   //循环迭代给i,反序输出
    println(i)
  }

  /*高级for循环*/
  for(i <- 1 to 10 if i % 2 == 0){   //循环迭代给i  修改步长，
    println(i)
  }

  /*双层for*/
  for(i <- 1 to 2 reverse){   //循环迭代给i,反序输出
    for (j <- 1 to 3){
      println(i + "-" + j )
    }

  }

  /*双层for*/   //很灵活，双重for
  for(i <- 1 to 2 ; j<-1 to 3){
    println(i + "-" + j)
  }

  /*for的推倒式*/
  var tmp = for (i <- 1 to 10) yield {i * 2 ; i + 1}   //每个元素*2 ,以最后一个条件为准
  println(tmp)


}
