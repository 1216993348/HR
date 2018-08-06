<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/goods/GoodsServletFront/show";
    String insertShopcarUrl = basePath + "pages/front/shopcar/ShopcarServletFront/insert";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
    <link rel="stylesheet" href="css/goods.css" type="text/css">
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
<div id="container">
    <div id="header">
        <jsp:include page="/pages/header.jsp"/>
        <jsp:include page="/pages/split_page_plugin_search.jsp"/>
    </div>
    <div id="page" class="clearfix">
                <h3>商品信息</h3>
                <a href="pages/front/goods/GoodsServletFront/list?iid=0">${0==iid?"<strong>全部商品</strong>":"全部商品"}</a>
                <c:forEach items="${allItems}" var="item">
                    <a href="pages/front/goods/GoodsServletFront/list?iid=${item.iid}">${item.iid==iid?"<strong>":""}${item.ititle}${item.iid==iid?"</strong>":""}</a>
                </c:forEach>

                    <div id="g1">
                <c:if test="${allRecorders != 0}" var="res">
                <c:forEach items="${allGoods}" var="goods" varStatus="status">
                        <div class="box">
                            <div id="t1" class="ce1">
                                <a href="<%=showUrl%>?gid=${goods.gid}"><img src="upload/goods/${goods.gphoto}" style="width:180px;height: 180px;"></a>
                            </div>
                            <div id="u1" class="cf1">
                                <div class="box2">商品名称：${goods.gtitle}</div>
                                <div class="box2">商品单价：${goods.gprice}</div>
                                <div class="box2">库存量：${goods.gamount}</div>
                                <div class="box2">访问量：${goods.gbrow}</div>
                            </div>
                            <div id="y1" class="cg1">
                                <a href="<%=insertShopcarUrl%>?gid=${goods.gid}">添加购物车</a>
                            </div>
                        </div>
                    <c:if test="${status.count%4 ==0}">
                        <br>
                    </c:if>
                </c:forEach>
                </c:if>
                    </div>
    </div>
<br>
<br>
<br>
<div id="footer">
<jsp:include page="/pages/split_page_plugin_bar.jsp"/>
</div>
</div>
</body>
</html>
