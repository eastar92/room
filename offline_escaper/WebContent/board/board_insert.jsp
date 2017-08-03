<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<div class="container">
		<div class="starter-template">
		<form method="get" action="<%=rootPath%>/board/board_insert_ok.jsp">
			<table border="1" class='table table bordered table hover'>
					<tr>
						<td>제목 :</td>
						<td><input type="text" name="bititle" id="bititle" /></td>
					</tr>
					<tr>
						<td>내용 :</td>
						<td><textarea name="bicontent" id="bicontent"></textarea></td>
					</tr>
					<tr>
						<td>비밀번호:</td>
						<td><input type="text" name="bipwd" id="bipwd" /></td>
					</tr>
					<tr>
						<td>작성자 :</td>
						<td><input type="text" name="creusr" id="creusr"
							value="<%=userId%>" /></td>
					</tr>
					</table>
					<input type="submit" value="저장" /> 
					<input type="button"value="뒤로가기" onclick="doMovePage('board')" /><br />
			
			</form>
		</div>
	</div>
</body>
</html>


