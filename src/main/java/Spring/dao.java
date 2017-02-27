package Spring;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/31.
 */
public class dao {
    //jie- 数据库连接池
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    protected static JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

    public static void main(String args[]) {

        //runtask(new DBHeartBeatCheckThread(), "Monitor DB connection");
        int i = 0;
        try {
             i = jdbcTemplate.queryForInt("select 1");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("--"+i);
    }

}
