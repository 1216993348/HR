<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String updateActiveUrl = basePath + "pages/back/admin/member/MemberServletBack/updateStatus?type=active";
    String updateLockUrl = basePath + "pages/back/admin/member/MemberServletBack/updateStatus?type=lock";
    String showUrl = basePath + "pages/back/admin/member/MemberServletBack/show";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>雇员列表</title>
    <link rel="stylesheet" type="text/css" href="css/member.css">
    <script type="text/javascript" src="js/member.js"></script>
    <script type="text/javascript" src="js/util.js"></script>
</head>
<body>
<h1>雇员列表</h1>
<div id="splitSearchDiv">
    <jsp:include page="/pages/split_page_plugin_search.jsp"/>
    <br>
</div>
<table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
    <tr align="left">
        <th><input type="checkbox" onclick="checkboxAll(this,'mid')" id="allCheck"></th>
        <th>用户名</th>
        <th>姓名</th>
        <th>电话</th>
        <th>注册日期</th>
        <th>状态</th>
    </tr>
    <c:if test="${allMembers != null}" var="res">
        <c:forEach items="${allMembers}" var="member">
            <tr>
                <td><input type="checkbox" id="mid" onclick="check(this,'allCheck','mid')" value="${member.mid}"></td>
                <td><a href="<%=showUrl%>?mid=${member.mid}">${member.mid}</a></td>
                <td>${member.mname}</td>
                <td>${member.mphone}</td>
                <td>${member.mregdate}</td>

                <c:if test="${ member.mstatus == 0}" var="res">
                    <td>锁定</td>
                </c:if>
                <c:if test="${ member.mstatus == 1}" var="res">
                    <td>已激活</td>
                </c:if>
                <c:if test="${ member.mstatus == 2}" var="res">
                    <td>未激活</td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
</table>
<input type="button" onclick="update_ids('<%=updateActiveUrl%>','ids','mid')" value="批量激活">
<input type="button" onclick="update_ids('<%=updateLockUrl%>','ids','mid')" value="批量锁定">
<div id="splitBar">
    <jsp:include page="/pages/split_page_plugin_bar.jsp"/>
</div>
</body>
</html>
