package threadtest.basethread;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        //实现Runnable接口,开发常用
        //implementsRunnable();

        //继承线程,java单集成,有局限性 ---数据不共享
        //extendThread();

       //数据共享 (非线程安全),多个线程堆一个数据操作,出现多个线程同时作用于一个值
       // dataShareNoSafe();

        //数据共享 (线程安全) ,等待一个线程完成后,在由另一个线程接手
        dataShareSafe();




    }

    /**
     * Synchronized关键字
     * 数据共享 (线程安全) ,等待一个线程完成后,在由另一个线程接手
     */
    private static void dataShareSafe() {
        MyThreadSafe myThread = new MyThreadSafe();
        new Thread(myThread,"d").start();
        new Thread(myThread,"e").start();
        new Thread(myThread,"f").start();
        new Thread(myThread,"g").start();
        new Thread(myThread,"h").start();
    }

    /**
     * 数据共享 (非线程安全),多个线程堆一个数据操作,出现多个线程同时作用于一个值
     */
    private static void dataShareNoSafe() {
        MyThreadNoSafe myThread = new MyThreadNoSafe();
        new Thread(myThread,"d").start();
        new Thread(myThread,"e").start();
        new Thread(myThread,"f").start();
        new Thread(myThread,"g").start();
        new Thread(myThread,"h").start();
    }

    /**
     * 继承线程,java单集成,有局限性
     * 数据不共享
     */
    private static void extendThread() {
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("b");
        MyThread c = new MyThread("c");
        a.start();
        b.start();
        c.start();
    }

    /**
     * //实现Runnable接口,开发常用
     */
    private static void implementsRunnable() {
        Runnable myThread00 = new MyThread00();
        Thread thread = new Thread(myThread00);
        thread.start();
    }
}
