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
import dto.RoomInfo;

public class RoomService {

	public boolean insertUser(RoomInfo ri) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "insert into room_info(num, id, pwd, name)";
			sql += "values(?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, ri.getUserId());
			ps.setString(2, ri.getUserPwd());
			ps.setString(3, ri.getUserName());
			ps.setInt(4, ri.getUserNum());
		
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

	public boolean deleteUser(RoomInfo ri) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "delete from room_info ";
			sql += "where num=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, ri.getUserNum());
			int result = ps.executeUpdate();
			if (result == 1) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
	
	public boolean updateUser(RoomInfo ri) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "update room_info ";
			sql += " set id=?,";
			sql += " name=?,";
			sql += " where num=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, ri.getUserId());
			ps.setString(2, ri.getUserName());
			ps.setInt(3, ri.getUserNum());
			int result = ps.executeUpdate();
			if (result == 1) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return "비번 틀림";
	}

	public String loginUser(RoomInfo ri) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConn2.getCon();
			String sql = "select pwd from room_info where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ri.getUserId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String userpwd = rs.getString("pwd");
				return checkPwd(userpwd, ri.getUserPwd());
			}
		} catch (Exception e) {

		}
		return "아이디 없다";

	}

	

	public List<RoomInfo> selectUser(RoomInfo ri) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "select num,id,name,pwd from room_info";
			if (ri.getUserName() != null && !ri.getUserName().equals("")) {
				sql += " where name like ?";
			}
			con = DBConn2.getCon();
			ps = con.prepareStatement(sql);
			if (ri.getUserName()!= null && !ri.getUserName().equals("")) {
				ps.setString(1, ri.getUserName());
			}
			ResultSet rs = ps.executeQuery();
			List userList = new ArrayList();
			while (rs.next()) {
				RoomInfo ri2 = new RoomInfo();
				ri2.setUserNum(rs.getInt("num"));
				ri2.setUserId(rs.getString("id"));
				ri2.setUserName(rs.getString("name"));
				ri2.setUserPwd(rs.getString("pwd"));
				userList.add(ri2);
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
