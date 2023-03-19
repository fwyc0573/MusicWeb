document.write("<script type='text/javascript' src='js/ShowCDComment.js'></script>");
document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
$(function(){
	var userId = sessionStorage.getItem("userId");
	var headSculptureUrl = sessionStorage.getItem("headSculptureUrl");//获取头像
	var CDId = sessionStorage.getItem("CDId");//获取专辑ID
	var CDName = sessionStorage.getItem("CDName");//获取专辑名称
	var CDpublishDate = sessionStorage.getItem("CDpublishDate");//获取专辑发行时间
	var CDintroduce = sessionStorage.getItem("CDintroduce");//获取专辑介绍
	var CDcoverUrl = sessionStorage.getItem("CDcoverUrl");//获取专辑图片地址
	
	console.log(headSculptureUrl+"headSculptureUrlheadSculptureUrlheadSculptureUrlheadSculptureUrl");
	//填充评论输入框前当前用户头像
	if(userId != null)
		{
           $("#user_header_photo").attr("src", serverURL + "userHeaderImgs/15923008577720.07986872737085149.jpg");
           console.log(serverURL + headSculptureUrl+"//////user_header_photouser_header_photouser_header_photo");
		}
	else{
//		$("#user_header_photo").attr("src", "../images/headd.png");.css("color","#C82F2E");
		$("#user_header_photo").css("background;","url(../images/headd.png)");
	}
	
	$("tr[class='table_title']").nextAll().remove();//清除表格中原来显示的歌曲
	$.ajax({
		"url":serverURL + "/SongsControlServlet?action=singleCDShow",
		"type":"POST",
		"data":{"CDId":CDId},
		"async" : false,
		"dataType":"text",
		"success":function(data){
			console.log(data);
			sessionStorage.setItem("songJSON",data);
			var CDJSONArr = eval("(" + data + ")");
			
			$("#songList_Name").html(CDName);
			$("#songList_singer").html("许嵩");
			$("#songList_Time").html(CDpublishDate);
			$("#songList_intro").html(CDintroduce);
			
			//专辑图像
			if(CDcoverUrl != "" && CDcoverUrl != null){
				$("#songList_photo").attr("src", serverURL + CDcoverUrl);
			}else{
				$("#songList_photo").attr("src","images/songListImg.jpg");
			}
			
			//填充评论信息
			var userCDCommArr = CDJSONArr.CDComm;
			showCDComment(userCDCommArr);
			
			if(CDJSONArr.length == 0){
				$("div[class='songList_song']").hide();//如果歌单为空，隐藏页面元素
				$("div[class='write_songlist_comment']").hide();
			}else{
				//填充歌曲信息
				$("div[class='songList_song']").show();//如果歌单不为空，显示页面元素
				$("div[class='write_songlist_comment']").show();
				
				console.log("CDJSONArr.CDtInfo.length"+CDJSONArr.CDtInfo.length);
				for(var i = 0; i < CDJSONArr.CDtInfo.length; i++){
					var songId = CDJSONArr.CDtInfo[i].song.songId;//歌曲编号
					var songName = CDJSONArr.CDtInfo[i].song.songName;//歌曲名
					var songTime = CDJSONArr.CDtInfo[i].song.songTime;//歌曲时长
					var songUrl = CDJSONArr.CDtInfo[i].song.songUrl;//歌曲地址
//					var singerName = CDJSONArr.song[i].singerName;//歌手名
					
					var right_tr = $('<tr>',{
						mouseover:function(){
							$(this).find("span[id='addSongList']").css("display","inline-block");//把歌曲添加到歌单按钮显示
							$(this).find("span[id='deleteSongFromSongList']").css("display","inline-block");//把歌曲从当前歌单移除按钮显示
						},
						mouseout:function(){
							$(this).find("span[id='addSongList']").css("display","none");//把歌曲添加到歌单按钮隐藏
							$(this).find("span[id='deleteSongFromSongList']").css("display","none");//把歌曲从当前歌单移除按钮隐藏
						}
					}).appendTo($("#song_list_table"));
					
					var td1 = $("<td>",{}).appendTo(right_tr);
					var playSongBtn = $("<span>",{
						class:"playSongBtn",id:"playSongBtn",title:'播放',songId:songId,songUrl:songUrl,songName:songName,
						click:function(){
							var songUrl = $(this).attr("songUrl");
							var songName = $(this).attr("songName");
							var songId = $(this).attr("songId");
							
							//播放音乐并显示对应歌曲信息
							$("#userPlaySong",window.parent.document).attr('src', songUrl);
							$("#userPlaySong",window.parent.document).trigger('play');
							$("#songPlay_name",window.parent.document).html(songName);
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
					}).appendTo(td1);
					var tdSongName = $("<td>").appendTo(right_tr).html(songName);
					$(tdSongName).css("width","230px");
					
					//收藏音乐
					var addSongList = $("<span>",{
						class:'addSongList',id:'addSongList',title:'添加到歌单',songId:songId,userId:userId,
						click:function(){
							var songId = $(this).attr("songId");
							var userId = $(this).attr("userId");
							if(userId != null){
								$.ajax({
									type:"post",
									url:serverURL + "/UserControlServlet?action=collectionUpdate",
									async:false,//同步
									data:{"songId":songId,"userId":userId},
									dataType:'text',//返回数据类型
									success:function(data){
										if(data == "false"){
											 alert("收藏成功!");
											//刷新收藏量
											$.ajax({
												type:"get",
												url:serverURL + "/SongsControlServlet?action=flashRecordCollection",
												async:false,//同步
												data:{"songId":songId},
												dataType:'text',//返回数据类型
												success:function(data){
													console.log(songId+"收藏数量刷新");
												},
											});
										}
										else{
											alert("已经收藏过啦！");
										}
									},
								});
							}else{
								//如果没有登录，提示先登录
								alert("请先登录");
							}
						}
					}).appendTo(tdSongName);
					
					var tdSongTime = $("<td>",{}).appendTo(right_tr).html(songTime);
					var tdCDName = $("<td>",{}).appendTo(right_tr).html(CDName);
				}
			}
		},
		"error":function(err){
			console.log("获取歌曲信息出现异常");
		}
	})
	

	
	//点击发表评论
	$("#comment_submit").click(function(){
		if(userId == null || userId == ""){
			alert("请先登录！");
		}else{
			var commContentText = $("#songlist_comment_textarea").val().trim().replace(/\s/g,"");
			if (commContentText == null || commContentText == "") {
				alert("评论不能为空！");
			} else{
				$.ajax({
					type:"get",
					url:serverURL + "/UserControlServlet",
					async:false,//同步
					data:{"userId":userId, "CDId":CDId, "CDCommText":commContentText},
					dataType:'text',//返回数据类型
					success:function(data){
						if (data == "300") {
							alert("添加失败");
						} else{
							alert("评论成功");
							window.location.href = "CDinfoSingle.html";
						}
					},//响应成功后执行代码
					error:function(){
						console.log("发表评论失败");
					}
				});
			}
		}
		
	})
})