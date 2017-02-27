package Spring;


import java.sql.Timestamp;

/**
 * DeviceStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DeviceStatus implements Cloneable,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6506158606200136824L;

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	// Fields
	private String gateNo;
	private String deviceId;
	private String appId;
	private String objId;
	private Short serviceStatus;
	private Short onlineStatus;
	private Short alarmStatus;
	private Timestamp alarmTime;
	private Short posMethod;

	private Short posPrecision;
	private Double posLongitude;
	private Double posLatitude;
	private Integer posAltitude;

	private Integer posSpeed;
	private Integer posDirection;
	private String networkDataId;
	private Double accForward;
	private Double accSide;
	private Double accVerticle;
	private Short rolloverLevel;

	private Timestamp rolloverReportTime;
	private Short voltageLevel;
	private Timestamp voltageReportTime;
	private Short collisionLevel;
	private Timestamp collisionReportTime;
	private Short signalLevel;
	private Timestamp signalReportTime;
	private Integer statusAreaMonitor;

	private String statushisId;
	private String statushisExtId;
	private Double powerVoltage;
	private Integer deviceStatus = 1;
	private String gid;
	private Short accStatus;


	private String brandId;
	private String gidVersion;

	private String dtcVersion;

	// ********2012-06-29*********//

	private String softwareVersion;
	private String hardwareVersion;
	private String gsmType;
	private String bootloaderVersion;
	private Integer updateFlag;

	// ***************************//
	// private String softwareDate;
	// private String carType;

	private Short offlineEventType;
	private Integer duration;
	private String deviceLogFlag;

	private Timestamp updateStatusTime = new Timestamp(System.currentTimeMillis());


	private Timestamp recordTime = updateStatusTime;
	// FOR DSE

	private Timestamp sampleTime;

	private Timestamp posTime = null;
	private String debugInfo1;
	private String debugInfo2;
	
	//gps 卫星颗数
	private Integer satellites;

	public String getDebugInfo1() {
		return debugInfo1;
	}

	public void setDebugInfo1(String debugInfo1) {
		this.debugInfo1 = debugInfo1;
	}

	public String getDebugInfo2() {
		return debugInfo2;
	}

	public void setDebugInfo2(String debugInfo2) {
		this.debugInfo2 = debugInfo2;
	}

	public Timestamp getSampleTime() {
		return sampleTime;
	}

	public void setSampleTime(Timestamp sampleTime) {
		this.sampleTime = sampleTime;
	}

	public Short getOfflineEventType() {
		return offlineEventType;
	}

	public void setOfflineEventType(Short offlineEventType) {
		this.offlineEventType = offlineEventType;
	}

	/*
	 * public String getSoftwareDate() { return softwareDate; }
	 * 
	 * public void setSoftwareDate(String softwareDate) { this.softwareDate =
	 * softwareDate;
	 * 
	 * public String getCarType() { return carType; }
	 * 
	 * public void setCarType(String carType) { this.carType = carType; }
	 */
	public String getGidVersion() {
		return gidVersion;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getGsmType() {
		return gsmType;
	}

	public void setGsmType(String gsmType) {
		this.gsmType = gsmType;
	}

	public String getBootloaderVersion() {
		return bootloaderVersion;
	}

	public void setBootloaderVersion(String bootloaderVersion) {
		this.bootloaderVersion = bootloaderVersion;
	}

	public Integer getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(Integer updateFlag) {
		this.updateFlag = updateFlag;
	}

	public void setGidVersion(String gidVersion) {
		this.gidVersion = gidVersion;
	}

	public String getDtcVersion() {
		return dtcVersion;
	}

	public void setDtcVersion(String dtcVersion) {
		this.dtcVersion = dtcVersion;
	}

	public Short getAccStatus() {
		return accStatus;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setAccStatus(Short accStatus) {
		this.accStatus = accStatus;
	}

	// Constructors
	public DeviceStatus() {
	}

	/** minimal constructor */
	public DeviceStatus(String deviceId) {
		this.deviceId = deviceId;
	}

	/** full constructor */
	public DeviceStatus(String deviceId, String appId, String objId, Short serviceStatus, Short onlineStatus,
                        Short alarmStatus, Timestamp alarmTime, Short posMethod, Timestamp posTime, Short posPrecision,
                        Double posLongitude, Double posLatitude, Integer posAltitude, Integer posSpeed, Integer posDirection,
                        String networkDataId, Double accForward, Double accSide, Double accVerticle, Short rolloverLevel,
                        Timestamp rolloverReportTime, Short voltageLevel, Timestamp voltageReportTime, Short collisionLevel,
                        Timestamp collisionReportTime, Short signalLevel, Timestamp signalReportTime, Integer statusAreaMonitor,
                        String statushisId, String statushisExtId, Double powerVoltage, Integer deviceStatus, String gid,
                        Timestamp updateStatusTime, Integer satellites) {
		this.deviceId = deviceId;
		this.appId = appId;
		this.objId = objId;
		this.serviceStatus = serviceStatus;
		this.onlineStatus = onlineStatus;
		this.alarmStatus = alarmStatus;
		this.alarmTime = alarmTime;
		this.posMethod = posMethod;
		this.posTime = posTime;
		this.posPrecision = posPrecision;
		this.posLongitude = posLongitude;
		this.posLatitude = posLatitude;
		this.posAltitude = posAltitude;
		this.posSpeed = posSpeed;
		this.posDirection = posDirection;
		this.networkDataId = networkDataId;
		this.accForward = accForward;
		this.accSide = accSide;
		this.accVerticle = accVerticle;
		this.rolloverLevel = rolloverLevel;
		this.rolloverReportTime = rolloverReportTime;
		this.voltageLevel = voltageLevel;
		this.voltageReportTime = voltageReportTime;
		this.collisionLevel = collisionLevel;
		this.collisionReportTime = collisionReportTime;
		this.signalLevel = signalLevel;
		this.signalReportTime = signalReportTime;
		this.statusAreaMonitor = statusAreaMonitor;
		this.statushisId = statushisId;
		this.statushisExtId = statushisExtId;
		this.powerVoltage = powerVoltage;
		this.deviceStatus = deviceStatus;
		this.gid = gid;
		this.updateStatusTime = updateStatusTime;
		this.satellites = satellites;
	}

	// Property accessors

	public String getDeviceId() {
		return this.deviceId;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Short getServiceStatus() {
		return this.serviceStatus;
	}

	public void setServiceStatus(Short serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Short getOnlineStatus() {
		return this.onlineStatus;
	}

	public void setOnlineStatus(Short onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Short getAlarmStatus() {
		return this.alarmStatus;
	}

	public void setAlarmStatus(Short alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public Timestamp getAlarmTime() {
		return this.alarmTime;
	}

	public String getAlarmTimeStr() {
		return this.alarmTime == null ? null : this.alarmTime.toLocaleString();
	}

	public void setAlarmTime(Timestamp alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Short getPosMethod() {
		return this.posMethod;
	}

	public void setPosMethod(Short posMethod) {
		this.posMethod = posMethod;
	}

	public Timestamp getPosTime() {
		return this.posTime;
	}

	/*
	 * public String getPosTimeStr() { return this.posTime == null ? null :
	 * this.posTime.toLocaleString(); }
	 */
	public void setPosTime(Timestamp posTime) {
		this.posTime = posTime;
	}

	public Short getPosPrecision() {
		return this.posPrecision;
	}

	public void setPosPrecision(Short posPrecision) {
		this.posPrecision = posPrecision;
	}

	public Double getPosLongitude() {
		return this.posLongitude;
	}

	public void setPosLongitude(Double posLongitude) {
		this.posLongitude = posLongitude;
	}

	public Double getPosLatitude() {
		return this.posLatitude;
	}

	public void setPosLatitude(Double posLatitude) {
		this.posLatitude = posLatitude;
	}

	public Integer getPosAltitude() {
		return this.posAltitude;
	}

	public void setPosAltitude(Integer posAltitude) {
		this.posAltitude = posAltitude;
	}

	public Integer getPosSpeed() {
		return this.posSpeed;
	}

	public void setPosSpeed(Integer posSpeed) {
		this.posSpeed = posSpeed;
	}

	public Integer getPosDirection() {
		return this.posDirection;
	}

	public void setPosDirection(Integer posDirection) {
		this.posDirection = posDirection;
	}

	public String getNetworkDataId() {
		return this.networkDataId;
	}

	public void setNetworkDataId(String networkDataId) {
		this.networkDataId = networkDataId;
	}

	public Double getAccForward() {
		return this.accForward;
	}

	public void setAccForward(Double accForward) {
		this.accForward = accForward;
	}

	public Double getAccSide() {
		return this.accSide;
	}

	public void setAccSide(Double accSide) {
		this.accSide = accSide;
	}

	public Double getAccVerticle() {
		return this.accVerticle;
	}

	public void setAccVerticle(Double accVerticle) {
		this.accVerticle = accVerticle;
	}

	public Short getRolloverLevel() {
		return this.rolloverLevel;
	}

	public void setRolloverLevel(Short rolloverLevel) {
		this.rolloverLevel = rolloverLevel;
	}

	public Timestamp getRolloverReportTime() {
		return this.rolloverReportTime;
	}

	public String getRolloverReportTimeStr() {
		return this.rolloverReportTime == null ? null : this.rolloverReportTime.toLocaleString();
	}

	public void setRolloverReportTime(Timestamp rolloverReportTime) {
		this.rolloverReportTime = rolloverReportTime;
	}

	public Short getVoltageLevel() {
		return this.voltageLevel;
	}

	public void setVoltageLevel(Short voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	public Timestamp getVoltageReportTime() {
		return this.voltageReportTime;
	}

	public String getVoltageReportTimeStr() {
		return this.voltageReportTime == null ? null : this.voltageReportTime.toLocaleString();
	}

	public void setVoltageReportTime(Timestamp voltageReportTime) {
		this.voltageReportTime = voltageReportTime;
	}

	public Short getCollisionLevel() {
		return this.collisionLevel;
	}

	public void setCollisionLevel(Short collisionLevel) {
		this.collisionLevel = collisionLevel;
	}

	public Timestamp getCollisionReportTime() {
		return this.collisionReportTime;
	}

	public String getCollisionReportTimeStr() {
		return this.collisionReportTime == null ? null : this.collisionReportTime.toLocaleString();
	}

	public void setCollisionReportTime(Timestamp collisionReportTime) {
		this.collisionReportTime = collisionReportTime;
	}

	public Short getSignalLevel() {
		return this.signalLevel;
	}

	public void setSignalLevel(Short signalLevel) {
		this.signalLevel = signalLevel;
	}

	public Timestamp getSignalReportTime() {
		return this.signalReportTime;
	}

	public String getSignalReportTimeStr() {
		return this.signalReportTime == null ? null : this.signalReportTime.toLocaleString();
	}

	public void setSignalReportTime(Timestamp signalReportTime) {
		this.signalReportTime = signalReportTime;
	}

	public Integer getStatusAreaMonitor() {
		return this.statusAreaMonitor;
	}

	public void setStatusAreaMonitor(Integer statusAreaMonitor) {
		this.statusAreaMonitor = statusAreaMonitor;
	}

	public String getStatushisId() {
		return this.statushisId;
	}

	public void setStatushisId(String statushisId) {
		this.statushisId = statushisId;
	}

	public String getStatushisExtId() {
		return this.statushisExtId;
	}

	public void setStatushisExtId(String statushisExtId) {
		this.statushisExtId = statushisExtId;
	}

	public Double getPowerVoltage() {
		return this.powerVoltage;
	}

	public void setPowerVoltage(Double powerVoltage) {
		this.powerVoltage = powerVoltage;
	}

	public Integer getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Timestamp getUpdateStatusTime() {
		return this.updateStatusTime;
	}

	public void setUpdateStatusTime(Timestamp updateStatusTime) {
		this.updateStatusTime = updateStatusTime;
	}

	public String getGateNo() {
		return gateNo;
	}

	public void setGateNo(String gateNo) {
		this.gateNo = gateNo;
	}


	@Override
	public String toString() {
		return "DeviceStatus [gateNo=" + gateNo + ", deviceId=" + deviceId + ", appId=" + appId + ", objId=" + objId
				+ ", serviceStatus=" + serviceStatus + ", onlineStatus=" + onlineStatus + ", alarmStatus="
				+ alarmStatus + ", alarmTime=" + alarmTime + ", posMethod=" + posMethod + ", posPrecision="
				+ posPrecision + ", posLongitude=" + posLongitude + ", posLatitude=" + posLatitude + ", posAltitude="
				+ posAltitude + ", posSpeed=" + posSpeed + ", posDirection=" + posDirection + ", networkDataId="
				+ networkDataId + ", accForward=" + accForward + ", accSide=" + accSide + ", accVerticle="
				+ accVerticle + ", rolloverLevel=" + rolloverLevel + ", rolloverReportTime=" + rolloverReportTime
				+ ", voltageLevel=" + voltageLevel + ", voltageReportTime=" + voltageReportTime + ", collisionLevel="
				+ collisionLevel + ", collisionReportTime=" + collisionReportTime + ", signalLevel=" + signalLevel
				+ ", signalReportTime=" + signalReportTime + ", statusAreaMonitor=" + statusAreaMonitor
				+ ", statushisId=" + statushisId + ", statushisExtId=" + statushisExtId + ", powerVoltage="
				+ powerVoltage + ", deviceStatus=" + deviceStatus + ", gid=" + gid + ", accStatus=" + accStatus
				+ ", brandId=" + brandId + ", gidVersion=" + gidVersion + ", dtcVersion=" + dtcVersion
				+ ", softwareVersion=" + softwareVersion + ", hardwareVersion=" + hardwareVersion + ", gsmType="
				+ gsmType + ", bootloaderVersion=" + bootloaderVersion + ", updateFlag=" + updateFlag
				+ ", offlineEventType=" + offlineEventType + ", updateStatusTime=" + updateStatusTime + ", recordTime="
				+ recordTime + ", sampleTime=" + sampleTime + ", posTime=" + posTime + ", debugInfo1=" + debugInfo1 + ", debugInfo2="
				+ debugInfo2 + ", satellites=" + satellites+ "]";
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDeviceLogFlag() {
		return deviceLogFlag;
	}

	public void setDeviceLogFlag(String deviceLogFlag) {
		this.deviceLogFlag = deviceLogFlag;
	}

	public Integer getSatellites() {
		return satellites;
	}

	public void setSatellites(Integer satellites) {
		this.satellites = satellites;
	}

}