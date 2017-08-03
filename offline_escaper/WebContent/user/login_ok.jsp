<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="common.DBConn2"%>
<%@ page import="dto.RoomInfo"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="java.io.*" %>

<%
	String id = null;
	String pwd = null;
	RoomInfo ri = null;
	Gson g = new Gson();
	ri = g.fromJson(request.getReader(),RoomInfo.class);
		
	String result = "";
	String login = "false";
	if(ri!=null){
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "select  pwd, name from room_info where id =?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ri.getUserId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String userPwd = rs.getString("pwd");
				String userName = rs.getString("name");
				if (userPwd.equals(ri.getUserPwd())) {
					result = "로그인 성공";
					login = "ok";
					session.setAttribute("id", ri.getUserId());
					session.setAttribute("name", userName);
				} else {
					result = "비번 틀림";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			if(ps!=null){
				ps.close();
				ps=null;
			}
			DBConn2.closeCon();
		}
		if (result.equals("")) {
			result = "그런 아이디 없어";
		}
	}else{
		result = "로그아웃 되었습니다.";
		session.invalidate();
	}
	HashMap hm = new HashMap();
	hm.put("login",login);
	hm.put("msg",result);
	
	String json = g.toJson(hm);
	System.out.println(json);
	out.write(json);
%>
