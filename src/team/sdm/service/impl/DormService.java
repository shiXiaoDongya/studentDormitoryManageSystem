package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.DormMapper;
import team.sdm.po.Dorm;
import team.sdm.service.IDormService;
@Service
public class DormService implements IDormService {
	@Autowired
	private DormMapper dormMapper;

	@Override
	public List<Dorm> showdorm(Dorm dorm1,int page, int rows) {
		// TODO �Զ����ɵķ������
		return dormMapper.showDorm(dorm1,page,rows);
	}
	@Override
	public int dormCount1(Dorm searchdorm) {
		// TODO �Զ����ɵķ������
		return dormMapper.dormCount1(searchdorm);
	}
	@Override
	public boolean adddorm(Dorm dorm) {
		// TODO �Զ����ɵķ������
//		if(dorm.getDorm_people_num()==0) {
//			dorm.setDorm_people_num(0);
//		}
		int count1 = dormMapper.adddorm(dorm); 
		Dorm dorm2 = dormMapper.getDormByBuildngAndName(dorm);
		int count2 = dormMapper.addDormBuilding(dorm2);
		if(count1>0 && count2 >0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int editdorm(Dorm dorm) {
		// TODO �Զ����ɵķ������
		return dormMapper.editdorm(dorm);
	}
	@Override
	public Dorm finddormbuilding(int dorm_id) {
		// TODO 自动生成的方法存根
		return dormMapper.finddormbuilding(dorm_id);
	}
	@Override
	public boolean deleteDorm(long delIds) {
		// TODO 自动生成的方法存根
		int count1 = dormMapper.deleteDorm(delIds);
		int count2 = dormMapper.deleteDormBuilding(delIds);
		if(count1>0 && count2>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public Dorm searchdorm(String dorm_building, String dorm_name) {
		// TODO 自动生成的方法存根
		return dormMapper.searchdorm(dorm_building,dorm_name);
	}
	@Override
	public Dorm showSearchDorm(long dorm_id) {
		// TODO 自动生成的方法存根
		return dormMapper.showSearchDorm(dorm_id);
	}
	@Override
	public Dorm getDorm(long student_id) {
		// TODO Auto-generated method stub
		return dormMapper.getDorm(student_id);
	}
	@Override
	public List<Dorm> queryDormByBuilding(long building_id) {
		// TODO Auto-generated method stub
		return dormMapper.queryDormByBuilding(building_id);
	}
	@Override
	public int deleteDormPeople(Dorm outDorm) {
		// TODO Auto-generated method stub
		return dormMapper.deleteDormPeople(outDorm);
	}
	@Override
	public Dorm getNum(long dorm_id) {
		// TODO Auto-generated method stub
		return dormMapper.getNum(dorm_id);
	}
	@Override
	public int addPeople(long dorm_id) {
		// TODO Auto-generated method stub
		return dormMapper.addPeople(dorm_id);
	}
	@Override
	public List<Dorm> findDormByBuildingId(long buildingId) {
		// TODO Auto-generated method stub
		return dormMapper.findDormByBuildingId(buildingId);
	}
	@Override
	public List<Dorm> findDormByBuildingName(String name) {
		// TODO Auto-generated method stub
		return dormMapper.findDormByBuildingName(name);
	}
	@Override
	public Dorm findDormById(long dorm_id) {
		// TODO Auto-generated method stub
		return dormMapper.findDormById(dorm_id);
	}
	@Override
	public int dormCount() {
		// TODO Auto-generated method stub
		return dormMapper.dormCount();
	}

}
