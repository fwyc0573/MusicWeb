document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");

$(function(){
	var userId = sessionStorage.getItem("userId");
	console.log("用户信息userId"+userId);
	if (userId == "" || userId == null) {
		alert("请先登录")
	} else{
		var userInfoJSON = null;//用户信息JSON
		$.ajax({
			type:"post",
			url:serverURL + "/UserControlServlet?action=userInfoGet",
			async:false,//同步"
			data:{"userId":userId},
			dataType:'json',//返回数据类型
			success:function(data){
				userInfoJSON = data;
				console.log("userInfoJSON"+userInfoJSON);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log("用户信息获取失败");
			}
		});
		
		//填充用户信息
		var userName = userInfoJSON.userName;//用户名
		var email = userInfoJSON.email;//用户邮箱
		var headSculptureUrl = serverURL + userInfoJSON.headSculptureUrl;//用户头像
		var userSex = userInfoJSON.userSex;//用户性别
		console.log("headSculptureUrl:"+headSculptureUrl);
		$("#userHeader_img").attr("src",headSculptureUrl);
		$("#personal_userName").html(userName);
		$("#personal_userSex").html(userSex);
		$("#userEmail").html(email);
		
		var userListenSongJSON;//用户收藏歌曲
		$.ajax({
			type:"post",
			url:serverURL + "/UserControlServlet?action=getUserCollectionSongs",
			async:false,//同步
			data:{"userId":userId},
			dataType:'text',//返回数据类型
			success:function(data){
				userListenSongJSON = eval("(" + data + ")");
				console.log("收藏歌曲："+userListenSongJSON);
			},//响应成功后执行代码
			error:function(){
				console.log("用户信息获取失败");
			}
		});

		//填充歌曲
		$.each(userListenSongJSON, function(index, value) {
			var songId = value.song.songId;
			var songName = value.song.songName;
			var singerName = value.song.singerName;
			var publishDate = value.song.publishDate;
			var songTime = value.song.songTime;
			var songUrl = value.song.songUrl;
			
			var userSongListen_tr = $("<tr>",{
				mouseover:function(){
					//鼠标移入，添加到歌单按钮显示
					$(this).find("div[class='addSongList_img_div']").css("display","block");
					$(this).addClass("mouseoverTr");
				},
				mouseout:function(){
					//鼠标移出，添加到歌单按钮消失
					$(this).find("div[class='addSongList_img_div']").css("display","none");
					$(this).removeClass("mouseoverTr");
				}
			}).appendTo(".listensong_table");
				
			var num_td = $("<td>").appendTo(userSongListen_tr).html(index + 1);	//序号列
			var songPlay_td = $("<td>").appendTo(userSongListen_tr);//播放按钮
			var playSongBtn = $("<span>",{
				class:"playSongBtn",
				id:"playSongBtn",
				title:'播放',
				songId:songId,
				songName:songName,
				songUrl:songUrl,
				click:function(){
					
					var songUrl = $(this).attr("songUrl");
					var songName = $(this).attr("songName");
					var songId = $(this).attr("songId");
					console.log(songUrl);
					
					//播放音乐并显示对应歌曲信息
					$("#userPlaySong",window.parent.document).attr('src', songUrl);
					$("#userPlaySong",window.parent.document).trigger('play');
					$("#songPlay_name",window.parent.document).html(songName);
					console.log(songUrl);
					console.log(songName);
					//刷新播放量
					$.ajax({
						type:"get",
						url:serverURL + "/SongsControlServlet?action=flashRecord",
						async:false,//同步
						data:{"songId":songId},
						dataType:'text',//返回数据类型
						success:function(data){
							console.log(songId+"has been played.");
						},
					});
				}
			}).appendTo(songPlay_td);
			
			$("<td>").appendTo(userSongListen_tr).html(songName);//歌曲名
			$("<td>").appendTo(userSongListen_tr).html(singerName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");//歌手名
			$("<td>").appendTo(userSongListen_tr).html(publishDate);//专辑名
			$("<td>").appendTo(userSongListen_tr).html(songTime);//听歌次数
		});
		
		//奇数偶数行不同背景颜色
		$(".listensong_table tr:even").css("background","#E8E8E8");
		$(".listensong_table tr:odd").css("background","#F4F4F4");
	}

	$("#setting_userInfo").click(function(){
		window.location.href = "updateUserHeaderImg.html";
	})
})
