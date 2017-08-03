<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.UserInfo"%>
<link rel = "stylesheet"  href="<%=rootPath %>/ui/signin.css"/>
<body>
 <div class="container">
      <form class="form-signin" action="/user/login_ok.jsp">
        <h2 class="form-signin-heading">로그인</h2>
        <label for="inputEmail" class="sr-only">ID</label>
        <input type="text" id ="id" name="id" class="form-control" placeholder="아이디" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id ="pwd" name="pwd" class="form-control" placeholder="비밀번호" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 아이디 저장
          </label>
        </div>
        <button type = "button" id = "btn2" class="btn btn-lg btn-primary btn-block" type="button" >로그인</button>
    
      </form>

	  </div> <!-- /container -->
<script>
$("#btn2").click(function(){
	var id = $("#id").val();
	var pwd = $("#pwd").val();
	var param = {};
	param["userId"] = id;
	param["userPwd"] = pwd;
	param = JSON.stringify(param);
   var a = { 
        type     : "POST"
    ,   url      : "/user/login_ok.jsp"
    ,   dataType : "json" 
    ,   beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
    }
    ,   data     : param
    ,   success : function(result){
       alert(result.msg);
       if(result.login=="ok"){
    	 location.href = "<%=rootPath%>/main.jsp";  
       }else{
    	   $("#id").val("");
    	   $("#pwd").val("");
       }
    }
    ,   error : function(xhr, status, e) {
          alert("에러 : "+e);
   },
   done : function(e) {
   }
   }; 
   $.ajax(a);
});
</script>
</body>
</html>