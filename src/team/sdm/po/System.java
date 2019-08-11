package team.sdm.po;

public class System {
	private long system_id;
	private String system_userName;
	private String system_userPass;
	
	@Override
	public String toString() {
		return "System [system_id=" + system_id + ", system_userName=" + system_userName + ", system_userPass="
				+ system_userPass + "]";
	}
	public long getSystem_id() {
		return system_id;
	}
	public void setSystem_id(long system_id) {
		this.system_id = system_id;
	}
	public String getSystem_userName() {
		return system_userName;
	}
	public void setSystem_userName(String system_userName) {
		this.system_userName = system_userName;
	}
	public String getSystem_userPass() {
		return system_userPass;
	}
	public void setSystem_userPass(String system_userPass) {
		this.system_userPass = system_userPass;
	}
}
