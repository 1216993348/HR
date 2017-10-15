<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String updateStatus = basePath + "pages/back/admin/member/MemberServletBack/updateStatus" ;
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" href="css/member.css">
    <script type="text/javascript" src="js/member.js"></script>
    <script type="text/javascript" src="js/util.js"></script>
    <base href="<%=basePath%>">
</head>
<body>
    <form action="<%=updateStatus%>">
        <table border="1" cellpadding="5" cellspacing="0" width="650px" bgcolor="#F0F0F0" id="table">
            <tr>
                <td>用户ID</td>
                <td>${member.mid}</td>
                <td>头像</td>
                </tr>
            <tr>
                <td>真实姓名</td>
                <td>${member.mname}</td>
                <td rowspan="5"><img src="upload/member/${member.mphoto}" id="preview"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td>${member.mphone}</td>
            </tr>
            <tr>
                <td>用户住址</td>
                <td>${member.maddress}</td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>${member.mname}</td>
            </tr>
            <tr>
                <td>用户状态</td>
                <td>
                    <select name = "type">
                        <option value="lock"${member.mstatus == 0?"selected":""} >锁定</option>
                        <option value="active"${member.mstatus == 1?"selected":""} >激活</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="hidden" value="${member.mid}" name="ids">
        <input type="submit" value="修改用户状态">
        <input type="reset" value="重置">
    </form>
</body>
