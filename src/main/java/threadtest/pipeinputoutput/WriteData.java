package threadtest.pipeinputoutput;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by Administrator on 2017/3/1.
 */
public class WriteData {
    public void  writeMethod(PipedOutputStream out){
        try {
            System.out.println("write : ");
            for(int i = 0; i<50;i++){
                String outData = "" + (i+1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
