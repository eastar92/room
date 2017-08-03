<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.BoardInfo"%>

<body>

 <script>
 function goBoard(biNum,param2){
	 location.href = "<%=rootPath%>/board/board_view.jsp?binum="+biNum;
 }
 </script>
 <div class="container" >
   <div class="starter-template">
	<%
		Connection con = null;
		PreparedStatement ps = null;
		BoardInfo bi = new BoardInfo();
		try {
			con = DBConn2.getCon();
			String sql = "select binum, bititle, bicontent, bipwd, creusr,credat from board_info";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			String tableStr = "<table border = '1' class='table table-striped'>";
			tableStr += "<tr align='center' class='info'>";
			tableStr += "<td >번호</td>";
			tableStr += "<td>제목</td>";
			tableStr += "<td>비밀번호</td>";
			tableStr += "<td>작성자</td>";
			tableStr += "<td>작성일자</td>";
			tableStr += "</tr>";
			while (rs.next()) {
				tableStr += "<tr align='center'>";
				tableStr += "<td>" + rs.getInt("binum") + "</td>";
				tableStr += "<td><a href = '#javascript' onclick = 'goBoard(" + rs.getInt("binum")+")'>"+rs.getString("bititle") + "</a></td>";
				tableStr += "<td>" + rs.getString("bipwd") + "</td>";
				tableStr += "<td>" + rs.getString("creusr") + "</td>";
				tableStr += "<td>" + rs.getString("credat") + "</td>";
				tableStr += "</tr>";
			}
			tableStr += "</table>";
			out.println(tableStr);

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
	<input type="button" value="게시글작성" onclick="doMovePage('insertBoard')" />
	<input type="button" value="메인가기" onclick="doMovePage('main')" />
	</div>
</div>
</body>
</html>