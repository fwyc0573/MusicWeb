//传入参数是要填充的html路径
function appendHtml(htmlUrl){
	//先清除界面内容
	$(".adminHome_content").empty();
	$.ajax({
		type:"get",
		url:htmlUrl,
		async:false,
		success:function(data){
			$(".adminHome_content").append(data);
		}
	});
}
