<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<style rel="stylesheet" type="text/css">
    #headerDiv{

    }
</style>
<div id="headerDiv">
<image src="images/logo.png" style="width: 350px;height: 72px"></image>
    <a href="pages/front/goods/GoodsServletFront/list">商城中心</a>
    <a href="pages/front/shopcar/ShopcarServletFront/list">购物车</a>
    <c:if test="${mid == null}">
    <a href="pages/front/member_login.jsp">登陆</a>
    <a href="pages/front/member_register.jsp">注册</a>
    </c:if>
    <c:if test="${mid !=null}">
        <img src="upload/member/${mphoto}" style="width:50px;height: 50px; ">
        <a href="pages/front/member/MemberInfoServletFront/updatePre">个人信息</a>
        <a href="">个人中心</a>
        <a href="pages/front/orders/OrdersServletFront/list">我的订单</a>
        <a href="pages/front/MemberServletFront/logout">注销用户</a>
    </c:if>
</div>