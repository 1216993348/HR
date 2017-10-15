<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
    request.setCharacterEncoding("UTF-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath + "pages/back/admin/goods/GoodsServletBack/insert" ;
    String updateUrl = basePath + "pages/back/admin/goods/GoodsServletBack/update" ;
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
    <form action="<%=updateUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" width="700px">
            <tr >
                <td width="100px">商品名称:</td>
                <td>
                    <input type="text" class="new_txt" id="gtitle" name="gtitle"
                           onblur="validateGtitle()" value="${goods.gtitle}">
                </td>
                <td width="120px">
                    <span id="gtitleMsg" >请输入正确数据</span>
                </td>
            </tr>
            <tr>
                <td >商品单价:</td>
                <td>
                    <input type="text" class="new_txt" id="gprice" name="gprice"
                           onblur="validateGprice()" value="${goods.gprice}">
                </td>
                <td>
                    <span id="gpriceMsg" >请输入数字</span>
                </td>
            </tr>
            <tr>
                <td >库存量:</td>
                <td>
                    <input type="text" class="new_txt" id="gamount" name="gamount"
                           onblur="validateGamount()" onfocus="initGamount()"
                           value="${goods.gamount}">
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
                            <option value="${item.iid}" ${goods.item.iid == item.iid?"selected":""}>${item.ititle}</option>
                        </c:forEach>
                    </c:if>
                </select>
                </td>
            </tr>
            <tr>
                <td>商品描述</td>
                <td>
                    <textarea name="gnote" id="gnote" cols="30" rows="10" value="${goods.gnote}"></textarea>
                </td>
            </tr>
            <tr>
                <td>商品状态</td>
                <td>
                    上架<input type="radio" name="gstatus" value="1"${goods.gstatus == 1?"checked":""} >
                    下架<input type="radio" name="gstatus" value="0"${goods.gstatus == 0?"checked":""} >
                    删除<input type="radio" name="gstatus" value="2"${goods.gstatus == 2?"checked":""} >
                </td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td>
                    <input type="file" name="gphoto" id="gphoto" onchange="preview(this)">
                </td>
            </tr>
        </table>
        <input type="submit" value="修改">
        <input type="reset" value="重置" onclick="resetPhoto('goods/${goods.gphoto}')">
        <input type="hidden" value="${goods.gid}" name="gid">
        <input type="hidden" value="${goods.gphoto}" name="oldPhoto">
        <input type="hidden" value="${back}" name="back">
    </form>

</div>
<div class="photo">
    商品图片：
    <div id="preview">
        <img src="upload/goods/${goods.gphoto}" width="250px" height="250px">
    </div>
</div>

</body>
</html>

