package threadtest.yield;

/**
 * Created by Administrator on 2017/2/28.
 */
public class MyThread  extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0 ;
        for (int i = 0 ; i < 50000000;i++){
            this.yield();
            count = count + (i++);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时: " + (endTime - beginTime) + "毫秒");

    }
}
