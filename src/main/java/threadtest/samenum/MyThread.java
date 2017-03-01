package threadtest.samenum;

/**
 * Created by Administrator on 2017/2/27.
 */
public class MyThread extends Thread {
    private  int count = 5;

    @Override
    synchronized public void run() {
        super.run();
        System.out.println("count = " + (count--) + "  "+this.currentThread().getName()  );
        //println是线程安全的, 但是 count是计算之后再输出,也会出现线程不安全的情况
    }
}
