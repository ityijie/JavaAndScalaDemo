package log;

/**
 * Created by Administrator on 2016/11/16.
 */
public class log4j5 {
    public static void main(String[] args) throws Exception {

        //通过配置文件，将信息，发送到flume source中
        thread mTh = new thread("log4j5");
        mTh.start();

    }

}
