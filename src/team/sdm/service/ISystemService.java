package team.sdm.service;

import java.util.List;

import team.sdm.po.Loginlog;
import team.sdm.po.System;

public interface ISystemService {

	int updatePass(String flag, String userPass, long parseLong);

	System login(String name, String password);

	List<Loginlog> getAllLoginlog(int page,int rows);

	int getTotalCount();

	List<Loginlog> getSearchList(String keyword,int page,int rows);

	int getSearchTotalCount(String keyword);

	int deleteLoginlogById(int id);

	Loginlog getLoginlogById(int id);

	int editLoginlog(Loginlog loginlog);

	int addLog(Loginlog log);
	
}
