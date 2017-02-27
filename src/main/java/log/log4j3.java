package log;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/15.
 */
public class log4j3 {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(log4j3.class);

        //通过配置文件，将信息，发送到flume source中
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);
        //并发数
       int concurrentNum = 2;
        ExecutorService executorService = Executors.newCachedThreadPool();

        thread3 a1 = new thread3("a");
        for (int i = 0; i < concurrentNum;i++) {
            executorService.execute(a1);
        }

        int i = 0;
        for (i = 0; i < 10; i++) {
            String a = i + "";
            thread3 mTh = new thread3(a);
            //mTh.start();
            fixedThreadPool.execute(mTh);
        }
    }

}

class thread3 extends Thread {

    String message = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest" +
            "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";

    Logger logger = Logger.getLogger(thread.class);
    private String name;

    public thread3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        SimpleDateFormat time = new SimpleDateFormat("Y:M:d:HH:mm:ss");
        int i = 0;

        while (true) {
            String format = time.format(System.currentTimeMillis());
            logger.info("log4j3" + " thread send message  on -" + format + "- :  ---" + name + " for " + i++ + "--->" + message);
        }


    }
}