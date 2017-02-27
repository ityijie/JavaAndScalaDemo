package ClassicCase

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 业务场景：通过采集的气象数据分析每年的最高温度
  * 每年的最高温度
  * Created by YJ on 2017/2/7.
  */


/*
1、原始数据分析
0067011990999991950051507004888888889999999N9+00001+9999999999999999999999
0067011990999991950051512004888888889999999N9+00221+9999999999999999999999
0067011990999991950051518004888888889999999N9-00111+9999999999999999999999
0067011990999991949032412004888888889999999N9+01111+9999999999999999999999
0067011990999991950032418004888888880500001N9+00001+9999999999999999999999
0067011990999991950051507004888888880500001N9+00781+9999999999999999999999
*/

/*
数据说明：
第15-19个字符是year
第45-50位是温度表示，+表示零上 -表示零下，且温度的值不能是9999，9999表示异常数据
第50位值只能是0、1、4、5、9几个数字
*/

object case1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //获取数据
    val one = sc.textFile("hdfs://192.168.109.130:8020//user/flume/ClassicCase/case1")
    //过滤数据
    //0 0670119909 9999195005 1507004888 8888805000 01N9+00781 +999999999 9999999999 999
    val yearAndTemp = one.filter(line => {
      //年份
     // println("year:"+line.substring(15,19))
      //匹配0、1、4、5、9 ，有其他含义
      val quality = line.substring(50, 51);
      var airTemperature = 0
      //温度： +零上，-零下
      if(line.charAt(45)=='+'){
        //00781
        airTemperature = line.substring(46, 50).toInt
      //  println("airTemperature:"+airTemperature)
      }else{
        //-00781
        airTemperature = line.substring(45, 50).toInt
       // println("airTemperature:"+airTemperature)
      }
      //airTemperature是9999为异常数据，排除，quality必须要是01459 ，数据过滤
      airTemperature != 9999 && quality.matches("[01459]")})
      .map{     //数据挑选，挑选年份和温度
      line =>{
        val year = line.substring(15,19)
        var airTemperature = 0

        if(line.charAt(45)=='+'){
          airTemperature = line.substring(46, 50).toInt
        }else{
          airTemperature = line.substring(45, 50).toInt
        }
        (year,airTemperature)
      }
    }

    //自己实现：
    println("自己过程：")
    val groupe = yearAndTemp.groupByKey()
    val groupedTop5 = groupe.map(rdd =>
    { //每个Key,取最大的一个,排序
      (rdd._1,rdd._2.toList.sortWith(_ > _ ).take(5))
    })
    groupedTop5.foreach(println)
    println("--")

    println("案例过程：")
    //案例实现：数据排序
    val res = yearAndTemp.reduceByKey(
      (x,y)=> if(x>y) x else y
    )
    res.collect.foreach(x=>println("year : " + x._1+", max : "+x._2))

    println("推理过程：")
    //相同key的值相加,传给key的值
    yearAndTemp.reduceByKey(_+_).foreach(println)
    yearAndTemp.reduceByKey((x,y)=> x+y).foreach(println)
    //相同的key的值相比较，取大的值
    yearAndTemp.reduceByKey(
      (x,y) =>
        if(x>y)
          x
        else
          y
    ).collect.foreach(x=>println("year : " + x._1+", max : "+x._2))


    sc.stop()
  }

 /*
 上面为了过滤非法的数据，在map前先做了filter过滤。
  mapreduce与spark执行的任务结果是一样的
  year : 1949, max : 111
  year : 1950, max : 78
  */

}
