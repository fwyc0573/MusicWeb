package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cd;
import model.CdComment;
import model.CdCommentDAO;
import model.CdDAO;
import model.JdbcUtils_DBCP;
import model.Song;
import model.SongDAO;
import model.User;
import model.UserDAO;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SongsControlServlet extends HttpServlet {

	public SongsControlServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//调用查询歌曲的服务
		String action = request.getParameter("action");
		//热度榜
		if ("play_rank".equals(action)) {
			this.songsCollectionCount(request, response);
//			System.out.println("play_rank->"+action);
		}
		//下载榜
		if ("download_rank".equals(action)) {
			this.songsDownloadCount(request, response);
//			System.out.println("download_rank->"+action);
		}
		
		//收藏榜
		if ("collection_rank".equals(action)) {
			this.songsPlayCount(request, response);
//			System.out.println("collection_rank->"+action);
		}
		//热度推荐
		if ("hot_give".equals(action)) {
			try {
				this.HotGive2(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//热度刷新
		if ("flashRecord".equals(action)) {
			try {
				this.flashRecord(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//收藏刷新
		if ("flashRecordCollection".equals(action)) {
			try {
				this.flashRecordCollection(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("fianl->"+action);
 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//专辑信息显示
		String action = request.getParameter("action");
		
		//单张专辑信息显示
		if ("singleCDShow".equals(action)) {
			try {
				this.CDSingleshow(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("singleCDShow->"+action);
		}
	}

	public void init() throws ServletException {
	}
	
	//收藏榜
	public void songsCollectionCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			System.out.println("进入收藏榜");
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.songArrayListCollectionCount(con);
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				//获取专辑编号
				int cdId = song.getCDId();
				//获取专辑信息
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//获取歌手编号
				int singerId = song.getSingerId();
				JSONObject oneSongAllInfo = new JSONObject();
				oneSongAllInfo.put("song", song);
				oneSongAllInfo.put("cd", cd);
				
				songInfoArray.add(oneSongAllInfo);
			}
			int songInfoArrLen = songInfoArray.size();
			if (songInfoArrLen > 0) {
				out.print(songInfoArray);
				
			}else {
				out.print("300");//没有数据
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//下载榜
	public void songsDownloadCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.songArrayListDownloadCount(con);
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				//获取专辑编号
				int cdId = song.getCDId();
				//获取专辑信息
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//获取歌手编号
				int singerId = song.getSingerId();
				JSONObject oneSongAllInfo = new JSONObject();
				oneSongAllInfo.put("song", song);
				oneSongAllInfo.put("cd", cd);
				
				songInfoArray.add(oneSongAllInfo);
			}
			int songInfoArrLen = songInfoArray.size();
			if (songInfoArrLen > 0) {
				out.print(songInfoArray);
				
			}else {
				out.print("300");//没有数据
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//热度榜
	public void songsPlayCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.songArrayListPlayCount(con);
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				//获取专辑编号
				int cdId = song.getCDId();
				//获取专辑信息
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//获取歌手编号
				int singerId = song.getSingerId();
				JSONObject oneSongAllInfo = new JSONObject();
				oneSongAllInfo.put("song", song);
				oneSongAllInfo.put("cd", cd);
				
				songInfoArray.add(oneSongAllInfo);
			}
			int songInfoArrLen = songInfoArray.size();
			if (songInfoArrLen > 0) {
				out.print(songInfoArray);
				
			}else {
				out.print("300");//没有数据
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//热度专辑推荐
	public void HotGive2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CdDAO cdservice = new CdDAO();
		
		Connection con = JdbcUtils_DBCP.getConnection();
		ArrayList<Cd> HotCdinfo = cdservice.cdInfoByhot(con);
		JSONObject allSongLIstObject = new JSONObject();
		int cdListNum = 0;
		for (int i = 0; i < HotCdinfo.size(); i++) {//遍历歌单
			JSONObject oneCd = new JSONObject();
			
			oneCd.put("HotCdinfo", HotCdinfo.get(i));//专辑信息
			allSongLIstObject.put(cdListNum, oneCd);
			cdListNum +=1;
		}
		System.out.println(allSongLIstObject);
		out.print(allSongLIstObject);
		JdbcUtils_DBCP.release(con);
	}
	
	//单个专辑信息展示
	public void CDSingleshow(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String CDIdString = request.getParameter("CDId");
		System.out.println("传入servelt的ID为："+CDIdString);
		int CDid = Integer.parseInt(CDIdString);
		
		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.SongsInCD(con,CDid);
			
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				JSONObject oneSongAllInfo = new JSONObject();
				//放入歌曲信息
				oneSongAllInfo.put("song", song);
				songInfoArray.add(oneSongAllInfo);
			}
			int songInfoArrLen = songInfoArray.size();
			System.out.println("songInfoArrLen->"+songInfoArrLen);
			
			//根据CD编号，查询该CD评论信息
			CdCommentDAO userCDCommentService = new CdCommentDAO();
			UserDAO userService = new UserDAO();
			ArrayList<CdComment> userCDCommentArr = userCDCommentService.selectCDCommOfCDId(CDid);
			JSONArray allCommJsonArray = new JSONArray();
			for (CdComment userSongListComment : userCDCommentArr) {	
				int commUserId = userSongListComment.getUserId();//评论人编号
				User commUserInfo = userService.selectUserInfoOfUserId(commUserId);//查询评论人信息
				
				//评论人可查看信息
				User commCanSeeUserInfo = new User();
				commCanSeeUserInfo.setUserId(commUserId);//评论人编号
				commCanSeeUserInfo.setUserName(commUserInfo.getUserName());//评论人名称
				commCanSeeUserInfo.setHeadSculptureUrl(commUserInfo.getHeadSculptureUrl());//评论人头像地址
				JSONObject oneCommJsonObject = new JSONObject();
				oneCommJsonObject.put("commentUserInfo", commCanSeeUserInfo);
				oneCommJsonObject.put("commentContent", userSongListComment);
				allCommJsonArray.add(oneCommJsonObject);
			}
			System.out.println("allCommJsonArray"+allCommJsonArray);
			
			//一个歌单的全部信息
			JSONObject songListAllInfo = new JSONObject();
			songListAllInfo.put("CDtInfo", songInfoArray);
			songListAllInfo.put("CDComm", allCommJsonArray);
			
			System.out.println("songListAllInfo"+songListAllInfo);
			if (songInfoArrLen > 0) {
				out.print(songListAllInfo);
			}else {
				out.print("300");//没有数据
			}
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    //听歌记录刷新（热度）
	public void flashRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//获取歌曲编号
		System.out.println("歌曲ID：" + songId);
		SongDAO songService = new SongDAO();
		songService.flashRecord(songId);
		System.out.println("成功刷新热度记录");
	}
	
    //收藏数量刷新
	public void flashRecordCollection(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//获取歌曲编号
		System.out.println("歌曲ID：" + songId);
		
		SongDAO songService = new SongDAO();
		Connection con = JdbcUtils_DBCP.getConnection();
		songService.flashRecordCollection(songId);
		System.out.println("成功刷新收藏记录");
		JdbcUtils_DBCP.release(con);
	}
}
