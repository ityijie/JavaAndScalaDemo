/**
  * Created by Administrator on 2016/9/2.
  */


/**
  * 高阶函数
  *
  * _  下划线的意义
  *
  *  -- 取元组的值，代表每个参数
  *  -- 传递的是一个确确的值
  *  -- 每一个元素
  *
  */
object Test61 extends App {

  //简单函数
  def add(a: Int, b: Int) = {
    a + b
  }

  var c = add(1, 2)
  println(c)

  println("=======高级函数========")
  //高级函数
  //把一个函数传递给另一个函数
  var func1 = add _
  println(func1(2, 3))

  println("=======匿名函数========")
  //匿名函数
  var func2 = (x: Int) => {
    x + 2
  }
  println(func2(1))

  println("=======传递方法，及指定参数类型========")

  def func3(f: (Int) => (Int)) = {
    f(1) + 1
  }

  println("=======传方法========")
  println(func3(func2))

  println("=======传匿名函数========")
  println(func3((x: Int) => {
    x + 1
  }))

  //这里的下划线对应的是每一个元素
  println((func3(_ + 1)))


  println("=======传递函数到另一个函数中========")
  def gup(a: Int) = {
    a + 1
  }

  def cpu(b: Int) = {
    b + 8
  }

  //接收的是函数
  def computer(men: Int, cpu: (Int) => (Int), gpu: (Int) => (Int)) = {
    men + cpu(2) + gup(1)
  }

  println(computer(2,cpu,cpu))

}
