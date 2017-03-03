package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class MyObject {
    //立即加载方式 == 饿汉模式,加载到内存
    private static  MyObject myObject = new MyObject();

    public MyObject() {
    }

    public static MyObject getInstance(){
        return myObject;
    }
}
