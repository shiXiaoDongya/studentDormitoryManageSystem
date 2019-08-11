package team.sdm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.sdm.po.Building;
import team.sdm.po.Teacher;
import team.sdm.service.IBuildingService;
import team.sdm.service.ITeacherService;

@Controller
public class BuildingCtrl {
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private ITeacherService teacherService;

	/**
	 * 显示所有楼宇信息、搜索
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/buildinglist.do")
	public String getBuildings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//分页信息
		String spage = request.getParameter("page");
		System.out.println(spage);
		String srows = request.getParameter("rows");
		System.out.println(srows);
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);
		
		String search_name = request.getParameter("b_name");
		JSONObject result = new JSONObject();
		if (search_name == null || "".equals(search_name)) {
			// 查看所有信息
			List<Building> res = buildingService.getListByPage(page, rows);
			int total = buildingService.getTotalCount();
			JSONArray buildings = JSONArray.fromObject(res);
			result.put("rows", buildings);// All Building's List
			result.put("total", total);
		} else {
			// 查看搜索结果
			List<Building> resBuildings = buildingService.getSearchBuilding(search_name,page,rows);
//			int total = resBuildings.size();
			int total = buildingService.resBuildingCount(search_name);
			JSONArray searchRes = JSONArray.fromObject(resBuildings);
			result.put("rows", searchRes);
			result.put("total", total);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		return "buildingrmanage/buildinglist";// 跳转至JSP页面
	}

	/**
	 * 删除楼宇信息 @param request @param response @return @throws
	 */
	@RequestMapping("/deleteBuilding.do")
	public String deleteBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
					count = buildingService.deleteBuildingById(Integer.parseInt(id[i]));
				}
			} else {
				// 删除单条数据
				count = buildingService.deleteBuildingById(Integer.parseInt(strs));
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
		return "buildingrmanage/buildinglist";
	}

	/**
	 * 保存楼宇信息：添加或修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/saveBuilding.do")
	public String saveBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("building_id");
		String name = request.getParameter("building_name");
		String remark = request.getParameter("building_remark");
		JSONObject result = new JSONObject();
		// 判断添加信息或编辑信息
		if ("".equals(id) || id == null) {
			// id不存在：添加
			int count = buildingService.addBuilding(name, remark);
			if (count > 0) {
				result.put("success", "true");
			} else {
				result.put("errorMsg", "添加失败！");
			}
		} else {
			// id存在：编辑
			Building building = buildingService.getBuildingById(Integer.parseInt(id));
			if (building == null) {
				result.put("errorMsg", "此楼宇不存在,暂时无法编辑");
			} else {
				building.setBuilding_name(name);
				building.setBuilding_remark(remark);
				int count = buildingService.editBuilding(building);
				if (count > 0) {
					result.put("success", "true");
				} else {
					result.put("errorMsg", "编辑失败！");
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		return "buildingrmanage/buildinglist";
	}

	/**
	 * 获取管理员列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getAllManager.do")
	public String getAllManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();// 获取会话
		// 获取所有的管理员信息
		List<Teacher> teachers = teacherService.getTeacherList();
		session.setAttribute("Alllist", teachers);
		JSONObject result = new JSONObject();
		JSONArray teacherlist = JSONArray.fromObject(teachers);
		result.put("rows", teacherlist);// 返回所有管理员的JSON数据
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		return "buildingrmanage/buildinglist";
	}

	/**
	 * 获取对应楼宇的管理员id列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getBuildingManager.do")
	public String getManagerByBuildingId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("building_id");
		request.getSession().setAttribute("building_id", id);
		// 获取全部管理员
		List<Teacher> all = teacherService.getTeacherList();
		// 获取楼宇对应的管理员的id列表
		List<Long> teacherIds = teacherService.getTeacherByBuidingId(Integer.parseInt(id));
		List<Integer> rows = new ArrayList<Integer>();
		if (teacherIds.size() > 0) {
			for (int i = 0; i < all.size(); i++) {
				Teacher teacher = all.get(i);
				// 判断选择楼宇是否已经分配管理员
				if (teacherIds != null && teacherIds.size() > 0) {
					for (int j = 0; j < teacherIds.size(); j++) {
						// 判断该管理员是否为楼宇的管理员
						if (teacherIds.get(j).equals(teacher.getTeacher_id())) {
							rows.add(i);// 获取下标
						}
					}
				}
			}
		}
		JSONArray data = JSONArray.fromObject(rows);
		System.out.println(data.toString());
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
		return "buildingrmanage/buildinglist";
	}

	/**
	 * 设置管理员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/setBuildingManager.do")
	public String setBuildingManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");// 设置文本编码
		int count = 0;
		// 获取building_id
		String building_id = (String) request.getSession().getAttribute("building_id");
		// 获取参数teacherId_box
		String strs = request.getParameter("teacherId_box");
		// 分割teacherId_box，获取管理员id数组
		String ids[] = strs.split(",");
		System.out.println(ids.toString());
		// 通过业务层为对应的楼宇设置管理员
		if (ids != null && ids.length > 0) {
			count = buildingService.setBuildingManager(ids, Integer.parseInt(building_id));
		}
		// 设置JSON数据：result{success，errorMsg}
		JSONObject result = new JSONObject();
		if (count > 0) {
			result.put("success", "true");
		} else {
			result.put("errorMsg", "设置失败");
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		return "buildingrmanage/buildinglist";
	}

	@RequestMapping(value = "/queryAllBuilding.do")
	public void queryAllBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Building> buildingList = buildingService.getBuildingList();

		String html = "<select id='building_id' name='building_id' style='width:170' class='easyui-combobox' onchange='changeBuilding(this.options[this.selectedIndex].value)'>";
		html += "<option value='' selected='selected'>==全部==</option>";
		if (buildingList != null & buildingList.size() > 0) {
			for (Building building : buildingList) {
				html += "<option value='" + building.getBuilding_name() + "'>" + building.getBuilding_name()
						+ "</option>";
			}
		}
		html += "</select>";
		System.out.println(html);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(html);
	}
}
