document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script type='text/javascript' src='js/SearchSongLimitAjax.js'></script>");
document.write("<script type='text/javascript' src='js/FillSearchSong.js'></script>");
document.write("<script type='text/javascript' src='js/FillSearchCDList.js'></script>");

$(function(){
	var userId = sessionStorage.getItem("userId");
	var searchSong = sessionStorage.getItem("searchSong");
	var searchSongName = sessionStorage.getItem("searchSongName");
	
	var search_type = sessionStorage.getItem("search_type");
	if (search_type == "null" || search_type == "search_song") {
		console.log("searchSong:"+searchSong);
		$("#search_song").addClass("searchType_span_select");
		FillSearchSong(userId, searchSong);//填充查询到的歌曲
	}else if(search_type == "search_songList"){
		$("#search_songList").addClass("searchType_span_select");
		FillsearchCD(userId, searchSong);
	}
	
	
	//设置table中tr奇数行背景颜色
	$("#searchSong_table tr:even").css("background","#F7F7F7");


	//点击搜索类别（单曲或者歌单）进行不同的搜索
	$(".search_type_span").click(function(){
		$("#search_song").removeClass("searchType_span_select");
		var thisId = $(this).prop("id");
		//点击搜索单曲
		if(thisId == "search_song"){
			sessionStorage.setItem("search_type","search_song");
			$("#search_song").addClass("searchType_span_select");
			$("#search_songList").removeClass("searchType_span_select");

			FillSearchSong(userId, searchSong);//填充查询到的歌曲
		}else if(thisId == "search_songList"){
			//点击搜索专辑
			sessionStorage.setItem("search_type","search_songList");
			$("#search_songList").addClass("searchType_span_select");
			$("#search_song").removeClass("searchType_span_select");
			searchCDLimit(searchSongName, 1);
			searchSong = sessionStorage.getItem("searchSong");
			FillsearchCD(userId, searchSong);
		}
		//设置table中tr奇数行背景颜色
		$("#searchSong_table tr:even").css("background","#F7F7F7");
	})
})
