package team.sdm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.sdm.po.Building;
import team.sdm.po.Dorm;
import team.sdm.service.IBuildingService;
import team.sdm.service.IDormService;

@Controller
public class DormCtrl {
	private Logger logger = Logger.getLogger(DormCtrl.class);

	@Autowired
	private IDormService dormService;
	@Autowired
	private IBuildingService buildingService;
	 private Dorm dorm1;

	@RequestMapping(value = "/showdorm.do", method = RequestMethod.POST)
	public void showdorm(HttpServletRequest request, HttpServletResponse response) {
		/***
		 * 第1种方法servlet api
		 */
		System.out.println("进入showdorm.do");
		// String flag = request.getParameter("flag");
		// System.out.println("flag"+flag);
		response.setContentType("text/html;charset=UTF-8");
		String spage = request.getParameter("page");
		String srows = request.getParameter("rows");
		int page = Integer.parseInt(spage);
		int rows = Integer.parseInt(srows);

		String t_name = request.getParameter("t_name");
		// String dorm_building1 = "10栋";
		String dorm_building1 = request.getParameter("dorm_building1");
		System.out.println("t_name:" + t_name + " dorm_building1:" + dorm_building1);

		Dorm dorm1 = new Dorm();
		dorm1.setDorm_building(("".equals(dorm_building1) ? null : dorm_building1));
		dorm1.setDorm_name(("".equals(t_name) ? null : t_name));
		// 查询

		List<Dorm> dorms = dormService.showdorm(dorm1, (page - 1) * rows, rows);
		System.out.println("查到的dorm" + dorms);
		int total = dormService.dormCount1(dorm1);
		System.out.println("总数" + total);

		// int pageNum = total % rows == 0 ? (total / rows) : (total / rows) + 1;

		JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(dorms);
		obj.put("rows", array);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	// request.setAttribute("dorms", dorms);
	// return "dormmanage/dormlist.jsp";

	// /**
	// * ��
	// * */
	// @RequestMapping(value="/queryAllBuilding.do",method = RequestMethod.POST)
	// public void queryAllBuilding(HttpServletRequest request,HttpServletResponse
	// response){
	// try{
	// List<Dorm> dorms = dormService.showdorm(3,3);
	//
	// String html="<select id='dorm_building' name='vo.dorm_building'
	// style='width:150' class='easyui-combobox' required='true'>";
	// html+="<option value='' selected='selected'>==ȫ��¥��==</option>";
	//// Dorm bVo=null;
	//// if(dorms!=null&dorms.size()>0){
	//// for(int i=0;i<dorms.size();i++){
	//// bVo=dorms.get(i);
	//// html+="<option
	// value='"+bVo.getDorm_id()+"'>"+bVo.getDorm_name()+"</option>";
	//// }
	//// }
	// System.out.println("=====================" + dorms.size());
	// for(Dorm d : dorms) {
	// String option = "<option
	// value='"+d.getDorm_id()+"'>"+d.getDorm_building()+"</option>";
	// System.out.println(option);
	// html += option;
	// }
	// html+="</select>";
	// ResponseUtil.addVaryFieldName(response, html);
	// //ResponseUtil.write(ServletActionContext.getResponse(),html);
	// request.getSession().setAttribute("html",html);
	//
	// }catch(Exception ex){
	// ex.printStackTrace();
	// }
	//
	// }
	@RequestMapping(value = "/adddorm.do", method = RequestMethod.POST)
	public void adddorm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		/***
		 * 第一种方法servlet api
		 */
		Dorm dorm = new Dorm();
		System.out.println("进入adddorm.do");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String dorm_building = request.getParameter("vo.dorm_building");
		String dorm_name = request.getParameter("vo.dorm_name");
		String dorm_tel = request.getParameter("vo.dorm_tel");
		String dorm_type = request.getParameter("vo.dorm_type");
		dorm.setDorm_building(dorm_building);
		dorm.setDorm_name(dorm_name);
		dorm.setDorm_tel(dorm_tel);
		dorm.setDorm_type(dorm_type);

		JSONObject obj = new JSONObject();
		// 新添加的宿舍人数默认为0
		dorm.setDorm_people_num(0);
		System.out.println(dorm.toString());

		// ���
		// 添加
		boolean success = dormService.adddorm(dorm);
		if (success) {
			System.out.println("++++++++保存成功++++++++++++");
			obj.put("success", "true");
		} else {
			obj.put("errorMsg", "新增失败！！请核对信息是否符合规范或已存在。");
		}
		int total = dormService.dormCount1(dorm1);
		System.out.println("得到总数" + total);
		//
		// int pageNum = total % rows == 0 ? (total / rows) : (total / rows) + 1;

		// JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(success);
		obj.put("rows", array);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		// request.setAttribute("dorms", dorms);
		// return "dormmanage/dormlist.jsp";
	}

	// �޸�����
	@RequestMapping(value = "/editdorm.do", method = RequestMethod.POST)
	public void editdorm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***
		 * servlet api
		 */
		Dorm dorm = new Dorm();
		System.out.println("进入editdorm.do");
		request.setCharacterEncoding("UTF-8");
		// 获取dorm表单数据
		String dorm_id = request.getParameter("editId");
		// 由于获取不到dorm_building这里直接用id去数据库查
		int dormid = Integer.parseInt(dorm_id);
		dorm = dormService.finddormbuilding(dormid);
		System.out.println(dorm.toString());

