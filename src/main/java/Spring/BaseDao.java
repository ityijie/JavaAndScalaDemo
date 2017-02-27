package Spring;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;
import java.util.Map;

public class BaseDao {

	private String tableName;

	//jie- 数据库连接池
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	protected JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
	
	/**---------------------
	 *  decode user and password of  connect mysql 【2016-9-26 石化盈科版本】
	 *----------------------/
	/*private static ComboPooledDataSource ds = null;
	static{
		Properties p = PropertyUtil.loadProperties("c3p0.properties");
		String user = p.getProperty("c3p0.user");
		String password = p.getProperty("c3p0.password");
		ds = new ComboPooledDataSource();
		String key="cpsdna12345";
		ds.setUser(DESUtils.decryptByDsc(user, key));
		ds.setPassword(DESUtils.decryptByDsc(password, key));
	}
	protected JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);*/
	
	protected Logger logger = Logger.getLogger(BaseDao.class);

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean 	checkDb() throws Exception {
		int i = jdbcTemplate.queryForInt("select 1");
		if (i == 1) {
			return true;
		}
		return false;

	}

	public boolean checkTable(String tableName) throws Exception {
		String tempDbName = FilenameUtils.getBaseName(tableName);
		if (tempDbName == null)
			tempDbName = "iov";
		String tempTableName = FilenameUtils.getExtension(tableName);
		if (tempTableName == null)
			return false;
		String sql = "select * from information_schema.TABLES  where table_schema = '" + tempDbName
				+ "' and table_name = '" + tempTableName + "';";
		List<Map<String, Object>> returnResult = jdbcTemplate.queryForList(sql); 
		if (returnResult.size() > 0)
			return true;
		return false;


	}

	protected void showSql(String sql, List<Object[]> paramsList) {
		if (paramsList == null || paramsList.size() == 0) {
			if(logger.isDebugEnabled()){
				logger.debug("sql:" + sql);
			}
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (Object[] params : paramsList) {
			int index = 0;
			for (char c : sql.toCharArray()) {
				if (c == '?') {
					if (params[index] == null) {
						sb.append(params[index]);
					} else {
						sb.append("\"").append(params[index]).append("\"");
					}
					index++;
				} else {
					sb.append(c);
				}
			}
			sb.append("; ");
		}
		if(logger.isInfoEnabled()){
			logger.info("sql:" + sb.toString());
		}
	}


}
