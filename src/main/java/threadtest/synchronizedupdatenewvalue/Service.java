package threadtest.synchronizedupdatenewvalue;

/**
 * Created by Administrator on 2017/3/1.
 */

/***
 * 项目以 -server 服务器模式运行,会出现死循环,更改不了isContinueRun的状态
 * 服务器模式,为了节省资源,不会把数据放到公共内存中,所以,其他线程,更改不了该value
 *
 * 所以要加上synchronied, 保证同步性,确定,每时每刻只有一个线程在操作
 */
public class Service {
    private  boolean isContinueRun = true;
    Object obj = new Object();
    public void runMethod(){
        while (isContinueRun == true) {
            System.out.println("running");
            //加上synchronied, 保证同步性,确定,每时每刻只有一个线程在操作
            synchronized (obj){
            };
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("stop");
    }

    public void stopMethod(){
        isContinueRun = false;
    }
}
