package dto;

public class BoardInfo {
	/*binum int(5) auto_increment primary key,
bititle varchar(255) not null,
bicontent text not null,
bipwd varchar(100) not null
creusr varchar(100) not null,
credat datetime not null*/
	
	private int binum;
	private String bititle;
	private String bicontent;
	private String bipwd;
	private String creusr;
	
	public int getBinum() {
		return binum;
	}
	public void setBinum(int binum) {
		this.binum = binum;
	}
	public String getBititle() {
		return bititle;
	}
	public void setBititle(String bititle) {
		this.bititle = bititle;
	}
	public String getBicontent() {
		return bicontent;
	}
	public void setBicontent(String bicontent) {
		this.bicontent = bicontent;
	}
	public String getBipwd() {
		return bipwd;
	}
	public void setBipwd(String bipwd) {
		this.bipwd = bipwd;
	}
	public String getCreusr() {
		return creusr;
	}
	public void setCreusr(String creusr) {
		this.creusr = creusr;
	}
	public String getCredat() {
		return credat;
	}
	public void setCredat(String credat) {
		this.credat = credat;
	}
	private String credat;
}
