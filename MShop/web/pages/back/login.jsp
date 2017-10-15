<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String checkUrl = basePath + "pages/back/AdminLoginServletBack/login";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>微商城后台管理程序</title>
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="css/login.css">
  </head>
  <body>
    <form action="<%=checkUrl%>" method="post" onsubmit="return validate()">
    	<div class="main">
    		<div class="title">微 商 城 后 台 登 陆</div>
    		<div class="input">
			<div class="user">
				<label class="item" for="aid">用户名:</label>
				<input type="text" class="new_txt" name="aid" id="aid" onblur="validateAid()">
				<span id="aidMsg" ></span>
			</div>
			<div class="password">
				<label class="item" for="password">密&nbsp;&nbsp;&nbsp;码:</label>
				<input type="password" class="new_txt" name="password" id="password"
				onblur="validatePassword()" >
				<span id="passwordMsg"></span>
			</div>

			<label class="item" for="code">验证码:</label>
				<input type="text" class="c_txt" name="code" id="code"
				onblur="validateCode()" maxlength="4">
				<span class="img">
				<img src="pages/image.jsp" onclick="changeCode(this)" >
				</span>
				<span id="codeMsg"></span>
			<div class="buttondiv">
					<input type="submit" value="登陆" class="button1">
					<input type="reset" value="取消" class="button2">

			</div>
		</div>
		</div>
    </form>
  </body>
</html>