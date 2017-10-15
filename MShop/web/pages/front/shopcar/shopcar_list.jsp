<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String deleteUrl = basePath + "pages/front/shopcar/ShopcarServletFront/delete?x=x" ;
    String updateUrl = basePath + "pages/front/shopcar/ShopcarServletFront/update" ;
    String insertOrders = basePath + "pages/front/orders/OrdersServletFront/insert";
    String showGoodsUrl = basePath + "pages/front/goods/GoodsServletFront/show";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="css/goods.css">
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/shopcar.js"></script>
</head>
<body>
<h1>购物车</h1>
<jsp:include page="/pages/header.jsp"/>
<form action="<%=updateUrl%>" method="post" id="form">
<table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
    <tr align="left">
        <th><label for="allCheck">全选</label><input type="checkbox" onclick="checkboxAll2(this,'gid')" id="allCheck"></th>
        <th>图片</th>
        <th>商品名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>价格</th>

    </tr>
    <c:if test="${allGoods != null}" var="res">
        <c:forEach items="${allGoods}" var="goods">
            <tr>
                <td><input type="checkbox" value="${goods.gid}" id="gid" onchange="change(this,${goods.gid})" onclick="check(this,'allCheck','gid')"></td>
                <td><img src="upload/goods/${goods.gphoto}" style="width:50px;height: 50px;"></td>
                <td><a href="<%=showGoodsUrl%>?gid=${goods.gid}">${goods.gtitle}</a></td>
                <td><span id="gprice-${goods.gid}">${goods.gprice}</span></td>
                <td>
                    <input type="button"value="-" onclick="subCar(${goods.gid})">
                    <input type="text" size="5" maxlength="5" value="${allCars[goods.gid]}"
                           name="${goods.gid}" id="${goods.gid}" >
                    <input type="button"value="+" onclick="addCar(${goods.gid})">
                </td>
                <td>
                    <span id="cal-${goods.gid}"></span>
                    <script>
                        cal(${goods.gid})
                    </script>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
    <span id="allPrice"></span>
<input type="button" value="删除" onclick="delete_ids('<%=deleteUrl%>','ids','gid')">
<input type="submit" value="修改" onclick="clean()">
<input type="reset" value="重置" >
<input type="button" value="创建订单" onclick="submitOrders('<%=insertOrders%>')">
</form>
</body>
</html>
