package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CdCommentDAO {
	final String UCDCOMMID = "uCDCommId";
	final String USERID = "userId";
	final String CDID = "CDId";
	final String CDCOMMTEXT = "CDCommText";
	final String CDCOMMDATE = "CDCommDate";
	
	//查询CD的评论信息
	public ArrayList<CdComment> selectCDCommOfCDId(int CDId)throws SQLException{
		Connection connection = JdbcUtils_DBCP.getConnection();
		ArrayList<CdComment> userCDCommentArrayList = new ArrayList<CdComment>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from usercdcomment where CDId = ?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, CDId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						CdComment CdComment = new CdComment();
						CdComment.setuCDCommId(rs.getInt(UCDCOMMID));
						CdComment.setUserId(rs.getInt(USERID));
						CdComment.setCDCommText(rs.getString(CDCOMMTEXT));
						CdComment.setCDCommDate(rs.getString(CDCOMMDATE));
						userCDCommentArrayList.add(CdComment);
					}
					rs.close();
					pstmt.close();
				}else {
					userCDCommentArrayList = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				userCDCommentArrayList = null;
			}
		}
		JdbcUtils_DBCP.release(connection);
		return userCDCommentArrayList;
	}
		
	//添加歌单评论信息
	public boolean addCDComm(CdComment userCDComment)throws SQLException{
		Connection connection = JdbcUtils_DBCP.getConnection();
		int row = 0;
		boolean addOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			try {
				String sql = "insert into usercdcomment(userId, CDId, CDCommText, CDCommDate)  values(?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userCDComment.getUserId());
				pstmt.setInt(2, userCDComment.getCDId());
				pstmt.setString(3, userCDComment.getCDCommText());
				pstmt.setString(4, userCDComment.getCDCommDate());
				row = pstmt.executeUpdate();
				if (row > 0) {
					addOrNot = true;
				}
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		JdbcUtils_DBCP.release(connection);
		return addOrNot;
	}
	
}
