package threadtest.deallock;

/**
 * Created by Administrator on 2017/2/28.
 */
public class DealThread implements Runnable {
    public String userName;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        if (userName.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username : " + userName);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //该锁获取不到
                synchronized (lock2) {
                    System.out.println("按lock1 -> lock2 代码顺序执行");
                }
            }

        }


        if (userName.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username : " + userName);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //该锁获取不到
                synchronized (lock1) {
                    System.out.println("按lock2 -> lock1 代码顺序执行");
                }
            }


        }

    }
}
