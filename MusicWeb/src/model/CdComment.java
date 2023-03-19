package model;

public class CdComment {
	private int uCDCommId;
	private int userId;
	private int CDId;
	private String CDCommText;
	private String CDCommDate;
	
	
	public int getuCDCommId() {
		return uCDCommId;
	}
	public void setuCDCommId(int uCDCommId) {
		this.uCDCommId = uCDCommId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCDId() {
		return CDId;
	}
	public void setCDId(int cDId) {
		CDId = cDId;
	}
	public String getCDCommText() {
		return CDCommText;
	}
	public void setCDCommText(String cDCommText) {
		CDCommText = cDCommText;
	}
	public String getCDCommDate() {
		return CDCommDate;
	}
	public void setCDCommDate(String cDCommDate) {
		CDCommDate = cDCommDate;
	}
	

	
}
