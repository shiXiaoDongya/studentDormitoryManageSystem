package team.sdm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;
import team.sdm.po.Tab_notice;
import team.sdm.service.INoticeService;

@Controller
public class NoticeCtrl {
	@Autowired
	private INoticeService noticeService;
	/**
	 * 迁出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/outDorm.do")
	public void outDorm(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//设置文本编码
		JSONObject result = new JSONObject();
		//获取参数值
		String id = request.getParameter("student_id");//学生id
		String date = request.getParameter("out_date");//迁出日期
		String remark = request.getParameter("student_remark");//迁出备注
		HttpSession session = request.getSession();
		String rec_userName = (String) session.getAttribute("userName");
		String rec_person = (String) session.getAttribute("realName");
		if ("".equals(id)||id==null) {
			result.put("errorMsg", "学生id不能为空！");
		}else {
			//迁出登记:修改学生信息 对应宿舍人数减1 删除中间表数据
			 boolean flag = noticeService.outDorm(Long.parseLong(id),date,remark);
			if (flag) {
				//迁出成功:修改消息状态
				result.put("success", "true");
				Tab_notice notice = new Tab_notice();
				notice.setSee_state("已确认");
				notice.setRec_userName(rec_userName);
				notice.setRec_person(rec_person);
				String guid = request.getParameter("guid");
				notice.setGuid(guid);
				boolean update = noticeService.updateNotice(notice);
				if (!update) {
					//修改失败
					result.put("errorMsg","修改消息查看状态失败！");
				}//end:if !update
			}else {
				//迁出失败
				result.put("errorMsg", "迁出寝室失败！");
			}//end:if flag
		}//end:if id==null
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
}
