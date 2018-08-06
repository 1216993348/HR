<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String updateUpUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=up" ;
    String updateDownUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=down" ;
    String updateDeleteUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=delete" ;
    String updateUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updatePre?gid=";
    String deleteUrl = basePath + "pages/back/admin/goods/GoodsServletBack/delete?x=x";
    String showGoodsUrl = basePath + "pages/front/goods/GoodsServletFront/show";
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
<div id="splitSearchDiv">
    <jsp:include page="/pages/split_page_plugin_search.jsp"/>
    <br>
</div>
<table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
    <tr align="left">
        <th>全选<input type="checkbox" onclick="checkboxAll(this,'gid')" id="allCheck"></th>
        <th>图片</th>
        <th>商品名称</th>
        <th>价格</th>
        <th>发布日期</th>
        <th>库存</th>
        <th>访问量</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:if test="${allGoods != null}" var="res">
        <c:forEach items="${allGoods}" var="goods">
            <tr>
                <td><input type="checkbox" value="${goods.gid}:${goods.gphoto}" id="gid" onclick="check(this,'allCheck','gid')"></td>
                <td><img src="upload/goods/${goods.gphoto}" style="width:50px;height: 50px;"></td>
                <td><a href="<%=showGoodsUrl%>?gid=${goods.gid}">${goods.gtitle}</a></td>
                <td>${goods.gprice}</td>
                <td>${goods.gpubdate}</td>
                <td>${goods.gamount}</td>
                <td>${goods.gbrow}</td>
                <c:if test="${goods.gstatus == 1}">
                   <td>已上架</td>
               </c:if>
                <c:if test="${goods.gstatus == 0}">
                    <td>已下架</td>
                </c:if>
                <c:if test="${goods.gstatus == 2}">
                    <td>已在回收站</td>
                </c:if>
                <td><a href="<%=updateUrl%>${goods.gid}">修改</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<c:if test="${param.status != 0}">
    <input type="button" value="下架商品" onclick="update_ids('<%=updateDownUrl%>','ids','gid')">
</c:if>
<c:if test="${param.status != 1}">
    <input type="button" value="上架商品" onclick="update_ids('<%=updateUpUrl%>','ids','gid')">
</c:if>
<c:if test="${param.status != 2}">
    <input type="button" value="删除" onclick="update_ids('<%=updateDeleteUrl%>','ids','gid')">
</c:if>
<c:if test="${param.status == 2}">
    <input type="button" value="彻底删除" onclick="delete_ids('<%=deleteUrl%>','ids','gid')">
</c:if>
<div id="splitBar">
    <jsp:include page="/pages/split_page_plugin_bar.jsp"/>
</div>
</body>
</html>
