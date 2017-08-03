package dto;

public class RoomInfo {
	/*
	 * `usernum` INT(5) NOT NULL AUTO_INCREMENT, `userid` VARCHAR(30) NOT NULL,
	 * `userpwd` VARCHAR(100) NOT NULL, `username` VARCHAR(100) NOT NULL, `age`
	 * * INT(1) NOT NULL, `address` VARCHAR(200) NOT NULL, ` hp1` CHAR(4) NULL
	 * DEFAULT NULL, `hp2` CHAR(4) NULL DEFAULT NULL, `hp3` CHAR(4) NULL DEFAULT
	 * NULL,
	 */
	private int userNum;
	private String userId;
	private String userName;
	private String userPwd;

	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
