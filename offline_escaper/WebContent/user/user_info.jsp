<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/header.jsp"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.RoomInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="starter-template">
	<form method="get" action="<%=rootPath%>/board/board_update_ok.jsp">
	<table align = "center" border="3" class='table-condensed'>
	<tr><td class="active">아이디:</td><td><%=userId%></td>
<tr><td>이름:</td><td><%=userName%></td>
<tr><td>방탈횟수:</td><td><%=userName%></td>

</table> 


	</form>
	</div></div>
</body>
 