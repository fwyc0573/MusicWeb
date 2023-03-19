package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CdDAO {
	final String CDID = "CDId";//专辑编号
	final String CDNAME = "CDName";//专辑名字
	final String COVERURL = "coverUrl";//专辑封面图片
	final String SONGCOUNT = "songCount";//歌曲数量
	final String PUBLISHDATE = "publishDate";//发行日期
	final String INTRODUCE = "introduce";//专辑简介
	
	//根据cd的Id查询专辑表
	public Cd selectCdOfCdId(Connection con, int cdId) throws Exception{
		Cd cd = new Cd();
		
		String sql = "select * from cd where CDId = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cdId);
		ResultSet rs=pstmt.executeQuery();
	
		while(rs.next()){
			cd.setCDId(rs.getInt(CDID));
			cd.setCDName(rs.getString(CDNAME));
			cd.setCoverUrl(rs.getString(COVERURL));
			cd.setSongCount(rs.getInt(SONGCOUNT));
			cd.setPublishDate(rs.getString(PUBLISHDATE));
			cd.setIntroduce(rs.getString(INTRODUCE));
		}
		rs.close();
		pstmt.close();
		return cd;
      }
	
	//按照歌单热度降序查询所有专辑信息
	public ArrayList<Cd> cdInfoByhot(Connection con) {	
		ArrayList<Cd> cdList = new ArrayList<Cd>();
		if (con != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					String sql = "select * from cd order by CDId ASC";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							Cd cd = new Cd();
							cd.setCDId(rs.getInt(CDID));
							cd.setCDName(rs.getString(CDNAME));
							cd.setCoverUrl(rs.getString(COVERURL));
							cd.setSongCount(rs.getInt(SONGCOUNT));
							cd.setPublishDate(rs.getString(PUBLISHDATE));
							cd.setIntroduce(rs.getString(INTRODUCE));
							cdList.add(cd);
						}
						rs.close();
						pstmt.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					cdList = null;
				}
		}
		return cdList;
	}
	
	//根据CD名称模糊查询
	public ArrayList<Cd> selectCDOfCDName(String CDName) throws SQLException{
		Connection connection = JdbcUtils_DBCP.getConnection();
		ArrayList<Cd> cdOfCDName = new ArrayList<Cd>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from cd where CDName like ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, CDName);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cd cd = new Cd();
					cd.setCDId(rs.getInt(CDID));
					cd.setCDName(rs.getString(CDNAME));
					cd.setPublishDate(rs.getString(PUBLISHDATE));
					cd.setSongCount(rs.getInt(SONGCOUNT));
					cd.setCoverUrl(rs.getString(COVERURL));
					cd.setIntroduce(rs.getString(INTRODUCE));
					cdOfCDName.add(cd);
				}
				pstmt.close();
				rs.close();
				JdbcUtils_DBCP.release(connection);
			}else {
				cdOfCDName = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cdOfCDName;
	}
	//
	
}
