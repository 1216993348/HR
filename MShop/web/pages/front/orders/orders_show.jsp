<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String revokeUrl = basePath + "pages/front/orders/OrdersServletFront/revoke";
    String showGoodsUrl = basePath + "pages/front/goods/GoodsServletFront/show";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
    <style rel="stylesheet" type="text/css">
        tr:hover {
            background-color: #FFFFFF;
        }

        #table {
            box-shadow: blue 0px 0px 10px;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<form action="<%=revokeUrl%>" method="post">
    <table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
        <tr>
            <th>订单编号：${orders.oid}</th>
            <th>买家姓名：${orders.mname}</th>
            <th>电话:${orders.mphone}</th>
            <th>收货地址:${orders.maddress}</th>
        </tr>
            <tr align="left">
                <th>商品名称</th>
                <th>商品价格</th>
                <th>商品数量</th>
                <th>价格</th>
            </tr>
        <c:forEach items="${orders.allDetails}" var="details">
            <tr>
                <td><a href="<%=showGoodsUrl%>?gid=${details.goods.gid}">${details.gname}</a></td>
                <td>${details.gprice}</td>
                <td>${details.odnumber}</td>
                <td>${details.gprice * details.odnumber}</td>
            </tr>
        </c:forEach>
        <tr>
           <th colspan="3" align="right">总价:</th><th align="left">${orders.mpay}</th>
        </tr>
        <th colspan="5">
            <input type="submit" value="撤销订单">
            <input type="hidden" name="oid" value="${orders.oid}">
            <input type="button" value="去支付">
        </th>
    </table>
</form>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
