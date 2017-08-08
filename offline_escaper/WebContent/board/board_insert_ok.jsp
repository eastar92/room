<%@page import="common.DBConn2"%>
<%@ include file="/common/header.jsp"%>
<%@page import="dto.BoardInfo"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String esday = request.getParameter("esday");
	String num = request.getParameter("num");
	String room1 = request.getParameter("room1");
	String room2 = request.getParameter("room2");
	String escapes = request.getParameter("escapes");
	String withwho = request.getParameter("withwho");
	String pwd = request.getParameter("pwd");

	String sql = "insert into escape_info(esday, num, room1, room2,escapes,withwho,credat,pwd)";
	sql += " values(?,?,?,?,?,?,now(),?)";
	String result = "저장안됨";
	int resultNum = 0;
	Connection con = null;
	PreparedStatement ps = null;
	try {
		con = DBConn2.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, esday);
		ps.setString(2, num);
		ps.setString(3, room1);
		ps.setString(4, room2);
		ps.setString(5, escapes);
		ps.setString(6, withwho);
		ps.setString(7, pwd);
		resultNum = ps.executeUpdate();
		if (resultNum == 1) {
			result = "저장되었습니다";
			con.commit();
		}
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		if (ps != null) {
			ps.close();
			ps = null;
		}
		DBConn2.closeCon();
	}
%>
<script>
alert("<%=result%>");
if(<%=resultNum%> ==1){
	location.href = "<%=rootPath%>/board/board_select.jsp";
	} else {
		history.back();
	}
</script>