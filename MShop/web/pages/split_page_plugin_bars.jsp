<%@ page pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<head>
<base href="<%=basePath%>">
<style>
.pagination{
	list-style:none;
	margin: 0px;
    padding: 0px;
}

.pagination li{
	text-align:center;
	float:left;
	margin:5px;
	border:1px	#D3D3D3 solid;
	border-radius: 3px;
}
.pagination .unchecked:hover{
	border:1px #E0E0E0 solid;
	background-color: #E0E0E0;
	cursor: pointer;
}
.active{
	background-color: #00BFFF;
	color:white;

}
.pagination .s,a{
	display:block;
	width:50px;
	line-height:3;
}
</style>
<%
	int currentPage = 1;
	int pageSize = 10;
	int allRecorders = 0;
	int pageCount = 0;
	String url = null;
	int seed = 3 ;
	String paramName = (String)request.getAttribute("paramName");
	String paramValue = (String)request.getAttribute("paramValue");
%>
<%
		currentPage = (int)request.getAttribute("currentPage");
		pageSize = (int)request.getAttribute("pageSize");
		allRecorders  = (int)request.getAttribute("allRecorders");
		url = basePath + (String)request.getAttribute("url");
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
            try{
                var column = document.getElementById("column").value;
                var keyWord = document.getElementById("kw").value;
                window.location = "<%=url%>" + "?cp=" +
                    currentPage +"&ps=<%=pageSize%>&col=" + column +
                    "&kw=" + keyWord + "<%=paramName == null?"":"&"+paramName+"="%>" + "<%=paramValue == null?"":paramValue%>";
            }catch(e){
                window.location = "<%=url%>" + "?cp=" +
                    currentPage +"&ps=<%=pageSize%>&<%=paramName%>=" + "<%=paramValue == null?"":paramValue%>";
            }
        }
	</script>
</head>
<body>
<%
	if(pageCount > 1) {
 %>
<ul class="pagination" >
	<li class=<%=currentPage == 1? "active":"unchecked" %>>
	<a <%if(currentPage != 1) {%>
		onclick="goSplit(<%=1%>)"
	<% }%>
	><%=1%></a>
	</li>
	<%
	   if(currentPage > seed + 3){
	 %>
	<li><span class="s">...</span></li>
	<%
		}
	 %>
<%
	int startPage = currentPage - seed;
	int endPage = currentPage + seed;
	if(  currentPage <= seed + 3){
		startPage = 2;
	}
	if(currentPage > pageCount - (seed + 3)){
		endPage = pageCount - 1;
	}
	for(int x = startPage; x <= endPage ;x++ ){
%>
	<li class=<%=currentPage == x? "active":"unchecked" %>>
	<a <%if(currentPage != x) {%>
		onclick="goSplit(<%=x%>)"
	<% }%>
	><%=x%></a>
	</li>
<%
	}
%>
<%
	   if(currentPage <=pageCount - (seed + 3)){
%>
	<li><span class="s">...</span></li>
<%
	}
%>
<li class=<%=currentPage == pageCount? "active":"unchecked" %>>
	<a <%if(currentPage != pageCount) {%>
		onclick="goSplit(<%= pageCount%>)"
	<% }%>
	><%= pageCount%></a>
</li>
</ul>
<%
	}
%>
</body>