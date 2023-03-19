document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
$(function(){
	var userId = sessionStorage.getItem("userId");
	$("#btn").click(function(){
		var src = $("#view").attr("src");
		if (src == "" || src == null) {
			alert("请先截取图片");
		} else{
			$.ajax({
				type:"post",
				url:serverURL + "/UserControlServlet?action=updateUserHeaderImg",
				async:false,//同步
				data:{
					updateImgString:src,
					userId:userId
				},
				dataType:'text',//返回数据类型
				success:function(data){
					if (data == "200") {
						alert("修改成功");
						parent.location.reload();
					} else{
						alert("修改失败");
					}
				},//响应成功后执行代码
				error:function(){
					console.log("修改头像响应失败");
				}
			});
		}
	})
})
