<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath + "pages/back/admin/item/ItemServletBack/insert";
    String updateUrl = basePath + "pages/back/admin/item/ItemServletBack/update?x=x";
    String deleteUrl = basePath + "pages/back/admin/item/ItemServletBack/delete?x=x";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>商品类型列表</title>
    <link rel="stylesheet" type="text/css" href="css/item.css">
    <script type="text/javascript" src="js/item.js"></script>
    <script type="text/javascript" src="js/util.js"></script>
</head>
<body>
<h1>商品类型列表</h1>
<table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
    <tr align="left">
        <th>全选<input type="checkbox" onclick="checkboxAll(this,'tiid')" id="allCheck"></th>
        <th>商品类型</th>
        <th>操作</th>
    </tr>
    <c:if test="${allItems != null}" var="res">
        <c:forEach items="${allItems}" var="item">
            <tr>
                <td><input type="checkbox" id="tiid"onclick="check(this,'allCheck','tiid')" value="${item.iid}"></td>
                <td><input type="text" value="${item.ititle}" id="item-${item.iid}" name="item-${item.iid}">
                    <span id="item-${item.iid}Msg"></span>
                </td>
                <td><input type="button" name="" value="修改" onclick="validateItitle('${item.iid}')"></td>
           </tr>
        </c:forEach>
    </c:if>
</table>
<input type="button" onclick="delete_ids('<%=deleteUrl%>','ids','tiid')"  value="删除">
<form id="updateForm" method="post" action="<%=updateUrl%>">
    <input type="hidden" id="iid" name="iid">
    <input type="hidden" id="ititle" name="ititle">
</form>
</body>
</html>
