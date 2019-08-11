package team.sdm.po;

public class Building {
	private long building_id;
	private String building_name;
	private String building_remark;
	public long getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(long building_id) {
		this.building_id = building_id;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_remark() {
		return building_remark;
	}
	@Override
	public String toString() {
		return "Building [building_id=" + building_id + ", building_name=" + building_name + ", building_remark="
				+ building_remark + "]";
	}
	public void setBuilding_remark(String building_remark) {
		this.building_remark = building_remark;
	}
}
