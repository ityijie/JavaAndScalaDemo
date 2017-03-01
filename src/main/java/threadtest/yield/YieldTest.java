package threadtest.yield;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * yield 将CPU的资源处让给他人 (不会释放锁)
 *  该方法与sleep()类似,只是不能由用户指定暂停时间,该方法只能让同优先级的线程有执行的机会
 *
 * sleep()
 *  是当前线程暂停执行一段时间,让其他线程有机会继续执行,但该操作不会释放锁,
 *
 * join()
 *
 * wait()
 *  使当前线程暂停执行并释放锁对象,让其他线程可以进入synchronized 数据块,当该线程被放进对象
 *  等待池中
 *
 *
 *
 *
 *
 *
 *
 */
public class YieldTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
