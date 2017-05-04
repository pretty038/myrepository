function addkey(){
	var formdata = new FormData($("#tab"));
	var keyword = $("#word").val();
	formdata.append("id","0");
	formdata.append("name",keyword);
	formdata.append("fname",$("#fword").val());
	$.ajax({
        url:"../../apcompany/translate/insertOrUpdateKeyWords",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
            $.growl.notice({title: "添加关键字", message: "添加成功!" });
            window.location.reload();
        }
	});
}

function showkey(){
	$.get("../../apcompany/translate/selectAllKey",function(data){
		var jsonObj = JSON.parse(data);
		for(var jn in jsonObj){
			//jsonObj[jn].id
			var tmpDiv = $("<div></div>",{class:"divch"});
			tmpDiv.prop("id",jsonObj[jn].id);
			var wordSpan = $("<span><span>");
			wordSpan.html(jsonObj[jn].name + "<--->");
			wordSpan.appendTo(tmpDiv);
			var fwordSpan = $("<span><span>");
			fwordSpan.html(jsonObj[jn].fname);
			fwordSpan.appendTo(tmpDiv);
			tmpDiv.appendTo($("#keysw"));
		}
	});
	//我需要一个展示所有keyword的接口。
}