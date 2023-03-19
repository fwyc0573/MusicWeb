package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CdDAO {
	final String CDID = "CDId";//ר�����
	final String CDNAME = "CDName";//ר������
	final String COVERURL = "coverUrl";//ר������ͼƬ
	final String SONGCOUNT = "songCount";//��������
	final String PUBLISHDATE = "publishDate";//��������
	final String INTRODUCE = "introduce";//ר�����
	
	//����cd��Id��ѯר����
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
	
	//���ո赥�ȶȽ����ѯ����ר����Ϣ
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
	
	//����CD����ģ����ѯ
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
