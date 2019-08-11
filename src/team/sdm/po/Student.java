package team.sdm.po;

import java.math.BigInteger;

public class Student {
	private long student_id;
	private String student_name;
	private String student_class;
	private String student_phone;
	private String student_remark;
	private String student_sex;
	private String student_state;
	private String student_userName;
	private String student_userPass;
	private String out_date;
	private String student_institution;
	private String student_major;

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", student_name=" + student_name + ", student_class="
				+ student_class + ", student_phone=" + student_phone + ", student_remark=" + student_remark
				+ ", student_sex=" + student_sex + ", student_state=" + student_state + ", student_userName="
				+ student_userName + ", student_userPass=" + student_userPass + ", out_date=" + out_date
				+ ", student_institution=" + student_institution + ", student_major=" + student_major
				+ ", student_building=" + student_building + ", student_dorm=" + student_dorm + ", lackFlag=" + lackFlag
				+ ", student_lackBeginTime=" + student_lackBeginTime + ", student_lackEndTime=" + student_lackEndTime
				+ ", student_headFlag=" + student_headFlag + ", student_ifOk=" + student_ifOk + "]";
	}

	private String student_building;

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

	public String getStudent_class() {
		return student_class;
	}

	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}

	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public String getStudent_remark() {
		return student_remark;
	}

	public void setStudent_remark(String student_remark) {
		this.student_remark = student_remark;
	}

	public String getStudent_sex() {
		return student_sex;
	}

	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}

	public String getStudent_state() {
		return student_state;
	}

	public void setStudent_state(String student_state) {
		this.student_state = student_state;
	}

	public String getStudent_userName() {
		return student_userName;
	}

	public void setStudent_userName(String student_userName) {
		this.student_userName = student_userName;
	}

	public String getStudent_userPass() {
		return student_userPass;
	}

	public void setStudent_userPass(String student_userPass) {
		this.student_userPass = student_userPass;
	}

	public String getOut_date() {
		return out_date;
	}

	public void setOut_date(String out_date) {
		this.out_date = out_date;
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

	public int getLackFlag() {
		return lackFlag;
	}

	public void setLackFlag(int lackFlag) {
		this.lackFlag = lackFlag;
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

	public String getStudent_headFlag() {
		return student_headFlag;
	}

	public void setStudent_headFlag(String student_headFlag) {
		this.student_headFlag = student_headFlag;
	}

	public String getStudent_ifOk() {
		return student_ifOk;
	}

	public void setStudent_ifOk(String student_ifOk) {
		this.student_ifOk = student_ifOk;
	}

	private String student_dorm;
	private int lackFlag;
	private String student_lackBeginTime;
	private String student_lackEndTime;
	private String student_headFlag;
	private String student_ifOk;
}