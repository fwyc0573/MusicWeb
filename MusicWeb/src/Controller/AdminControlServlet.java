package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Song;
import model.SongDAO;
import model.User;
import model.UserDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminControlServlet extends HttpServlet {
	public static ArrayList<User> RecorduserInfo = null;
	public static ArrayList<Song> RecordusongInfo = null;
	public AdminControlServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		//�û���Ϣ��ѯ
		if ("userInfo".equals(action)) {
			try {
				this.UserInfo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//������Ϣ��ѯ
		if ("songInfo".equals(action)) {
			try {
				this.SongInfo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//����ɾ��
		if ("songdelete".equals(action)) {
			try {
				this.songdelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�û�ɾ��
		if ("userdelete".equals(action)) {
			try {
				this.userdelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	//�û�����
	public void UserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//��ʼҳ
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		
		if(RecorduserInfo == null || startPage==1){
			//������������������
			UserDAO userService = new UserDAO();
			ArrayList<User> userInfo = userService.selectAllUserInfoLimit();
			RecorduserInfo = userInfo;
		}
		
		int allUserRowsNum = RecorduserInfo.size();//��ѯ����������
		System.out.println("�������û����У�" + allUserRowsNum);

		int startRow = (startPage - 1) * 15;//��ʼ��
		int rowNum = 15;//ÿҳ��ʾ����
		int PageNum = allUserRowsNum % 15 == 0 ? allUserRowsNum / rowNum : (allUserRowsNum / rowNum) + 1;//���㹲��ҳ
		
		System.out.println("startRow:"+startRow);
		JSONArray userJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//��ǰ��βҳ���Ա����������е�������
			 num = allUserRowsNum - 15*(PageNum-1);
		System.out.println("num:"+num);
		
		//�洢�û���Ϣ����ҳ������ǰҳ��
		JSONObject userInfoPageNumAndCurrentPage = new JSONObject();
		userInfoPageNumAndCurrentPage.put("pagesNum", PageNum);//�洢��ҳ��
		userInfoPageNumAndCurrentPage.put("currentPage", startPage);//�洢��ǰҳ
		
		if(allUserRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				userJsonArray.add(RecorduserInfo.get(i));
			}
		}
		userInfoPageNumAndCurrentPage.put("userInfo", userJsonArray);
		System.out.println("�û���Ϣ��" + userInfoPageNumAndCurrentPage);
		out.print(userInfoPageNumAndCurrentPage);
    }
	
	//��������
	public void SongInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String startPageString = request.getParameter("startPage");//��ʼҳ
		int startPage = 1;
		System.out.println("startPage"+startPage);
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		System.out.println("���Ȼ�ȡstartPage:"+startPage);
		
		if(RecordusongInfo == null || startPage ==1){
			System.out.println("�ڿ�������������" + startPageString);
			//������������������
			SongDAO songService = new SongDAO();
			ArrayList<Song> songInfo = songService.selectSongLimit();
			RecordusongInfo = songInfo;
		}
		
		int allUserRowsNum = RecordusongInfo.size();//��ѯ����������
		System.out.println("���и������У�" + allUserRowsNum);

		int startRow = (startPage - 1) * 15;//��ʼ��
		int rowNum = 15;//ÿҳ��ʾ����
		int PageNum = allUserRowsNum % 15 == 0 ? allUserRowsNum / rowNum : (allUserRowsNum / rowNum) + 1;//���㹲��ҳ
		
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//��ǰ��βҳ���Ա����������е�������
			 num = allUserRowsNum - 15*(PageNum-1);
		System.out.println("num:"+num);
		
		//�洢�û���Ϣ����ҳ������ǰҳ��
		JSONObject songInfoPageNumAndCurrentPage = new JSONObject();
		songInfoPageNumAndCurrentPage.put("pagesNum", PageNum);//�洢��ҳ��
		songInfoPageNumAndCurrentPage.put("currentPage", startPage);//�洢��ǰҳ
		
		if(allUserRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				songJsonArray.add(RecordusongInfo.get(i));
			}
		}
		songInfoPageNumAndCurrentPage.put("oneSongInfo", songJsonArray);
		System.out.println("������Ϣ��" + songInfoPageNumAndCurrentPage);
		out.print(songInfoPageNumAndCurrentPage);
    }
	
	//����ɾ��
	public void songdelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		
		String songIdString = request.getParameter("songId");//��ȡ����ID
		System.out.println("����servelt��IDΪ��"+songIdString);
		int songId = Integer.parseInt(songIdString);
		
		SongDAO songService = new SongDAO();
		songService.deleteSong(songId);
		out.print("true");
    }
	
	//����ɾ��
	public void userdelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		
		String userString = request.getParameter("userId");//��ȡ����ID
		System.out.println("����servelt��IDΪ��"+userString);
		int userId = Integer.parseInt(userString);
		
		UserDAO songService = new UserDAO();
		songService.deleteUser(userId);
		out.print("true");
    }
	
	public void init() throws ServletException {
		
	}

}
