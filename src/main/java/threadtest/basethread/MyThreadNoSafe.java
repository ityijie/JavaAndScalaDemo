package threadtest.basethread;

/**
 * Created by Administrator on 2017/2/27.
 */
public class MyThreadNoSafe extends Thread {
    private  int count = 5;

    @Override
    public void run() {
        super.run();
            count--;
            System.out.println("It " + this.currentThread().getName() + " count :" + count);
    }
}
