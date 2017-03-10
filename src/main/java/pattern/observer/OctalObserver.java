package pattern.observer;

/**
 * Created by Administrator on 2017/3/10.
 */
public class OctalObserver extends Observer {

    int a = 0;

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("执行了 " + (a++) + " 次操作" );
    }
}
