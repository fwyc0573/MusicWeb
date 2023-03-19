package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class SongDAO {
	final String SONGID = "songId";//歌曲编号
	final String SONGNAME = "songName";//歌曲名字
	final String SINGERNAME = "singerName";//歌手编号
	final String CDID = "CDId";//专辑编号
	final String PLAYCOUNT = "playCount";//播放次数
	final String DOWNLOADCOUNT = "downloadCount";//下载次数
	final String COLLECTIONCOUNT = "collectionCount";//收藏次数
	final String PUBLISHDATE = "publishDate";//歌曲发行时间
	final String SONGURL = "songUrl";//歌曲链接
	final String CYRICURL = "cyricUrl";//歌词连接地址
	final String SONGTIME = "songTime";//歌曲时间
	
	//歌曲收藏榜单
	public ArrayList<Song> songArrayListCollectionCount(Connection con) throws Exception{
		ArrayList<Song> songArrayList = new ArrayList<Song>();
		String sql = "select * from song ORDER BY collectionCount desc LIMIT 0,50";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
	
		while(rs.next()){
			Song song = new Song();
			song.setSongId(rs.getInt(SONGID));
			song.setSongName(rs.getString(SONGNAME));
			song.setCDId(rs.getInt(CDID));
			song.setPlayCount(rs.getInt(PLAYCOUNT));
			song.setDownloadCount(rs.getInt(DOWNLOADCOUNT));
			song.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
			song.setPublishDate(rs.getString(PUBLISHDATE));
			song.setSongUrl(rs.getString(SONGURL));
			song.setCyricUrl(rs.getString(CYRICURL));
			song.setSongTime(rs.getString(SONGTIME));
			songArrayList.add(song);
		}
		rs.close();
		pstmt.close();
		return songArrayList;
      }
	
	//歌曲下载榜单
	public ArrayList<Song> songArrayListDownloadCount(Connection con) throws Exception{
		ArrayList<Song> songArrayList = new ArrayList<Song>();
		String sql = "select * from song ORDER BY downloadCount desc LIMIT 0,50";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
	
		while(rs.next()){
			Song song = new Song();
			song.setSongId(rs.getInt(SONGID));
			song.setSongName(rs.getString(SONGNAME));
			song.setCDId(rs.getInt(CDID));
			song.setPlayCount(rs.getInt(PLAYCOUNT));
			song.setDownloadCount(rs.getInt(DOWNLOADCOUNT));
			song.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
			song.setPublishDate(rs.getString(PUBLISHDATE));
			song.setSongUrl(rs.getString(SONGURL));
			song.setCyricUrl(rs.getString(CYRICURL));
			song.setSongTime(rs.getString(SONGTIME));
			songArrayList.add(song);
		}
		rs.close();
		pstmt.close();
		return songArrayList;
      }
	
	//歌曲热度榜单
	public ArrayList<Song> songArrayListPlayCount(Connection con) throws Exception{
		ArrayList<Song> songArrayList = new ArrayList<Song>();
		String sql = "select * from song ORDER BY playCount desc LIMIT 0,50";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
	
		while(rs.next()){
			Song song = new Song();
			song.setSongId(rs.getInt(SONGID));
			song.setSongName(rs.getString(SONGNAME));
			song.setCDId(rs.getInt(CDID));
			song.setPlayCount(rs.getInt(PLAYCOUNT));
			song.setDownloadCount(rs.getInt(DOWNLOADCOUNT));
			song.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
			song.setPublishDate(rs.getString(PUBLISHDATE));
			song.setSongUrl(rs.getString(SONGURL));
			song.setCyricUrl(rs.getString(CYRICURL));
			song.setSongTime(rs.getString(SONGTIME));
			songArrayList.add(song);
		}
		rs.close();
		pstmt.close();
		return songArrayList;
      }
	
	//根据CDID搜索对应歌曲
	public ArrayList<Song> SongsInCD(Connection con,int CDid) throws Exception{
		ArrayList<Song> songArrayList = new ArrayList<Song>();
		String sql = "select * from song where CDId = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, CDid);
		ResultSet rs=pstmt.executeQuery();
	
		while(rs.next()){
			Song song = new Song();
			song.setSongId(rs.getInt(SONGID));
			song.setSongName(rs.getString(SONGNAME));
			song.setPublishDate(rs.getString(PUBLISHDATE));
			song.setSongUrl(rs.getString(SONGURL));
			song.setSongTime(rs.getString(SONGTIME));
			songArrayList.add(song);
		}
		rs.close();
		pstmt.close();
		System.out.println(songArrayList);
		return songArrayList;
      }

	public void deleteSong(int songId)throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM song WHERE songId=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.executeUpdate();
			pstmt.close();
			JdbcUtils_DBCP.release(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(songId+"成功被删除");
		
	}
	
	//热度记录刷新
	public void flashRecord(int songId) throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		if (con != null) {
			PreparedStatement pstmt = null;			
			try {
				String sql = "update song set playCount = (playCount + 1) where songId = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, songId);
				pstmt.executeUpdate();
				pstmt.close();
				JdbcUtils_DBCP.release(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//收藏记录刷新
	public void flashRecordCollection(int songId) throws Exception{
		Connection con = JdbcUtils_DBCP.getConnection();
		if (con != null) {		
			PreparedStatement pstmt = null;			
			try {
				String sql = "update song set collectionCount = (collectionCount + 1) where songId = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, songId);
				pstmt.executeUpdate();
				pstmt.close();
				JdbcUtils_DBCP.release(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//根据歌曲关键字模糊查询
	public ArrayList<Song> selectSongOfSongName(String songName) throws SQLException{
		Connection connection = JdbcUtils_DBCP.getConnection();
		ArrayList<Song> songsOfSongName = new ArrayList<Song>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from song where songName like ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, songName);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Song song = new Song();
					song.setSongId(rs.getInt(SONGID));//歌曲ID
					song.setSongName(rs.getString(SONGNAME));//歌曲名称
					song.setSongUrl(rs.getString(SONGURL));//歌曲链接
					song.setSongTime(rs.getString(SONGTIME));//歌曲时间
					song.setPublishDate(rs.getString(PUBLISHDATE));
					song.setSingerName(rs.getString(SINGERNAME));//歌手名称
					songsOfSongName.add(song);
				}
				pstmt.close();
				rs.close();
				JdbcUtils_DBCP.release(connection);
			}else {
				songsOfSongName = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsOfSongName;
	}
	
	//分页查询所有歌曲
	public ArrayList<Song> selectSongLimit()throws Exception{
		Connection connection = JdbcUtils_DBCP.getConnection();
		ArrayList<Song> songLimitArrayList = new ArrayList<Song>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from song";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Song song = new Song();
						song.setSongId(rs.getInt(SONGID));
						song.setSongName(rs.getString(SONGNAME));
						song.setCDId(rs.getInt(CDID));
						song.setPublishDate(rs.getString(PUBLISHDATE));
						song.setSongTime(rs.getString(SONGTIME));
						songLimitArrayList.add(song);
					}
					pstmt.close();
					rs.close();
					JdbcUtils_DBCP.release(connection);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return songLimitArrayList;
	}

}
	
