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
		//用户信息查询
		if ("userInfo".equals(action)) {
			try {
				this.UserInfo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//歌曲信息查询
		if ("songInfo".equals(action)) {
			try {
				this.SongInfo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//歌曲删除
		if ("songdelete".equals(action)) {
			try {
				this.songdelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//用户删除
		if ("userdelete".equals(action)) {
			try {
				this.userdelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	//用户搜素
	public void UserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//开始页
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		
		if(RecorduserInfo == null || startPage==1){
			//计算搜索到的总行数
			UserDAO userService = new UserDAO();
			ArrayList<User> userInfo = userService.selectAllUserInfoLimit();
			RecorduserInfo = userInfo;
		}
		
		int allUserRowsNum = RecorduserInfo.size();//查询到的总行数
		System.out.println("搜索到用户共有：" + allUserRowsNum);

		int startRow = (startPage - 1) * 15;//开始行
		int rowNum = 15;//每页显示行数
		int PageNum = allUserRowsNum % 15 == 0 ? allUserRowsNum / rowNum : (allUserRowsNum / rowNum) + 1;//计算共分页
		
		System.out.println("startRow:"+startRow);
		JSONArray userJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//当前是尾页，对遍历数量进行单独处理
			 num = allUserRowsNum - 15*(PageNum-1);
		System.out.println("num:"+num);
		
		//存储用户信息与总页数，当前页数
		JSONObject userInfoPageNumAndCurrentPage = new JSONObject();
		userInfoPageNumAndCurrentPage.put("pagesNum", PageNum);//存储总页数
		userInfoPageNumAndCurrentPage.put("currentPage", startPage);//存储当前页
		
		if(allUserRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				userJsonArray.add(RecorduserInfo.get(i));
			}
		}
		userInfoPageNumAndCurrentPage.put("userInfo", userJsonArray);
		System.out.println("用户信息：" + userInfoPageNumAndCurrentPage);
		out.print(userInfoPageNumAndCurrentPage);
    }
	
	//歌曲搜素
	public void SongInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String startPageString = request.getParameter("startPage");//开始页
		int startPage = 1;
		System.out.println("startPage"+startPage);
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		System.out.println("首先获取startPage:"+startPage);
		
		if(RecordusongInfo == null || startPage ==1){
			System.out.println("在库中重新搜索：" + startPageString);
			//计算搜索到的总行数
			SongDAO songService = new SongDAO();
			ArrayList<Song> songInfo = songService.selectSongLimit();
			RecordusongInfo = songInfo;
		}
		
		int allUserRowsNum = RecordusongInfo.size();//查询到的总行数
		System.out.println("库中歌曲共有：" + allUserRowsNum);

		int startRow = (startPage - 1) * 15;//开始行
		int rowNum = 15;//每页显示行数
		int PageNum = allUserRowsNum % 15 == 0 ? allUserRowsNum / rowNum : (allUserRowsNum / rowNum) + 1;//计算共分页
		
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//当前是尾页，对遍历数量进行单独处理
			 num = allUserRowsNum - 15*(PageNum-1);
		System.out.println("num:"+num);
		
		//存储用户信息与总页数，当前页数
		JSONObject songInfoPageNumAndCurrentPage = new JSONObject();
		songInfoPageNumAndCurrentPage.put("pagesNum", PageNum);//存储总页数
		songInfoPageNumAndCurrentPage.put("currentPage", startPage);//存储当前页
		
		if(allUserRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				songJsonArray.add(RecordusongInfo.get(i));
			}
		}
		songInfoPageNumAndCurrentPage.put("oneSongInfo", songJsonArray);
		System.out.println("歌曲信息：" + songInfoPageNumAndCurrentPage);
		out.print(songInfoPageNumAndCurrentPage);
    }
	
	//歌曲删除
	public void songdelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		
		String songIdString = request.getParameter("songId");//获取歌曲ID
		System.out.println("传入servelt的ID为："+songIdString);
		int songId = Integer.parseInt(songIdString);
		
		SongDAO songService = new SongDAO();
		songService.deleteSong(songId);
		out.print("true");
    }
	
	//歌曲删除
	public void userdelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		
		String userString = request.getParameter("userId");//获取歌曲ID
		System.out.println("传入servelt的ID为："+userString);
		int userId = Integer.parseInt(userString);
		
		UserDAO songService = new UserDAO();
		songService.deleteUser(userId);
		out.print("true");
    }
	
	public void init() throws ServletException {
		
	}

}
