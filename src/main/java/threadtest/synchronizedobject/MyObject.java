package threadtest.synchronizedobject;

/**
 * Created by Administrator on 2017/2/28.
 */
public class MyObject {

    /**
     * 没有synchronized ,AB线程同时进入该方法,即异步
     * synchronied,等待进入,即同步
     *
     * A线程先持有object对象的Lock锁,B线程可以以异步的方式调用Object对象中非synchronized类型的方法
     * A线程先持有object对象的lock锁,B线程如果在这个时候调用object对象中的synchronized类型的方法则需要等待,也就是同步
     */
    synchronized public void methodA(){
        System.out.println("begin methodA threadName = " + Thread.currentThread().getName()+"  begin time :" +System.currentTimeMillis());
        try {
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public void methodB(){

        System.out.println("begin methodB threadName = " + Thread.currentThread().getName() +"  begin time :" +System.currentTimeMillis());
        try {
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
