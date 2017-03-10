package mysql;

import java.sql.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/8.
 */

/**
 * 1、加载连接器(驱动)   通过Driver类 (最好用类反射来加载，更加灵活)
 * 2、建立与数据库的连接
 * 3、获取语句对象
 * 4、对数据库进行操作(增删改查)
 */
public class Run {
    Connection con =null;
    Statement st  = null;
    @Before
    public void init() throws ClassNotFoundException, SQLException{
        //1、加载连接器(驱动)  Driver
        Class.forName("com.mysql.jdbc.Driver");
        //用类反射加载，更灵活

        //2、建立连接
        //String url = "jdbc:mysql://127.0.0.1:3306/hncu";//这一句是采用默认编码
        String url = "jdbc:mysql://192.168.200.30:3306/cyj?useUnicode=true&characterEncoding=utf-8";//采用指定编码连接

        con  = DriverManager.getConnection(url, "root", "abcd1234");
        //这3个参数是:连接串、用户名、密码
        st = con.createStatement();
    }


    @Test
    public void hello() throws ClassNotFoundException, SQLException {
        //1、加载连接器(驱动)  Driver
        Class.forName("com.mysql.jdbc.Driver");
        //用类反射加载，更灵活

        //2、建立连接
        //String url = "jdbc:mysql://127.0.0.1:3306/hncu";//这一句是采用默认编码
        String url = "jdbc:mysql://192.168.200.30:3306/cyj?useUnicode=true&characterEncoding=utf-8";//采用指定编码连接

        Connection con  = DriverManager.getConnection(url, "root", "abcd1234");
        //这3个参数是:连接串、用户名、密码

        //3、获取语句对象
        Statement st = con.createStatement();
        //对表格hncu增加一行数据

        ////4 对数据库进行操作(增删改）
        String sql = "insert into stud values('1010','杨过',26,'武侠')";
        //String sql = "insert into stud values('1010','杨过',26,'武侠');";//sql语句加上分号也是一样的。
        //但是注意！！！不能一次添加多条语句运行！(中间用分号隔也不行)
        st.execute(sql);//增删改用:st.excute()方法
        sql = "insert into stud values('1011','杨过1',26,'武侠');";
        st.execute(sql);
    }

    @Test
    public  void  Query()throws  SQLException {
        String sql = "select * from stud where sname like '%三%' or sname like '%四%';";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            //为什么不用hasNext呢，因为rs就像一个指针，rs最开始的位置并不是指向第一个数据，
            //而是指向开头地址,所以不能用hasNext来判断，而必须跳过第一个不读.
            String sno = rs.getString(1);//指定列号的方式读取。第一列的序号为1。
            String sname = rs.getString("sname");//指定列名的方式来读取
            int age = rs.getInt("age");
            String saddress = rs.getString(4);
            System.out.println(sno + "," + sname + "," + age + "," + saddress);

        }
    }

    @Test
    public  void transactionDemo() throws ClassNotFoundException, SQLException{
        try {
            con.setAutoCommit(false);//从设置false开始，以下都是一个事务

            String sql = "insert into aa values(5,'李四');";
            st.execute(sql);//增

            sql = "insert into aa values(8,'张三');";
            st.execute(sql);//增

            sql = "delete from aa where id = 5;";
            st.execute(sql);//删

            sql = "update aa set sname='rose' where id=7;";
            st.execute(sql); //更新

            con.commit();//提交

        } catch (Exception e) {
            con.rollback();//如果出现异常，我们就让事务回滚
        } finally{
            con.setAutoCommit(true);//再设置回去
            con.close();
        }
    }
}
