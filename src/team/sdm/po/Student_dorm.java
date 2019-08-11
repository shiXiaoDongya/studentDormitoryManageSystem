package team.sdm.po;

public class Student_dorm {
	private long dorm_id;
	private long student_id;
	@Override
	public String toString() {
		return "Student_dorm [dorm_id=" + dorm_id + ", student_id=" + student_id + "]";
	}
	public long getDorm_id() {
		return dorm_id;
	}
	public void setDorm_id(long dorm_id) {
		this.dorm_id = dorm_id;
	}
	public long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}
}
