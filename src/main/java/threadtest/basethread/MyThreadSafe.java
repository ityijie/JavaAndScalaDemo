package threadtest.basethread;

/**
 * Created by Administrator on 2017/2/27.
 */
public class MyThreadSafe extends Thread {
    private  int count = 5;

    @Override
    synchronized  public void run() {
        super.run();
            count--;
            System.out.println("It " + this.currentThread().getName() + " count :" + count);
    }
}
