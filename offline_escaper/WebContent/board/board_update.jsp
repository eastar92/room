<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.BoardInfo"%>
<body>
	<%
		int pBinum = Integer.parseInt(request.getParameter("binum"));
		String pBipwd = request.getParameter("bipwd");
		Connection con = null;
		PreparedStatement ps = null;
		int binum = 0;
		String bititle = "";
		String bicontent = "";
		String bipwd = "";
		String creusr = "";
		String credat = "";
		try {
			con = DBConn2.getCon();
			String sql = "select binum, bititle, bicontent, bipwd, creusr, credat from board_info where binum=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pBinum);
			ResultSet rs = ps.executeQuery();
			rs.last();
			int rowCnt = rs.getRow();
			if (rowCnt == 0) {
	%>
	<script>
			alert("<%=pBinum%>번 게시물은 없습니다.");
			history.back();
			</script>
	<%
		}
			rs.beforeFirst();
			while (rs.next()) {
				binum = rs.getInt("binum");
				bititle = rs.getString("bititle");
				bicontent = rs.getString("bicontent");
				creusr = rs.getString("creusr");
				credat = rs.getString("credat");
				bipwd = rs.getString("bipwd");
				if (!bipwd.equals(pBipwd)) {
	%>
	<script>
		alert("<%=pBinum%> 번 게시불의 비밀번호가 아닙니다");
		history.back();
	</script>
	<%
		}
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

<body>
<div class="container">
		<div class="starter-template">
	<form method="get" action="<%=rootPath%>/board/board_update_ok.jsp">
	<table border="1" class='table table bordered table hover'>
<tr><td>제목:</td><td><input type="text" name="bititle" id="bititle" value="<%=bititle%>" /></td>
<tr><td>내용:	</td><td><textarea name="bicontent" id="bicontent"><%=bicontent%></textarea></td>
<tr><td>작성자:</td><td><input type="text" name="creusr" id="creusr" value="<%=creusr%>" /></td>
<tr><td>비밀번호:</td><td><input type="password" name="bipwd" id="bipwd" value="<%=bipwd%>" /></td>
</table>
<input type="hidden"	value="<%=binum%>" name="binum" /> 
<input type="submit" value="수정하기" />
<input type="button" value="취소" onclick = "doMovePage('board')"/>

	</form>
	</div></div>
</body>