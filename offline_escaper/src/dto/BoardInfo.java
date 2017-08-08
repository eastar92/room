
package dto;

public class BoardInfo {
	/*binum int(5) auto_increment primary key,
esday varchar(300),
num int,
room1 varchar(300),
room2 varchar(300),
escapes varchar(2),
withwho
credat datetime not null*/
	
	private String esday;
	private int num;
	private String room1;
	private String room2;
	private String escapes;
	private String withwho;
	private String credat;

	private String pwd;

	public String getEsday() {
		return esday;
	}
	public void setEsday(String esday) {
		this.esday = esday;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getRoom1() {
		return room1;
	}
	public void setRoom1(String room1) {
		this.room1 = room1;
	}
	
	public String getRoom2() {
		return room2;
	}
	public void setRoom2(String room2) {
		this.room2 = room2;
	}
	
	public String getEscapes() {
		return escapes;
	}
	public void setEscapes(String escapes) {
		this.escapes = escapes;
	}
	
	public String getWithwho() {
		return withwho;
	}
	public void setWithwho(String withwho) {
		this.withwho = withwho;
	}
	public String getCredat() {
		return credat;
	}
	public void setCredat(String credat) {
		this.credat = credat;
	}
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd =pwd;
	}
}


