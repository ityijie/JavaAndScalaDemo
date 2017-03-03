package mlib.ALS

/**
  * Created by Administrator on 2017/3/2.
  */

/** *
  *u.data
  * userId moiveId 评分rate 时间戳
  * *
  * 196	242	3	881250949
  * 186	302	3	891717742
  * 22	377	1	878887116
  * 244	51	2	880606923
  * 166	346	1	886397596
  * *
  *u.user
  * UserId|age|sex|job|zipcode
  * *
  * 1|24|M|technician|85711
  * 2|53|F|other|94043
  * 3|23|M|writer|32067
  * 4|24|M|technician|43537
  * 5|33|F|other|15213
  * *
  *
  * 实现的功能
  * 这里有10w条用户对电影的评分，从1-5分，1分表示差劲，5分表示非常好看。根据用户对电影的喜好，给用户推荐可能感兴趣的电影。
  * *
  * 关联:
  *u.data=> 用户,物品,评分(两者关联)
  *u.user=> 用户,
  * result=> 得到物品
  * *
  * 实现思路
  * 代码实现如下：
  * 1、加载u.data数据到rating RDD中
  * 2、对rating RDD的数据进行分解，只需要userId，moiveId，rating
  * 3、使用rating RDD训练ALS模型
  * 4、使用ALS模型为u.user中的用户进行电影推荐，数据保存到HBase中
  * 5、评估模型的均方差
  */
object MovieAls {

  val numRecommender = 10

  case class Params(
                     input: String = null,
                     numIterations: Int = 20,     //是迭代的次数
                     lambda: Double = 0.01,        //是ALS的正则化参数
                     rank: Int = 10,              //模式中,隐语义因子的个数
                     numUserBlocks: Int = -1,     //是用于并行化计算的分块个数 (设置为-1为自动配置)。
                     numProductBlocks: Int = -1,
                     implicitPrefs: Boolean = false,  //决定了是用显性反馈ALS的版本还是用适用隐性反馈数据集的版本。
                     userDataInput: String = null)

  def main(args: Array[String]): Unit = {
    val defaultParams = Params()


  }


}
