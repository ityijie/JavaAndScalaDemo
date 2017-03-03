package threadtest.singleton;

import java.io.*;

/**
 * Created by Administrator on 2017/3/2.
 */
public class SaveAndRead {
    public static void main(String[] args) {
        try {
            SerializeInnerObject instance = SerializeInnerObject.getInstance();
            FileOutputStream fosRef = new FileOutputStream(new File("myObjectFile.txt"));
            ObjectOutputStream oosRef = new ObjectOutputStream(fosRef);
            oosRef.writeObject(instance);
            oosRef.close();
            fosRef.close();
            System.out.println(instance.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fisRef = new FileInputStream(new File("myObjectFile.txt"));
            ObjectInputStream iosRef = new ObjectInputStream(fisRef);
            SerializeInnerObject Object = (SerializeInnerObject)iosRef.readObject();
            iosRef.close();
            fisRef.close();
            System.out.println(Object.hashCode());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
