document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
$(function(){
	var userId = sessionStorage.getItem("userId");
	function clickLimitAjax(searchSongName, startPage){
		$.ajax({
			type:"get",
			url:serverURL + "/SearchControlServlet?action=search",
			data:{"searchSongName":searchSongName, "startPage":startPage},
			async:true,
			dataType:'text',
			success:function(data){
				console.log("clickLimitAjax"+data);
				sessionStorage.setItem("searchSong", data);
				window.main.location.href  = "SearchSong.html";
			},
			error:function(){
				alert("数据请求失败！");
			}
		})
	}
	
	$("#search_button").click(function(){
		sessionStorage.setItem("search_type",null);
		//获取搜索框输入的内容
		//.trim()去除首位空白字符
		//.replace(/\s/g,"")：去除中间的空白字符（其中/与/g是用来包含前面的，\s则是匹配任何空白字符，包括空格、制表符、换页符等
		var searchSongName = "";
		var searchValue = $("#searchSongName_input").val().trim().replace(/\s/g,"");
		if (searchValue == null || searchValue == "") {//没有输入值
			alert("请输入内容");
		} else{
			//按值查询
			$("div[class='header_a1_little_menu']").children().filter("div").css("display","none");
			$("div[class='header_a1_little_menu']").css("height","5px");	
			searchSongName = searchValue;
			sessionStorage.setItem("searchSongName", searchSongName);
			clickLimitAjax(searchSongName);
		}
	});
	
})
