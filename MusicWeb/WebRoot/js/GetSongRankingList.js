//获取歌曲排行榜
//获取播放次数排行榜

function getSongPlayCountList(){
	var songPlayCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SongsControlServlet?action=play_rank",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("播放次数排行榜：" + data)
			songPlayCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songPlayCountListJSON;
}

//获取下载次数排行榜
function getSongDownloadCountList(){
	var songDownloadCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SongsControlServlet?action=download_rank",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("下载次数排行榜：" + data )
			songDownloadCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songDownloadCountListJSON;
}

//获取收藏次数排行榜
function getCollectionCountList(){
	var songCollectionCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL +"/SongsControlServlet?action=collection_rank",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("收藏次数排行榜：" + data);
			songCollectionCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songCollectionCountListJSON;
}


//歌曲推荐，填充函数
function tianCSongList(songListJSON, tableId, songCountType, songNum){//推荐歌曲JSON数据，填充的table id
	var userId = sessionStorage.getItem("userId");
	var songListJSONLen = songListJSON.length;
	//只显示前songNum首
	var showEleven;
	if (songListJSONLen > songNum)
	{showEleven = songNum;}
	else{showEleven = songListJSONLen;}
	
	for(var i = 0; i < showEleven; i++){
		var songId = songListJSON[i].song.songId;//歌曲编号
		var songName = songListJSON[i].song.songName;//歌曲名字
		var songUrl = songListJSON[i].song.songUrl;//歌曲地址
		
		var song_tr = $("<tr>",{
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
		}).appendTo(tableId);
		
		var num_td = $("<td>").appendTo(song_tr).html(i+1);
		if(i <= 4){
			$(num_td).css("color","#C82F2E");
		}
		//歌曲播放div
		var play_td = $("<td>").appendTo(song_tr);
		
		//播放按钮
		var play_span_btn = $("<span>",{class:'playSongBtn',id:"playSongBtn",title:'播放',songId:songId,
			songName:songName,songUrl:songUrl,
			click:function(){
				var songUrl = $(this).attr("songUrl");
				var songName = $(this).attr("songName");
				var songId = $(this).attr("songId");
				//播放音乐并显示对应歌曲信息
				$("#userPlaySong",window.parent.document).attr('src', songUrl);
				$("#userPlaySong",window.parent.document).trigger('play');
				$("#songPlay_name",window.parent.document).html(songName);
				console.log(songUrl);
				console.log(songName);
				//刷新播放量
				$.ajax({
					type:"get",url:serverURL + "/SongsControlServlet?action=flashRecord",
					async:false,data:{"songId":songId},
					dataType:'text',//返回数据类型
					success:function(data){
						console.log(songId+"has been played.");
					},
				});
			}
		}).appendTo(play_td);
		

		//歌曲名称
		var songName_td = $("<td>",{
			title:songName
		}).appendTo(song_tr).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+songName);
		
		//收藏音乐
		var addSongToSongList = $("<td>",{
			songId:songId,
			click:function(){
				
			}
		}).appendTo(song_tr);
		
		var addSongList_img_div = $("<div>",{
			class:'addSongList_img_div',
			title:'收藏',
			songId:songId,
			userId:userId,
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
							if(data =="false")
								{
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
		}).appendTo(addSongToSongList);

	}
	var more_song_tr = $("<tr>").appendTo(tableId);
	var more_song_td = $("<td>",{colspan:'4'}).appendTo(more_song_tr).html("");
	$(more_song_td).css({"text-alirn":"right"});

	
}