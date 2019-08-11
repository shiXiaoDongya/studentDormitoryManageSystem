package team.sdm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import team.sdm.service.IStudentService;

@Controller
public class ViewController {
//	@RequestMapping("/login.do")
//	public String login(HttpServletRequest request, HttpServletResponse response) {
//		return "login";
//	}
	@RequestMapping("/loginForm.do")
	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	@RequestMapping("/main.do")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		return "main";
	}
}
