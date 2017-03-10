package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/3/8.
 */
public class OneThread extends Thread {
    private int n;
    public OneThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        Connection con = null;
        try{
            con = ConnsUtil.getConnection();
            con.setAutoCommit(false);

            Statement st = con.createStatement();
            String sql ="insert into stud values('P40"+n+"','刘备"+n+"',30) ";
            st.execute(sql);
            sql ="insert into stud values('P41"+n+"','曹操"+n+"',25) ";
            st.execute(sql);
            System.out.println("第"+n+"个线程准备提交...");
            con.commit();
            System.out.println("第"+n+"个线程提交完毕...");
        }catch (Exception e) {
            try {
                con.rollback();
                System.out.println("第"+n+"个线程回滚了...");
            } catch (SQLException e1) {
                throw new RuntimeException("第"+n+"事务回滚失败!", e1);
            }
        }finally{
            try {
                if(con!=null){
                    con.setAutoCommit(true);
                    con.close();//这样，我们直接调用close方法就可以了！！！
                }
            } catch (SQLException e) {
                throw new RuntimeException("第"+n+"连接关闭失败!", e);
            }
        }
    }
}
