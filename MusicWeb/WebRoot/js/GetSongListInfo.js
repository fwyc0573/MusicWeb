document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
	//获取歌单信息
	function getSongListInfo(userId){
		if(userId == null){
			alert("没有登录")
			window.main.location.href = "MyMusicIframe.html";
		}else{
			$.ajax({
				"url":serverURL + "/SelectUserSongListOfUserId",
				"type":"POST",
				"data":{"userId":userId},
				"async" : false,
				"dataType":"text",
				"success":function(data){
					var dataArr = eval("(" + data + ")");
					var firstSongListId = dataArr[0].userCreateSongList[0].songListId;
					sessionStorage.setItem("firstSongListId", firstSongListId);
					sessionStorage.setItem("allSongListJSON",data);
				},
				"error":function(err){
					alert("出现异常");
				}
			})
		}
	}

