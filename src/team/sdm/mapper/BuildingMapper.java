package team.sdm.mapper;

import java.util.List;

import team.sdm.po.Building;

public interface BuildingMapper {

	List<Building> getBuildingList();

	int addBuilding(String name, String remark);

	Building getBuildingById(int id);

	int editBuilding(Building building);

	int deleteBuildingById(int id);

	int getTotalCount();

	List<Building> getSearchBuilding(String search_name, int page, int rows);

	int deleteManagerByBuildingId(int building_id);

	int setBuildingManager(int ids, int building_id);

	List<Building> getListByPage(int page, int rows);

	int resBuildingCount(String search_name);


}
