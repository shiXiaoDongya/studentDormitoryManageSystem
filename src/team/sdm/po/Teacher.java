package team.sdm.po;
//管理员实体
public class Teacher {
	private Long teacher_id;
	private String teacher_name;
	private String teacher_sex;
	private String teacher_tel;
	private String teacher_userName;
	private String teacher_userPass;
	public long getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public String getTeacher_tel() {
		return teacher_tel;
	}
	public void setTeacher_tel(String teacher_tel) {
		this.teacher_tel = teacher_tel;
	}
	public String getTeacher_userName() {
		return teacher_userName;
	}
	public void setTeacher_userName(String teacher_userName) {
		this.teacher_userName = teacher_userName;
	}
	public String getTeacher_userPass() {
		return teacher_userPass;
	}
	public void setTeacher_userPass(String teacher_userPass) {
		this.teacher_userPass = teacher_userPass;
	}
	@Override
	public String toString() {
		return "Teacher [teacher_id=" + teacher_id + ", teacher_name=" + teacher_name + ", teacher_sex=" + teacher_sex
				+ ", teacher_tel=" + teacher_tel + ", teacher_userName=" + teacher_userName + ", teacher_userPass="
				+ teacher_userPass + "]";
	}
}
