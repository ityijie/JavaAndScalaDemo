package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */

/**
 * 利用DCL双检查所机制
 */
public class RightMyObject {
    //刷新到共存中
    private volatile  static RightMyObject rightMyObject;

    public RightMyObject() {
    }

    public static RightMyObject getInstance(){
        try {
            if(rightMyObject != null){

            }else {
                //模拟在创建对象之前要做一些准备性的工作
                Thread.sleep(3000);
                synchronized (RightMyObject.class){
                    if(rightMyObject == null){
                        rightMyObject = new RightMyObject();
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rightMyObject;
    }
}
