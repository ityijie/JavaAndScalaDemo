/**
  * Created by Administrator on 2016/9/4.
  */


abstract class Person {
  //抽象类可以有抽象的方法和字段，也可以有非抽象方法和字段
  val id: Int
  var name: String

  def show

  def showfunc1: Unit = {
    println("showfunc1")
  }

}


//继承abstract不需要override
class Student extends Person {
  val id: Int = 1    //val 只能使用val覆盖
  var name: String = "jackson"   //var只能使用var覆盖
  def show : Unit = {

  }
}

/***
  * 抽象类测试
  * 抽象方法的方法体可以写或者不写，与java有所区别
  */
abstract class Per2 {

  def speak

  def talk: Unit = {
    println("stu talk")
  }

  val name:String

  var age :Int

}

/**
  * 继承抽象类
  */
class stu2 extends Per2{

  //抽象方法的实现
  override def speak: Unit = {
    println("stu speak")
  }

  override val name: String = "zhangsan"
  override var age: Int = 30
}

object Test71 extends App {
  var s = new Student
  s.showfunc1
}
