package Spring;

import java.io.Serializable;
import java.sql.Timestamp;

public class DeviceWifi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5543806836338036544L;
	private String deviceId;
	private String objId;
	private String ssid;
	private String password;
	private Long monthTraffic;//??wifiOutflow
	private Timestamp posTime;
	private Timestamp sampleTime;
	private Timestamp lastUpdateTime;
	private Integer  triggerType;//上报WIFI信息的触发原因。0:未知;1:点火;2:熄火;3:修改SSID或密码;4.恢复出厂设置;5:无密码访问（开发网络）切换
	
	private Integer wifiStatus;
	private Integer wifiConnections;
	private Integer wifiFreeStatus;
	private Integer wifiFreeTime;
	private String wifiMac;
	private String gateNo;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getMonthTraffic() {
		return monthTraffic;
	}
	public void setMonthTraffic(Long monthTraffic) {
		this.monthTraffic = monthTraffic;
	}
	public Timestamp getPosTime() {
		return posTime;
	}
	public void setPosTime(Timestamp posTime) {
		this.posTime = posTime;
	}
	public Timestamp getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(Timestamp sampleTime) {
		this.sampleTime = sampleTime;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getTriggerType() {
		if (triggerType==null) return 0;
		return triggerType;
	}
	public void setTriggerType(Integer triggerType) {
		this.triggerType = triggerType;
	}
	public Integer getWifiStatus() {
		if (wifiStatus==null) return 0;
		return wifiStatus;
	}
	public void setWifiStatus(Integer wifiStatus) {
		this.wifiStatus = wifiStatus;
	}
	public Integer getWifiConnections() {
		if (wifiConnections==null) return 0;
		return wifiConnections;
	}
	public void setWifiConnections(Integer wifiConnections) {
		this.wifiConnections = wifiConnections;
	}
	public Integer getWifiFreeStatus() {
		if (wifiFreeStatus==null) return 0;
		return wifiFreeStatus;
	}
	public void setWifiFreeStatus(Integer wifiFreeStatus) {
		this.wifiFreeStatus = wifiFreeStatus;
	}
	public Integer getWifiFreeTime() {
		if (wifiFreeTime==null) return 5;
		return wifiFreeTime;
	}
	public void setWifiFreeTime(Integer wifiFreeTime) {
		this.wifiFreeTime = wifiFreeTime;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getGateNo() {
		return gateNo;
	}
	public void setGateNo(String gateNo) {
		this.gateNo = gateNo;
	}
	
}



