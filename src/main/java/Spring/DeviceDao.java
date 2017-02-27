package Spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDao extends BaseDao {

	public DeviceDao() {
		super.setTableName("device");
	}

	public DeviceDao(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}

	public List<String> getAllDeviceId() {
		final List<String> idList = new ArrayList<String>();
		jdbcTemplate.query("select distinct device_id from iov.device", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				do{
					idList.add(rs.getString("device_id"));
				}while (rs.next());
			}
		});
		return idList;
	}

}
