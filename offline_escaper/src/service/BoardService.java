package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import common.DBConn2;

public class BoardService {
	Connection con = null;
	PreparedStatement ps = null;

	public boolean insertUser(HashMap<String, String> hm) {

		try {
			con = DBConn2.getCon();
			String sql = "insert into escape_info(esday,num,room1,room2,escapes,withwho)";
			sql += "values(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("esday"));
			ps.setString(2, hm.get("num"));
			ps.setString(3, hm.get("room1"));
			ps.setString(4, hm.get("room2"));
			ps.setString(5, hm.get("escapes"));
			ps.setString(6, hm.get("withwho"));
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

	public boolean deleteUser(String writer) {
		try {
			con = DBConn2.getCon();
			String sql = ("delete from escape_info where num ="+writer);
			ps = con.prepareStatement(sql);
			ps.setString(1, writer);

			int result = ps.executeUpdate();
			if (result == 1) {
				con.commit();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
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

	public boolean updateUser(String user_num) {
		try {
			String sql = "update board set user_name = '홍길동' where num = 1";
			con = DBConn2.getCon();
			ps = con.prepareStatement(sql); // ps.setString();

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

}
