package model;

public class User {
	private int userId;//用户编号
	private String loginId;//用户账号
	private String password;//用户密码
	private String userName;//用户名称
	private String userSex;//用户性别
	private String email;//用户邮箱
	private String phone;//用户手机
	private int userType;//用户类型
	private String sign;//个人签名
	private String headSculptureUrl;//用户头像地址
	private String registationDate;//注册日期
	private int userStateId;//用户状态信息
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getHeadSculptureUrl() {
		return headSculptureUrl;
	}
	public void setHeadSculptureUrl(String headSculptureUrl) {
		this.headSculptureUrl = headSculptureUrl;
	}
	public String getRegistationDate() {
		return registationDate;
	}
	public void setRegistationDate(String date) {
		this.registationDate = date;
	}
	public int getUserStateId() {
		return userStateId;
	}
	public void setUserStateId(int userStateId) {
		this.userStateId = userStateId;
	}
	
}
