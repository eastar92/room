<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.BoardInfo"%>
<body>

	<%
		int pNum = Integer.parseInt(request.getParameter("num"));
		Connection con = null;
		PreparedStatement ps = null;
		int num = 0;
		String esday = "";
		String room1 = "";
		String room2 = "";
		String escapes = "";
		String withwho = "";
		String credat = "";
		try {
			con = DBConn2.getCon();
			String sql = "select esday,num,room1,room2,escapes,withwho,credat from escape_info where num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pNum);
			ResultSet rs = ps.executeQuery();
			rs.last();
			int rowCnt = rs.getRow();
			if (rowCnt == 0) {
	%>
	<script>
alert("<%=pNum%>번 게시물은 없습니다.")
history.back();
</script>
	<%
		}
			rs.beforeFirst();
			while (rs.next()) {
				num = rs.getInt("num");
				esday = rs.getString("esday");
				room1 = rs.getString("room1");
				room2 = rs.getString("room2");
				escapes = rs.getString("escapes");
				withwho = rs.getString("withwho");
				credat = rs.getString("credat");
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
	<div class="container">
		<div class="starter-template">
			<table align="center" border="3" class='table-condensed'>
				<tr>
					<td>날짜 :</td>
					<td><%=esday%></td>
				</tr>
				<tr>
					<td>횟수 :</td>
					<td><%=num%></td>
				</tr>
				<tr>
					<td>지점 :</td>
					<td><%=room1%></td>
				</tr>
				<tr>
					<td>방이름 :</td>
					<td><%=room2%></td>
				</tr>
				<tr>
					<td>탈출여부 :</td>
					<td><%=escapes%></td>
				</tr>
					<tr>
					<td>함께한 사람 :</td>
					<td><%=withwho%></td>
				</tr>
				<tr>
					<td>작성일자  :</td>
					<td><%=credat%></td>
				</tr>
				<tr>
					<td>비밀번호 :</td>
					<td><input type="password" name="bipwd" id="bipwd" /></td>
				</tr>
			</table>
			<input type="button" value="수정" onclick="modifyBoard()" /> <input
				type="button" value="삭제" onclick="deleteBoard()" /> <input
				type="button" value="뒤로가기" onclick="doMovePage('board')" />

		</div>
	</div>
	<script>
function deleteBoard(){
	var bipwd = document.getElementById("bipwd").value;
	location.href="<%=rootPath%>/board/board_delete.jsp?binum=<%=num%>&bipwd=" +bipwd;
}
function modifyBoard(){
	var bipwd = document.getElementById("bipwd").value;
	location.href="<%=rootPath%>/board/board_update.jsp?binum=<%=num%>&bipwd="+ bipwd;
	}
	</script>
</body>
</html>