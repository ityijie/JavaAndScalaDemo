
import Spring.DeviceAlarmEvent;
import Spring.DeviceStatus;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2016/11/1.
 */
public class Springtest {
    private static ComboPooledDataSource ds;
    protected static JdbcTemplate jdbcTemplate;

    @Before
    public void init(){
    ds = new ComboPooledDataSource();
        jdbcTemplate   = new JdbcTemplate(ds);
    }

    /**
     * 测试连接
     */
    @Test
    public void con(){
        int i = 0;
        try {
            i = jdbcTemplate.queryForInt("select 1");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("--"+i);
    }


    /***
     * jie- for what?
     */
    protected List<String> baseFilterList = new ArrayList<String>();
    public void setBaseFilterList() {
        baseFilterList.add("update_flag");
        baseFilterList.add("dtc_version");
        baseFilterList.add("GID_VERSION");
        baseFilterList.add("SOFTWARE_VERSION");
        baseFilterList.add("HARDWARE_VERSION");
        baseFilterList.add("update_status_time");
        baseFilterList.add("POWER_STATUS");
        baseFilterList.add("GUARD_STATUS");
        baseFilterList.add("SLEEP_STATUS");
        baseFilterList.add("collision_report_time");
        baseFilterList.add("bootloader_version");
        baseFilterList.add("brand_id");
        baseFilterList.add("offline_event_type");
        baseFilterList.add("device_status");
        baseFilterList.add("rollover_report_time");
        baseFilterList.add("statushis_ext_id");
        baseFilterList.add("statushis_id");
        baseFilterList.add("gsm_type");
        baseFilterList.add("app_id");
        baseFilterList.add("voltage_report_time");
        baseFilterList.add("status_area_monitor");
        baseFilterList.add("signal_report_time");
        baseFilterList.add("device_log_flag");
    }

    protected List<String> enhanceFilterList = new ArrayList<String>();
    public void setEnhanceFilterList() {
        enhanceFilterList.addAll(baseFilterList);
        //enhanceFilterList.add("duration");
    }


    /***
     * Tablename:iov_his.device_statushis_invisible
     * 检查数据库iov_his  中 device_statushis_invisible 是否存在
     */
    private boolean checkSeperateTable(String tablename) {
        //获取 . 前面的字符串
        String tempDbName = FilenameUtils.getBaseName(tablename);
        System.out.println("--"+tempDbName);
        if (tempDbName == null)
            tempDbName = "iov";
        //获取 . 后面的字符串
        String tempTableName = FilenameUtils.getExtension(tablename);
        System.out.println("--"+tempTableName);
        if (tempTableName == null)
            return false;
        String sql = "select * from information_schema.TABLES  where table_schema = '" + tempDbName
                + "' and table_name = '" + tempTableName + "';";
        List<Map<String, Object>> returnResult = jdbcTemplate.queryForList(sql);
        System.out.println("--"+returnResult.size());
        System.out.println("--"+returnResult.get(0));
        if (returnResult.size() > 0)
            return true;
        return false;
    }



    /***
     * Tablename:iov_his.device_statushis_invisible
     * 检查数据库iov_his  中 device_statushis_invisible 是否存在
     */
    @Test
    public void gps(){
         String tablename = "iov_his.device_statushis_invisible";
        setBaseFilterList();
        setEnhanceFilterList();
        boolean IsExistTable = checkSeperateTable(tablename);
        if(IsExistTable){
            //模拟插入数据

        }
    }

    @Test
    public void sava(){
        String tablename = "iov.device_alarm_event";
        DeviceAlarmEvent dAE = new DeviceAlarmEvent();
        dAE.setRecUid("e0da7dd34c30444ea39b611a147aa170");
        dAE.setDeviceId("41011100112");
        dAE.setEventType(0);
        long currenttime = System.currentTimeMillis();
        dAE.setPosTime(new Timestamp(currenttime));
//        checkSeperateTable(tablename);

        //Map<String, Object> map = BeanUtil.bean2Map(vbp);
        Map<String,Object> Map = new HashMap();
        Map.put("REC_UID",dAE.getRecUid());
        Map.put("DEVICE_ID",dAE.getDeviceId());
        Map.put("EVENT_TYPE",dAE.getEventType());
        Map.put("POS_TIME",dAE.getPosTime().toString());
        System.out.println("时间数据:"+dAE.getPosTime());

        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(getTableName(tablename)).append("(").append(StringUtils.join(Map.keySet(), ","))
                .append(")");
        sql.append(" values (");
        sql.append(StringUtils.repeat("?", ",", Map.size()));
        sql.append(")");
        System.out.println("sql?--"+sql.toString());
        System.out.println("sql?--"+StringUtils.join(Map.values()));
        //1, 2016-11-01 11:15:35.331, e0da7dd34c30444ea39b611a147aa321, 41011100012
       int update = jdbcTemplate.update(sql.toString(), Map.values().toArray());

        System.out.print("是否插入?--"+update);

    }

    private char[] getTableName(String tablename) {
        String tempTableName = FilenameUtils.getExtension(tablename);
        char[] chars = tempTableName.toCharArray();
        return  chars;
    }


    @Test
    public void th(){
        String sql = "insert into zt_newstype(TYPE_ID,TYPE_NAME,PARENT_ID) values (\"3\",\"测试\",\"1\")";
        int update = jdbcTemplate.update(sql.toString());
        System.out.print("是否插入?--"+update);
    }


    @Test
    public  void  othe(){
        String sql = "select * from information_schema.TABLES  where table_schema = '" + "iov_his"
                + "' and table_name = '" + "device_statushis_invisible" + "';";
        List<Map<String, Object>> returnResult = jdbcTemplate.queryForList(sql);
        System.out.println(returnResult.toString());
    }


    @Test
    public  void  qu(){
        String sql = "select * from iov_his.device_statushis_invisible";
        List<Map<String, Object>> returnResult = jdbcTemplate.queryForList(sql);
        System.out.println(returnResult.toString());
    }

}