		String dorm_name = request.getParameter("vo.dorm_name");
		String dorm_tel = request.getParameter("vo.dorm_tel");
		String dorm_type = request.getParameter("vo.dorm_type");
		logger.info("editdorm values(dorm_building:" + dorm.getDorm_building() + ", name:" + dorm_name + ", dorm_tel:"
				+ dorm_tel + ", dorm_type:" + dorm_type);
		int id = Integer.parseInt(dorm_id);
		dorm.setDorm_id(id);

		dorm.setDorm_name(dorm_name);
		dorm.setDorm_tel(dorm_tel);
		dorm.setDorm_type(dorm_type);
		System.out.println(dorm.toString());

		// ���
		// List<Dorm> dorms = dormService.showdorm((page - 1) * rows, rows);
		int count = dormService.editdorm(dorm);

		JSONObject result = new JSONObject();
		if (count > 0) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "����ʧ�ܣ�����˶���Ϣ�Ƿ���Ϲ淶���Ѵ��ڡ�");
		}
		response.getWriter().write(result.toString());
	}

	// 查询searcherdrom.do
	@RequestMapping(value = "/searcherdrom.do", method = RequestMethod.POST)
	public void searcherdrom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***
		 * 第一种方法servlet api
		 */

		System.out.println("进入searcherdrom.do");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取传进来的宿舍数组
		String buildingData1 = request.getParameter("#dorm_building1");
		String dormName = request.getParameter("t_name");
		logger.info("   get buildingData1:  " + buildingData1 + "     get dormName  " + dormName);

		// 查询
		Dorm searchdorm = dormService.searchdorm(buildingData1, dormName);
		System.out.println("查到的searchdorm" + searchdorm.toString());

		// 显示
		long dorm_id = searchdorm.getDorm_id();
		Dorm showSearchDorm = dormService.showSearchDorm(dorm_id);

		int total = dormService.dormCount();
		System.out.println("总数" + total);

		// int pageNum = total % rows == 0 ? (total / rows) : (total / rows) + 1;

		JSONObject obj = new JSONObject();
		obj.put("total", total);

		JSONArray array = JSONArray.fromObject(showSearchDorm);
		obj.put("rows", array);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	// 删除
	@RequestMapping(value = "/deletedorm.do", method = RequestMethod.POST)
	public void deletedorm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***
		 * 第一种方法servlet api
		 */

		System.out.println("进入deletedorm.do");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取传进来的宿舍数组
		String delIds = request.getParameter("delIds");
		logger.info("method: StudentController.delete id:" + delIds);
		// 安逗号分割数组

		boolean flag = false;
		String strid[] = delIds.split(",");
		int delNums = 0;
		if (strid != null) {
			delNums = strid.length;
			if (strid.length > 1) {// 多于1条的删除
				for (int i = 0; i < delNums; i++) {
					flag = dormService.deleteDorm(Long.parseLong(strid[i]));
				}
			} else {// 只有一条的删除
				flag = dormService.deleteDorm(Long.parseLong(delIds));
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

	@RequestMapping(value = "/queryDormByBuilding.do", method = RequestMethod.POST)
	public void queryDormByBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String pid = request.getParameter("building_id");
//		if ("".equals(pid) || pid == null) {
//			return;
//		}
//		List<Dorm> dorms = dormService.findDormByBuildingId(Long.parseLong(pid));
		request.setCharacterEncoding("UTF-8");
		String pname = request.getParameter("building_id");
		if ("".equals(pname) || pname == null) {
			return;
		}
		System.out.println(pname);
		List<Dorm> dorms = dormService.findDormByBuildingName(pname);

		String html = "<select id='dorm_id' name='fixVo.dorm_id' class='easyui-combobox' style='width:170'";
		html += "<option value=''>==全部==</option>";
		for (Dorm dorm : dorms) {

			html += "<option value='" + dorm.getDorm_id() + "'>" + dorm.getDorm_name() + "</option>";
		}
		html += "</select>";
		System.out.println(html);
		response.getWriter().write(html);
	}

	// 查询出全部的building列表
	@RequestMapping(value = "/queryAllBuilding1.do", method = RequestMethod.POST)
	public void queryAllBuilding1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Building> buildingList = buildingService.getBuildingList();
		String html = "<select id=\"dorm_building\" name=\"vo.dorm_building\" style=\"width:150\" class=\"easyui-combobox\" required=\"true\">";
		html += "<option value=\"\" selected=\"selected\">==全部楼宇==</option>";
		Building bVo = null;
		if (buildingList != null & buildingList.size() > 0) {
			for (int i = 0; i < buildingList.size(); i++) {
				bVo = buildingList.get(i);
				html += "<option value='" + bVo.getBuilding_name() + "'>" + bVo.getBuilding_name() + "</option>";
				// html+="<option value='"+bVo.getBuilding_name()+"</option>";
			}
		}
		html += "</select>";
		// ResponseUtil.write(ServletActionContext.getResponse(),html);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(html);
		;
	}

	@RequestMapping(value = "/queryAllBuilding2.do", method = RequestMethod.POST)
	public void queryAllBuilding2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Building> buildingList = buildingService.getBuildingList();

		String html = "<select id=\"dorm_building1\" name=\"dorm_building1\" style=\"width:80\" class=\"easyui-combobox\">";
		html += "<option value=\"\" selected=\"selected\">==楼宇==</option>";
		Building bVo = null;
		if (buildingList != null & buildingList.size() > 0) {
			for (int i = 0; i < buildingList.size(); i++) {
				bVo = buildingList.get(i);
				html += "<option value='" + bVo.getBuilding_name() + "'>" + bVo.getBuilding_name() + "</option>";
				// html+="<option value='"+bVo.getBuilding_name()+"</option>";
			}
		}
		html += "</select>";
		// ResponseUtil.write(ServletActionContext.getResponse(),html);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(html);

	}

}
