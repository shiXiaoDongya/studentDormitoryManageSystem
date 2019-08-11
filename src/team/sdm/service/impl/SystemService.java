package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.SystemMapper;
import team.sdm.po.Loginlog;
import team.sdm.po.System;
import team.sdm.service.ISystemService;
@Service
public class SystemService implements ISystemService {
	@Autowired
    private SystemMapper systemMapper;

	@Override
	public int updatePass(String flag, String userPass, long parseLong) {
		// TODO Auto-generated method stub
		return systemMapper.updatePass(flag, userPass, parseLong);
	}

	@Override
	public System login(String name, String password) {
		// TODO Auto-generated method stub
		return systemMapper.login(name,password);
	}

	@Override
	public List<Loginlog> getAllLoginlog(int page,int rows) {
		// TODO Auto-generated method stub
		int start = (page - 1) * rows;
		int end = rows;
		return systemMapper.getAllLoginlog(start,end);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return systemMapper.getTotalCount();
	}

	@Override
	public List<Loginlog> getSearchList(String keyword,int page,int rows) {
		// TODO Auto-generated method stub
		int start = (page - 1) * rows;
		int end = rows;
		return systemMapper.getSearchList(keyword,start,end);
	}

	@Override
	public int getSearchTotalCount(String keyword) {
		// TODO Auto-generated method stub
		return systemMapper.getSearchTotalCount(keyword);
	}

	@Override
	public int deleteLoginlogById(int id) {
		// TODO Auto-generated method stub
		return systemMapper.deleteLoginlogById(id);
	}

	@Override
	public Loginlog getLoginlogById(int id) {
		// TODO Auto-generated method stub
		return systemMapper.getLoginlogById(id);
	}

	@Override
	public int editLoginlog(Loginlog loginlog) {
		// TODO Auto-generated method stub
		return systemMapper.editLoginlog(loginlog);
	}

	@Override
	public int addLog(Loginlog log) {
		// TODO Auto-generated method stub
		return systemMapper.addLog(log);
	}
}
