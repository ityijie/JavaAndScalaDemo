package threadtest.synchronizedclass;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * synchronized 作用在static 的上
 *     都能启到数据同步的效果,但是本质上有很大的区别
 * synchronized 关键字加到static静态方法上,是给Class类上锁
 * 而synchronized 加到非static静态方法上,是给对象上锁.不同的概念
 *      一个是操作对象,一个是操作类,相当于操作两个同步模块
 *
 *
 * synchroniezd static 作用于类
 *      是某个类的范围,防止多个线程同时访问这个类中的synchronized static()方法
 *      他可以对类的所有对象实例起作用
 * synchroniezd        作用于类的实例对象
 *      是某个实例的范围,防止多个线程同时访问这个类的synchronized()方法
 *
 */
public class Test {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        //必须等待A执行完
        b.setName("B");
        ThreadC c = new ThreadC(service);
        //不用理会A的执行
        c.setName("C");
        ThreadC d = new ThreadC(service);
        d.setName("D");
        a.start();
        b.start();
        c.start();
        d.start();


    }


}
