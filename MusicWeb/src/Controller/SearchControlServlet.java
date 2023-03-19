package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cd;
import model.CdDAO;
import model.Song;
import model.SongDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchControlServlet extends HttpServlet {

	public static ArrayList<Song> RecordSimSongsInfo = null;
	public static String RecordsearchSongName = null;
	public static ArrayList<Cd> RecordSimCDsInfo = null;
	public static String RecordsearchCDName = null;
	
	
	public SearchControlServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		//查询歌曲或者专辑
		if ("search".equals(action)) {
			try {
				this.Search(request, response);
				System.out.println("SearchSearchSearchSearch!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//查询歌曲或者专辑
		if ("searchCD".equals(action)) {
			try {
				this.SearchCD(request, response);
				System.out.println("SearchSearchSearchSearch!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	//歌曲关键字搜索
	public void Search(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String searchSongName = request.getParameter("searchSongName");

		String songName = "%" + searchSongName + "%";
		System.out.println("搜索内容：" + songName);
		if(RecordSimSongsInfo == null || RecordsearchSongName != songName)
		{
			//计算搜索到的总行数
			SongDAO songService = new SongDAO();
			ArrayList<Song> SimSongsInfo = songService.selectSongOfSongName(songName);	
			RecordSimSongsInfo = SimSongsInfo;
			RecordsearchSongName = songName;
		}
		
		int allSongRowsNum = RecordSimSongsInfo.size();//查询到的总行数
		System.out.println("搜索到歌曲有：" + allSongRowsNum);
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//开始页
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		int startRow = (startPage - 1) * 25;//开始行
		int rowNum = 25;//每页显示行数
		int PageNum = allSongRowsNum % 25 == 0 ? allSongRowsNum / rowNum : (allSongRowsNum / rowNum) + 1;//计算共分页
			
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//当前是尾页，对遍历数量进行单独处理
			 num = allSongRowsNum - 25*(PageNum-1);
		System.out.println("num:"+num);
		if(allSongRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				JSONObject songJSONAndPage = new JSONObject();
				songJSONAndPage.put("songInfo", RecordSimSongsInfo.get(i));
				songJSONAndPage.put("PageNum", PageNum);//共几页
				songJSONAndPage.put("startPage", startPage);//当前页
				songJSONAndPage.put("searchSongName", searchSongName);
				songJSONAndPage.put("allSongRowsNum", allSongRowsNum);//总行数
				JSONObject songJsonObject = JSONObject.fromObject(songJSONAndPage);
				songJsonArray.add(songJsonObject);
			}
		}
		if (songJsonArray.size() > 0) {
			out.print(songJsonArray);
		}else {
			out.print("false");//没有数据
		}
		System.out.println("搜索到的歌曲：" + songJsonArray.toString());	
    }
	
	//CD关键字搜索
	public void SearchCD(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String searchCDName = request.getParameter("searchSongName");

		String CDName = "%" + searchCDName + "%";
		System.out.println("搜索内容：" + CDName);
		
		if(RecordSimCDsInfo == null || RecordsearchCDName != CDName)
		{
			//计算搜索到的总行数
			CdDAO songService = new CdDAO();
			ArrayList<Cd> SimCDsInfo = songService.selectCDOfCDName(CDName);
			RecordSimCDsInfo = SimCDsInfo;
			RecordsearchCDName = CDName;
		}
		
		int allSongRowsNum = RecordSimCDsInfo.size();//查询到的总行数
		System.out.println("搜索到CD有：" + allSongRowsNum);
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//开始页
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		int startRow = (startPage - 1) * 25;//开始行
		int rowNum = 25;//每页显示行数
		int PageNum = allSongRowsNum % 25 == 0 ? allSongRowsNum / rowNum : (allSongRowsNum / rowNum) + 1;//计算共分页
			
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//当前是尾页，对遍历数量进行单独处理
			 num = allSongRowsNum - 25*(PageNum-1);
		System.out.println("num:"+num);
		if(allSongRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				JSONObject songJSONAndPage = new JSONObject();
				songJSONAndPage.put("cdInfo", RecordSimCDsInfo.get(i));
				songJSONAndPage.put("PageNum", PageNum);//共几页
				songJSONAndPage.put("startPage", startPage);//当前页
				songJSONAndPage.put("searchSongName", searchCDName);
				songJSONAndPage.put("allSongRowsNum", allSongRowsNum);//总行数
				JSONObject songJsonObject = JSONObject.fromObject(songJSONAndPage);
				songJsonArray.add(songJsonObject);
			}
		}
		if (songJsonArray.size() > 0) {
			out.print(songJsonArray);
		}else {
			out.print("false");//没有数据
		}
		System.out.println("搜索到的歌曲：" + songJsonArray.toString());	
    }
	
	
	public void init() throws ServletException {
		// Put your code here
	}

}
