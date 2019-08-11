<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>校园宿舍管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<style type="text/css">
	</style>
	<script>
		window.onload = function(){
			if("" != "${msg }"){
				alert("${msg}");
			}
		}
		
	</script>
  </head>
  
  <body>
  <div >
    <div id="header">
		<div class="header_title">
			<span class="title_con">校园宿舍管理系统</span>
		</div>
	</div>
	<!--  style="background-image: url(image/pic16.jpg); background-size: 100% 100%;  -->
	<div id="content" ">
		<center>
			<div class="con">
			<div class="con_title">
				<span class="con_title_sp">欢迎登录校园宿舍管理系统</span>
			</div>
			<div class="con_border_bottom"></div>
			<div class="con_panel">
				<form id="form" method="post" action="${pageContext.request.contextPath}/login.do">
					<div class="con_input">
						<span>用户名：</span>
						<input type="text" id="userName" name="userName" placeholder="学号/工号"/>
					</div>
					<div class="con_input">
						<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
						<input type="password" id="userPass" name="userPass" placeholder="密码"/>
					</div>
					<div class="con_select">
						<input type="radio" name="flag" id="flag" value="student" checked="checked"/>学生
						<input type="radio" name="flag" id="flag" value="teacher" />楼宇管理员
						<input type="radio" name="flag" id="flag" value="system" />系统管理员
					</div>
					<input type="submit" value="登    录" class="submit-btn" style="cursor:hand;"/>
					<div>
						<ul class="errorMessage" style="color:red">
						<c:if test="${errorMessage == '迁出'}">
							<span>该学生处于迁出状态，不能登录本系统！</span>
						</c:if>
						<c:if test="${errorMessage == '未入住'}">
							<span>该学生处于未入住状态，请联系管理员入住！</span>
						</c:if>
						<c:if test="${errorMessage == '用户名或密码错误'}">
							<span>用户名或密码错误！</span>
						</c:if>
						</ul>
					</div>
				</form>
			</div>
		</div>
		</center>
	</div>
	
	<div id="footer">
		<center>
			© 版权所有：<a href="http://www.zhku.edu.cn/" target="_blank">仲恺农业工程学院</a>&nbsp; &nbsp; QQ:123456789
		</center>
	</div>
   </div>
  </body>
</html>
