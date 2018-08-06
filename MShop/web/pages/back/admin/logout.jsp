<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "pages/back/login.jsp" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>雇员管理程序</title>
	<base href="<%=basePath%>">
	<link type="text/css" rel="stylesheet" href="css/mldn.css">
	<script type="text/javascript" src="js/mldn.js"></script>
</head>
<%
	request.setCharacterEncoding("UTF-8") ;
	response.setCharacterEncoding("UTF-8") ;
	session.invalidate() ;	// 注销
%>

<script type="text/javascript">
	window.alert("用户注销成功，欢迎再来！") ;
	window.parent.location.href = "<%=loginUrl%>" ;
</script>
</html>
