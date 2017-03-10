package getproperties;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Pro {

    public Map getProperties(String filename){
        HashMap<String, String> rs = new HashMap<>();
        java.util.Properties prop = new java.util.Properties();
        try {
            //读取属性文件a.properties
            // InputStream in = new BufferedInputStream(new FileInputStream("ceshi.properties"));

            //InputStream in= this.getClass().getResourceAsStream(filename);
           // InputStream in=new FileInputStream(this.getClass().getResource(filename).getPath());
            InputStream in =new BufferedInputStream(new FileInputStream(this.getClass().getResource(filename).getPath()));
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                rs.put(key,prop.getProperty(key));
                System.out.println(key + ":" + prop.getProperty(key));
            }
            in.close();
        } catch (Exception e) {
            System.out.println("get");
            System.out.println(e);
        }
        return rs;
    }


    public int setProperties(String filename,String key,String value){
        Properties prop=new Properties();
        int rs = 0;
        try {
            prop.setProperty(key,value);
            OutputStream fos=new FileOutputStream(this.getClass().getResource(filename).getPath(),true);
            prop.store(fos,"ceshi");
            fos.flush();
            fos.close();
        } catch (Exception e) {
            System.out.println("set");
            System.out.println(e);
        }
        return rs;
    }
}
