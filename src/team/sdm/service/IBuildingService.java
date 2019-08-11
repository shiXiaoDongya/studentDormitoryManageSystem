package team.sdm.service;

import java.util.List;

import team.sdm.po.Building;

public interface IBuildingService {
	
	List<Building> getListByPage(int page,int rows);

	List<Building> getBuildingList();

	int addBuilding(String name, String remark);

	Building getBuildingById(int id);

	int editBuilding(Building building);

	int deleteBuildingById(int id);

	int getTotalCount();

	List<Building> getSearchBuilding(String search_name,int page,int rows);

	int setBuildingManager(String[] ids, int building_id);

	int resBuildingCount(String search_name);

}
