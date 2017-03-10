package pattern.p_r_test;

/**
 * Created by Administrator on 2017/3/1.
 */
public class C {
    private  Object lock;

    public C(Object lock) {
        this.lock = lock;
    }

    public  void getValue(){
        try {
            synchronized (lock){
    /*            if(ValueObject.value.equals("")){
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+" running");

                System.out.println(Thread.currentThread().getName() +" get 的值是 " + ValueObject.value);
                ValueObject.value = "";
                System.out.println("C......");
                //Thread.sleep(100);
                lock.notifyAll();*/

                if(ValueObject.queue.size() < 50){
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }else {
                    System.out.println(Thread.currentThread().getName()+" running");

                    String value = ValueObject.queue.remove();

                    System.out.println(Thread.currentThread().getName() +" get 的值是 " + value);
                    System.out.println("C......");
                    Thread.sleep(500);
                    lock.notifyAll();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
