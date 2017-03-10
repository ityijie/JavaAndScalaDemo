package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/6.
 */

/**
 * 创建一个定长线程池，支持定时及周期性任务执行。
 *
 */
public class NewScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
  /*      scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                //表示延迟3秒执行。
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);*/
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                //表示延迟1秒后每3秒执行一次。
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
