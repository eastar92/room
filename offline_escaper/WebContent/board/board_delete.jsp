<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.UserInfo"%>

<%
	String biNum = request.getParameter("binum");
	String biPwd = request.getParameter("bipwd");
	String sql = "delete from board_info";
	sql += " where binum=? and bipwd=?";
	
	Connection con = null;
	PreparedStatement ps = null;
	String result = "삭제 안됨";
	int resultNum = 0;
	try{
		con = DBConn2.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1,biNum);
		ps.setString(2,biPwd);
		resultNum = ps.executeUpdate();
		if(resultNum ==1){
			result = "삭제 되었다.";
			con.commit();
		}else if(resultNum==2){
		result= "삭제 안됨";
		con.commit();
		}
	}catch(Exception e){
		System.out.println(e);
	}finally{
		if(ps != null){
			ps.close();
			ps = null;
		}
		DBConn2.closeCon();
	}
%>
<script>
alert("<%=result%>");
if(<%=resultNum%> ==1){
	location.href="<%=rootPath%>/board/board_select.jsp";
}else{
	history.back();
}
</script>