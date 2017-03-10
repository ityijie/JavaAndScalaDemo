package pattern.p_r_test;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadP extends  Thread {
    private P p;

    public ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while(true){
            p.setValue();
        }
    }
}
