# Music website

## Introduction
My undergraduate work, an original music website for my favourite singer, Vae, implemented by Java.

## Video demonstration

https://www.bilibili.com/video/BV1F54y1R7UC/

<img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig.png" width="900" /><br/>

## Project Overview
 
The project is based on the MVC model and uses HTML+CSS+jQuery on the front end, Ajax+Java on the back end and MySQL on the database.

The site is meticulously designed visually and functionally with the needs of most users in mind, with features such as listening, downloading, collecting, commenting, searching, logging in, registering, modifying personal avatars, and a super user interface for site maintenance.

<div align=center>
<img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig2.png" width="400" />
</div>


This website uses a three-layer development design model that divides the WEB into three basic layers: **the user interface layer**, **the business logic layer**, **the business logic and the data access layer**. The user interface layer is used for interaction; the business logic layer is responsible for the processing of business and the feedback of information between the layers; the data access layer is responsible for the storage and retrieval of database files and the feedback of query information. The site uses **prepared tatements** and **connection buffer pooling** techniques for database usage operations.


<div align=center>
<img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig1.png" height="200" /> <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig3.png" height="255" /> <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig4.png" height="255" />
 </div>
 <br/>
 
 
 
## Hot Stats of Songs
 
 By listening to the click event, when the user clicks on the play or collection button of the corresponding song, the servlet refreshes the corresponding quantity in the database by passing in the ID of the song from the front end.
 ```
	 $.ajax({  
	         type:"get",url:serverURL + "/SongsControlServlet?action=flashRecord",  
	         async:false,data:{"songId":songId},  
	         dataType:'text',
	         success:function(data){  
	         console.log(songId+"has been played.");  
	         },  
   });
 ```
 
 ``` 
	public void flashRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{  
	    response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter();  
	    String songIdString = request.getParameter("songId");  
	    int songId = Integer.parseInt(songIdString);
	    System.out.println("song_id：" + songId);  
	    SongDAO songService = new SongDAO();  
	    songService.flashRecord(songId);  
	    System.out.println("Successfully updates song records.");  
	}  
	    }).appendTo(play_td); 
 ```
 
##  Search Paging Function Implementation and Optimization
The search paging function is implemented through the following process:
1. Filter the keywords entered by the user in the search field to get valid information.
2. Call the searchServlet via ajax to process the specific search transaction.
3. SearchServlet will get the search results from the database for paging, and only return to the front-end need to render the page (this step with static variables and marker variables to prevent unnecessary duplication of queries).
4. The front-end gets the Json array from the back-end to fill the corresponding page.
  
**Optimization:** In fact, when the user is only switching between pages for the same search, or when the keywords are the same for the previous and subsequent searches, there is no need to repeat the database search and it is sufficient to **use the current cache**.

 ```
	PrintWriter out = response.getWriter();  
	String searchSongName = request.getParameter("searchSongName");  
	  
	String songName = "%" + searchSongName + "%";  
	System.out.println("search content：" + songName);  
	if(RecordSimSongsInfo == null || RecordsearchSongName != songName)  
	{  
	    //  number of rows
	    SongDAO songService = new SongDAO();  
	    ArrayList<Song> SimSongsInfo = songService.selectSongOfSongName(songName);      
	    RecordSimSongsInfo = SimSongsInfo;  
	    RecordsearchSongName = songName;  
	}
 ```
 
 
##  Pages and Function Presentation

 <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig11.png" height="235" /> <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig12.png" height="235" />  
 
<img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig5.png" height="240" /> <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig6.png" height="240" /> 
 
 <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig8.png" height="200" />  <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig10.png" height="200" />
 
 <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig13.png" height="180" /> <img src="https://github.com/fwyc0573/MyMusicWeb/blob/main/fig/fig14.png" height="180" /> 
 

 <br/>



 
