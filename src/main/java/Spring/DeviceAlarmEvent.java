package Spring;

import java.io.Serializable;
import java.util.Date;

public class DeviceAlarmEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3921324997675412305L;
    private String recUid;
    private String deviceId;
    private int eventType;
    private Date posTime;
    
	public String getRecUid() {
		return recUid;
	}
	public void setRecUid(String recUid) {
		this.recUid = recUid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public Date getPosTime() {
		return posTime;
	}
	public void setPosTime(Date posTime) {
		this.posTime = posTime;
	}
	@Override
	public String toString() {
		return "UnbindDeviceEvent [recUid=" + recUid + ", deviceId=" + deviceId + ", eventType=" + eventType
				+ ", posTime=" + posTime + "]";
	}
    
}
