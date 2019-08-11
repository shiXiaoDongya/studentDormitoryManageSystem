<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
    function addUser(){
    	window.location.href="${pageContext.request.contextPath}/addUser.do";
    }
</script>
</head>
<body>
	<button onclick="addUser()">增加用户</button>
    <table>
        <tr>
           <th>编号:</th> <th>用户名:</th> <th>密码:</th>
           <th>真实姓名:</th>  <th>邮箱:</th>
        </tr>
        <c:forEach items="${users}" var="user">
          <tr>
           <td>${user.id}</td> <td>${user.name}</td> <td>${user.password}</td>
           <td>${user.fullname}</td>  <td>${user.email}</td>
           <td><a href="${pageContext.request.contextPath}/updateUser.do?id=${user.id }">修改</a>  |  <a href="${pageContext.request.contextPath}/deleteUser.do?id=${user.id}">删除</a></td>
        </tr>
        </c:forEach>
     </table>
</body>
</html>