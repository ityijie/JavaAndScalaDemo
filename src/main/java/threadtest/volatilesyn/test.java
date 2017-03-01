package threadtest.volatilesyn;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * 原子性:同步性,一步到位
 *       原子操作是不能分割的整体,没有其他线程能够中断或者检查正在院子操作中的变量
 * 非原子性:操作可能会中断,不是一步到位,别人插不进来(不具备同步性)
 * 变量可分为:私有内存(线程)
 *           公共内存(程序)
 *           volatile -> 将私有内存,共有内存数据统一(保持变量在两者间一致)
 *                       多个线程之间的可见性
 * volatile:
 *      1.volatile只能修饰变量,而synchronized可以修饰方法,类,代码块
 *      2.多线程访问volatile不会发生阻塞,而synchronized会出现阻塞
 *      3.volatile能保证数据的可见性,到不能保证原子性(不具备同步性),synchronized可以保证原子性.
 *
 *
 *      4.volatile 解决的是变量在多个线程之间的可见性,
 *        synchronized解决的是多个线程之间访问资源的同步性
 *
 * 工作原理:
 *      1)read 和 load 阶段 : 从主存复制变量到当前线程的工作内存;
 *      2)use 和 assign 阶段 : 执行代码,改变共存变量值
 *      3)store 和 write 阶段 : 用工作内存数据刷新主存对应变量的值
 *
 *
 * synchronized 解决该volatile 原子性问题
 * 注意一定要加static,作用于整个类(因为该变量是属于该类)
 * synchroniezd static method()
 *
 */
public class test {
    public static void main(String[] args) {
        //测试非原子性(非同步),理论上来说count应该是10000,
        //volatile和synchronized 相结合,可解决原子性问题
        MyThread[] myThreadArray = new MyThread[100];
        for(int i =0 ; i<100 ;i++){
            myThreadArray [i] = new MyThread();
            myThreadArray[i].start();
        }
    }


}
