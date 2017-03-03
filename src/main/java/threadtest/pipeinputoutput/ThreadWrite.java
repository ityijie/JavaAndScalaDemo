package threadtest.pipeinputoutput;

import java.io.PipedOutputStream;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadWrite extends Thread {
    private  WriteData wrire;
    private PipedOutputStream out;

    public ThreadWrite(WriteData wrire, PipedOutputStream out) {
        this.wrire = wrire;
        this.out = out;
    }

    @Override
    public void run() {
        super.run();
        wrire.writeMethod(out);
    }
}
