function FillsearchCD(userId, searchCD){
	$("#searchCD_table").empty();//清空歌曲填充表
	$("div[class='pageNum_div']").empty();//清空页码div
	//填充搜索类型
	$("#searchTypeName").html("专辑");
	
	if (searchCD !="false") {
		var searchCDArr = eval("(" + searchCD + ")");
		console.log(searchCDArr);
		
		var searchName = searchCDArr[0].searchCDName;
		$("#searchCDName").html(searchCDArr[0].searchCDName);//填充搜索的关键字
		$("#songRowNum").html(searchCDArr[0].allSongRowsNum);//填充搜索到个数
		var allPageNums = searchCDArr[0].PageNum;//总页数
		var currentPage = searchCDArr[0].startPage;//当前页
			
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
					click:function(){searchCDLimit(searchName, currentPage - 1);}
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
						searchCDLimit(searchName, currentPage + 1);
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
							searchCDLimit(searchName, $(this).attr("theSpanPageNum"));//searchCDLimitAjax
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}
			}
		}
		
		var headerTr = $("<tr>").appendTo("#searchSong_table");
		var headerPlayTd = $("<td>").appendTo(headerTr);//播放列
		var headerCDNameTd = $("<td>").appendTo(headerTr).html("专辑");//专辑名列
		var headerSongSetTd = $("<td>").appendTo(headerTr).html("操作");//操作列
		var headerSingerNameTd = $("<td>").appendTo(headerTr).html("歌手");//歌手列
		var headerCdNameTd = $("<td>").appendTo(headerTr).html("发行时间");//发行列
		var headerSongTimeTd = $("<td>").appendTo(headerTr).html("时长");//时长列
		
		//填充当页的歌曲
		$.each(searchCDArr, function(index, onesearchCD){
			var CDName = onesearchCD.cdInfo.CDName;//专辑名
			var songId = onesearchCD.cdInfo.CDId;//专辑Id
			var singerName = onesearchCD.cdInfo.singerName;//歌手名
			var songpublishDate = onesearchCD.cdInfo.publishDate;//发行时间
			var coverUrl = onesearchCD.cdInfo.coverUrl;//封面地址
			
			var searchCD_Tr = $("<tr>", {
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
			
			var searchCDPlay_td = $("<td>").appendTo(searchCD_Tr);
			var play_div = $("<div>",{
				class:'play_div',
				songId:songId,
				click:function(){
					upSongListenCount(userId, songId);
				}
			}).appendTo(searchCDPlay_td);
			
			var searchCDName_td = $("<td>",{
				title:CDName,
				coverUrl:coverUrl,
				songpublishDate:songpublishDate,
				CDId:songId,
				mouseover:function(){
					$(this).css({"cursor":"pointer", "text-decoration":"underline"});
				},
				mouseout:function(){
					$(this).css({"text-decoration":"none"});
				},
				click:function(){
					sessionStorage.setItem("CDId", $(this).attr("CDId"));
					sessionStorage.setItem("CDName", $(this).attr("title"));
					sessionStorage.setItem("CDcoverUrl", $(this).attr("coverUrl"));
					sessionStorage.setItem("CDpublishDate", $(this).attr("songpublishDate"));
					
					window.location.href = "CDinfoSingle.html";
				}
			}).appendTo(searchCD_Tr).html(CDName);//填充专辑名
			
			var searchCDSet_td = $("<td>").appendTo(searchCD_Tr);
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
			}).appendTo(searchCDSet_td);
			
			
			var searchCDSingerName_td = $("<td>",{title:singerName}).appendTo(searchCD_Tr).html(singerName);//填充歌手名
			var searchCDCdName_td = $("<td>").appendTo(searchCD_Tr).html(songpublishDate);//填充出版时间
//			var searchCDSongTime_td = $("<td>").appendTo(searchCD_Tr).html(songTime);//填充时长
		});
	}
	else
	{
		var searchCDName = sessionStorage.getItem("searchCDName");
		$("#searchCDName").html(searchCDName);//填充搜索的关键字
		$("#songRowNum").html("0");//填充搜索到的行数
	}
}