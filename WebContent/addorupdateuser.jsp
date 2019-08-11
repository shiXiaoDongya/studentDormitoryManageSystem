<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function(){
		if("" != "${msg}"){
			alert("${msg}");
		}
	}
</script>
</head>
<body>
     <form action="${pageContext.request.contextPath}/addOrUpdateUser.do" method="post">
     <table>
     	<input type="hidden" name="id" value="${user.id}">
     	<c:if test="${user != null}">
        <tr>
           <td>用户名</td>
           <td><input type="text" name="username" value="${user.name }"/></td>
        </tr>
        <tr>
           <td>密码</td>
           <td><input type="password" name="password" value="${user.password }"/></td>
        </tr>
        <tr>
            <td>真实姓名</td>
           	<td><input type="text" name="fullname" value="${user.fullname }"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
           	<td><input type="text" name="email" value="${user.email }"/></td>
        </tr>
        <tr>
           <td><input type="submit" value="提交"></td><td><input type="reset" value="重置"></td>
        </tr>
        </c:if>
        
        <c:if test="${user == null}">
		<tr>
           <td>用户名</td>
           <td><input type="text" name="username" /></td>
        </tr>
        <tr>
           <td>密码</td>
           <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>真实姓名</td>
           	<td><input type="text" name="fullname" /></td>
        </tr>
        <tr>
            <td>邮箱</td>
           	<td><input type="text" name="email" /></td>
        </tr>
        <tr>
           <td><input type="submit" value="提交"></td><td><input type="reset" value="重置"></td>
        </tr>
        </c:if>
        
     </table>
     </form>
</body>
</html>