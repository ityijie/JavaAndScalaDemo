package threadtest.basethread;

/**
 * Created by Administrator on 2017/2/27.
 */
public class MyThread extends Thread {
    int count = 5;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while(count > 0 ){
            count--;
            System.out.println("It " + this.currentThread().getName() + " count :" + count);
        }
    }
}
