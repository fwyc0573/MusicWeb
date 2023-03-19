document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script src='js/ajaxAppendHtml.js' type='text/javascript'></script>");

document.write("<script src='js/getUserInfoLimit.js' type='text/javascript'></script>");//填充用户信息界面
document.write("<script src='js/fillUserManageHtml.js' type='text/javascript'></script>");//填充用户信息界面

document.write("<script src='js/getSongInfoLimit.js' type='text/javascript'></script>");//获取歌曲信息
document.write("<script src='js/fillSongManageHtml.js' type='text/javascript'></script>");//填充歌曲界面

$(function(){
	
	$("#home_click").click(function(){window.location.reload();})//首页
	function getWindowHeight(){var windowHeight = $(window).height();return windowHeight;}//获取窗口高度
	
	//窗口大小刷新，页面高度也刷新
	$(window).resize(function(){
		$(".usermanage_content").css("height", (getWindowHeight() - 100) + "px");
		$(".usermanage_content2").css("height", (getWindowHeight() - 100) + "px");
		$(".user_manage_content_table").css("height", (getWindowHeight() - 140) + "px");//用户管理（表格高度设置）
		$(".user_manage_content_table2").css("height", (getWindowHeight() - 140) + "px");//歌曲管理（表格高度设置）
	})
	
	
	//导航栏
	$("div[class='manage_header_div']").click(function(){
		var height = getWindowHeight() - 100;
		var manageTypeName = $(this).attr("manage-type");
		$(".manage_header_div").each(function(index, element){
			var typeName = $(element).attr("manage-type");
			if (typeName == manageTypeName) {
				$(element).addClass("selectManage");
			} else{
				$(element).removeClass("selectManage");
			}
		})
		switch (manageTypeName){
		//用户管理
			case "user_manage":
				//先清除界面内容
				$(".adminHome_content").empty();
				$.ajax({
					type:"get",
					url:"UserManage.html",
					async:false,
					success:function(data){
						$(".adminHome_content").append(data);
					}
				});						
				$(".usermanage_content").css("height", (getWindowHeight() - 100) + "px");
				$(".user_manage_content_table").css("height", (getWindowHeight() - 140) + "px");
					
				var userInfoAndPageInfoJSON = getAllUserInfoLimit(1);//获取用户信息
				fillUserManageHTML(userInfoAndPageInfoJSON);//填充界面
				break;

			case "song_manage":
				//先清除界面内容
				$(".adminHome_content").empty();
				$.ajax({
					type:"get",
					url:"SongManage.html",
					async:false,
					success:function(data){
						$(".adminHome_content").append(data);
					}
				});
				$(".usermanage_content2").css("height", (getWindowHeight() - 100) + "px");
				$(".user_manage_content_table2").css("height", (getWindowHeight() - 140) + "px");
				
				var songInfoAndPageInfoJSON = getSongInfoLimit(1);
				fillSongManageHTML(songInfoAndPageInfoJSON);//填充界面
				break;
				
			default:
				break;
		}
	})
	
})
