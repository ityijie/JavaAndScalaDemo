import scala.beans.BeanProperty

/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 类的基本内容
  */

class TestClass41 {
  //定义基本属性，包含了get/set方法
  // BeanProperty　不能与 private 一起共用，private 只能在本类中使用，而BeanProperty就是公开使用，矛盾
  @BeanProperty var money = 100

  def add (): Unit ={
    println("加法")
  }

}

object Test41 extends App {

  //scala字段用var定义都会生成对应的setter和getter的方法，名称分别为money _= 和 money
  //如果用val定义，则不再提供set方法 --val定义的是不可变的常量
  var a = new TestClass41()
  a.money = 200     // 默认调用了set方法
  println(a.money)  // 默认调用了get方法获取值


  //添加BeanProperty注解后，方可用
  a.setMoney(400)
  println(a.money)




}
