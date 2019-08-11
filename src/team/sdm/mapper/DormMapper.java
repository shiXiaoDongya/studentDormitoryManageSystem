package team.sdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.sdm.po.Dorm;

public interface DormMapper {

	List<Dorm> showDorm(int page, int rows);

	int dormCount1(Dorm searchdorm);

	int adddorm(Dorm dorm);

	int editdorm(Dorm dorm);

	Dorm finddormbuilding(int dorm_id);

	int deleteDorm(long dorm_id);

	Dorm searchdorm(String dorm_building, String dorm_name);

	Dorm showSearchDorm(long dorm_id);

	List<Dorm> showDorm(@Param("Dorm") Dorm dorm, @Param("page") int page, @Param("rows") int rows);

	Dorm getDorm(long student_id);

	List<Dorm> queryDormByBuilding(long building_id);

	int deleteDormPeople(Dorm outDorm);

	Dorm getNum(long dorm_id);

	int addPeople(long dorm_id);

	List<Dorm> findDormByBuildingId(long buildingId);

	List<Dorm> findDormByBuildingName(String name);

	Dorm findDormById(long dorm_id);

	int addDormBuilding(Dorm dorm);

	Dorm getDormByBuildngAndName(Dorm dorm);

	int deleteDormBuilding(long delIds);

	int dormCount();
}
