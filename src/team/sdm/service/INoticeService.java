package team.sdm.service;

import java.util.List;

import team.sdm.po.Tab_notice;

public interface INoticeService {

	int addNotice(Tab_notice notice);

	List<Tab_notice> getNoticeList(Tab_notice notice, int page, int rows);

	int getNoticeCount(Tab_notice notice);

	int updateNoticeByChangeDorm(String rec_userName, String guid);

	List<Tab_notice> qryNotOkNotice(Tab_notice notice);

	boolean outDorm(long parseLong, String date, String remark);

	boolean updateNotice(Tab_notice notice);
}
