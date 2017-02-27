/**
  * Created by Administrator on 2016/9/2.
  */

object test21 extends App{
  /**
    * 控制结构
    * if else
    */

    /*判断if*/
    /*scala 默认返回之后一行的数据，自行判断数据类型*/
    def  judge (a  :Int)={
      if (a>0){
        1
      }else if(a<0){
        -1
      }else{
        0
      }

    }

    println(judge(3))


}