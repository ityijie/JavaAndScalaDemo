package threadtest.twothreadtransdata;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadB extends Thread {
    private MyList list;

    public ThreadB(MyList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {

        while(true){
            try {
                System.out.println("list "+ list.size());
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }


    }
}