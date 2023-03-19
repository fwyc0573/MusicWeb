document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
//热门歌单Ajaxjs
document.write("<script type='text/javascript' src='js/SongListOfAccessCountLimit.js'></script>");
//登录界面显示
document.write("<script type='text/javascript' src='js/ShowLoginDiv.js'></script>");


$(function(){
	new_element = document.createElement("script");
	new_element.setAttribute("type","text/javascript");
	new_element.setAttribute("src", "js/GetSongListInfo.js");
	document.body.appendChild(new_element);
	
	//热门专辑信息
	function getAccessCountSongList(){
		console.log("进入index.js->getAccessCountSongList函数");
		$.ajax({
			type:"get",
			url:serverURL + "/SongsControlServlet?action=hot_give",
			async:false,
			dataType:'text',
			success:function(data){
				//alert("热门歌单响应成功：" + data);
				sessionStorage.setItem("HotCDInfo",data);
			},
			error:function(){
				console.log("无法连接到服务器");
			}
		});
	}
	getAccessCountSongList();

	function setIndexAjax(inputId, setHtmlId, setHtmlText, inputValue, loginId,password, url){
		//inputId要判断的输入框的id值，格式为#id
		//setHtmlId提示语显示位置的id值，格式为#id
		//setHtmlText提示语
		//inputValue输入框输入的值
		if(inputValue != null || inputValue != ""){
			$.ajax({
				type:"post",//提交的方式
				url:url,//提交的URL路径
				data : {"loginId":loginId, "password":password},
				dataType : "text",//指定返回数据类型
				async : false,//同步
				success : callBack,//响应成功后执行代码
				error : function(){//响应失败执行代码
					alert("账号验证出错！");
					return false;
				}
			});

			//响应成功时的回调函数
			function callBack(data){
				var dataArr = eval("(" + data + ")")
				console.log(dataArr);
				if(dataArr.shibai == "false"){
					$(inputId).focus();
					$(setHtmlId).empty();
					$(setHtmlId).html(setHtmlText);
					$(setHtmlId).css({"color":"red", "font-size":"14px", "text-align":"center"});
				}else{
					var userStateId = dataArr.userStateId;
					if (userStateId == 0) {
						userId = dataArr.userId;
						userName = dataArr.userName;
						headSculptureUrl = dataArr.headSculptureUrl;
						sessionStorage.setItem("userName", userName);
						sessionStorage.setItem("userId", userId);
						sessionStorage.setItem("headSculptureUrl", headSculptureUrl);
						sessionStorage.setItem("commentSongList",data);
						window.location.href = "index.jsp";
					}
					else if(userStateId == 1){
						window.location.href = "adminHome.html";
					}
				}
			}
		}
	}
	var userName = sessionStorage.getItem("userName");
	//登录后设置界面
	function setLodinDivUserName(){
		if(userName != "undefined" && userName != null){
			//登录后
			var showUserNameSpan = $("<span>", {}).appendTo($("#index_login_div"));
			$(showUserNameSpan).html(userName);
			//填充菜单
			var login_after = $("<div>",{id:'login_after',class:'login_way'}).appendTo($("#index_login_div"));
			var myHome_p = $("<p>",{class:'login_yet_MyHome'}).appendTo(login_after);
			var home_a = $("<a>",{href:"PersonalCenter.html",target:'main'}).appendTo(myHome_p);
			$(home_a).html("个人中心");
			var exit_p = $("<p>",{class:'login_yet_exit'}).appendTo(login_after);
			var exit = $("<a>",{}).appendTo(exit_p);
			$(exit).html("退出");
		}
		else
		{
			//登录前
			var login_before = $("<div>",{id:'login_before'}).appendTo($("#index_login_div"));
			var loginName = $("<span>", {id:'login_text'}).appendTo(login_before);$(loginName).html("登录");
			var login_way = $("<div>",{class:'login_way'}).appendTo(login_before);
			var login_phone_p = $("<p>",{class:'login_phone_p'}).appendTo(login_way);
			var phone_login_img = $("<img/>",{
				alt:'',
				src:'images/phone.png',
				height:'17',
				align:'center'
			}).appendTo(login_phone_p);
			
			$(login_phone_p).append("手机号登录");
			var login_zhanghao_p = $("<p>",{class:'login_zhanghao_p'}).appendTo(login_way);

			var loginId_login_img = $("<img/>",{
				alt:'',
				src:'images/zhanghao.png',
				height:'17',
				align:'center'
			}).appendTo(login_zhanghao_p)
			$(login_zhanghao_p).append("账号登录");
		}
	}
	setLodinDivUserName();
	
	$("nav[class='index_header_nav'] a").click(function(){
		if($(this).index() != 1){
			$("div[class='header_a1_little_menu']").children().filter("div").css("display","none");
			$("div[class='header_a1_little_menu']").css("height","5px");
		}else{
			$("div[class='header_a1_little_menu']").children().filter("div").css("display","block");
			$("div[class='header_a1_little_menu']").css("height","35px");
		}
		$("nav[class='index_header_nav'] input").each(function(index, element){
			$("nav[class='index_header_nav'] input:checked").attr("checked", false);
		});
		$(this).prev().attr('checked',true);
	})
	
	$("div[class='header_a1_little_menu'] a").click(function(){
		var thisA = this;
		$("div[class='header_a1_little_menu'] input").each(function(index, element){
			$("div[class='header_a1_little_menu'] input:checked").attr("checked", false);
		});
		$(this).prev().attr('checked',true);
	})
	//登录方式显示
	$("div[class='index_header_login']").mouseover(function(){
		$("div[class='login_way']").css("display","block");
	})
	$("div[class='index_header_login']").mouseout(function(){
		$("div[class='login_way']").css("display","none");
	})
	
	//手机号登录
	$("p[class='login_phone_p']").click(function(){
		setPhoneLoginHtml();
	});
	
	//账号登录
	$("p[class='login_zhanghao_p']").click(function(){
		setLoginIdHtml();
	});
	//关闭登录窗口
	$("div[class='phone_login").delegate("span[class='none_login']", "click", function(){
		$("div[class='phone_login']").children().remove();
		$("div[class='phone_login']").css("display","none");
	})
	//修改界面
	$("div[class='phone_login").delegate("span[id='change_to_loginId']", "click", function(){
		setLoginIdHtml();
	})
	$("div[class='phone_login").delegate("span[id='change_to_phone']", "click", function(){
		setPhoneLoginHtml();
	})
	
	//手机号点击登录
	$("div[class='phone_login").delegate("input[id='phone_login_button']", "click", function(){
		var phone = $("#phone").val();
		var password = $("#password").val();
		var inputId = "#phone";
		var setHtmlId = "#phoneDiv";
		var setHtmlText = "账号或密码错误！";
		var inputValue = phone;
		var url = serverURL + "/UserControlServlet?action=login2";
		setIndexAjax(inputId, setHtmlId, setHtmlText, inputValue, phone, password, url);
	})
	//账号点击登录
	$("div[class='phone_login").delegate("input[id='loginid_login_button']", "click", function(){
		var loginId = $("#loginId").val();
		var password = $("#password").val();
		var inputId = "#loginId";
		var setHtmlId = "#phoneDiv";
		var setHtmlText = "账号或密码错误！";
		var inputValue = loginId;
		var url = serverURL + "/UserControlServlet?action=login";
		setIndexAjax(inputId, setHtmlId, setHtmlText, inputValue, loginId, password,url);
	})
	
	//点击注册
	$("div[class='phone_login").delegate("span[class='regist_span']", "click", function(){
		$("div[class='phone_login']").css("display","none");
		window.open("Register.html", "_blank");//打开新窗口
	})
	$("#index_login_div").delegate("p[class='login_yet_exit']", "click", function(){
		sessionStorage.clear();
		alert("已退出");
		window.location.href = "index.jsp";
	})
	
	
	//底部播放音乐div
	$(".index_footer").mouseover(function(){
		$(this).css("height","60px");
	})
	$(".index_footer").mouseout(function(){
		$(this).css("height","10px");
	})
})
