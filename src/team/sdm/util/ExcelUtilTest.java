package team.sdm.util;

import java.util.ArrayList;
import java.util.List;

import team.sdm.po.StudentVo;
import team.sdm.service.IStudentService;

public class ExcelUtilTest {
	public static void main(String[] args) {
		String path = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\hehe.xls";
		List<String> head = new ArrayList<String>();
		head.add(0, "name");
		head.add(0, "phone");
		head.add(0, "status");
		head.add(0, "major");
		head.add(0, "header");
		
		List<DoorVo> listData = new ArrayList<DoorVo>();
		for (int i = 0; i < 6; i++) {
			DoorVo vo = new DoorVo(); // Ҫ������������
			vo.setId("hehe " + i);
			vo.setFjh("e" + i);
			vo.setFzzjzmj(90.22);
			vo.setDeptId("22" + i);
			listData.add(vo);
		}
		
		
		ExcelUtil.fillExcelData(head, listData);
	}
}

class DoorVo {
	private String id;
	private String fjh;
	private double fzzjzmj;
	private String deptId;

	public String getFjh() {
		return fjh;
	}

	public void setFjh(String fjh) {
		this.fjh = fjh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getFzzjzmj() {
		return fzzjzmj;
	}

	public void setFzzjzmj(double fzzjzmj) {
		this.fzzjzmj = fzzjzmj;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}

