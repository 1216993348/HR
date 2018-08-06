<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
    <style rel="stylesheet" type="text/css" >
        tr:hover{
            background-color: #FFFFFF;
        }
        #table{
            box-shadow:blue 0px 0px 10px;
            border-radius:5px;
            margin-top:10px;
        }
    </style>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<c:forEach items="${allItems}" var="item">
    <a href="pages/front/goods/GoodsServletFront/list?iid=${item.iid}">${item.ititle}</a>
</c:forEach>
        <table border="1" cellpadding="5" cellspacing="0" width="800px" bgcolor="#F0F0F0" id="table">
            <tr >
                <td rowspan="6"><img src="upload/goods/${goods.gphoto}" style="width:180px;height: 180px;"></td>
                <td>上架日期：${goods.gpubdate}</td>
            </tr>
            <tr>
                <td>商品名称：${goods.gtitle}</td>
            </tr>
            <tr>
                <td>商品单价：${goods.gprice}</td>
            </tr>
            <tr>
                <td>库存量：${goods.gamount}</td>
            </tr>
            <tr>
                <td>访问量：${goods.gbrow}</td>
            </tr>
            <tr>
                <td>添加购物车</td>
            </tr>
            <tr>
                <td colspan="2">商品介绍：${goods.gnote == ""?"暂无详细信息":goods.gnote}</td>
            </tr>
        </table>
    <jsp:include page="/pages/footer.jsp"/>

</body>
</html>
