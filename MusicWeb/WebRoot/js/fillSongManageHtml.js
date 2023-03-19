//填充用户管理界面
function fillSongManageHTML(songInfoAndPageInfoJSON){//传入参数是获取到的用户当前页信息
	var currentPage = songInfoAndPageInfoJSON.currentPage;//当前页
	var pagesNum = songInfoAndPageInfoJSON.pagesNum;//总页数
	var songInfoJSON = songInfoAndPageInfoJSON.oneSongInfo;//用户信息JSON数组
	console.log("songInfoJSON"+songInfoJSON);
	console.log("pagesNum"+pagesNum);
	console.log("currentPage"+currentPage);
	
	//清空底部页码信息
	$("div[class='pageNum_div2']").empty();
	//清空用户信息table
	$(".user_manage_content_table2").empty();
	//填充页码信息
	if(pagesNum > 0){
			if(currentPage == 1){
				var prevPage_span2 = $("<span>",{
					class:'prevPage_span currentPageIsOneOrLast2',
					click:function(){}//不跳转
				}).prependTo("div[class='pageNum_div2']").html("<上一页");
			}else{
				var prevPage_span2 = $("<span>",{
					class:'prevPage_span2',
					click:function(){
						var songInfoAndPageInfoJSON = getSongInfoLimit(currentPage - 1);
						fillSongManageHTML(songInfoAndPageInfoJSON);//填充界面
					}
				}).prependTo("div[class='pageNum_div2']").html("<上一页");
			}

			//页码span
			for(var j = 1; j <= pagesNum; j++){
				if(j == currentPage){//判断当前页
					var pageNum_span = $("<span>",{
						class:'pageNum_span pageNumIsCurrentPage2',
						theSpanPageNum:j,
						click:function(){}//不跳转
					}).appendTo("div[class='pageNum_div2']").html(j);
				}else{
					var pageNum_span = $("<span>",{
						class:'pageNum_span2',
						theSpanPageNum:j,
						click:function(){
							//传入参数，点击的哪一页,获取用户信息
							var songInfoAndPageInfoJSON = getSongInfoLimit($(this).attr("theSpanPageNum"));			
							console.log("点击页数为"+songInfoAndPageInfoJSON);
							fillSongManageHTML(songInfoAndPageInfoJSON);//填充界面
						}
					}).appendTo("div[class='pageNum_div2']").html(j);
				}
			}
			//下一页span
			if(currentPage == pagesNum){
				var nextPage_span = $("<span>",{
					class:'nextPage_span currentPageIsOneOrLast2',
					click:function(){}//不跳转	
				}).appendTo("div[class='pageNum_div2']").html("下一页>");
			}else{
				var nextPage_span = $("<span>",{
					class:'nextPage_span2',
					click:function(){
						var songInfoAndPageInfoJSON = getSongInfoLimit(currentPage + 1);//传入参数，点击的哪一页,获取用户信息
						fillSongManageHTML(songInfoAndPageInfoJSON);//填充界面
					}
				}).appendTo("div[class='pageNum_div2']").html("下一页>");
			}
		}
	
	
	var userInfoTitle_tr = $("<tr>").appendTo(".user_manage_content_table2");
	
	//用户编号
	var userInfoUserId_td = $("<td>").appendTo(userInfoTitle_tr).html("编号");
	
	//账号
	var userInfoLoginId_td = $("<td>").appendTo(userInfoTitle_tr).html("歌曲名");
	
	//用户名
	var userInfoUserName_td = $("<td>").appendTo(userInfoTitle_tr).html("歌手名");
	
//	//性别
//	var userInfoUserSex_td = $("<td>").appendTo(userInfoTitle_tr).html("性别");
	
	//注册时间
	var userInfoRegistationDate_td = $("<td>").appendTo(userInfoTitle_tr).html("发行时间");
	
//	//电话
//	var userInfoPhone_td = $("<td>").appendTo(userInfoTitle_tr).html("电话");
//	
//	//邮箱
//	var userInfoEmail_td = $("<td>").appendTo(userInfoTitle_tr).html("邮箱");
	//操作
	var userInfoSetting_td = $("<td>").appendTo(userInfoTitle_tr).html("操作");
	$.each(songInfoJSON, function(index, value) {
		var userId = value.songId;//歌曲编号
		var loginId = value.songName;//歌曲名
		var userName = "许嵩";
		var registationDate = value.publishDate;//出版时间
		
		console.log("value"+value);
		console.log("userId"+userId);
		//填充行
		var userInfo_tr = $("<tr>",{
			mouseover:function(){
				$(this).addClass("table_tr_mouseover");
			},
			mouseout:function(){
				$(this).removeClass("table_tr_mouseover");
			}
		}).appendTo(".user_manage_content_table2");
		
		//填充用户编号列
		var userId_td = $("<td>").appendTo(userInfo_tr).html(userId);
		
		//填充歌曲名
		var loginId_td = $("<td>").appendTo(userInfo_tr).html(loginId);
		
		//填充歌手名
		var userName_td = $("<td>").appendTo(userInfo_tr).html(userName);
		
		//填充发行时间
		var registationDate_td = $("<td>").appendTo(userInfo_tr).html(registationDate);
		
		//填充操作
		var setting_td = $("<td>").appendTo(userInfo_tr);
		
		//删除按钮
		var deleteUserBtn = $("<div>",{
			userId:userId,
			class:'deleteUserBtn',
			title:'删除',
			click:function(){
				alert("确认删除？");
				var songId = $(this).attr("userId");
				console.log(songId);
				//删除并刷新
				$.ajax({
					type:"post",
					url:serverURL + "/AdminControlServlet?action=songdelete",
					async:false,//同步
					data:{"songId":songId},
					dataType:'text',//返回数据类型
					success:function(data){		
						var songInfoAndPageInfoJSON = getSongInfoLimit(1);
						fillSongManageHTML(songInfoAndPageInfoJSON);//填充界面
						alert("删除成功");
					},
				});
			}
		}).appendTo(setting_td).html("删除");
	});
	
	$(".user_manage_content_table2 tr:odd").css("background", "#EEEEEE");
}