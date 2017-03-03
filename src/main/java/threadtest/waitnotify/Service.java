package threadtest.waitnotify;

/**
 * Created by Administrator on 2017/3/1.
 */
public class Service {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {

                System.out.println("begin wait()" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait()" + Thread.currentThread().getName());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {

        try {
            synchronized (lock) {
                System.out.println("begin notify()" + Thread.currentThread().getName());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("end notify()" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


