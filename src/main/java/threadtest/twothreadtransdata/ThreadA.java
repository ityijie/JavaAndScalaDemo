package threadtest.twothreadtransdata;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadA extends Thread {
    private MyList list;

    public ThreadA(MyList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < 10; i++) {
            list.add();
            System.out.println(Thread.currentThread().getName()+"添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
                e.printStackTrace();

        }

    }

}
