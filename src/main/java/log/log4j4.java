package log;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/16.
 */
public class log4j4 {
    public static void main(String[] args) throws Exception {

        //通过配置文件，将信息，发送到flume source中
        thread mTh = new thread("log4j4");
        mTh.start();

    }

}
