<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    request.setCharacterEncoding("UTF-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath + "pages/back/admin/goods/GoodsServletBack/insert" ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <link type="text/css" rel="stylesheet" href="css/goods.css">
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/goods.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/WdatePicker.js"></script>
</head>
<body>
<div class="left">
    <form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" width="700px">
            <tr >
                <td width="100px">商品名称:</td>
                <td>
                    <input type="text" class="new_txt" id="gtitle" name="gtitle"
                           onblur="validateGtitle()" >
                </td>
                <td width="100px">
                    <span id="gtitleMsg" >请输入正确数据</span>
                </td>
            </tr>
            <tr>
                <td >商品单价:</td>
                <td>
                    <input type="text" class="new_txt" id="gprice" name="gprice"
                           onblur="validateGprice()" >
                </td>
                <td>
                    <span id="gpriceMsg" >请输入数字</span>
                </td>
            </tr>
            <tr>
                <td >库存量:</td>
                <td>
                    <input type="text" class="new_txt" id="gamount" name="gamount"
                           onblur="validateGamount()" onfocus="initGamount()">
                </td>
                <td >
                    <span id="gamountMsg" >请输入整数</span>
                </td>
            </tr>
            <tr>
                <td>商品类型</td>
                <td><select name="iid">
                    <c:if test="${allItems != null}">
                        <c:forEach items="${allItems}" var="item">
                                <option value="${item.iid}">${item.ititle}</option>
                        </c:forEach>
                    </c:if>
                </select>
                </td>
            </tr>
            <tr>
                <td>商品描述</td>
                <td>
                    <textarea name="gnote" id="gnote" cols="30" rows="10"></textarea>
                </td>
            </tr>
            <tr>
                <td>商品状态</td>
                <td>
                    上架<input type="radio" name="gstatus" value="1"checked >
                    下架<input type="radio" name="gstatus" value="0" >
                </td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td>
                    <input type="file" name="gphoto" id="gphoto" value="0" onchange="preview(this)">
                </td>
            </tr>
        </table>
        <input type="submit" value="添加">
    </form>

</div>
<div class="photo">
    商品图片：
    <div id="preview"></div>
</div>

</body>
</html>

