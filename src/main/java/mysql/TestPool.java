package mysql;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TestPool {
    public static void main(String[] args) {
       Connection con = null;
        try{
            con = ConnsUtil.getConnection();
            con.setAutoCommit(false);

            Statement st = con.createStatement();
            String sql ="insert into stud values('P201','关羽',30) ";
            st.execute(sql);
            sql ="insert into stud values('P202','张飞',25) ";
            st.execute(sql);
            new OneThread(1).start();
            new OneThread(2).start();
            new OneThread(3).start();
            new OneThread(4).start();
            new OneThread(5).start();

            System.out.println("主线程准备提交...");
            con.commit();
            System.out.println("主线程提交完毕...");
        }catch (Exception e) {
            try {
                con.rollback();
                System.out.println("主线程回滚了...");
            } catch (SQLException e1) {
                throw new RuntimeException("主线程事务回滚失败!", e1);
            }
        }finally{
            try {
                if(con!=null){
                    con.setAutoCommit(true);
                   // ConnsUtil.back(con);
                    con.close();//如果要把close内部的功能换成还连接，就需要我们以后的技术来实现
                }
            } catch (SQLException e) {
                throw new RuntimeException("主线程连接关闭失败!", e);
            }
        }

    }
}

