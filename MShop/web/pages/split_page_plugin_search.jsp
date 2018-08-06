<%@ page pageEncoding="UTF-8" %>
<%
	String columnData = null;
	String column = null;
	String keyWord = null;
	int pageSize = 10;
	int allRecorders = 0;
	int pageCount = 1 ;
%>
<%
	columnData = (String)request.getAttribute("columnData");
	column = (String)request.getAttribute("column");
	keyWord = (String)request.getAttribute("keyWord");
	try{
		pageSize = (int)request.getAttribute("pageSize");
	}catch(Exception e){}
	try{
		allRecorders = (Integer)request.getAttribute("allRecorders") ;
	}catch(Exception e){}

 %>
<%
	if(allRecorders > 0){
		pageCount = (allRecorders + pageSize - 1) / pageSize;
	}
%>
 <% if(columnData != null) { %>
根据:<select id="column" >
<%
	String result[] = columnData.split("\\|");
	for(int x=0 ;x < result.length ;x++){
	 String temp[] = result[x].split(":");
%>
 	<option value=<%=temp[1]%> <%=temp[1].equals(column)?"selected":""%>><%=temp[0]%></option>
		<%
			}
		%>
<%
	}
%>
 	 	</select>
 	 	<input type="text" name="kw" id="kw" value=<%=keyWord %>>
 	 	<input type="button" value="查询" onclick="goSplit(1)">
 	 查找到<%= allRecorders%>条记录
 	 共有<%=pageCount%>页
