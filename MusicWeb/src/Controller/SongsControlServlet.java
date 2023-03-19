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
		//���ò�ѯ�����ķ���
		String action = request.getParameter("action");
		//�ȶȰ�
		if ("play_rank".equals(action)) {
			this.songsCollectionCount(request, response);
//			System.out.println("play_rank->"+action);
		}
		//���ذ�
		if ("download_rank".equals(action)) {
			this.songsDownloadCount(request, response);
//			System.out.println("download_rank->"+action);
		}
		
		//�ղذ�
		if ("collection_rank".equals(action)) {
			this.songsPlayCount(request, response);
//			System.out.println("collection_rank->"+action);
		}
		//�ȶ��Ƽ�
		if ("hot_give".equals(action)) {
			try {
				this.HotGive2(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�ȶ�ˢ��
		if ("flashRecord".equals(action)) {
			try {
				this.flashRecord(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�ղ�ˢ��
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
		//ר����Ϣ��ʾ
		String action = request.getParameter("action");
		
		//����ר����Ϣ��ʾ
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
	
	//�ղذ�
	public void songsCollectionCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			System.out.println("�����ղذ�");
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.songArrayListCollectionCount(con);
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				//��ȡר�����
				int cdId = song.getCDId();
				//��ȡר����Ϣ
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//��ȡ���ֱ��
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
				out.print("300");//û������
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//���ذ�
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
				//��ȡר�����
				int cdId = song.getCDId();
				//��ȡר����Ϣ
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//��ȡ���ֱ��
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
				out.print("300");//û������
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ȶȰ�
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
				//��ȡר�����
				int cdId = song.getCDId();
				//��ȡר����Ϣ
				Cd cd = cdService.selectCdOfCdId(con,cdId);
				//��ȡ���ֱ��
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
				out.print("300");//û������
			}
			System.out.println(songInfoArray);
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//�ȶ�ר���Ƽ�
	public void HotGive2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CdDAO cdservice = new CdDAO();
		
		Connection con = JdbcUtils_DBCP.getConnection();
		ArrayList<Cd> HotCdinfo = cdservice.cdInfoByhot(con);
		JSONObject allSongLIstObject = new JSONObject();
		int cdListNum = 0;
		for (int i = 0; i < HotCdinfo.size(); i++) {//�����赥
			JSONObject oneCd = new JSONObject();
			
			oneCd.put("HotCdinfo", HotCdinfo.get(i));//ר����Ϣ
			allSongLIstObject.put(cdListNum, oneCd);
			cdListNum +=1;
		}
		System.out.println(allSongLIstObject);
		out.print(allSongLIstObject);
		JdbcUtils_DBCP.release(con);
	}
	
	//����ר����Ϣչʾ
	public void CDSingleshow(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String CDIdString = request.getParameter("CDId");
		System.out.println("����servelt��IDΪ��"+CDIdString);
		int CDid = Integer.parseInt(CDIdString);
		
		try {	
			Connection con = JdbcUtils_DBCP.getConnection();
			
			SongDAO songService = new SongDAO();
			ArrayList<Song> songArrayList = songService.SongsInCD(con,CDid);
			
			CdDAO cdService = new CdDAO();
			JSONArray songInfoArray = new JSONArray();
			
			for (Song song : songArrayList) {
				JSONObject oneSongAllInfo = new JSONObject();
				//���������Ϣ
				oneSongAllInfo.put("song", song);
				songInfoArray.add(oneSongAllInfo);
			}
			int songInfoArrLen = songInfoArray.size();
			System.out.println("songInfoArrLen->"+songInfoArrLen);
			
			//����CD��ţ���ѯ��CD������Ϣ
			CdCommentDAO userCDCommentService = new CdCommentDAO();
			UserDAO userService = new UserDAO();
			ArrayList<CdComment> userCDCommentArr = userCDCommentService.selectCDCommOfCDId(CDid);
			JSONArray allCommJsonArray = new JSONArray();
			for (CdComment userSongListComment : userCDCommentArr) {	
				int commUserId = userSongListComment.getUserId();//�����˱��
				User commUserInfo = userService.selectUserInfoOfUserId(commUserId);//��ѯ��������Ϣ
				
				//�����˿ɲ鿴��Ϣ
				User commCanSeeUserInfo = new User();
				commCanSeeUserInfo.setUserId(commUserId);//�����˱��
				commCanSeeUserInfo.setUserName(commUserInfo.getUserName());//����������
				commCanSeeUserInfo.setHeadSculptureUrl(commUserInfo.getHeadSculptureUrl());//������ͷ���ַ
				JSONObject oneCommJsonObject = new JSONObject();
				oneCommJsonObject.put("commentUserInfo", commCanSeeUserInfo);
				oneCommJsonObject.put("commentContent", userSongListComment);
				allCommJsonArray.add(oneCommJsonObject);
			}
			System.out.println("allCommJsonArray"+allCommJsonArray);
			
			//һ���赥��ȫ����Ϣ
			JSONObject songListAllInfo = new JSONObject();
			songListAllInfo.put("CDtInfo", songInfoArray);
			songListAllInfo.put("CDComm", allCommJsonArray);
			
			System.out.println("songListAllInfo"+songListAllInfo);
			if (songInfoArrLen > 0) {
				out.print(songListAllInfo);
			}else {
				out.print("300");//û������
			}
			JdbcUtils_DBCP.release(con);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    //�����¼ˢ�£��ȶȣ�
	public void flashRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//��ȡ�������
		System.out.println("����ID��" + songId);
		SongDAO songService = new SongDAO();
		songService.flashRecord(songId);
		System.out.println("�ɹ�ˢ���ȶȼ�¼");
	}
	
    //�ղ�����ˢ��
	public void flashRecordCollection(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//��ȡ�������
		System.out.println("����ID��" + songId);
		
		SongDAO songService = new SongDAO();
		Connection con = JdbcUtils_DBCP.getConnection();
		songService.flashRecordCollection(songId);
		System.out.println("�ɹ�ˢ���ղؼ�¼");
		JdbcUtils_DBCP.release(con);
	}
}
