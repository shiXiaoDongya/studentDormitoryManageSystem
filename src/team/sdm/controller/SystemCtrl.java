package team.sdm.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.sdm.util.ExcelUtil;
import team.sdm.util.ResponseUtil;
import team.sdm.po.Loginlog;
import team.sdm.po.Student;
import team.sdm.service.IStudentService;
import team.sdm.service.ISystemService;

@Controller
public class SystemCtrl {
	@Autowired
    private IStudentService studentService;
	
	@Autowired
    private ISystemService systemService;
	/**
	 * 更改密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updatePass.do")
	public void updatePass(HttpServletRequest request,HttpServletResponse response) {
		
		/*
		 * userPass:修改的新密码
		 * flag:修改密码的身份(student、teacher、system)
		 * loginId:修改密码的用户编号id
		 * inputOldPass:用户的原密码
		 */
		String userPass = request.getParameter("userPass");
		String flag = request.getParameter("flag");
		String loginId = request.getParameter("loginId");
		String inputOldPass = request.getParameter("inputOldPass");
		
		//如果身份为学生
		if("student".equals(flag)) {
			//根据编号id获取该学生的原密码与输入的原密码比较
			Student tempstu = new Student();
			tempstu.setStudent_id(Integer.parseInt(loginId));
			Student stu = studentService.getStudent(tempstu);
			if(!inputOldPass.equals(stu.getStudent_userPass())) {
				try {
					//与数据库里的原密码不匹配分，返回false
					ResponseUtil.write(response, false);
					return;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//修改密码
		int count = systemService.updatePass(flag,userPass,Long.parseLong(loginId));
		
		if(count > 0) {
			try {
				//修改成功的话将新密码返回给前端
				ResponseUtil.write(response, userPass);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 显示登录日志列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/loginloglist.do")
	public void getLoginlogList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取模糊搜索关键字
		String keyword = request.getParameter("t_name");
		//分页信息
		String spage = request.getParameter("page");
		System.out.println(spage);
		String srows = request.getParameter("rows");
		System.out.println(srows);
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);
		
		JSONObject result = new JSONObject();
		if ("".equals(keyword)||keyword==null) {
			//查看所有信息
			List<Loginlog> loginlogs = systemService.getAllLoginlog(page,rows);//获取列表
			int total = systemService.getTotalCount();//获取总数
			JSONArray list = JSONArray.fromObject(loginlogs);
			result.put("rows", list);
			result.put("total", total);
		}else {
			//根据关键字进行模糊搜索
			List<Loginlog> searchlist = systemService.getSearchList(keyword,page,rows);//搜索
			int total = systemService.getSearchTotalCount(keyword);
			JSONArray list = JSONArray.fromObject(searchlist);
			result.put("rows",list);
			result.put("total",total);
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
	/**
	 * 删除日志信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/deleteLoginlog.do")
	public void deleteLoginlog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");// 设置文本编码
		int count = 0;
		JSONObject result = new JSONObject();// JSON格式数据
		String strs = request.getParameter("delIds");
		String id[] = strs.split(",");// 获取id数据组
		if (id == null || id.length < 1) {
			result.put("errorMsg", "请选择需要删除的数据");
		} else {
			if (id.length > 1) {
				// 删除多条数据
				for (int i = 0; i < id.length; i++) {
					count = systemService.deleteLoginlogById(Integer.parseInt(id[i]));
				}
			} else {
				// 删除单条数据
				count = systemService.deleteLoginlogById(Integer.parseInt(strs));
			}
		}
		if (count > 0) {
			result.put("success", "true");
			result.put("delNums", id.length);
		} else {
			result.put("errorMsg", "删除失败");
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
	/**
	 * 修改用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/saveLoginlog.do")
	public void saveLoginlog(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取参数
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String loginDate = request.getParameter("loginDate");
		String loginIP = request.getParameter("loginIP");
		String iPaddress = request.getParameter("iPaddress");
		//定义JSON
		JSONObject result = new JSONObject();
		if ("".equals(id)||id==null) {
			//id为空
			result.put("errorMsg", "id不能为空！");
		}else {
			Loginlog loginlog = systemService.getLoginlogById(Integer.parseInt(id));
			if (loginlog==null) {
				result.put("errorMsg", "该条登录信息不存在！");
			}else {
				//修改
				loginlog.setUserName(userName);
				System.out.println(loginDate);
				loginlog.setLoginDate(loginDate);
				loginlog.setLoginIP(loginIP);
				loginlog.setIPaddress(iPaddress);
				int count = systemService.editLoginlog(loginlog);
				if (count>0) {
					result.put("success", "true");
				}else {
					result.put("errorMsg", "修改失败！");
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
	
	@RequestMapping("/exportLoginlog.do")
	public void exportLoginlog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//设置文本编码
		//获取模糊搜索关键字
		String keyword = request.getParameter("t_name");
		List<Loginlog> logs = null;
		if ("".equals(keyword)||keyword==null) {
			//导出所有信息
			int total = systemService.getTotalCount();
			logs = systemService.getAllLoginlog(1, total);
		}else {
			//导出搜索结果
			int total = systemService.getSearchTotalCount(keyword);
			logs = systemService.getSearchList(keyword, 1, total);
		}
		HSSFWorkbook wb = ExcelUtil.fillExcelData(logs);
		ExcelUtil.writeExcel(response, wb, "登录日志信息表");
		
	}
}
