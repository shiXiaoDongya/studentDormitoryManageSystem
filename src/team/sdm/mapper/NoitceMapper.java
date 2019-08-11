package team.sdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import team.sdm.po.Tab_notice;

@Repository
public interface NoitceMapper {

	int addNotice(Tab_notice notice);

	List<Tab_notice> getNoticeList(@Param("notice")Tab_notice notice, @Param("start")int start, @Param("rows")int rows);

	int getNoticeCount(@Param("notice")Tab_notice notice);

	int updateNoticeByChangeDorm(String rec_userName, String guid);

	List<Tab_notice> qryNotOkNotice(@Param("notice")Tab_notice notice);

	int updateStudent(long sid, String date, String remark);

	long getDormIdBySid(long sid);

	int dormRemoveOne(long dormId);

	int deleteDormMsg(long sid);

	int updateNotice(Tab_notice notice);

}
