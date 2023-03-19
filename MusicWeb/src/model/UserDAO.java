package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	public final String USERID = "userId";
	public final String LOGINID = "loginId";
	public final String PASSWORD = "password";
	public final String USERNAME = "userName";
	public final String USERSEX = "userSex";
	public final String EMAIL = "email";
	public final String PHONE = "phone";
	public final String SIGN = "sign";
	public final String HEADSCULPTUREURL = "headSculptureUrl";
	public final String REGISTATIONDATE = "registationDate";
	public final String USERSTATEID = "userStateId";
	
	//注册
	public boolean addUser(User user)throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		boolean addTf = false;
		try{
			String sql = "insert into user(" +
					LOGINID + "," + PASSWORD + "," +
					USERNAME + "," + USERSEX + "," +
					EMAIL + "," + PHONE + "," + SIGN + "," +
					HEADSCULPTUREURL + "," + REGISTATIONDATE  + ") values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserSex());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getSign());
			pstmt.setString(8, user.getHeadSculptureUrl());
			pstmt.setString(9, user.getRegistationDate());
			int row = pstmt.executeUpdate();
			if (row > 0) {
				addTf = true;
			}
			pstmt.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(con);
		return addTf;
      }

	
	
	//账号是否已经被注册
	public boolean selectUserOfLoginId(String loginId)throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		boolean selectTf = true;
		try{
			String sql = "select userId from user where " + LOGINID + " = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			ResultSet rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				selectTf = false;
			}else {
				selectTf = true;
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(con);
		return selectTf;//返回值为false说明该账号不可使用（查询值不为空）
      }
	
	//手机号是否被绑定
	public boolean selectUserOfPhone(String phone)throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		boolean selectTf = true;
		try{
			String sql = "select userId from user where " + PHONE + " = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, phone);
			ResultSet rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				selectTf = false;
			}else {
				selectTf = true;
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
			selectTf = true;
		}
		JdbcUtils_DBCP.release(con);
		return selectTf;
      }
	
	
	//账号密码登录
	public User selectUserOfLoginIdAndPasswordToLogin(String loginId,String password)throws Exception{
		User user = null;
		Connection con = JdbcUtils_DBCP.getConnection();
		try{
			String sql = "select * from user where binary loginId = ? and password = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(USERID));
				user.setLoginId(rs.getString(LOGINID));
				user.setPassword(rs.getString(PASSWORD));
				user.setUserName(rs.getString(USERNAME));
				user.setUserSex(rs.getString(USERSEX));
				user.setEmail(rs.getString(EMAIL));
				user.setPhone(rs.getString(PHONE));
				user.setSign(rs.getString(SIGN));
				user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
				user.setRegistationDate(rs.getString(REGISTATIONDATE));
				user.setUserStateId(rs.getInt(USERSTATEID));
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(con);
		System.out.println("user"+user);
		return user;
      }
	
	//用于手机号密码登录
	public User selectUserOfPhoneAndPasswordToLogin(String phone,String password)throws Exception{
		User user = null;
		Connection con = JdbcUtils_DBCP.getConnection();
		try{
			String sql = "select * from user where binary phone = ? and password = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, password);
			System.out.println("phone+password"+phone+"  "+password);
			ResultSet rs=pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(USERID));
				user.setLoginId(rs.getString(LOGINID));
				user.setPassword(rs.getString(PASSWORD));
				user.setUserName(rs.getString(USERNAME));
				user.setUserSex(rs.getString(USERSEX));
				user.setEmail(rs.getString(EMAIL));
				user.setPhone(rs.getString(PHONE));
				user.setSign(rs.getString(SIGN));
				user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
				user.setRegistationDate(rs.getString(REGISTATIONDATE));
				user.setUserStateId(rs.getInt(USERSTATEID));
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(con);
		return user;
      }
	
	//根据用户编号查询用户信息
	public User selectUserInfoOfUserId(int userId)throws Exception{
		System.out.println("userId根据用户编号查询用户信息" );
		User user = new User();
		Connection con = JdbcUtils_DBCP.getConnection();
		try{
			String sql = "select * from user where userId = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user.setUserId(rs.getInt(USERID));
				user.setLoginId(rs.getString(LOGINID));
				user.setPassword(rs.getString(PASSWORD));
				user.setUserName(rs.getString(USERNAME));
				user.setUserSex(rs.getString(USERSEX));
				user.setEmail(rs.getString(EMAIL));
				user.setPhone(rs.getString(PHONE));
				user.setSign(rs.getString(SIGN));
				user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
				user.setRegistationDate(rs.getString(REGISTATIONDATE));
				user.setUserStateId(rs.getInt(USERSTATEID));
			}else {
				user = null;
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
			user = null;
		}
		JdbcUtils_DBCP.release(con);
		return user;
      }

	
	//根据用户编号查询收藏歌曲
	public ArrayList<Song> getUserCollectionSongs(int userId)throws Exception{
		System.out.println(userId+"查询收藏歌曲" );
		ArrayList<Song> songArrayList = new ArrayList<Song>();
		Connection con = JdbcUtils_DBCP.getConnection();
		ResultSet rs = null;PreparedStatement pstmt = null;
		//先获取歌曲ID再根据ID查找信息
		try{
			String sql = "select songId from usersongcollection where userId = ?";
        	pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setSongId(rs.getInt("songId"));
				songArrayList.add(song);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("收藏歌曲ID数量："+songArrayList.size());
		
		for(Song song : songArrayList){
			try{
				String sql = "select songName,singerName,publishDate,songTime,songUrl from song where songId = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, song.getSongId());
				rs=pstmt.executeQuery();
				if (rs != null && rs.next()) {
					song.setSingerName("许嵩");
					song.setSongName(rs.getString("songName"));
					song.setPublishDate(rs.getString("publishDate"));
					song.setSongTime(rs.getString("songTime"));
					song.setSongUrl(rs.getString("songUrl"));
				}
				rs.close();
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("收藏歌曲结果："+songArrayList );
		JdbcUtils_DBCP.release(con);
		return songArrayList;
      }
	
	
	//修改/上传用户头像
	public boolean updateUserHeaderImg(String headSculptureUrl, int userId)throws Exception{
		int row = 0;
		Connection con = JdbcUtils_DBCP.getConnection();
		boolean updateOrNot = false;
		try{
			String sql = "update user set headSculptureUrl = ? where userId = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, headSculptureUrl);
			pstmt.setInt(2, userId);
			row = pstmt.executeUpdate();
			if (row > 0) {
				updateOrNot = true;
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
			updateOrNot = false;
		}
		JdbcUtils_DBCP.release(con);
		System.out.println("头像更新："+updateOrNot);
		return updateOrNot;
      }
	
	//收藏歌曲
	public boolean userCollecitonUpdate(int userId,int songId)throws Exception{
		//true为错误情况，false为正常情况
		Connection con = JdbcUtils_DBCP.getConnection();
		boolean hasFind = false;
		System.out.println("收藏数量更新："+userId+songId);
		try{
			String sql = "select * from usersongcollection where userId = ? and songId = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, songId);
			ResultSet rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				hasFind = true;
			}else {
				hasFind = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(!hasFind){
			try{
				String sql = "insert into usersongcollection(" +
						USERID + "," + "songId" + ") values(?,?)";
				PreparedStatement pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, userId);
				pstmt.setInt(2, songId);
				int row = pstmt.executeUpdate();
				if (row <= 0) {
					hasFind = true;
				}
				pstmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("fianl："+hasFind);
		JdbcUtils_DBCP.release(con);
		return hasFind;
      }
	
	
	//查询用户所有信息
	public ArrayList<User> selectAllUserInfoLimit() throws Exception{
		ArrayList<User> userList = new ArrayList<User>();
		Connection con = JdbcUtils_DBCP.getConnection();
		try{
			String sql = "select * from user";
			PreparedStatement pstmt=con.prepareStatement(sql);;
			ResultSet rs=pstmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt(USERID));
					user.setLoginId(rs.getString(LOGINID));
					user.setPassword(rs.getString(PASSWORD));
					user.setUserName(rs.getString(USERNAME));
					user.setUserSex(rs.getString(USERSEX));
					user.setEmail(rs.getString(EMAIL));
					user.setPhone(rs.getString(PHONE));
					user.setSign(rs.getString(SIGN));
					user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
					user.setRegistationDate(rs.getString(REGISTATIONDATE));
//					user.setUserStateId(rs.getInt(USERSTATEID));
					userList.add(user);
				}
			}else {
				userList = null;
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("userList个数："+userList);
		JdbcUtils_DBCP.release(con);
		return userList;
      }
	
	public void deleteUser(int userId)throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM user WHERE userId=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			pstmt.close();
			JdbcUtils_DBCP.release(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userId+"成功被删除");
		
	}
	

	
}
