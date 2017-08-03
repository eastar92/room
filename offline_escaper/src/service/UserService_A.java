package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBConn2;

public class UserService_A {

	public boolean insertUser(HashMap<String, String> hm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "insert into user_info(userid, userpwd, username, address,hp1,hp2,hp3,age)";
			sql += "values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("userid"));
			ps.setString(2, hm.get("userpwd"));
			ps.setString(3, hm.get("username"));
			ps.setString(4, hm.get("address"));
			ps.setString(5, hm.get("hp1"));
			ps.setString(6, hm.get("hp2"));
			ps.setString(7, hm.get("hp3"));
			ps.setString(8, hm.get("age"));
			int result = ps.executeUpdate();
			if (result == 1) {
				con.commit();
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				DBConn2.closeCon();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean deleteUser(HashMap<String, String> hm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "delete from user_info ";
			sql += " where user_num=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("user_num"));
			int result = ps.executeUpdate();
			if (result == 1) {
				con.commit();
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				DBConn2.closeCon();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public String checkPwd(String pwd1, String pwd2) {
		if (pwd1.equals(pwd2)) {
			return "로그인 성공";
		}
		return "비밀번호 틀림";
	}

	public String loginUser(HashMap<String, String> hm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "select userpwd from user_info where userid= ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("userid"));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String userpwd = rs.getString("userpwd");
				return checkPwd(userpwd, hm.get("userpwd"));
			}
		} catch (Exception e) {

		}
		return "그런 아이디 없다";
	}

	public List<Map> selectUser(HashMap<String, String> hm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "select user_num, user_id, user_pwd, user_name, class_num from user_info";
			if (hm.get("name") != null) {
				sql += " where user_name like ?";
			}
			con = DBConn2.getCon();
			ps = con.prepareStatement(sql);
			if (hm.get("name") != null) {
				ps.setString(1, hm.get("name"));
			}
			ResultSet rs = ps.executeQuery();
			List userList = new ArrayList();
			while (rs.next()) {
				HashMap hm1 = new HashMap();
				hm1.put("user_num", rs.getString("user_num"));
				hm1.put("user_id", rs.getString("user_id"));
				hm1.put("user_pwd", rs.getString("user_pwd"));
				hm1.put("user_name", rs.getString("user_name"));
				hm1.put("class_num", rs.getString("class_num"));
				userList.add(hm1);
			}
			return userList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				DBConn2.closeCon();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
