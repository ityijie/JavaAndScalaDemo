/**
 *
 */
package loadLocaldata;

import java.io.Serializable;

/**
 * MQ服务配置
 */
@SuppressWarnings("serial")
public class MQConfigure implements Serializable {
	private String name = "";
	
	// 多个用逗号分隔
	private String url = "tcp://127.0.0.1:61616";
	
	private String failover = "tcp://127.0.0.1:61616";
	
	private String username = "";
	
	private String password = "";
	
	// private int threads = 1;
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFailover() {
		return failover;
	}
	
	public void setFailover(String failover) {
		this.failover = failover;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//
	// public int getThreads() {
	// return threads;
	// }
	//
	// public void setThreads(int threads) {
	// this.threads = threads;
	// }
	@Override
	public int hashCode() {
		int hashCode = 0;
		if (name != null) {
			hashCode += name.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MQConfigure)) {
			return false;
		}
		return obj.hashCode() == this.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MQConfigure:\r\n");
		sb.append("\tname\t").append(this.name).append("\r\n");
		sb.append("\tURL\t").append(this.url).append("\r\n");
		sb.append("\tusername\t").append(this.username).append("\r\n");
		sb.append("\tpassword\t").append(this.password).append("\r\n");
		// sb.append("\tthreads\t").append(this.threads).append("\r\n");
		return sb.toString();
	}
}
