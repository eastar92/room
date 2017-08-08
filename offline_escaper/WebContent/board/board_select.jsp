<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.BoardInfo"%>

<body>

 <script>
 function goBoard(biNum,param2){
	 location.href = "<%=rootPath%>/board/board_view.jsp?num="+biNum;
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
			String sql = "select esday,num,room1,room2,escapes,withwho,recommend, credat from escape_info";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			String tableStr = "<table border = '1' class='table table-striped'>";
			tableStr += "<tr align='center' class='info'>";
			tableStr += "<td>날짜</td>";
			tableStr += "<td>횟수</td>";
			tableStr += "<td>지점</td>";
			tableStr += "<td>방 이름</td>";
			tableStr += "<td>탈출 여부</td>";
			tableStr += "<td>함께한 사람</td>";
			tableStr += "<td>추천/비추</td>";
			tableStr += "<td>작성 일자</td>";
			tableStr += "</tr>";
			while (rs.next()) {
				tableStr += "<tr align='center'>";
				tableStr += "<td>" + rs.getString("esday") + "</td>";
				tableStr += "<td><a href = '#javascript' onclick = 'goBoard(" + rs.getInt("num")+")'>"+rs.getString("num") + "</a></td>";
				tableStr += "<td>" + rs.getString("room1") + "</td>";
				tableStr += "<td>" + rs.getString("room2") + "</td>";
				tableStr += "<td>" + rs.getString("escapes") + "</td>";
				tableStr += "<td>" + rs.getString("withwho") + "</td>";
				tableStr += "<td>" + rs.getString("recommend") + "</td>";
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