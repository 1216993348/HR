<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String insertUrl = basePath + "pages/back/admin/item/ItemServletBack/insert" ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>

    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/item.js"></script>
</head>
<body>
    用户注册
    <form action="<%=insertUrl%>" method="post" onsubmit="validateInsert()">
        <table border="1">
            <tr>
                <td>商品类型名</td>
                <td><input type="text" id="ititle" name="ititle" onblur="validateItitle('${item.ititle}')"></td>
                <td width="120px"><span id="item-${item.ititle}Msg"></span></td>
            </tr>
            <tr>
                <td><input type="submit" value="添加"></td>
                <td><input type="reset" value="重填"></td>
            </tr>
        </table>
    </form>
</html>
