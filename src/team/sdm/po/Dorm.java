package team.sdm.po;

public class Dorm {
	private long dorm_id;
	private String dorm_name;
	private int dorm_people_num;
	private String dorm_tel;
	private String dorm_type;
	private String dorm_building;
	public long getDorm_id() {
		return dorm_id;
	}
	public void setDorm_id(long dorm_id) {
		this.dorm_id = dorm_id;
	}
	public String getDorm_name() {
		return dorm_name;
	}
	public void setDorm_name(String dorm_name) {
		this.dorm_name = dorm_name;
	}
	public int getDorm_people_num() {
		return dorm_people_num;
	}
	public void setDorm_people_num(int dorm_people_num) {
		this.dorm_people_num = dorm_people_num;
	}
	public String getDorm_tel() {
		return dorm_tel;
	}
	public void setDorm_tel(String dorm_tel) {
		this.dorm_tel = dorm_tel;
	}
	public String getDorm_type() {
		return dorm_type;
	}
	public void setDorm_type(String dorm_type) {
		this.dorm_type = dorm_type;
	}
	public String getDorm_building() {
		return dorm_building;
	}
	public void setDorm_building(String dorm_building) {
		this.dorm_building = dorm_building;
	}
	@Override
	public String toString() {
		return "Dorm [dorm_id=" + dorm_id + ", dorm_name=" + dorm_name + ", dorm_people_num=" + dorm_people_num
				+ ", dorm_tel=" + dorm_tel + ", dorm_type=" + dorm_type + ", dorm_building=" + dorm_building + "]";
	}
	
}
