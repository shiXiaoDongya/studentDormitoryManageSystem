package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.NoitceMapper;
import team.sdm.po.Tab_notice;
import team.sdm.service.INoticeService;

@Service
public class NoitceSerivce implements INoticeService {
	@Autowired
    private NoitceMapper noticeMapper;
	@Override
	public int addNotice(Tab_notice notice) {
		// TODO Auto-generated method stub
		return noticeMapper.addNotice(notice);
	}
	@Override
	public List<Tab_notice> getNoticeList(Tab_notice notice, int page, int rows) {
		// TODO Auto-generated method stub
		int start = (page-1)*rows;
		return noticeMapper.getNoticeList(notice,start,rows);
	}
	@Override
	public int getNoticeCount(Tab_notice notice) {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeCount(notice);
	}
	@Override
	public int updateNoticeByChangeDorm(String rec_userName,String guid) {
		// TODO Auto-generated method stub
		return noticeMapper.updateNoticeByChangeDorm(rec_userName,guid);
	}
	@Override
	public List<Tab_notice> qryNotOkNotice(Tab_notice notice) {
		// TODO Auto-generated method stub
		return noticeMapper.qryNotOkNotice(notice);
	}
	/**
	 * 迁出登记
	 */
	@Override
	public boolean outDorm(long sid, String date, String remark) {
		//迁出登记:修改学生信息 对应宿舍人数减1 删除中间表数据
		int count1 = updateStudent(sid,date,remark);
		int count2 = dormRemoveOne(sid);
		int count3 = deleteDormMsg(sid);
		if (count1>0&&count2>0&&count3>0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 删除中间表数据
	 * @param sid
	 * @return
	 */
	private int deleteDormMsg(long sid) {
		// TODO Auto-generated method stub
		return noticeMapper.deleteDormMsg(sid);
	}
	/**
	 *  对应宿舍人数减1
	 * @param sid
	 * @return
	 */
	private int dormRemoveOne(long sid) {
		// TODO Auto-generated method stub
		long dormId = getDormIdBySid(sid);
		return noticeMapper.dormRemoveOne(dormId);
	}
	/**
	 * 获取学生对应的宿舍编号
	 * @param sid
	 * @return
	 */
	private long getDormIdBySid(long sid) {
		// TODO Auto-generated method stub
		return noticeMapper.getDormIdBySid(sid);
	}
	/**
	 * 迁出登记:修改学生信息 
	 * @param sid
	 * @param date
	 * @param remark
	 * @return
	 */
	private int updateStudent(long sid, String date, String remark) {
		// TODO Auto-generated method stub
		return noticeMapper.updateStudent(sid,date,remark);
	}
	@Override
	public boolean updateNotice(Tab_notice notice) {
		int count = noticeMapper.updateNotice(notice);
		if (count>0) {
			return true;
		}else{
			return false;
		}
	}


}
