package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.BuildingMapper;
import team.sdm.mapper.TeacherMapper;
import team.sdm.po.Building;
import team.sdm.service.IBuildingService;
@Service
public class BuildingService implements IBuildingService {
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Override
	public List<Building> getBuildingList() {
		// TODO Auto-generated method stub
		return buildingMapper.getBuildingList();
	}
	@Override
	public int addBuilding(String name, String remark) {
		// TODO Auto-generated method stub
		return buildingMapper.addBuilding(name,remark);
	}
	@Override
	public Building getBuildingById(int id) {
		// TODO Auto-generated method stub
		return buildingMapper.getBuildingById(id);
	}
	@Override
	public int editBuilding(Building building) {
		// TODO Auto-generated method stub
		return buildingMapper.editBuilding(building);
	}
	@Override
	public int deleteBuildingById(int id) {
		// TODO Auto-generated method stub
		return buildingMapper.deleteBuildingById(id);
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return buildingMapper.getTotalCount();
	}
	@Override
	public List<Building> getSearchBuilding(String search_name,int page,int rows) {
		// TODO Auto-generated method stub
		//定义分页信息
		page = (page-1)*rows;
		return buildingMapper.getSearchBuilding(search_name,page,rows);
	}
	@Override
	public int setBuildingManager(String[] ids, int building_id) {
		// TODO Auto-generated method stub
		int result =0;
		int count = 0;
		List<Long> teachers = teacherMapper.getTeacherByBuidingId(building_id);
		System.out.println(teachers.size());
		if(teachers.size()>0) {
			//存在旧分配
			count = buildingMapper.deleteManagerByBuildingId(building_id);
			if (count>0) {
				for (int i = 0; i < ids.length; i++) {
					result=0;
					result = buildingMapper.setBuildingManager(Integer.parseInt(ids[i]),building_id);
				}
			}
		}else {
			//不存在旧分配
			for (int i = 0; i < ids.length; i++) {
				result = 0;
				result = buildingMapper.setBuildingManager(Integer.parseInt(ids[i]),building_id);
			}
		}
		return result;
	}
	@Override
	public List<Building> getListByPage(int page, int rows) {
		// TODO Auto-generated method stub
		//定义分页信息
		page = (page-1)*rows;
		return buildingMapper.getListByPage(page,rows);
	}
	@Override
	public int resBuildingCount(String search_name) {
		// TODO Auto-generated method stub
		return buildingMapper.resBuildingCount(search_name);
	}

}
