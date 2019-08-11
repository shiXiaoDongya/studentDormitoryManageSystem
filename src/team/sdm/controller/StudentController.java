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
import team.sdm.mapper.DormMapper;
import team.sdm.po.Dorm;
import team.sdm.po.StudentVo;
import team.sdm.service.IDormService;
import team.sdm.service.IStudentService;
import team.sdm.util.ExcelUtil;

@Controller
@RequestMapping("/student/*")
public class StudentController {

	private Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;
	@Autowired
	private IDormService dormService;
	/*
	 * 学生基本信息-加载信息列表/查询功能
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);

		String s_state = request.getParameter("s_state");
		String s_institution = request.getParameter("s_institution");
		String s_class = request.getParameter("s_class");
		String s_name = request.getParameter("s_name");
		logger.info("load list page " + page + ", rows " + rows + ", p( state:" + s_state + ", institution:"
				+ s_institution + ", class:" + s_class + ", name like:" + s_name + ")");

		StudentVo vo = new StudentVo();
		vo.setStudent_state(("".equals(s_state) ? null : s_state));
		
		vo.setStudent_institution(("".equals(s_institution) ? null : s_institution));
		vo.setStudent_class(("".equals(s_class) ? null : s_class));
		vo.setStudent_name(("".equals(s_name) ? null : s_name));

		List<StudentVo> studentVo = studentService.studentList(vo, (page - 1) * rows, rows);
		int total = studentService.StudentCount(vo);
//		int total = studentVo.size();
		JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(studentVo);
		obj.put("rows", array);

		response.getWriter().write(obj.toString());
	}

	@RequestMapping(value = "/queryDormByBuilding.do", method = RequestMethod.POST)
	public void queryDormByBuilding(HttpServletRequest request, HttpServletResponse response) {

	}

	/*
	 * 学生基本信息-删除学生
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response) {

		String delIds = request.getParameter("delIds");
		logger.info("method: StudentController.delete id:" + delIds);

		boolean flag = false;
		try {
			JSONObject obj = new JSONObject();
			String str[] = delIds.split(",");
			int delNums = 0;
			if (str != null) {
				delNums = str.length;
				if (str.length > 1) {
					for (int i = 0; i < delNums; i++) {
						flag = studentService.deleteStudentAndStudentDorm(Long.parseLong(str[i]));
					}
				} else {
					flag = studentService.deleteStudentAndStudentDorm(Long.parseLong(delIds));
				}
			}
			if (flag) {
				obj.put("success", "true");
				obj.put("delNums", delNums);
			} else {
				obj.put("errorMsg", "Sorry！删除失败 ！");
			}
			response.getWriter().write(obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/*
	 * 学生基本信息-添加学生
	 */
	@RequestMapping(value = "/addStudent.do", method = RequestMethod.POST)
	public void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");

		String student_userName = request.getParameter("vo.student_userName");
		String student_name = request.getParameter("vo.student_name");
		String student_sex = request.getParameter("vo.student_sex");
		String student_institution = request.getParameter("vo.student_institution");
		String student_major = request.getParameter("vo.student_major");
		String student_class = request.getParameter("vo.student_class");
		String student_phone = request.getParameter("vo.student_phone");
		String student_remark = request.getParameter("vo.student_remark");
		String student_headFlag = request.getParameter("vo.student_headFlag");

		logger.info("addStudent values(userName:" + student_userName + ", name:" + student_name + ", sex:" + student_sex
				+ ", institution:" + student_institution + ", major:" + student_major + ", class:" + student_class
				+ ", phone:" + student_phone + ", remark:" + student_remark + ", headFlag:" + student_headFlag + ")");

		StudentVo vo = new StudentVo();
		vo.setStudent_userName(student_userName);
		vo.setStudent_name(student_name);
		vo.setStudent_sex(student_sex);
		vo.setStudent_institution(student_institution);
		vo.setStudent_major(student_major);
		vo.setStudent_class(student_class);
		vo.setStudent_phone(student_phone);
		vo.setStudent_remark(student_remark);
		vo.setStudent_headFlag(student_headFlag);

