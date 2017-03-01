package threadtest.dirtyread;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 *   当A线程调用AnyObject对象加入synchronized关键字的 X 方法时,A线程就获得了X的方法锁,更准确的说是获得了对象锁,
 * 所以其他线程必须等待A线程执行完毕才可以调用 X 方法,但是B线程可以随意调用该AnyObject的非synchroniezd同步方法
 *
 *   当A线程调用AnyObject对象加入synchronized关键字的 X 方法时,A线程就获得了X 方法所在对象的锁,所以其他线程必须
 * 等到A线程执行完毕,其他线程才可以调用 X 方法, 而B线程如果调用声明了 synchroniezd 关键字的非 X 方法,这时,也必须
 * 等待A线程将 X 方法执行完毕释放对象锁后才可以调用,这时, setValue, 都执行完了,才执行 main 线程的getValue 不存在
 * 脏数据读取问题.
 *
 *
 *
 */
public class Test {
    public static void main(String[] args) {

        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            //单独运行
            threadA.start();
            //停顿200毫秒,等待setname,再执行getValue,停顿3000毫秒,此时,由于时间,获取的数据不一致,出现脏读数据
            //这时候,也需要在getValue()中加上synchronized关键字
            Thread.sleep(200); //打印结果受此影响
            publicVar.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
