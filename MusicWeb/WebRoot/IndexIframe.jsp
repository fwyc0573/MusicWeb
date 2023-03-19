<%@ page language="java" contentType="text/html;charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>Insert title here</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	    <meta http-equiv="description" content="this is my page">
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/IndexIframe2.css">
	</head>
	<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
	<script src="js/lunbo.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/indexIframe.js"></script>

	<body id="indexIframe_body">
		<!-- 轮播图开始 -->
		<div id="zong_div">
			<div id="container">
				<div id="list" style="left: -1000px;">
					<img src="img/5.jpg" />
					<img src="img/1.jpg" />
					<img src="img/2.jpg" />
					<img src="img/3.jpg" />
					<img src="img/4.jpg" />
					<img src="img/5.jpg" />
					<img src="img/1.jpg" />
				</div>
				<div id="buttons">
					<span index="1" class="on"></span>
					<span index="2"></span>
					<span index="3"></span>
					<span index="4"></span>
					<span index="5"></span>
				</div>
				<a href="javascript:;" class="arrow" id="prev">
					<</a>
						<a href="javascript:;" class="arrow" id="next">></a>
			</div>
		</div>
		<!-- 轮播图结束 -->
		<div class="index_singer_info">
			<div class="index_singer_info_left">
				<div style="clear:both;"></div>
				<!--热门专辑-->
				<div id="rementuijain">
					<div class="index_reco_1">
						<i></i><span id="remenSongList">热门专辑</span>
						<p id="index_reco_p">
							
						</p>
					</div>
					<div class="index_songList_1">
						<ul id="accessList_ul">
							
						</ul>
					</div>
				</div>
				<div style="clear:both;"></div>
				<!--歌曲排行榜-->
				<div id="song_ranking_list">
					<div class="index_reco_1">
						<i></i><span>歌曲排行榜</span>
					</div>
					<div class="index_songCountList">
						<!--播放次数歌曲显示-->
						<div class="song_playcount_list">
							<div class="songCount_header playCount_header">
								热度榜
							</div>
							<table class="songList_table" cellpadding="0" cellspacing="0" id="playCount_song_table" align="center">
								
							</table>
						</div>
						<!--下载次数歌曲显示-->
						<div class="song_downloadcount_list">
							<div class="songCount_header downloadCount_header">
								下载榜
							</div>
							<table class="songList_table" cellpadding="0" cellspacing="0" id="downloadCount_song_table" align="center">
								
							</table>
						</div>
						<!--收藏次数歌曲显示-->
						<div class="song_collectioncount_list">
							<div class="songCount_header collectionCount_header">
								收藏榜
							</div>
							<table class="songList_table" cellpadding="0" cellspacing="0" id="collectionCount_song_table" align="center">
								
							</table>
						</div>
					</div>
				</div>
				
			</div>
			<div class="index_singer_info_right">
			</div>
		</div>
	</body>

</html>
