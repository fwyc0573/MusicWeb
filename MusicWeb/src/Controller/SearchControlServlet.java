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
		
		//��ѯ��������ר��
		if ("search".equals(action)) {
			try {
				this.Search(request, response);
				System.out.println("SearchSearchSearchSearch!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//��ѯ��������ר��
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
	
	//�����ؼ�������
	public void Search(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String searchSongName = request.getParameter("searchSongName");

		String songName = "%" + searchSongName + "%";
		System.out.println("�������ݣ�" + songName);
		if(RecordSimSongsInfo == null || RecordsearchSongName != songName)
		{
			//������������������
			SongDAO songService = new SongDAO();
			ArrayList<Song> SimSongsInfo = songService.selectSongOfSongName(songName);	
			RecordSimSongsInfo = SimSongsInfo;
			RecordsearchSongName = songName;
		}
		
		int allSongRowsNum = RecordSimSongsInfo.size();//��ѯ����������
		System.out.println("�����������У�" + allSongRowsNum);
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//��ʼҳ
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		int startRow = (startPage - 1) * 25;//��ʼ��
		int rowNum = 25;//ÿҳ��ʾ����
		int PageNum = allSongRowsNum % 25 == 0 ? allSongRowsNum / rowNum : (allSongRowsNum / rowNum) + 1;//���㹲��ҳ
			
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//��ǰ��βҳ���Ա����������е�������
			 num = allSongRowsNum - 25*(PageNum-1);
		System.out.println("num:"+num);
		if(allSongRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				JSONObject songJSONAndPage = new JSONObject();
				songJSONAndPage.put("songInfo", RecordSimSongsInfo.get(i));
				songJSONAndPage.put("PageNum", PageNum);//����ҳ
				songJSONAndPage.put("startPage", startPage);//��ǰҳ
				songJSONAndPage.put("searchSongName", searchSongName);
				songJSONAndPage.put("allSongRowsNum", allSongRowsNum);//������
				JSONObject songJsonObject = JSONObject.fromObject(songJSONAndPage);
				songJsonArray.add(songJsonObject);
			}
		}
		if (songJsonArray.size() > 0) {
			out.print(songJsonArray);
		}else {
			out.print("false");//û������
		}
		System.out.println("�������ĸ�����" + songJsonArray.toString());	
    }
	
	//CD�ؼ�������
	public void SearchCD(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String searchCDName = request.getParameter("searchSongName");

		String CDName = "%" + searchCDName + "%";
		System.out.println("�������ݣ�" + CDName);
		
		if(RecordSimCDsInfo == null || RecordsearchCDName != CDName)
		{
			//������������������
			CdDAO songService = new CdDAO();
			ArrayList<Cd> SimCDsInfo = songService.selectCDOfCDName(CDName);
			RecordSimCDsInfo = SimCDsInfo;
			RecordsearchCDName = CDName;
		}
		
		int allSongRowsNum = RecordSimCDsInfo.size();//��ѯ����������
		System.out.println("������CD�У�" + allSongRowsNum);
		int startPage = 1;
		String startPageString = request.getParameter("startPage");//��ʼҳ
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		int startRow = (startPage - 1) * 25;//��ʼ��
		int rowNum = 25;//ÿҳ��ʾ����
		int PageNum = allSongRowsNum % 25 == 0 ? allSongRowsNum / rowNum : (allSongRowsNum / rowNum) + 1;//���㹲��ҳ
			
		System.out.println("startRow:"+startRow);
		JSONArray songJsonArray = new JSONArray();
		int num = rowNum;
		if(startPage == PageNum)//��ǰ��βҳ���Ա����������е�������
			 num = allSongRowsNum - 25*(PageNum-1);
		System.out.println("num:"+num);
		if(allSongRowsNum!=0){
			for(int i=startRow;i<startRow+num;i++)
			{
				JSONObject songJSONAndPage = new JSONObject();
				songJSONAndPage.put("cdInfo", RecordSimCDsInfo.get(i));
				songJSONAndPage.put("PageNum", PageNum);//����ҳ
				songJSONAndPage.put("startPage", startPage);//��ǰҳ
				songJSONAndPage.put("searchSongName", searchCDName);
				songJSONAndPage.put("allSongRowsNum", allSongRowsNum);//������
				JSONObject songJsonObject = JSONObject.fromObject(songJSONAndPage);
				songJsonArray.add(songJsonObject);
			}
		}
		if (songJsonArray.size() > 0) {
			out.print(songJsonArray);
		}else {
			out.print("false");//û������
		}
		System.out.println("�������ĸ�����" + songJsonArray.toString());	
    }
	
	
	public void init() throws ServletException {
		// Put your code here
	}

}
