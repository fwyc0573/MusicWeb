document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
//歌曲排行榜
document.write("<script type='text/javascript' src='js/GetSongRankingList.js'></script>");

$(function(){

	//获取index.js从后台获得的热度专辑信息
	var userId = sessionStorage.getItem("userId");
	var HotCDInfo = sessionStorage.getItem("HotCDInfo");
	var HotCDInfoArr = eval("(" + HotCDInfo + ")");
	var eachNum = 0;//记录遍历几次
	
	$.each(HotCDInfoArr, function(name, value) {
		var cdImg = value.HotCdinfo.coverUrl;//歌单图片
//		console.log("专辑编号：" + name);
//		console.log("图片地址：" + cdImg)
		eachNum ++;
		if(eachNum > 8){
			return false;
		}
		//填充页面
		var li = $("<li>", {}).appendTo($("#accessList_ul"));//填充li
		var songList_img_div = $("<div>", {class:'songList_img_div'}).appendTo(li);//填充图片div	
		var songList_img = $("<img/>",{
			id:'cdImg',
			src:function(){if (cdImg != "" && cdImg != null) {return serverURL + cdImg;} else{return "images/songListImg.jpg";}},
			alt:'专辑图片',
		}).appendTo(songList_img_div);//填充图片img
			
		var songList_a = $("<a>",{
			id:'songList_a',
			title:value.HotCdinfo.CDName,
			CDId:value.HotCdinfo.CDId,
			CDpublishDate:value.HotCdinfo.publishDate,
			CDintroduce:value.HotCdinfo.introduce,
			CDcoverUrl:value.HotCdinfo.coverUrl,
			click:function(){
				sessionStorage.setItem("CDId", $(this).attr("CDId"));
				sessionStorage.setItem("CDName", $(this).attr("title"));
				sessionStorage.setItem("CDpublishDate", $(this).attr("CDpublishDate"));
				sessionStorage.setItem("CDintroduce", $(this).attr("CDintroduce"));
				sessionStorage.setItem("CDcoverUrl", $(this).attr("CDcoverUrl"));	
				window.location.href = "CDinfoSingle.html";
			}
		}).appendTo(songList_img_div);
				
		var accessCount_div = $("<div>",{
				id:'accessCount_div'
		}).appendTo(songList_img_div);//填充显示访问量的div
				
		accessCount_div.html(value.HotCdinfo.publishDate);
				
		var songList_name = $("<p>",{
					class:'songList_name'
		}).appendTo(li);//填充歌单名字p
				
		var songList_name_a = $("<a>",{
			id:'songList_name_a',
			title:value.HotCdinfo.CDName,
			CDId:value.HotCdinfo.CDId,
			click:function(){
			sessionStorage.setItem("CDId", $(this).attr("CDId"));
			window.location.href = "CDinfoSingle.html";
			}
		}).appendTo(songList_name);		
		songList_name_a.html(value.HotCdinfo.CDName);
	});
	
	
	
	var timer = null;
	$(window).resize(function(){
		clearTimeout(timer);
		timer = setTimeout(function(){
			$(window.parent.document).find("#main").css("height","auto");
			var parentHeight = $(window.parent.document).height();//窗口高度
			$("#indexIframe_body").css("height", (parentHeight - 105) + "px");
			$(window.parent.document).find("#main").css("height", (parentHeight - 105) + "px")//设置iframe高度
		},60)
	})
	
	
	//获取播放排行榜JSON数据
	var songPlayCountListJSON = getSongPlayCountList();
	//填充播放排行榜
	var tableId = "#playCount_song_table";
	var songCountType = "songPlay";
	tianCSongList(songPlayCountListJSON, tableId, songCountType, 11);
	
	//获取下载次数排行榜JSON数据
	var songDownloadCountListJSON = getSongDownloadCountList();
	//填充下载排行榜
	var tableId = "#downloadCount_song_table";
	var songCountType = "songDownload";
	tianCSongList(songDownloadCountListJSON, tableId, songCountType, 11);

	//获取收藏次数排行榜JSON数据
	var songCollectionCountListJSON = getCollectionCountList();
	//填充播放排行榜
	var tableId = "#collectionCount_song_table";
	var songCountType = "songCollection";
	tianCSongList(songCollectionCountListJSON, tableId, songCountType, 11);
	
	$(".songList_table tr:even").css({"background":"#E8E8E8"});//tr偶数行背景颜色
	$(".songList_table tr:odd").css({"background":"#F4F4F4"});//tr奇数行背景颜色
	
})