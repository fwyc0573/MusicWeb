function searchSongLimit(songName, startPage){
	$.ajax({
		type:"get",
		url:serverURL + "/SearchControlServlet?action=search",
		data:{"searchSongName":songName, "startPage":startPage},
		async:false,//异步
		dataType:'text',//返回数据类型
		success:function(data){
			sessionStorage.setItem("searchSong", data);
			var searchSongArr = eval("(" + data + ")");
			window.location.href = "SearchSong.html";
		},
		error:function(){
			alert("数据请求失败！");
		}
	})
}

function searchCDLimit(songName, startPage){
	$.ajax({
		type:"get",
		url:serverURL + "/SearchControlServlet?action=searchCD",
		data:{"searchSongName":songName, "startPage":startPage},
		async:false,//异步
		dataType:'text',//返回数据类型
		success:function(data){
			sessionStorage.setItem("searchSong", data);
			var searchSongArr = eval("(" + data + ")");
			window.location.href = "SearchSong.html";
		},
		error:function(){
			alert("数据请求失败！");
		}
	})
}
