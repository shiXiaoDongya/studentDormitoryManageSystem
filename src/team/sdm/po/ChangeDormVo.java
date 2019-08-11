package team.sdm.po;

public class ChangeDormVo {
	private Long student_id;
	private Long dorm_id;
	private String student_userName;
	private String student_name;
	private String student_sex;
	private String building_name;
	private String dorm_name;
	private String guid;
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public Long getDorm_id() {
		return dorm_id;
	}
	public void setDorm_id(Long dorm_id) {
		this.dorm_id = dorm_id;
	}
	public String getStudent_userName() {
		return student_userName;
	}
	public void setStudent_userName(String student_userName) {
		this.student_userName = student_userName;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getDorm_name() {
		return dorm_name;
	}
	public void setDorm_name(String dorm_name) {
		this.dorm_name = dorm_name;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	@Override
	public String toString() {
		return "ChangeDormVo [student_id=" + student_id + ", dorm_id=" + dorm_id + ", student_userName="
				+ student_userName + ", student_name=" + student_name + ", student_sex=" + student_sex
				+ ", building_name=" + building_name + ", dorm_name=" + dorm_name + ", guid=" + guid + "]";
	}
}