		boolean bool = studentService.addStudent(vo);
		JSONObject result = new JSONObject();
		if (bool) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "新增失败！！请核对信息是否符合规范或已存在。");
		}
		response.getWriter().write(result.toString());

	}

	/*
	 * 学生基本信息-编辑学生
	 */
	@RequestMapping(value = "/editStudent.do", method = RequestMethod.POST)
	public void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");

		String student_id = request.getParameter("editId");
		String student_userName = request.getParameter("vo.student_userName");
		String student_name = request.getParameter("vo.student_name");
		String student_sex = request.getParameter("vo.student_sex");
		String student_institution = request.getParameter("vo.student_institution");
		String student_major = request.getParameter("vo.student_major");
		String student_class = request.getParameter("vo.student_class");
		String student_phone = request.getParameter("vo.student_phone");
		String student_remark = request.getParameter("vo.student_remark");
		String student_headFlag = request.getParameter("vo.student_headFlag");

		logger.info("editStudent values(id:" + student_id + ", userName:" + student_userName + ", name:" + student_name
				+ ", sex:" + student_sex + ", institution:" + student_institution + ", major:" + student_major
				+ ", class:" + student_class + ", phone:" + student_phone + ", remark:" + student_remark + ", headFlag:"
				+ student_headFlag + ")");

		StudentVo vo = new StudentVo();
		vo.setStudent_id(Long.parseLong(student_id));
		vo.setStudent_userName(student_userName);
		vo.setStudent_name(student_name);
		vo.setStudent_sex(student_sex);
		vo.setStudent_institution(student_institution);
		vo.setStudent_major(student_major);
		vo.setStudent_class(student_class);
		vo.setStudent_phone(student_phone);
		vo.setStudent_remark(student_remark);
		vo.setStudent_headFlag(student_headFlag);

		boolean bool = studentService.editStudent(vo);
		JSONObject result = new JSONObject();
		if (bool) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "更新失败！！请核对信息是否符合规范或已存在。");
		}
		response.getWriter().write(result.toString());
	}

	@RequestMapping(value = "/exportStudent.do", method = RequestMethod.POST)
	public void exportStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");

		String s_state = request.getParameter("s_state");
		String s_institution = request.getParameter("s_institution");
		String s_class = request.getParameter("s_class");
		String s_name = request.getParameter("s_name");
		logger.info("export list p( state:" + s_state + ", institution:" + s_institution + ", class:" + s_class
				+ ", name like:" + s_name + ")");

		StudentVo vo = new StudentVo();
		vo.setStudent_state(("".equals(s_state) ? null : s_state));
		vo.setStudent_institution(("".equals(s_institution) ? null : s_institution));
		vo.setStudent_class(("".equals(s_class) ? null : s_class));
		vo.setStudent_name(("".equals(s_name) ? null : s_name));

		/*
		 * 自定义表头
		 */
//		List<String> head = new ArrayList<String>();
//		head.add("ID");
//		head.add("账号");
//		head.add("密码");
//		head.add("姓名");
//		head.add("性别");
//		head.add("班级");
//		head.add("电话");
//		head.add("状态");
//		head.add("备注");
//		head.add("迁出日期");
//		head.add("学院");
//		head.add("专业");
//		head.add("宿舍楼");
//		head.add("宿舍号");
//		head.add("");
//		head.add("");
//		head.add("");
//		head.add("是否班长");
//		head.add("");
//		List<StudentVo> studentVo = studentService.studentList(vo, -1, -1);
//		HSSFWorkbook wb = ExcelUtil.fillExcelDataWithHead(head, studentVo);

		List<StudentVo> studentVo = studentService.studentList(vo,
				(spage == null || "".equals(spage) ? -1 : (Integer.parseInt(spage) - 1) * Integer.parseInt(srows)),
				(srows == null || "".equals(srows) ? -1 : Integer.parseInt(srows)));
		HSSFWorkbook wb = ExcelUtil.fillExcelData(studentVo);

		ExcelUtil.writeExcel(response, wb, "学生基本信息表");

	}

	/*
	 * 缺寝记录
	 */
	@RequestMapping(value = "/lackList.do")
	public void lackList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);
		String s_name = request.getParameter("s_name");
		StudentVo vo = new StudentVo();
		vo.setStudent_name(s_name);
		List<StudentVo> list = studentService.studentLackList(vo,
				(spage == null || "".equals(spage) ? -1 : (Integer.parseInt(spage) - 1) * Integer.parseInt(srows)),
				(srows == null || "".equals(srows) ? -1 : Integer.parseInt(srows)));
		JSONArray array = JSONArray.fromObject(list);
