//填充用户管理界面
function fillUserManageHTML(userInfoAndPageInfoJSON){//传入参数是获取到的用户当前页信息
	var currentPage = userInfoAndPageInfoJSON.currentPage;//当前页
	var pagesNum = userInfoAndPageInfoJSON.pagesNum;//总页数
	var userInfoJSON = userInfoAndPageInfoJSON.userInfo;//用户信息JSON数组
	//清空底部页码信息
	$("div[class='pageNum_div']").empty();
	//清空用户信息table
	$(".user_manage_content_table").empty();
	//填充页码信息
	if(pagesNum > 0){
			if(currentPage == 1){
				var prevPage_span = $("<span>",{
					class:'prevPage_span currentPageIsOneOrLast',
					click:function(){}//不跳转
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}else{
				var prevPage_span = $("<span>",{
					class:'prevPage_span',
					click:function(){
						var userInfoAndPageInfoJSON = getAllUserInfoLimit(currentPage - 1);
						fillUserManageHTML(userInfoAndPageInfoJSON);//填充界面
					}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}

			//页码span
			for(var j = 1; j <= pagesNum; j++){
				if(j == currentPage){//判断当前页
					var pageNum_span = $("<span>",{
						class:'pageNum_span pageNumIsCurrentPage',
						theSpanPageNum:j,
						click:function(){}//不跳转
					}).appendTo("div[class='pageNum_div']").html(j);
				}else{
					var pageNum_span = $("<span>",{
						class:'pageNum_span',
						theSpanPageNum:j,
						click:function(){
							//传入参数，点击的哪一页,获取用户信息
							var userInfoAndPageInfoJSON = getAllUserInfoLimit($(this).attr("theSpanPageNum"));										
							fillUserManageHTML(userInfoAndPageInfoJSON);//填充界面
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}
			}
			//下一页span
			if(currentPage == pagesNum){
				var nextPage_span = $("<span>",{
					class:'nextPage_span currentPageIsOneOrLast',
					click:function(){}//不跳转	
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}else{
				var nextPage_span = $("<span>",{
					class:'nextPage_span',
					click:function(){
						var userInfoAndPageInfoJSON = getAllUserInfoLimit(currentPage + 1);//传入参数，点击的哪一页,获取用户信息
						fillUserManageHTML(userInfoAndPageInfoJSON);//填充界面
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
		}
	
	
	var userInfoTitle_tr = $("<tr>").appendTo(".user_manage_content_table");
	
	//用户编号
	var userInfoUserId_td = $("<td>").appendTo(userInfoTitle_tr).html("编号");
	
	//账号
	var userInfoLoginId_td = $("<td>").appendTo(userInfoTitle_tr).html("账号");
	
	//用户名
	var userInfoUserName_td = $("<td>").appendTo(userInfoTitle_tr).html("用户名");
	
	//性别
	var userInfoUserSex_td = $("<td>").appendTo(userInfoTitle_tr).html("性别");
	
	//注册时间
	var userInfoRegistationDate_td = $("<td>").appendTo(userInfoTitle_tr).html("注册时间");
	
	//电话
	var userInfoPhone_td = $("<td>").appendTo(userInfoTitle_tr).html("电话");
	
	//邮箱
	var userInfoEmail_td = $("<td>").appendTo(userInfoTitle_tr).html("邮箱");
	//操作
	var userInfoSetting_td = $("<td>").appendTo(userInfoTitle_tr).html("操作");
	$.each(userInfoJSON, function(index, value) {
		var userId = value.userId;//用户编号
		var loginId = value.loginId;//账号
		var userName = value.userName;//用户名
		var userSex = value.userSex;//性别
		var registationDate = value.registationDate;//注册时间
		var phone = value.phone;//电话
		var email = value.email;//邮箱
		var userStateId = value.userStateId;//用户账号状态
		//填充行
		var userInfo_tr = $("<tr>",{
			mouseover:function(){
				$(this).addClass("table_tr_mouseover");
			},
			mouseout:function(){
				$(this).removeClass("table_tr_mouseover");
			}
		}).appendTo(".user_manage_content_table");
		
		//填充用户编号列
		var userId_td = $("<td>").appendTo(userInfo_tr).html(userId);
		
		//填充用户账号列
		var loginId_td = $("<td>").appendTo(userInfo_tr).html(loginId);
		
		//填充用户名列
		var userName_td = $("<td>").appendTo(userInfo_tr).html(userName);
		
		//填充性别列
		var userSex_td = $("<td>").appendTo(userInfo_tr).html(userSex);
		
		//填充注册时间
		var registationDate_td = $("<td>").appendTo(userInfo_tr).html(registationDate);
		
		//填充电话
		var phone_td = $("<td>").appendTo(userInfo_tr).html(phone);
		
		//填充邮箱
		var email_td = $("<td>").appendTo(userInfo_tr).html(email);
		
		//填充操作
		var setting_td = $("<td>").appendTo(userInfo_tr);
		
		//删除按钮
		var deleteUserBtn = $("<div>",{
			userId:userId,
			class:'deleteUserBtn',
			title:'删除',
			click:function(){
				alert("确认删除？");
				var userId = $(this).attr("userId");
				console.log(userId);
				//删除并刷新
				$.ajax({
					type:"post",
					url:serverURL + "/AdminControlServlet?action=userdelete",
					async:false,//同步
					data:{"userId":userId},
					dataType:'text',//返回数据类型
					success:function(data){		
						var userInfoAndPageInfoJSON = getAllUserInfoLimit(1);
						fillUserManageHTML(userInfoAndPageInfoJSON);//填充界面
						alert("删除成功");
					},
				});
			}
		}).appendTo(setting_td).html("删除");
		
	});
	
	$(".user_manage_content_table tr:odd").css("background", "#EEEEEE");
}
