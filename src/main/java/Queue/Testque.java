package Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/5/27.
 */
public class Testque {

    /**
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws
     * @Title: 使用Semaphore实现简单的阻塞队列
     * @Description:
     */
    public static void main(String[] args) {
        SemaphoreQueue queue = new SemaphoreQueue(20);
        //开始生产
        Productor productor = new Productor(queue);
        productor.setName("生产者");
        productor.start();
        //开始消费
        Cousumertor c1 = new Cousumertor(queue);
        c1.setName("消费者-c1");
        Cousumertor c2 = new Cousumertor(queue);
        c2.setName("消费者-c2");
        c1.start();
        c2.start();
    }

}

/**
 * 队列
 * mutex相当于锁，用于控制非线程安全的valueList的操作
 */
class SemaphoreQueue {
    private List<Integer> valueList;
    private Semaphore putActionNum;//可以进行put操作的许可数量
    private Semaphore getActionNum;//可以进行take操作的许可数量
    private Semaphore mutex;

    public SemaphoreQueue(int capacity) {
        putActionNum = new Semaphore(capacity);//维护队列大小
        getActionNum = new Semaphore(0);//初始化时，队列为空，put操作许可数量为0
        mutex = new Semaphore(1);//用于保护非线程安全的valueList操作，用于并发生产时控制
        valueList = new ArrayList<Integer>(capacity);
    }


    public void put(Integer message) {
        try {
            putActionNum.acquire();//put操作许可减1
            mutex.acquire();
            valueList.add(message);
            mutex.release();
            getActionNum.release();//get操作许可加1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int size() {
            return valueList.size();
    }

    public Integer take() {
        Integer message = null;
        try {
            getActionNum.acquire();//get操作许可减1
            mutex.acquire();
            if (valueList.size() > 0) {
                message = valueList.get(0);
                valueList.remove(0);
            } else {
                return null;
            }
            mutex.release();
            putActionNum.release();//put操作许可加1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
}

/**
 * 生产者
 */
class Productor extends Thread {
    SemaphoreQueue queue;

    public Productor(SemaphoreQueue queue) {
        this.queue = queue;
    }

    @SuppressWarnings("static-access")
    public void run() {
        int i = 0;
        try {
            while (true) {
                i++;
                Integer message = new Integer(i);
                queue.put(message);
                if (i % 20 == 0) {
                    System.out.println("======== " + this.getName() + " 累计生产了 " + i + " 条消息  =======");
                    Thread.currentThread().sleep(5000);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者
 */
class Cousumertor extends Thread {
    SemaphoreQueue queue;

    public Cousumertor(SemaphoreQueue queue) {
        this.queue = queue;
    }

    @SuppressWarnings("static-access")
    public void run() {
        try {
            while (true) {
                Integer message = queue.take();
                if (message != null) {
                    System.out.println("======== " + this.getName() + " 消费消息：" + message + " =======" + queue.size());
                }else{
                    System.out.println("没有消费的");
                }
                Thread.currentThread().sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

