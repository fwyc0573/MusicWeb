//获取用户信息
function getAllUserInfoLimit(clickPageNum){//传入参数是点击的页码
	var userInfoAndPageInfoJSON;
				$.ajax({
					type:"post",
					url:serverURL + "/AdminControlServlet?action=userInfo",
					data:{"startPage":clickPageNum},//点击的页码
					async:false,//同步
					dataType:'json',//返回数据类型
					success:function(data){
						userInfoAndPageInfoJSON = data;
						console.log("userInfoAndPageInfoJSON"+userInfoAndPageInfoJSON);
					},//响应成功后执行代码
					error:function(){
						console.log("无法连接到服务器");
					}
				});
	return userInfoAndPageInfoJSON;
}
