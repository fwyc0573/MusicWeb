document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
//歌曲排行榜
document.write("<script type='text/javascript' src='js/GetSongRankingList.js'></script>");
//根据用户编号，查询用户创建的歌单

$(function(){
	var songTypeAndContent = [
		["播放排行榜","songPlay"],
		["下载排行榜", "songDownload"],
		["收藏排行榜", "songCollection"]
	];
	
	for (var i = 0; i < songTypeAndContent.length; i++) {
		var songType = songTypeAndContent[i][1];
		var songTypeContent = songTypeAndContent[i][0];
		var songType_li = $("<li>",{
			songType:songType,
			click:function(){
				var songType = $(this).attr("songType");
				$("li[songType]").removeClass("songRanking_type_ul_selectes");
				$(this).addClass("songRanking_type_ul_selectes");
				$("tr[class='table_title']").nextAll().remove();//清除表格中原来显示的歌曲
				//填充右侧歌曲信息
				switch (songType){
					case "songPlay":
						//点击“歌曲播放排行榜”
						var songCount_type = "songPlay";
						var songListJSON = getSongPlayCountList();
						tianCSongList(songListJSON, "#songRanking_table", songCount_type, 50);
						break;
					case "songDownload":
						//点击“歌曲下载排行榜”
						var songCount_type = "songDownload";
						var songListJSON = getSongDownloadCountList();
						tianCSongList(songListJSON, "#songRanking_table", songCount_type, 50);
						break;
					case "songCollection":
						//点击“歌曲收藏排行榜”
						var songCount_type = "songCollection";
						var songListJSON = getCollectionCountList();
						tianCSongList(songListJSON, "#songRanking_table", songCount_type, 50);
						break;
					default:
						break;
				}
				$("#songRanking_table tr:even").css({"background":"#E8E8E8"});//tr偶数行背景颜色
				$("#songRanking_table tr:odd").css({"background":"#F4F4F4"});//tr奇数行背景颜色
			}
		}).appendTo("#songRanking_type_ul").html(songTypeContent);
	}
//	var songCount_type = sessionStorage.getItem("songContent_type");
//	var songListJSON;
//	switch (songCount_type){
//		case "songPlay":
//			//获取播放歌单
//			songListJSON = getSongPlayCountList();
//			break;
//		case "songDownload":
//			//获取下载排行榜
//			songListJSON = getSongDownloadCountList();
//			break;
//		case "songCollection":
//			//获取收藏排行榜
//			songListJSON = getCollectionCountList();
//			break;
//		default:
//			break;
//	}
//	
	//填充右侧歌曲
//	tianCSongList(songListJSON, "#songRanking_table", songCount_type, 50);
//	
//	$("li[songType]").removeClass("songRanking_type_ul_selectes");
//	$("li[songType=" + songCount_type + "]").addClass("songRanking_type_ul_selectes");
	
	//用户添加歌曲到歌单，点击X号关闭窗口
	$("#none_addSongList_showDiv").click(function(){
		$("div[class='addSongList_songList']").attr("songid","");
		$("div[class='addSongList_showDiv']").css("display","none");
	})
})
