package threadtest.deallock;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * 多线程死锁
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DealThread t1 = new DealThread();
        t1.setFlag("a");
        Thread thread1 = new Thread(t1);
        thread1.start();
        Thread.sleep(100);
        t1.setFlag("b");
        Thread thread2 = new Thread(t1);
        thread2.start();

    }
}
