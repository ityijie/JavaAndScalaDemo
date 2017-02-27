package log;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/15.
 */
public class log4j6 {
    public static void main(String[] args) throws Exception {
        thread3 mTh = new thread3("log4j6");
        mTh.start();
    }

}

