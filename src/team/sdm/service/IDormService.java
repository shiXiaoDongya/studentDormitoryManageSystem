package team.sdm.service;

import java.util.List;

import team.sdm.po.Dorm;

public interface IDormService {

	List<Dorm> showdorm(Dorm dorm1, int page, int rows);

	int dormCount1(Dorm searchdorm);

	boolean adddorm(Dorm dorm);

	int editdorm(Dorm dorm);

	Dorm finddormbuilding(int dormid);

	boolean deleteDorm(long delIds);

	Dorm searchdorm(String buildingData1, String dormName);

	Dorm showSearchDorm(long dorm_id);

	Dorm getDorm(long student_id);

	List<Dorm> queryDormByBuilding(long building_id);

	int deleteDormPeople(Dorm outDorm);

	Dorm getNum(long dorm_id);

	int addPeople(long dorm_id);

	List<Dorm> findDormByBuildingId(long parseLong);

	List<Dorm> findDormByBuildingName(String pname);

	Dorm findDormById(long dorm_id);

	int dormCount();

}
