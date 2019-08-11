package team.sdm.po;

public class Student_lack {
	private long student_id;
	private String student_name;
	private String student_institution;
	private String student_major;
	private String student_class;
	private String student_building;
	private String student_dorm;
	private String student_lackBeginTime;
	private String student_lackEndTime;
	private String student_remark;
	private String student_ifOk;
	private String student_sex;
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	@Override
	public String toString() {
		return "Student_lack [student_id=" + student_id + ", student_name=" + student_name + ", student_institution="
				+ student_institution + ", student_major=" + student_major + ", student_class=" + student_class
				+ ", student_building=" + student_building + ", student_dorm=" + student_dorm
				+ ", student_lackBeginTime=" + student_lackBeginTime + ", student_lackEndTime=" + student_lackEndTime
				+ ", student_remark=" + student_remark + ", student_ifOk=" + student_ifOk + "]";
	}
	public long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_institution() {
		return student_institution;
	}
	public void setStudent_institution(String student_institution) {
		this.student_institution = student_institution;
	}
	public String getStudent_major() {
		return student_major;
	}
	public void setStudent_major(String student_major) {
		this.student_major = student_major;
	}
	public String getStudent_class() {
		return student_class;
	}
	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}
	public String getStudent_building() {
		return student_building;
	}
	public void setStudent_building(String student_building) {
		this.student_building = student_building;
	}
	public String getStudent_dorm() {
		return student_dorm;
	}
	public void setStudent_dorm(String student_dorm) {
		this.student_dorm = student_dorm;
	}
	public String getStudent_lackBeginTime() {
		return student_lackBeginTime;
	}
	public void setStudent_lackBeginTime(String student_lackBeginTime) {
		this.student_lackBeginTime = student_lackBeginTime;
	}
	public String getStudent_lackEndTime() {
		return student_lackEndTime;
	}
	public void setStudent_lackEndTime(String student_lackEndTime) {
		this.student_lackEndTime = student_lackEndTime;
	}
	public String getStudent_remark() {
		return student_remark;
	}
	public void setStudent_remark(String student_remark) {
		this.student_remark = student_remark;
	}
	public String getStudent_ifOk() {
		return student_ifOk;
	}
	public void setStudent_ifOk(String student_ifOk) {
		this.student_ifOk = student_ifOk;
	}
}
