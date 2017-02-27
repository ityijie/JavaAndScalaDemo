import scala.beans.BeanProperty

/**
  * Created by Administrator on 2016/9/2.
  */

/**
  * 类的基本内容
  */

/**
  * 自定义set ,get 方法
  * 应用场景，在自定义方法中实现逻辑
  */
class TestClass42 {
  private var money = 100

  def setMoney ( money :Int): Unit ={
    if(money >0){
      this.money = money
    }
  }

  def getMoney () ={
    this.money
  }

  def add(a : TestClass42): Unit ={
    this.money += a.money  //累加
  }
}

object Test42 extends App {
  var c = new TestClass42
  c.setMoney(200)
  println(c.getMoney())



}
