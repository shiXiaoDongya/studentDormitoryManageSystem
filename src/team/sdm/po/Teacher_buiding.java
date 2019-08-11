package team.sdm.po;

public class Teacher_buiding {
	private long building_id;
	private long teacher_id;
	@Override
	public String toString() {
		return "Teacher_buiding [building_id=" + building_id + ", teacher_id=" + teacher_id + "]";
	}
	public long getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(long building_id) {
		this.building_id = building_id;
	}
	public long getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}
}
