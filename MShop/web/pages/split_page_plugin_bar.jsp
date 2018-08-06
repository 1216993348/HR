<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://www.xhy.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
	<style type="text/css">
	.button{
		height:40px;
		width:100px;
		font-size: 15px;
		margin:10px ;
		background-image:url("<%=basePath%>pages/split.png");
	}
	</style>
<%
	int currentPage = 1;
	int pageSize = 10;
	int allRecorders = 0;
	int pageCount = 1;
	String url = null;
	int pcData[] = new int []{1,5,10,15,20,30,50,100};

%>
<% 
	try{
	currentPage = (Integer)request.getAttribute("currentPage");
	}catch(Exception e){}
	try{
	pageSize = (int)request.getAttribute("pageSize");
	}catch(Exception e){}
	try{
	allRecorders  = (int)request.getAttribute("allRecorders");
	}catch(Exception e){}
	url = basePath + (String)request.getAttribute("url");
	String paramName = (String)request.getAttribute("paramName");
	String paramValue = (String)request.getAttribute("paramValue");
%>
 <%
      if(allRecorders > 0){
         pageCount = (allRecorders + pageSize - 1) / pageSize;
       } else {
           pageCount = 1 ;
       }
     %>
<script type="text/javascript">
	function goSplit(currentPage){
        var pageSize = 10;
        try {
            var pageSize = document.getElementById("pcSel").value;
		}catch (e){}
		try{
		var column = document.getElementById("column").value;
		var keyWord = document.getElementById("kw").value;
		window.location = "<%=url%>" + "?cp=" +
		currentPage +"&ps=" + pageSize +
		"&col=" + column +
		"&kw=" + keyWord + "<%=paramName == null?"":"&"+paramName+"="%>" + "<%=paramValue == null?"":paramValue%>";
		}catch(e){
		window.location = "<%=url%>" + "?cp=" +
							currentPage +"&ps=" + pageSize + "&<%=paramName%>=" + "<%=paramValue == null?"":paramValue%>";
		}
}
</script>
</head>
<body>
 	<input type="button" value="首页" onclick="goSplit(1)" 
 	 	class="button" <%=currentPage==1?"disabled":""%>>
 	 	<input type="button"  value="上一页" onclick="goSplit(<%=currentPage-1%>)" 
 	 	class="button" <%=currentPage==1?"disabled":""%>>
 	 	<input type="button" value="下一页" onclick="goSplit(<%=currentPage+1 %>)" 
 	 	class="button" <%=currentPage==pageCount?"disabled":""%>>
 	 	<input type="button" value="尾页" onclick="goSplit(<%=pageCount %>)" 
 	 	class="button" <%=currentPage==pageCount?"disabled":""%>> 
 	 	跳转到第:
 	<select onchange="goSplit(this.value)">
 	 <%
 	 	for(int x = 1 ; x <=pageCount ; x++){
 	  %>
 	  <option value="<%=x%>" <%=x==currentPage?"selected":""%>><%=x%></option>
 	  <% 
 	 	}
 	  %>
 	</select>页
 	每页显示:<select id="pcSel" onchange="goSplit(1)">
 	 		<%
 	 			for(int x=0 ;x < pcData.length ;x++){
 	 		%> <option value=<%=pcData[x]%> <%=pcData[x]==pageSize?"selected":""%>><%=pcData[x]%></option>
 	 		<%
 	 			}
 	 		%>
	</select>
</body>