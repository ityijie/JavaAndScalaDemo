package threadtest.waitnotify;

/**
 * Created by Administrator on 2017/3/1.
 */

/**
 * wait(自动释放锁) notify(执行完代码块才释放锁)
 *
 *        PS:必须要执行在同步代码块中,也就是synchronized
 *           notify()唤起线程,需要同一把锁.
 * wait(long) 超过该long时间,自动唤醒,或者在该时间内给其他线程唤醒
 *
 */
public class Run {
    public static void main(String[] args) {
    /*    try {
            String newString = new String("");
            System.out.println("syn up");
            synchronized (newString){
                System.out.println("syning first");
                newString.wait();
                System.out.println("syn wait");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    /*    try {
            Object lock = new Object();
            MyThread1 t1 = new MyThread1(lock);
            t1.start();
            Thread.sleep(3000);
            MyThread2 t2 = new MyThread2(lock);
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/




    }
}
