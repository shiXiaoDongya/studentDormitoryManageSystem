package team.sdm.po;

public class Dorm_building {
	private long building_id;
	private long dorm_id;
	@Override
	public String toString() {
		return "Dorm_building [building_id=" + building_id + ", dorm_id=" + dorm_id + "]";
	}
	public long getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(long building_id) {
		this.building_id = building_id;
	}
	public long getDorm_id() {
		return dorm_id;
	}
	public void setDorm_id(long dorm_id) {
		this.dorm_id = dorm_id;
	}
}
