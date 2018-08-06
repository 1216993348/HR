<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String register = basePath + "pages/front/MemberServletFront/register";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>

    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<div id="mainDiv">
    用户注册
    <form action="<%=register%>" method="post" onsubmit="validateRegister()">
        <table border="1">
            <tr>
                <td>用户名 </td>
                <td><input type="text" id="mid" name="mid" onblur="validateMid()"></td>
                <td width="120px"><span id="midMsg"></span></td>
            </tr>
            <tr>
                <td>密&nbsp;码</td>
                <td><input type="password" id="password" name="password" onblur="validatePassword()"></td>
                <td width="120px"><span id="passwordMsg"></span></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
                <td><input type="reset" value="重填"></td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
