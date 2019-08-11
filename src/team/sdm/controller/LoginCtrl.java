package team.sdm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import team.sdm.po.Loginlog;
import team.sdm.po.Student;
import team.sdm.po.Teacher;
//import team.sdm.po.System;
import team.sdm.service.IStudentService;
import team.sdm.service.ISystemService;
import team.sdm.service.ITeacherService;
import team.sdm.util.IPUtils;
import team.sdm.util.IpUtil;

@Controller
public class LoginCtrl {
       @Autowired
       private IStudentService studentService;
       @Autowired
       private ITeacherService teacherSerivce;
       @Autowired
       private ISystemService systemService;
   	   @RequestMapping("/login.do")
       public String login(HttpServletRequest request,HttpServletResponse response) {
    	   /***
    	    * 第一种参数绑定 servlet api
    	    */
		   String name =request.getParameter("userName");
		   String password = request.getParameter("userPass");
		   String flag = request.getParameter("flag");
		   String ip = IpUtil.getIp(request);
		   System.out.println(ip);
		   HttpSession session = request.getSession();
		   if("".equals(name) || name == null || "".equals(password) || password == null) {
			   request.setAttribute("msg", "用户名或者密码为空");
			   return "login";
		   }else {
			   if(flag.equals("student")) {
				   //普通学生登录
				   Student student = studentService.login(name,password);
				   if(student != null) {
					   if(student.getStudent_state().equals("迁出")) {
						   //迁出学生不能登录
						   request.setAttribute("errorMessage", "迁出");
						   return "login";
					   }
					   if(student.getStudent_state().equals("未入住")){
						   //未入住学生不能登录
						   request.setAttribute("errorMessage", "未入住");
						   return "login";
					   }
					   session.setAttribute("realName",student.getStudent_name());
					   if("是".equals(student.getStudent_headFlag())) {
						   //登录学生为班级负责人
						   session.setAttribute("studentId", student.getStudent_id());
						   session.setAttribute("loginFlag", "vipStudent");
						   session.setAttribute("userName", student.getStudent_userName());
						   session.setAttribute("loginId", student.getStudent_id());
					   }else{
						   //登录学生为普通学生
						   session.setAttribute("studentId", student.getStudent_id());
						   session.setAttribute("loginFlag", "student");
						   session.setAttribute("userName", student.getStudent_userName());
						   session.setAttribute("loginId", student.getStudent_id());
					   }
					   //登录成功
					   addLog(ip,null,student.getStudent_id(),student.getStudent_name());
					   return "main";
				   }else {
					   //数据库查询不到结果，账号或密码错误，返回重新登录
					   request.setAttribute("errorMessage", "用户名或密码错误");
					   return "login";
				   }
			   }else if(flag.equals("teacher")){
				   //楼宇管理员登录
				   Teacher teacher = teacherSerivce.login(name,password);
				   if (teacher==null) {
					   //数据库查询不到结果，账号或密码错误，返回重新登录
						request.setAttribute("errorMessage", "用户名或密码错误");
						return "login";
				   }else {
					   session.setAttribute("realName", teacher.getTeacher_name());
					   session.setAttribute("loginFlag", "teacher");
					   session.setAttribute("teacherId", teacher.getTeacher_id());
					   session.setAttribute("userName", teacher.getTeacher_userName());
					   session.setAttribute("loginId", teacher.getTeacher_id());
					   addLog(ip,null,teacher.getTeacher_id(),teacher.getTeacher_name());
					   return "main";
				   }
			   }else {
				   //系统管理员登录
				   team.sdm.po.System system = systemService.login(name,password);
				   if (system==null) {
					   //数据库查询不到结果，账号或密码错误，返回重新登录
						request.setAttribute("errorMessage", "用户名或密码错误");
						return "login";
				   }else {
					   session.setAttribute("loginFlag", "system");
					   session.setAttribute("realName", system.getSystem_userName());
					   session.setAttribute("systemId", system.getSystem_id());
					   session.setAttribute("userName", system.getSystem_userName());
					   session.setAttribute("loginId", system.getSystem_id());
					   addLog(ip,null,system.getSystem_id(),system.getSystem_userName());
					   return "main";
				   }
			   }
		   }
       }
//	   @RequestMapping("/login.do")
//	   public void login(String name,String password) {
//		   /**
//		    * 第二种参数绑定 直接在方法参数列表中添加 前端传入的参数 ，参数类型必须一致 参数名称必须一致
//		    */
//		   System.out.println(name+"  \t"+password);
//	   }
//	@RequestMapping("/login.do")
//  	public void login(User user) {
//		System.out.println(user.getName()+"  \t"+user.getPassword());
//	}
	private void addLog(String iP2, String iPAdress, long id, String name) {
		// TODO Auto-generated method stub
		Loginlog log = new Loginlog();
		log.setIPaddress(iPAdress);
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		log.setLoginDate(dateFormat.format(date));
		log.setLoginIP(iP2);
		log.setUserId(id);
		log.setUserName(name);
		int count = systemService.addLog(log);
	}
}
