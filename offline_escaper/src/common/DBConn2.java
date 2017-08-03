package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn2 {
	private static Connection con;

	public static Connection getCon() throws ClassNotFoundException, SQLException{
		if (con == null) {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iot", "root", "dongkyu92");
			con.setAutoCommit(false);
		}
		return con;
	}
	
	public static void main(String[] args){
		try{
			Connection con = DBConn2.getCon();
			System.out.println("접속 됐네잉!!" + con);
			DBConn2.closeCon();

			System.out.println("종료 됐다네잉~!" + con);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void closeCon() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	
}

