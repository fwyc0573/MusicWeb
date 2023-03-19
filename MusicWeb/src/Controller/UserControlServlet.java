package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CdComment;
import model.CdCommentDAO;
import model.JdbcUtils_DBCP;
import model.Song;
import model.User;
import model.UserDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import sun.misc.BASE64Decoder;
import java.util.Date;
import java.util.Random;


public class UserControlServlet extends HttpServlet {

	public UserControlServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	//�������
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//�û����
		String CDIDString = request.getParameter("CDId");
		int CDtId = Integer.parseInt(CDIDString);//�赥���
		String CDCommText = request.getParameter("CDCommText");//��������
		
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CDCommDate = temp.format(date);//����ʱ��
		
		CdComment userCDComment = new CdComment();
		userCDComment.setUserId(userId);
		userCDComment.setCDId(CDtId);
		userCDComment.setCDCommText(CDCommText);
		userCDComment.setCDCommDate(CDCommDate);
		
		CdCommentDAO userCDcomSer = new CdCommentDAO();
		boolean addOrNot = false;
		try {
			addOrNot = userCDcomSer.addCDComm(userCDComment);//����������۵ķ���
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!addOrNot) {
			out.print("300");//���ʧ��
		}
	}
   
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		//�˺ŵ�¼
		if ("login".equals(action)) {
			try {
				this.Login(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�ֻ���¼
		else if ("login2".equals(action)) {
			try {
				this.Login2(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("register".equals(action)) {
			try {
				this.Register(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("accountCheck".equals(action)) {
			try {
				this.Register(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("phoneCheck".equals(action)) {
			try {
				this.Register(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("userInfoGet".equals(action)) {
			try {
				this.UserInfoGet(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("getUserCollectionSongs".equals(action)) {
			try {
				this.GetUserCollectionSongs(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("updateUserHeaderImg".equals(action)) {
			try {
				this.UpdateUserHeaderImg(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("collectionUpdate".equals(action)) {
			try {
				this.CollectionUpdates(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//�˺ŵ�¼
	public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		System.out.println("loginId,password->"+loginId+password);
		UserDAO userService = new UserDAO();
		User user = null;
		try {
			user = userService.selectUserOfLoginIdAndPasswordToLogin(loginId, password);//��¼
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("userInfo", user);
		JSONObject json = JSONObject.fromObject(user);
		String userJSON = json.toString();
		System.out.println("�˺ŵ�¼��" + userJSON);
		String errorString = "{\"shibai\":\"false\"}";
		if (user == null) {
			out.write(errorString);
		}else{
			out.write(userJSON);
		}
	}
	
	//�ֻ���¼
	public void Login2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String phone = request.getParameter("loginId");//������һ����������
		String password = request.getParameter("password");
		UserDAO userService = new UserDAO();
		User user = userService.selectUserOfPhoneAndPasswordToLogin(phone, password);
		session.setAttribute("userInfo", user);
		JSONObject json = JSONObject.fromObject(user);
		String userJSON = json.toString();
		System.out.println("�ֻ��ŵ�¼��" + userJSON);
		String errorString = "{\"shibai\":\"false\"}";
		if (user == null) {
			out.write(errorString);
		}else {
			out.write(userJSON);
		}
	}

	//�˺�ע��
	public void Register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String loginId = request.getParameter("loginid");
		String password = request.getParameter("psd1");
		String userName = request.getParameter("username");
		String usersex = request.getParameter("usersex");
		String email = request.getParameter("email");
		String phone = request.getParameter("phoneno");
		String sign = request.getParameter("sign");
		String headSculptureUrl = "";
		Date time = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = day.format(time);
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setUserSex(usersex);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSign(sign);
		user.setHeadSculptureUrl(headSculptureUrl);
		user.setRegistationDate(date);
		
		
		UserDAO userService = new UserDAO();
		boolean addOrNot = userService.addUser(user);
		out.print(addOrNot);//����trueע��ɹ�������falseע��ʧ��
		out.print("<script language='javaScript'> alert('ע��ɹ�');</script>");
		response.setHeader("refresh", "0;url=Register.html");
	}
	
	
	//�˺Ų���
	public void accountCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String loginId = request.getParameter("loginId");
		System.out.println(loginId);
		UserDAO userService = new UserDAO();
		boolean loginExist = userService.selectUserOfLoginId(loginId);
		System.out.println(loginExist);
		out.print(loginExist);
	}
	
	//�ֻ��Ų���
	public void phoneCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String phone = request.getParameter("phone");
		UserDAO userServiceImpl = new UserDAO();
		boolean cs = userServiceImpl.selectUserOfPhone(phone);
		String dataString = new Boolean(cs).toString();
		out.print(dataString);
		System.out.println(dataString);
	}

	
	//�˺���Ϣ��ѯ
	public void UserInfoGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String userIdstring = request.getParameter("userId");
		System.out.println("userIdstring:"+userIdstring);
		int userId = Integer.parseInt(userIdstring);
		UserDAO userService = new UserDAO();
		User userInfo = userService.selectUserInfoOfUserId(userId);
		JSONObject userInfoJSONObject = JSONObject.fromObject(userInfo);
		System.out.println(userInfoJSONObject);
		out.print(userInfoJSONObject);
	}
	
	//�û��ղظ�����ѯ
	public void GetUserCollectionSongs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String userIdstring = request.getParameter("userId");
		System.out.println("userIdstring:"+userIdstring);
		int userId = Integer.parseInt(userIdstring);
		UserDAO userService = new UserDAO();
		ArrayList<Song> songArrayList = userService.getUserCollectionSongs(userId);
		JSONArray songInfoArray = new JSONArray();
		for (Song song : songArrayList) {
			JSONObject oneSongAllInfo = new JSONObject();
			//���������Ϣ
			oneSongAllInfo.put("song", song);
			songInfoArray.add(oneSongAllInfo);
		}
		System.out.println("�ղظ���������"+songInfoArray.size());
		System.out.println(songInfoArray);
		out.print(songInfoArray);
	}
	
	//�����ղ����
	public void CollectionUpdates(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String userIdstring = request.getParameter("userId");
		System.out.println("userIdstring:"+userIdstring);
		int userId = Integer.parseInt(userIdstring);
		String songIdstring = request.getParameter("songId");
		System.out.println("songIdstring:"+songIdstring);
		int songId = Integer.parseInt(songIdstring);	
		
		UserDAO userService = new UserDAO();
		boolean userInfo = userService.userCollecitonUpdate(userId,songId);
		System.out.println(userInfo);
		out.print(userInfo);
	}
	
	
	//�û�ͷ���޸�
	public void UpdateUserHeaderImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String userHeaderImgString = request.getParameter("updateImgString");
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		System.out.println("�û���ţ�" + userIdString);
		//��ȡʱ���
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime());
		System.out.println("������ʱ�䣺" + timestamp);
		if (userHeaderImgString == null){
			System.out.println("û��ͼƬ");
		}else {
			
			Decoder decoder = Base64.getDecoder();
			String headerImgStrings = userHeaderImgString.substring(23);
			//Base64����
            byte[] b = decoder.decode(headerImgStrings);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//�����쳣����
                    b[i]+=256;
                }
            }
            String randomString = "" + Math.random();   
            String userHeaderImgName = timestamp+ randomString + ".jpg";   
            //���ɵ�Ҫ���浽���ݿ��е��û�ͷ���ļ�Ŀ¼
            String headSculptureToMYSQL = "/userHeaderImgs/" + userHeaderImgName;
            String headSculptureUrl = request.getRealPath("") + headSculptureToMYSQL;
            // String s2=
            //���û�ͷ�����ݱ��浽���ݿ�
            UserDAO userService = new UserDAO();
            boolean updateOrNot = userService.updateUserHeaderImg(headSculptureToMYSQL, userId);
            if (updateOrNot) {
            	//����ͼƬ
//            	String path=System.getProperty("user.dir") + headSculptureUrl;
                System.out.println("headSculptureUrl::"+headSculptureUrl);
                OutputStream outImg = new FileOutputStream(headSculptureUrl);
                outImg.write(b);
                outImg.flush();
                outImg.close();
				out.print("200");//�ɹ�
			}else {
				out.print("400");//ʧ��
			}
		}
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
