package team.sdm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import team.sdm.po.Building;
import team.sdm.po.ChangeDormVo;
import team.sdm.po.Dorm;
import team.sdm.po.Student;
import team.sdm.po.Student_lack;
import team.sdm.po.Tab_notice;
import team.sdm.service.IBuildingService;
import team.sdm.service.IDormService;
import team.sdm.service.INoticeService;
import team.sdm.service.IStudentService;
import team.sdm.util.ExcelUtil;
import team.sdm.util.ResponseUtil;

@Controller
public class StudentCtrl {
	@Autowired
    private IStudentService studentService;
	
	@Autowired
	private INoticeService noticeService;
	
	@Autowired
	private IDormService dormService;
	
	@Autowired
	private IBuildingService buildingService;
	
	/*
	 * 学生端-获取缺勤记录列表
	 */
	@RequestMapping("/getlacklist.do")
	public void getLackList(HttpServletRequest request,HttpServletResponse response) {
		//前端easyui-datagrid会默认将第page页，每页rows行传到后台
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//获取模糊搜索的输入值
		String s_name = request.getParameter("s_name");
		
		//后台获取分页的缺勤记录
		List<Student_lack> s_l = studentService.getStudentLackList(s_name, Integer.parseInt(page),Integer.parseInt(rows));
		
		//获取总条数，给前端分页用
		int total = studentService.getStudentLackCount();
		
		JSONObject result = new JSONObject();
		try {
			//将List转化为JSON数组
            JSONArray jsonArray = JSONArray.fromObject(s_l);
            
            //将所需数据丢入JSON中
            result.put("rows", jsonArray);
            result.put("total", total);
            
            //利用工具类将JSON数据返回前端
            ResponseUtil.write(response,result);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
	}
//	@RequestMapping("/checkIfOk.do")
//	public void checkIfOk(HttpServletRequest request,HttpServletResponse response) {
//		String StudentId = request.getParameter("student_id");
//		String trueFlag = null;
//		Student_lack stu_lack = studentService.getStudent_lack(Long.parseLong(StudentId));
//		List<String> list = studentService.qryIfOfFlag(stu_lack.getStudent_name());
//		if(list == null){
//			list = new ArrayList<String>();
//		}
//		for(String str : list){
//			if(str != null && !str.equals("是")){
//				trueFlag = "true";
//			}
//		}
//		System.out.println("checkIn");
//		System.out.println(StudentId);
//	}
	
	/*
	 * 学生端-缺勤确认
	 */
	@RequestMapping("/updataLack.do")
	public void updateLack(HttpServletRequest request,HttpServletResponse response) {
		//获取前端被勾选的学生id
		String StudentId = request.getParameter("student_id");
		
		//创建JSON对象
		JSONObject result=new JSONObject();
		
		int count = 0;
		if(StudentId != null){
			//修改"是否确认"状态
			count = studentService.updateLack(Long.parseLong(StudentId));
		}
		if(count > 0){
			result.put("success","true");
		}else{
			result.put("success","true");
			result.put("errorMsg","确认缺寝信息失败！！");
		}
		try {
			//返回数据库修改情况
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addNotice.do")
	public void addNotice(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result=new JSONObject();
		
		//获取前端传来的需要发送的信息数据，保存到Tab_notice中
		Tab_notice notice = new Tab_notice();
		notice.setTitle(request.getParameter("notice.title"));
		notice.setContent(request.getParameter("notice.content"));
		notice.setSend_userName(request.getParameter("notice.send_userName"));
		notice.setSend_person(request.getParameter("notice.send_person"));
		notice.setSend_role(request.getParameter("notice.send_role"));
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		notice.setSend_time(dateFormat.format(date));
		if(!("".equals(request.getParameter("notice.rec_userName")) || request.getParameter("notice.rec_userName") == null)) {
			notice.setRec_userName(request.getParameter("notice.rec_userName"));
		}
		notice.setRec_role(request.getParameter("notice.rec_role"));
		notice.setMsg_type(request.getParameter("notice.msg_type"));
		notice.setGuid(UUID.randomUUID().toString());
		
		//将Tab_notice数据插入表中
		int count = noticeService.addNotice(notice);
		if(count > 0){
			result.put("success","true");
		}else{
			result.put("success","true");
			result.put("errorMsg","发送失败！！");
		}
		try {
			//返回数据库插入情况
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getNoticeList.do")
	public void getNoticeList(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("in");
		//前端easyui-datagrid会默认将第page页，每页rows行传到后台
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//获取登录用户的用户名
		Tab_notice notice = new Tab_notice();
		notice.setSend_userName(request.getParameter("send_userName"));
		
		//查询登录用户的所有申请
		List<Tab_notice> noticeList = noticeService.getNoticeList(notice,Integer.parseInt(page),Integer.parseInt(rows));
		
		//获取总条数，给前端分页用
		int total = noticeService.getNoticeCount(notice);
		
		JSONObject result = new JSONObject();
		try {
			//将List转化为JSON数据
            JSONArray jsonArray = JSONArray.fromObject(noticeList);
            
            //将所需要数据丢入JSON对象
            result.put("rows", jsonArray);
            result.put("total", total);
            
            //将JSON对象返回给前端
            ResponseUtil.write(response, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
	}
	
	@RequestMapping("/export.do")
	public void export(HttpServletRequest request,HttpServletResponse response) {
		//前端easyui-datagrid会默认将第page页，每页rows行传到后台
		
		//获取模糊搜索的输入值
		String s_name = request.getParameter("s_name");
		System.out.println();
		
		//获取所有缺勤记录
		List<Student_lack> s_l = studentService.getAllStudentLack();
		HSSFWorkbook wb = ExcelUtil.fillExcelData(s_l);
		try {
			ResponseUtil.export(response, wb, "利用模版导出excel.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/saveLack.do")
	public void saveLack(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Student_lack stuLack = new Student_lack();
		stuLack.setStudent_name(request.getParameter("vo.student_name"));
		stuLack.setStudent_sex(request.getParameter("vo.student_sex"));
		stuLack.setStudent_institution(request.getParameter("vo.student_institution"));
		stuLack.setStudent_major(request.getParameter("vo.student_major"));
		stuLack.setStudent_class(request.getParameter("vo.student_class"));
		stuLack.setStudent_building(request.getParameter("vo.student_building"));
		stuLack.setStudent_dorm(request.getParameter("vo.student_dorm"));
		stuLack.setStudent_lackBeginTime(request.getParameter("vo.student_lackBeginTime"));
		stuLack.setStudent_lackEndTime(request.getParameter("vo.student_lackEndTime"));
		stuLack.setStudent_remark(request.getParameter("vo.student_remark"));
		stuLack.setStudent_ifOk("否");
		
		
		String lackFlag = request.getParameter("lackFlag");
		String studentId = request.getParameter("id");
		
		
		int flag=0;
		try{
			// 如果缺寝标识 =1
			if(lackFlag != null && Integer.parseInt(lackFlag) == 1){
				int count = studentService.updateLackFlag(Long.parseLong(studentId));
				if(count >0) {
					System.out.println("成功");
				}else {
					System.out.println("失败");
				}
			}
			flag=studentService.addStudentLack(stuLack);
			
			JSONObject result=new JSONObject();
			if(flag>0){
				result.put("success","true");
			}else{
				result.put("success","true");
				result.put("errorMsg","保存失败！！");
			}
			ResponseUtil.write(response, result);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/qryIfOfFlag.do")
	public void qryIfOfFlag(HttpServletRequest request,HttpServletResponse response) {
		String rec_userName = request.getParameter("rec_userName");
		System.out.println(rec_userName);
		List<String> list = null;
		String trueFlag = null;
		
		try {
			if(rec_userName != null){
				list = studentService.qryIfOfFlag(rec_userName);
				if(list == null){
					list = new ArrayList<String>();
				}
				for(String str : list){
					if(str != null && !str.equals("是")){
						trueFlag = "true";
					}
				}
				ResponseUtil.write(response, trueFlag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/qryNotSeeNoticeListByTeacher.do")
	public void qryNotSeeNoticeListByTeacher(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		HttpSession session=request.getSession();
		String rec_userName = (String) session.getAttribute("userName");
		String rec_role = (String) session.getAttribute("loginFlag");
		String see_state= request.getParameter("see_state");
		String send_person = request.getParameter("send_person");
		Tab_notice notice = new Tab_notice();
		if("".equals(see_state) || see_state==null) {
			notice.setSee_state("否");
		}else {
			notice.setSee_state(see_state);
		}
		System.out.println(rec_userName);
		System.out.println(rec_role);
		notice.setSend_person(send_person);
		notice.setRec_userName(rec_userName);
		notice.setRec_role(rec_role);
		try {
			List<Tab_notice> noticeList = noticeService.getNoticeList(notice, Integer.parseInt(page), Integer.parseInt(rows));
			System.out.println(noticeList);
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(noticeList);
			int total = noticeService.getNoticeCount(notice);
			System.out.println("---------------"+total);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/qryStudentChangeDorm.do")
	public void qryStudentChangeDorm(HttpServletRequest request,HttpServletResponse response) {
		String stu_userName = request.getParameter("student_userName");
		String guid = request.getParameter("guid");
		Student tempstu = new Student();
		tempstu.setStudent_userName(stu_userName);
		Student stu = studentService.getStudent(tempstu);
		Dorm dorm = dormService.getDorm(stu.getStudent_id());
		ChangeDormVo vo = new ChangeDormVo();
		vo.setStudent_id(stu.getStudent_id());
		vo.setDorm_id(dorm.getDorm_id());
		vo.setStudent_userName(stu.getStudent_userName());
		vo.setStudent_name(stu.getStudent_name());
		vo.setStudent_sex(stu.getStudent_sex());
		vo.setBuilding_name(stu.getStudent_building());
		vo.setDorm_name(dorm.getDorm_name());
		vo.setGuid(guid);
		System.out.println(dorm);
		JSONArray array = JSONArray.fromObject(vo);
		try {
			ResponseUtil.write(response, array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryAllBuilding_changeDorm.do")
	public void queryAllBuilding_changeDorm(HttpServletRequest request,HttpServletResponse response) {
		List<Building> buildingList = buildingService.getBuildingList();
		String html="<select id=\"building_id\" name=\"fixVo.building_id\" style=\"width:200px;font-size:12\" class=\"easyui-combobox\" onchange=\"changeBuilding_changeDorm(this.options[this.selectedIndex].value)\">";
		html+="<option value=\"\" selected=\"selected\" class=\"easyui-combobox\" style=\"width:169px;\">====全部====</option>";
		System.out.println(html);
		if(buildingList!=null&buildingList.size()>0){
			for(Building b : buildingList){
				html+="<option value='"+b.getBuilding_id()+"'>"+b.getBuilding_name()+"</option>";
			}
		}
		html+="</select>";
		try {
			ResponseUtil.write(response,html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryDormByBuilding_changeDorm.do")
	public void queryDormByBuilding_changeDorm(HttpServletRequest request,HttpServletResponse response) {
		String building_id=request.getParameter("building_id");
		try{
			System.out.println("building_id======"+building_id);
			List<Dorm> list=dormService.queryDormByBuilding(Long.parseLong(building_id));
			System.out.println(list);
			String html="<select id=\"dorm_id\" name=\"fixVo.dorm_id\" class=\"easyui-combobox\" style=\"width:200\"";
			html+="<option value=\"\">====全部====</option>";
			for(Dorm dorm:list) {
				html+="<option value=\""+dorm.getDorm_id()+"\">"+dorm.getDorm_name()+"</option>";
			}
			
			html+="</select>";
			ResponseUtil.write(response,html);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@RequestMapping("/change.do")
	public void change(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String student_id=request.getParameter("fixVo.student_id");
		String dorm_id = request.getParameter("fixVo.dorm_id");
		String building_id = request.getParameter("fixVo.building_id");
		String remark = request.getParameter("fixVo.student_remark");
		String old_dorm_id = request.getParameter("dorm_id");
		String guid=request.getParameter("guid");
		HttpSession session=request.getSession();
		String rec_userName = (String) session.getAttribute("userName");
		
		System.out.println(student_id);
		System.out.println(old_dorm_id);
		System.out.println(dorm_id);
		System.out.println(building_id);
		System.out.println(remark);
		System.out.println(guid);
		
		try {
			JSONObject result=new JSONObject();
			if("".equals(building_id) || building_id==null) {
				result.put("errorMsg","请先选择或手动填写楼宇！");
				ResponseUtil.write(response, result);
				return;
			}
			if("".equals(dorm_id) || dorm_id==null) {
				result.put("errorMsg","请先选择或手动填写楼宇！");
				ResponseUtil.write(response, result);
				return;
			}
			
			Dorm outDorm = dormService.getDorm(Long.parseLong(student_id));
			System.out.println(outDorm);
			//修改dorm表，使宿舍居住人数减一
			int deleteFlag = dormService.deleteDormPeople(outDorm);
			if(deleteFlag>0) {
				Dorm dorm= dormService.getNum(Long.parseLong(dorm_id));
				System.out.println(dorm.getDorm_people_num());
				System.out.println(dorm.getDorm_type().charAt(0));
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
				//判断可以调入，宿舍是否已经满人
				boolean canChange = (canLiveNum - dorm.getDorm_people_num() >= 1);
				if(canChange) {
					System.out.println("===========canChange============");
					//修改student_dorm表，宿舍号改为调去的宿舍号
					int count1 = studentService.changeStudentDorm(Long.parseLong(dorm_id),Long.parseLong(student_id));
					if(count1>0) {
						System.out.println("修改student_dorm表成功");
					}else {
						result.put("errorMsg","修改student_dorm表失败！");
						ResponseUtil.write(response, result);
						return;
					}
					//修改dorm表，使调去的宿舍居住人数加一
					int count2 = dormService.addPeople(Long.parseLong(dorm_id));
					if(count2>0) {
						System.out.println("修改dorm表成功");
					}else {
						result.put("errorMsg","修改dorm表失败！");
						ResponseUtil.write(response, result);
						return;
					}
					//修改student
					int count3 = studentService.changeStudentByChangeDorm(Long.parseLong(student_id),Long.parseLong(building_id),Long.parseLong(dorm_id),remark);
					if(count3>0) {
						System.out.println("修改student表成功");
					}else {
						result.put("errorMsg","修改student表失败！");
						ResponseUtil.write(response, result);
						return;
					}
					//修改tab_notice
					int count4 = noticeService.updateNoticeByChangeDorm(rec_userName,guid);
					if(count4>0) {
						System.out.println("修改tab_notice表成功");
					}else {
						result.put("errorMsg","修改tab_notice表失败！");
						ResponseUtil.write(response, result);
						return;
					}
					result.put("success", "true");
					ResponseUtil.write(response, result);		
				}else {
					result.put("errorMsg","该宿舍人数已满，请选择其他宿舍入住！");
					ResponseUtil.write(response, result);
				}
			}else {
				result.put("errorMsg", "调换寝室失败！");
				ResponseUtil.write(response, result);
			}
	    	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