//		int total = studentService.studentLackCount();
		int total = list.size();

		JSONObject obj = new JSONObject();
		obj.put("rows", array);
		obj.put("total", total);

		response.getWriter().write(obj.toString());

	}

	@RequestMapping(value = "/exportLackStudent.do")
	public void exportLackStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");

		String s_name = request.getParameter("s_name");
		StudentVo vo = new StudentVo();
		vo.setStudent_name(s_name);

		List<StudentVo> list = studentService.studentLackList(vo,
				(spage == null || "".equals(spage) ? -1 : (Integer.parseInt(spage) - 1) * Integer.parseInt(srows)),
				(srows == null || "".equals(srows) ? -1 : Integer.parseInt(srows)));
		HSSFWorkbook wb = ExcelUtil.fillExcelData(list);

		ExcelUtil.writeExcel(response, wb, "学生缺寝记录");

	}

	@RequestMapping(value = "/inDorm.do")
	public void inDorm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String building_id = request.getParameter("building_id");
		String dorm_id = request.getParameter("dorm_id");
		String student_userName = request.getParameter("student_userName");
		JSONObject obj = new JSONObject();

		// 如果页面不输入东西，用户直接点击确定入住，就会进入if
		if ("".equals(building_id) || building_id == null) {
			obj.put("errorMsg", "请先选择楼宇！");
			response.getWriter().write(obj.toString());
			return;
		}
		if ("".equals(dorm_id) || dorm_id == null) {
			obj.put("errorMsg", "请先选择或手动填写宿舍！");
			response.getWriter().write(obj.toString());
			return;
		}
		if ("".equals(student_userName) || student_userName == null) {
			obj.put("errorMsg", "请先填写学生学号！");
			response.getWriter().write(obj.toString());
			return;
		}

		StudentVo vo = new StudentVo();
		vo.setStudent_userName(student_userName);
		/* 根据学号查询出全部的List，并看看是否入住 */
		vo = studentService.findStudent(vo);

		/* 根据学号查询出其对应的id，用于入住操作 */
		if (vo == null) { // 说明已经入住
			obj.put("errorMsg", "学生学号不存在，请重新输入！");

		} else {
			if ("入住".equals(vo.getStudent_state())) {
				obj.put("errorMsg", "该学生已入住！不能重复入住！");
			} else if ("迁出".equals(vo.getStudent_state())) {
				obj.put("errorMsg", "该学生处于已迁出状态，不能入住！");
			} else if ("未入住".equals(vo.getStudent_state())) {
				Dorm dorm = new Dorm();
				dorm.setDorm_id(Long.parseLong(dorm_id));
				dorm = dormService.findDormById(dorm.getDorm_id());
				int canLiveNum = 0;
				switch(dorm.getDorm_type().charAt(0)){
			    case '二' :
			    	canLiveNum = 2;
			    	break;
			    case '四' :
			    	canLiveNum = 4;
			    	break;
			    case '六' :
		    		canLiveNum = 6;
		    		break;
			    case '八' :
		    		canLiveNum = 8;
		    		break;
				}
				
				if (dorm.getDorm_people_num()>=canLiveNum ) {
					obj.put("errorMsg", "宿舍人數達到上限，不能入住！");
				}else {
				
				if (studentService.inDorm(vo, dorm)) {
					dormService.addPeople(dorm.getDorm_id());
					obj.put("success", "true");
				}
				}
			}
		}

		response.getWriter().write(obj.toString());

	}
}
