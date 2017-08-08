<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	</script>
<body>
	<div class="container">
		<div class="starter-template">
		<form method="get" action="<%=rootPath%>/board/board_insert_ok.jsp">
			<table border="1" class='table table bordered table hover'>
					<tr>
						<td>날짜 :</td>
						<td><input type="button" value="달력" onclick="popUpCalendar(this,txtDate,'yyyy-mm--dd')">
						<input type ="text"	name="txtDate"></td>
						<input  type="text" id="txtDate" value="달력2" onclick="fnPopUpCalendar(day,day,'yyyy/mm/dd')" class='text_box1'>
<script  type="text/javascript" src="/js/calendar.js"></script>
					</tr>
					<tr>
						<td>횟수 :</td>
						<td><input type="text" name="num" id="num" ></td>
					</tr>
					<tr>
						<td>지점:</td>
						<td><input type="text" name="room1" id="room1" ></td>
					</tr>
					<tr>
						<td>방 이름 :</td>
						<td><input type="text" name="room2" id="room2" ></td>
					</tr>
						<tr>
						<td>탈출여부 :</td>
						<td><select id="escapes" name="escapes">
						<option value=""></option>
						<option value="O">O</option>
						<option value="X">X</option>
				</select></td>
					</tr>
						<tr>
						<td>함께한 사람 :</td>
						<td><input type="text" name="withwho" id="withwho" ></td>
					</tr>
					<tr>
						<td>비밀번호 :</td>
						<td><input type="password" name="pwd" id="pwd" ></td>
					</tr>
					
					</table>
					<input type="submit" value="저장" /> 
					<input type="button"value="뒤로가기" onclick="doMovePage('board')" /><br />
			
			</form>
		</div>
	</div>
</body>
</html>


