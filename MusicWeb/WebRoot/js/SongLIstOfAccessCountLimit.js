function selectSongListOfAccessCountLimit(tags, currentPage, a_id){
	//热门歌单分页
	$.ajax({
		type:'get',//提交数据方式
		url:serverURL + "/SongsControlServlet?action=hot_give",
		async:false,
		data:{"currentPage":currentPage, "tags":tags},
		dataType:'text',//返回数据类型
		success:function(data){
			sessionStorage.setItem("songListLimit",data);
			alert("热门歌单data：" + data)
			window.location.href = "MoreSongList.html";
		},
		error:function(){
		alert("请求失败");
		}
	});
}

function showSongList_ofTags(tagsChoose){
	$("ul[class='songList_ul']").empty();//清空原歌单列表
	$("div[class='pageNum_div']").empty();//清空页码
	$("span[class='songList_tags']").empty();//清空分类
	if (tagsChoose == "" || tagsChoose == null) {
		$("span[class='songList_tags']").html("全部");
	} else{
		$("span[class='songList_tags']").html(tagsChoose);
	}
	var songListLimit = sessionStorage.getItem("songListLimit");
	if (songListLimit != null || songListLimit != "") {
		//全部热门歌单信息
		var songListLimitArr = eval("(" + songListLimit + ")");
		var i = 0;
		var currentPage = 0;//当前页码
		var allPageNums = 0;//总页数
		$.each(songListLimitArr, function(name,value){
			i += 1;
			var songListId = value.songListInfo.songListId;//歌单编号
			var songListName = value.songListInfo.songListName;//歌单名字
			var songListImg = value.songListInfo.songListImg;//歌单图片
			if(i <= 1){
				currentPage = value.currentPage;//当前页码
				allPageNums = value.allPageNums;//总页数
				
			}
			
			var one_songList = $("<li>",{
				class:'one_songList',
			}).appendTo("ul[class='songList_ul']");
			
			var songList_img_div = $("<div>",{
				id:'songList_img_div'
			}).appendTo(one_songList);
			
			var songList_img = $("<img />",{
				id:'songList_img',
				alt:'图片',
				src:function(){
					if (songListImg != "" && songListImg != null) {
						return serverURL + songListImg;
					} else{
						return "images/songListImg.jpg";
					}
				},
				height:'150',
				title:songListName,
				songListId:songListId,
				click:function(){
					sessionStorage.setItem("indexIframeSongListId", $(this).attr("songlistId"));
					window.location.href = "indexIframeSongList.html";
				}
			}).appendTo(songList_img_div);
			
			var songList_name = $("<p>",{
				id:'songList_name',
				title:songListName,
				songListId:songListId,
				click:function(){
					sessionStorage.setItem("indexIframeSongListId", $(this).attr("songlistId"));
					window.location.href = "indexIframeSongList.html";
				}
			}).appendTo(one_songList).html(songListName);
		})
		if(allPageNums > 0){
			if(currentPage == 1){
				var prevPage_span = $("<span>",{
					class:'prevPage_span currentPageIsOneOrLast',
					click:function(){
						//不跳转
					}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}else{
				var prevPage_span = $("<span>",{
					class:'prevPage_span',
					click:function(){
						//传入参数，点击的哪一页（当前页-1）,在js/SongListOfAccessCountLimit.js中定义
						selectSongListOfAccessCountLimit("", currentPage - 1);
					}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}
			
			
			//页码span
			for(var j = 1; j <= allPageNums; j++){
				if(j == currentPage){//判断当前页
					var pageNum_span = $("<span>",{
						class:'pageNum_span pageNumIsCurrentPage',
						theSpanPageNum:j,
						click:function(){
							//不跳转
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}else{
					var pageNum_span = $("<span>",{
						class:'pageNum_span',
						theSpanPageNum:j,
						click:function(){
							//传入参数，点击的哪一页,在js/SongListOfAccessCountLimit.js中定义
							selectSongListOfAccessCountLimit("", $(this).attr("theSpanPageNum"));
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}
			}
			//下一页span
			if(currentPage == allPageNums){
				var nextPage_span = $("<span>",{
					class:'nextPage_span currentPageIsOneOrLast',
					click:function(){
						//不跳转
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}else{
				var nextPage_span = $("<span>",{
					class:'nextPage_span',
					click:function(){
						//传入参数，点击的哪一页（当前页+1）,在js/SongListOfAccessCountLimit.js中定义
						selectSongListOfAccessCountLimit("", currentPage + 1);
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
		
		}
	} else{
		alert("没有请求到数据！");
	}
	
}
