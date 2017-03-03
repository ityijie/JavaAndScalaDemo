package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */

import java.io.Serializable;

/**
 *  静态内置类实现单例模式
 *  由于内部静态类只会被加载一次，故该实现方式时线程安全的！
 *  静态内置类可以达到线程安全问题,但是如果遇到序列化对象时,运行时得到的结果还是多例
 */
public class SerializeInnerObject implements Serializable {
    private static final long serialVersionUID = 888L;

    private static class InnerObjectHandler{
        private static SerializeInnerObject innerObject = new SerializeInnerObject();
    }

    public SerializeInnerObject() {
    }

    public static SerializeInnerObject getInstance(){
        return InnerObjectHandler.innerObject;
    }

    /*需要加改方法,返回该反序列化的对象,自动调用*/
    protected Object readResolve(){
        System.out.println("调用readResolve()");
        return InnerObjectHandler.innerObject;
    }
}
