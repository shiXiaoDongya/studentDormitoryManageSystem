package team.sdm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.sdm.po.Dorm;
import team.sdm.po.StudentVo;
import team.sdm.po.Tab_notice;
import team.sdm.po.Teacher;
import team.sdm.service.IDormService;
import team.sdm.service.INoticeService;
import team.sdm.service.ITeacherService;
import team.sdm.util.ExcelUtil;
import team.sdm.util.ResponseUtil;

@Controller
public class TeacherCtrl {
	private Logger logger = Logger.getLogger(TeacherCtrl.class);

	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private INoticeService noticeService;

	// private Dorm dorm;
	
	@RequestMapping(value = "/getTeacherList.do", method = RequestMethod.POST)
	public void showdorm(HttpServletRequest request, HttpServletResponse response) {
		/***
		 * 第1种方法servlet api
		 */
		System.out.println("进入getTeacherList.do");
		// String flag = request.getParameter("flag");
		// System.out.println("flag"+flag);
		response.setContentType("text/html;charset=UTF-8");
		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);

		String teacher_name = request.getParameter("t_name");
		//Teacher teacher = new Teacher();
		//teacher.getTeacher_name();
		// 查询

		List<Teacher> teachers = teacherService.getTeacherList1(teacher_name,(page-1)*rows,rows);
		System.out.println("查到的teacherService" + teachers);
		int total = teacherService.teacherCount(teacher_name);
		System.out.println("总数" + total);

		// int pageNum = total % rows == 0 ? (total / rows) : (total / rows) + 1;

		JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(teachers);
		obj.put("rows", array);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		
		
	}



	@RequestMapping(value = "/addteacher.do", method = RequestMethod.POST)
	public void addteacher(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		/***
		 * 第1种方法servlet api
		 */
		System.out.println("进入addteacher.do");
		// String flag = request.getParameter("flag");
		// System.out.println("flag"+flag);
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		String teacher_userName = request.getParameter("vo.teacher_userName");
		String teacher_name = request.getParameter("vo.teacher_name");
		String teacher_sex = request.getParameter("vo.teacher_sex");
		String teacher_tel = request.getParameter("vo.teacher_tel");
		
		Teacher teacher = new Teacher();
		teacher.setTeacher_name(teacher_name);
		teacher.setTeacher_userName(teacher_userName);
		teacher.setTeacher_sex(teacher_sex);
		teacher.setTeacher_tel(teacher_tel);
		teacher.setTeacher_userPass("123");
		teacher.toString();
		// 查询

		int count = teacherService.addteacher(teacher);
		if (count > 0) {
			System.out.println("++++++++保存成功++++++++++++");
		}
		int total = teacherService.teacherCount(teacher.getTeacher_name());
		System.out.println("得到总数" + total);
		//
		// int pageNum = total % rows == 0 ? (total / rows) : (total / rows) + 1;

		JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(count);
		obj.put("rows", array);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		
		
	}

	
	
	@RequestMapping(value = "/editTeacher.do", method = RequestMethod.POST)
	public void editTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***
		 * servlet api
		 */
		Teacher teacher = new Teacher();
		System.out.println("进入editTeacher.do");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 获取dorm表单数据
		String getteacher_id = request.getParameter("editId");
		long teacher_id = Long.parseLong(getteacher_id);
		String teacher_name = request.getParameter("vo.teacher_name");
		String teacher_userName = request.getParameter("vo.teacher_userName");
		String teacher_tel = request.getParameter("vo.teacher_tel");
		String teacher_sex = request.getParameter("vo.teacher_sex");
		
		teacher.setTeacher_id(teacher_id);
		teacher.setTeacher_name(teacher_name);
		teacher.setTeacher_userName(teacher_userName);
		teacher.setTeacher_tel(teacher_tel);
		teacher.setTeacher_sex(teacher_sex);
		
		teacher.toString();
		// ���
		// List<Dorm> dorms = dormService.showdorm((page - 1) * rows, rows);
		int count = teacherService.editTeacher(teacher);

		JSONObject result = new JSONObject();
		if (count > 0) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "编辑失败，请检查登录名是否重复");
		}
		response.getWriter().write(result.toString());
	}

	
	
	@RequestMapping(value = "/deleteTeacher.do", method = RequestMethod.POST)
	public void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***
		 * 第一种方法servlet api
		 */

		System.out.println("进入deleteTeacher.do");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取传进来的数组
		String delIds = request.getParameter("delIds");
		logger.info("method: deleteTeacher.delete id:" + delIds);
		// 安逗号分割数组
		boolean flag = false;
		String strid[] = delIds.split(",");
		int delNums = 0;
		if (strid != null) {
			delNums = strid.length;
			if (strid.length > 1) {// 多于1条的删除
				for (int i = 0; i < delNums; i++) {
					flag = teacherService.deleteTeacher(Long.parseLong(strid[i]));
				}
			} else {// 只有一条的删除
				flag = teacherService.deleteTeacher(Long.parseLong(delIds));
			}
		}
		JSONObject obj = new JSONObject();
		if (flag) {
			obj.put("success", "true");
			obj.put("delNums", delNums);
		} else {
			obj.put("errorMsg", "Sorry！删除失败 ！");
		}
		response.getWriter().write(obj.toString());
	}
	
	
	
	@RequestMapping(value = "/exportTeacher.do", method = RequestMethod.POST)
	public void exportTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");

		System.out.println("进入exportTeacher.do");

		String teacher_name = request.getParameter("t_name");

		

		Teacher teacher = new Teacher();
		teacher.setTeacher_name("".equals(teacher_name)?null:teacher_name);
		teacher.toString();

	

		List<Teacher> teachers = teacherService.teacherList(teacher,
				(spage == null || "".equals(spage) ? -1 : (Integer.parseInt(spage) - 1) * Integer.parseInt(srows)),
				(srows == null || "".equals(srows) ? -1 : Integer.parseInt(srows)));
		HSSFWorkbook wb = ExcelUtil.fillExcelData(teachers);

		ExcelUtil.writeExcel(response, wb, "教师基本信息表");

	}
	@RequestMapping("/queryAllTeacher.do")
	public void queryAllTeacher(HttpServletRequest request,HttpServletResponse response) {
		try{
			List<Teacher> teacherList = teacherService.getTeacherList();
			String html="<select id=\"sendManage\" name=\"notice.rec_userName\" style=\"width:100\" class=\"easyui-combobox\">";
			html+="<option value=\"\" selected=\"selected\">==全部==</option>";
			if(teacherList!=null&teacherList.size()>0){
				for(Teacher t:teacherList){
					html+="<option value='"+t.getTeacher_userName()+"'>"+t.getTeacher_name()+"</option>";
				}
			}
			html+="</select>";
			ResponseUtil.write(response,html);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@RequestMapping("/qryNotOkNoticeListByTeacher.do")
	public void qryNotOkNoticeListByTeacher(HttpServletRequest request,HttpServletResponse response) {
		String rec_role = request.getParameter("rec_role");
		String rec_userName=request.getParameter("rec_userName");
		List<Tab_notice> list = null;
		try {
			Tab_notice notice = new Tab_notice();
			notice.setRec_role(rec_role);
			notice.setRec_userName(rec_userName);
			list = noticeService.qryNotOkNotice(notice);
			if(list != null && list.size() > 0){
				ResponseUtil.write(response,"true");
			} else {
				ResponseUtil.write(response,"false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
