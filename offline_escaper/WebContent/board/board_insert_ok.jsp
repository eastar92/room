<%@page import="common.DBConn2"%>
<%@ include file="/common/header.jsp"%>
<%@page import="dto.BoardInfo"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String biTitle = request.getParameter("bititle");
	String biContent = request.getParameter("bicontent");
	String biPwd = request.getParameter("bipwd");
	String creusr = request.getParameter("creusr");

	String sql = "insert into board_info(bititle, bicontent, bipwd, creusr,credat)";
	sql += " values(?,?,?,?,now())";
	String result = "저장안됨";
	int resultNum = 0;
	Connection con = null;
	PreparedStatement ps = null;
	try {
		con = DBConn2.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, biTitle);
		ps.setString(2, biContent);
		ps.setString(3, biPwd);
		ps.setString(4, creusr);
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