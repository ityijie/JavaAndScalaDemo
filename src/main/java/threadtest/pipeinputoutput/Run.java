package threadtest.pipeinputoutput;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Administrator on 2017/3/1.
 */

/**
 * 管道字节流,PipedInputStream
 *
 * 管道字符流,PipedReader
 */
public class Run {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            //管道连接
            outputStream.connect(inputStream);

            new ThreadRead(readData,inputStream).start();

            Thread.sleep(2000);

            new ThreadWrite(writeData,outputStream).start();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
