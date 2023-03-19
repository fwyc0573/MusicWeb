//分页获取歌曲信息
function getSongInfoLimit(clickPageNum){//参数点击的页码
	var songInfoAndPageInfoJSON;
	$.ajax({
		type:"post",
		url:serverURL + "/AdminControlServlet?action=songInfo",
		data:{"startPage":clickPageNum},//点击的页码,是否删除
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
			songInfoAndPageInfoJSON = data;
			console.log("songInfoAndPageInfoJSON"+songInfoAndPageInfoJSON);
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接到服务器");
		}
	});
	return songInfoAndPageInfoJSON;
}
