package pattern.observer;

/**
 * Created by Administrator on 2017/3/10.
 */

/**
 * 观察器管理类
 */
public abstract class  Observer {
    protected  Subject subject;
    public abstract void update();

}
