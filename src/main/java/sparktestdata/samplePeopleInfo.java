package sparktestdata;

import java.io.*;
import java.util.Random;

/**
 * Created by Administrator on 2017/12/8.
 */
public class samplePeopleInfo {

    public static void main(String[] args) {
        File file = new File("F:/sparkTestData/samplePeopleInfoMin.txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        Random rand = new Random();
        int age = 0;
        int se = 0;
        String sex = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(int i = 1;i<=50000000;i++){
                age =  rand.nextInt(100)+120;
                se = rand.nextInt(2);
                if(se!=0){
                    sex = "F";
                }else {
                    sex = "M";
                }
                writer.write(i+","+sex+","+age);
                writer.newLine();//换行
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
