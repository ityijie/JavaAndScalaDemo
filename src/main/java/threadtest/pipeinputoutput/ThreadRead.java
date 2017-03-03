package threadtest.pipeinputoutput;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadRead extends Thread {
    private  ReadData read;
    private PipedInputStream input;

    public ThreadRead(ReadData read, PipedInputStream input) {
        this.read = read;
        this.input = input;
    }

    @Override
    public void run() {
        super.run();
        read.readMethod(input);
    }
}
