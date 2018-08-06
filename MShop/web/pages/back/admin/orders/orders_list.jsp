<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String showUrl = basePath + "pages/back/admin/orders/OrdersServletBack/show";
    String revokeUrl = basePath + "pages/front/orders/OrdersServletFront/revoke";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>${orders.mname}订单列表</title>
    <link rel="stylesheet" type="text/css" href="css/item.css">
</head>
<body>

<div id="splitSearchDiv">
    <jsp:include page="/pages/split_page_plugin_search.jsp"/>
    <br>
</div>
<c:if test="${allRecorders > 0}" var="res">
    <c:forEach items="${allOrders}" var="orders">
        <form action="<%=revokeUrl%>" method="post">
            <table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
                <tr align="left">
                    <td>订单编号</td>
                    <th>订单日期</th>
                    <th>买家姓名</th>
                    <th>电话</th>
                    <th>收货地址</th>
                    <th>总价</th>
                </tr>
                <tr>
                    <td><a href="<%=showUrl%>?oid=${orders.oid}">${orders.oid}</a></td>
                    <td>${orders.credate}</td>
                    <td>${orders.mname}</td>
                    <td>${orders.mphone}</td>
                    <td>${orders.maddress}</td>
                    <td>${orders.mpay}</td>
                </tr>
            </table>
        </form>
    </c:forEach>
    <div id="splitBar">
    </div>
</c:if>
<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
<c:if test="${allRecorders == 0}" var="res">
    <h2>无符合条件的订单</h2>
</c:if>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
