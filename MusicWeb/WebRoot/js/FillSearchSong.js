function FillSearchSong(userId, searchSong){
	$("#searchSong_table").empty();//清空歌曲填充表
	$("div[class='pageNum_div']").empty();//清空页码div
	//填充搜索类型
	$("#searchTypeName").html("单曲");
	console.log("searchSong[0]"+searchSong[0]);
	
	if (searchSong !="false") {
		var searchSongArr = eval("(" + searchSong + ")");
		var searchName = searchSongArr[0].searchSongName;
		$("#searchSongName").html(searchSongArr[0].searchSongName);//填充搜索的关键字
		$("#songRowNum").html(searchSongArr[0].allSongRowsNum);//填充搜索到个数
		var allPageNums = searchSongArr[0].PageNum;//总页数
		var currentPage = searchSongArr[0].startPage;//当前页
			
		if(allPageNums > 0){
			//上一页
			if(currentPage == 1){
				var prevPage_span = $("<span>",{
					class:'prevPage_span currentPageIsOneOrLast',
					click:function(){}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}
			else
			{
				var prevPage_span = $("<span>",{
					class:'prevPage_span',
					click:function(){searchSongLimit(searchName, currentPage - 1);}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}
			
			//下一页
			if(currentPage == allPageNums){
				var nextPage_span = $("<span>",{
					class:'nextPage_span currentPageIsOneOrLast',
					click:function(){}//不跳转
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
			else
			{
				var nextPage_span = $("<span>",{
					class:'nextPage_span',
					click:function(){
						searchSongLimit(searchName, currentPage + 1);
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
			
			//所有页数
			for(var j = 1; j <= allPageNums; j++){
				if(j == currentPage){//判断当前页
					var pageNum_span = $("<span>",{
						class:'pageNum_span pageNumIsCurrentPage',
						theSpanPageNum:j,
						click:function(){}//不跳转
					}).appendTo("div[class='pageNum_div']").html(j);
				}
				else{
					var pageNum_span = $("<span>",{
						class:'pageNum_span',
						theSpanPageNum:j,
						click:function(){
							searchSongLimit(searchName, $(this).attr("theSpanPageNum"));//SearchSongLimitAjax
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}
			}
		}
		
		var headerTr = $("<tr>").appendTo("#searchSong_table");
		var headerPlayTd = $("<td>").appendTo(headerTr);//播放列
		var headerSongNameTd = $("<td>").appendTo(headerTr).html("歌曲名");//歌曲名列
		var headerSongSetTd = $("<td>").appendTo(headerTr).html("操作");//操作列
		var headerSingerNameTd = $("<td>").appendTo(headerTr).html("歌手");//歌手列
		var headerCdNameTd = $("<td>").appendTo(headerTr).html("发行时间");//发行列
		var headerSongTimeTd = $("<td>").appendTo(headerTr).html("时长");//时长列
		
		//填充当页的歌曲
		$.each(searchSongArr, function(index, oneSearchSong){
			var songName = oneSearchSong.songInfo.songName;//歌曲名
			var songId = oneSearchSong.songInfo.songId;//歌曲Id
			var singerName = oneSearchSong.songInfo.singerName;//歌手名
			var cdName = oneSearchSong.songInfo.CDName;//专辑名
			var songTime = oneSearchSong.songInfo.songTime;//时长
			var songpublishDate = oneSearchSong.songInfo.publishDate;//发行时间
			var songUrl = oneSearchSong.songInfo.songUrl;//歌曲播放地址
			var searchSong_Tr = $("<tr>", {
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
			}).appendTo("#searchSong_table");
			
			var searchSongPlay_td = $("<td>").appendTo(searchSong_Tr);
			var play_div = $("<div>",{
				class:'play_div',
				songId:songId,
				songName:songName,
				songUrl:songUrl,
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
			}).appendTo(searchSongPlay_td);
			
			var searchSongName_td = $("<td>").appendTo(searchSong_Tr).html(songName);//填充歌曲名
			var searchSongSet_td = $("<td>").appendTo(searchSong_Tr);
			var addSongList_img_div = $("<div>",{
				class:'addSongList_img_div',
				title:'收藏',
				songId:songId,
				click:function(){
					if(userId != null){
						var allSongListJSONOfUserId = getSongListOfUserId(userId);
						if(allSongListJSONOfUserId == null || allSongListJSONOfUserId == "null"){
							alert("你还没有创建歌单，先去创建吧")
						}else{
							var thisSongId = $(this).attr("songId");
							$("div[class='addSongList_showDiv']").css("display","block");
							$("div[class='addSongList_songList']").empty();
							$("div[class='addSongList_songList']").attr("songid",$(this).attr("songId"));
							setSongListOfUserId(allSongListJSONOfUserId);
						}
					}else{
						alert("请先登录");
					}
				}
			}).appendTo(searchSongSet_td);
			
			
			var searchSongSingerName_td = $("<td>",{title:singerName}).appendTo(searchSong_Tr).html(singerName);//填充歌手名
			var searchSongCdName_td = $("<td>").appendTo(searchSong_Tr).html(songpublishDate);//填充出版时间
			var searchSongSongTime_td = $("<td>").appendTo(searchSong_Tr).html(songTime);//填充时长
		});
	}
	else
	{
		var searchSongName = sessionStorage.getItem("searchSongName");
		$("#searchSongName").html(searchSongName);//填充搜索的关键字
		$("#songRowNum").html("0");//填充搜索到的行数
	}
}
