<%@ page language="java" contentType="text/html;charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Unique Vae</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/GetSongListInfo.js"></script>
	<script type="text/javascript" src="js/SearchSong_click.js"></script>
	<body>
		<div class="header">
			<!-- 顶部导航栏 -->
			<header class="index_header" id="index_header">
				<!-- LOGO -->
				<div class="LOGO">
					<img alt="LOGO" src="images/head.png" align="center"><span>Vae</span>
				</div>
				<!-- 具体跳转 -->
				<nav class="index_header_nav">
					<input type="radio" id="header_radio1" checked="checked" name="indexHeaderA" />
					<a href="IndexIframe.jsp" target="main" id="header_a1">发现Vae</a>
					<input type="radio" id="header_radio2" name="indexHeaderA" />
					<a href="PersonalCenter.html" target="main" id="header_a2">我的Vae</a>
				</nav>
				<!-- 搜索框 -->
				<div class="index_header_seek">
					<form action="">
						<input type="text" placeholder="请输入搜索关键字" id="searchSongName_input" align="center" />
						<input type="button" id="search_button" value="搜索" align="center"  />
					</form>
				</div>
				<!-- 登录前“登录按钮”， 登录后显示用户名及相关菜单 -->
				<div id="index_login_div" class="index_header_login">

				</div>
			</header>
		</div>

		<div class="clear"></div>
		<div class="header_a1_little_menu">
			<div>
				<input type="radio" id="little_menu_input1" checked="checked" name="menu_input" />
				<a href="IndexIframe.jsp" target="main" id="menu_a1">首页</a>
				<input type="radio" id="little_menu_input3" name="menu_input" />
				<a href="SongRankingList.html" target="main" id="menu_a3">歌单</a>
			</div>
		</div>
		<iframe src="IndexIframe.jsp" name="main" id="main" class="index_iframe">
			
		</iframe>
		<div class="phone_login">
		</div>
		<footer class="index_footer">
			<div class="audio_div">
					<span id="songPlay_name" style="display: inline-block;"></span>
				<audio id="userPlaySong" src="" autoplay="autoplay" controls="controls" ></audio>
			</div>
		</footer>
	</body>

</html>
