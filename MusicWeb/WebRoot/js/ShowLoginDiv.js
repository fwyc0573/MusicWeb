//创建手机号登录界面
	function setPhoneLoginHtml(){
		$("div[class='phone_login']").children().remove();
		$("div[class='phone_login']").css("display","block");
		var html = '<div class="phone_login_header">手机号登录<span class="none_login">+</span></div>' + 
					'<form method="post" id="login_form" class="phone_login_form">' + 
					'<input type="text" name="phone" id="phone" placeholder="请输入手机号" required="required" pattern="1[3|5|7|8][0-9]{9}"/>'+
					'<input type="password" name="password" id="password" placeholder="请输入密码" required="required" />'+
					'<p id="phoneDiv"></p>'+
					'<input type="button" id="phone_login_button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录">'+
					'</form>'+
					'<div class="phone_login_foot">' +
					'<span class="change_login_way" id="change_to_loginId"><<账号登录</span>'+
					'<span class="regist_span">没有账号？立即注册</span>' +
					'</div>';
		var phone_login_html = $(html);
		$("div[class='phone_login']").append(phone_login_html);
	}
	
	//创建账号登录界面
	function setLoginIdHtml(){
		$("div[class='phone_login']").children().remove();
		$("div[class='phone_login']").css("display","block");
		var html = '<div class="phone_login_header">账号登录<span class="none_login">+</span></div>' + 
					'<form method="post" class="phone_login_form" id="login_form">' + 
					'<input type="text" name="loginId" id="loginId" placeholder="请输入账号" required="required"/>'+
					'<input type="password" name="password" id="password" placeholder="请输入密码" required="required" />'+
					'<p id="phoneDiv"></p>'+
					'<input type="button" id="loginid_login_button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录">'+
					'</form>'+
					'<div class="phone_login_foot">' +
					'<span class="change_login_way" id="change_to_phone"><<手机号登录</span>'+
					'<span class="regist_span">没有账号？立即注册</span>' +
					'</div>';
		var loginId_login_html = $(html);
		$("div[class='phone_login']").append(loginId_login_html);
	}