package team.sdm.po;

public class Loginlog {
	private long id;
	private String IPaddress;
	private String loginDate;
	private String loginIP;
	private long userId;
	private String userName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIPaddress() {
		return IPaddress;
	}
	public void setIPaddress(String iPaddress) {
		IPaddress = iPaddress;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Loginlog [id=" + id + ", IPaddress=" + IPaddress + ", loginDate=" + loginDate + ", loginIP=" + loginIP
				+ ", userId=" + userId + ", userName=" + userName + "]";
	}
}
