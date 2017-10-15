<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String updateUrl = basePath + "pages/front/member/MemberInfoServletFront/update" ;
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
<jsp:include page="/pages/header.jsp"/>
    <form action="<%=updateUrl%>" method="post" onsubmit="return validateUpdate()" enctype="multipart/form-data">
        <table border="1" cellpadding="5" cellspacing="0" width="650px" bgcolor="#F0F0F0" id="table">
            <tr >
                <td>用户ID</td>
                <td colspan="2">${member.mid}</td>
                <td>头像</td>
                </tr>
            <tr>
                <td>真实姓名</td>
                <td width="10%"><input type="text" value="${member.mname}" id="mname" name="mname" onblur="validateMname()"></td>
                <td width="100px"><span id="mnameMsg">数据不能为空！</span></td>
                <td rowspan="5"><div id="preview" ><img src="upload/member/${member.mphoto}" class="img"></div></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" value="${member.mphone}" id="mphone" name="mphone" onblur="validateMphone()"></td>
                <td><span id="mphoneMsg">数据不能为空！</span></td>
            </tr>
            <tr>
                <td>用户住址</td>
                <td><input type="text" value="${member.maddress}" id="maddress" name="maddress" onblur="validateMaddress()"></td>
                <td><span id="maddressMsg">数据不能为空！</span></td>
            </tr>
            <tr>
                <td>头像上传</td>
                <td><input type="file" onchange="preview(this)" name="mphoto" id="mphoto"></td>
            </tr>
        </table>
        <input type="hidden" value="${member.mphoto}" name="oldPhoto">
        <input type="submit" value="修改">
        <input type="reset" value="重置" onclick="resetPhoto('member/${member.mphoto}')">
    </form>
<jsp:include page="/pages/footer.jsp"/>
</body>
