package getproperties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by Administrator on 2017/2/27.
 */
public class GetProperties {

    public static void main(String[] args) {
        Pro pro = new Pro();
        pro.getProperties("/ce.properties");
        pro.setProperties("/ce.properties","ace","132");
    }



}
