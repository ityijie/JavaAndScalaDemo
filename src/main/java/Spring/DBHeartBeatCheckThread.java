package Spring;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/10/31.
 */
public class DBHeartBeatCheckThread implements Runnable {

    private Logger logger = Logger.getLogger(DBHeartBeatCheckThread.class);
    DeviceDao deviceDao = new DeviceDao();
    @Override
    public void run() {
        System.out.println("DBHeartBeatCheckThread begin");
        while (true) {
            try {
                System.out.println("--");

                boolean connect = deviceDao.checkDb();
                System.out.println("是否连接:"+connect);
                // logger.error("Current db connection status is="+connect);
            } catch (Exception ex) {
                System.out.println("发生异常?"+ex.toString());
                logger.warn("Current db connection status error");
            }
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
