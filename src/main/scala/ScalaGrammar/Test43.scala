/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 类的构造器
  * 声明中是默认值
  * 传值会改变默认值
  *
  *
  * 辅助构造器
  */

class testClass43() {
  //该类就是构造函数    //主构造器


  var a: Int = 1
  var b: Int = 2

  def this(a:Int){
    this()    //主构造器
    this.a = a
  }

  //重载辅构造器
  def this(a:Int,b:Int){
    this(a)
    this.a = a
    this.b = b
  }
  println(a + b)
}


object Test43 extends App {
  var c = new testClass43(2) //会调用 println(a+b)    -- 3 ,因为先调用的是this()主构造器
  var d = new testClass43(2,3) // 会调用 println(a+b) -- 3
  println(c.a + c.b)   //4
  println(d.a + d.b)   //5
}
