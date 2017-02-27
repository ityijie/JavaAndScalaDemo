/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 对象是一个单独类型，提供类似java中的静态字段和静态方法功能
  */

//名字一样，是Object TestObject51的伴生对象，才可以访问
//伴生对象
class  TestObject51{
  def display(): Unit ={
    println(TestObject51.val1)
    }
}

object TestObject51{
  private var val1 = 2  //private 外部不能访问，需要伴生对象才可以访问
  var val2 = 1
  def show(): Unit ={
    println("hello world")
  }
}

object Test51 extends App{
  TestObject51.show()
  println(TestObject51.val2)

  var tmp = new TestObject51
  println(tmp.display())

}
