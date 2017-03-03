package threadtest.p_r_test;
/**
 * Created by Administrator on 2017/3/1.
 */
public class P {
    private Object lock;

    public P(Object lock) {
        this.lock = lock;
    }

    public void setValue(){
        try {
            synchronized (lock){
             /*   if(!ValueObject.value.equals("")){
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }



                System.out.println(Thread.currentThread().getName()+" running");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println(Thread.currentThread().getName() +" set的值是 "+ value);
                ValueObject.value = value;
                System.out.println("P...........");
                //Thread.sleep(100);
                lock.notifyAll();*/

                if(ValueObject.queue.size() > 100){
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }

                System.out.println(Thread.currentThread().getName()+" running");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                ValueObject.queue.add(value);
                System.out.println(Thread.currentThread().getName() +" set的值是 "+ value);
                System.out.println("P...........");
                Thread.sleep(500);
                lock.notifyAll();


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
