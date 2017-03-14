$(document).ready(function(){
	$("#dv2").on("mouseover",".quesT,.anT,.opT",function(){
		$(this).css("background-color","#C8E0F0");
	});
	$("#dv2").on("mouseout",".quesT,.anT,.opT",function(){
		$(this).css("background-color","#FFFFFF");
	});
	$("#dv2").on("click",".quesT,.anT,.opT",function(){
		var tmp = $("<input />");
		tmp.attr("class", "tmpInput");
		var tmp2 = $("<button>Open Editor</button>");
		tmp.attr("class", "tmpBt");
		tmp.insertBefore($(this));
		tmp2.insertBefore($(this));
		$(this).css("background-color","#C8E0F0");
		//id赋值
		var str = $("#flg").text();
		$(this).attr("id","tmpPage" + str);
		
		tmp2.css("background-color","#C8E0F0");
		tmp.css("background-color","#C8E0F0");
		tmp2.attr("onclick","tmpClick($(this))");
		
	});
});
function formUp(){
	var formdata = new FormData($("#tabUp"));
	$("#cinQues .neip").each(function(){
		//console.log($(this).children("label").text());
		//console.log($(this).children("input").prop("name"));
		var tmpname = $(this).children("input").prop("name");
		var tmpvalue = $(this).children("label").text();
		console.log(tmpname,tmpvalue);
		//formdata.append($(this).children("input").prop("name"),$(this).children("label").text());
		formdata.append(tmpname,tmpvalue);
		
	});
	for(var valuetmp in formdata.values())
		console.log(vluetmp);
	$.ajax({
        url:"../../apcompany/data/insert",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
        }
	});
	
}
function editorOK(){
	var oldtxt = document.getElementById("quesInp").value;
	//console.log(oldtxt);
	var le = "$\{";
	var rg ="\}$";
	var newtxt = le + oldtxt  + rg;
	//console.log(newtxt);
	document.getElementById("quesLabel").innerHTML = newtxt;
	
	oldtxt = document.getElementById("answ").value;
	newtxt = "";
	//console.log(oldtxt);
	newtxt = "$\{" + oldtxt + "\}$";
	//console.log(newtxt);
	document.getElementById("answLabel").innerHTML = newtxt;
	
	oldtxt = document.getElementById("op1").value;
	newtxt = "";
	newtxt = "$\{" + oldtxt + "\}$";
	document.getElementById("option1").innerHTML = newtxt;
	
	oldtxt = document.getElementById("op2").value;
	newtxt = "";
	newtxt = "$\{" + oldtxt + "\}$";
	document.getElementById("option2").innerHTML = newtxt;
	
	oldtxt = document.getElementById("op3").value;
	newtxt = "";
	newtxt = "$\{" + oldtxt + "\}$";
	document.getElementById("option3").innerHTML = newtxt;
	
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
	
}
function refresh(){
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}
function test2(){
	$.get("/apcompany/data/select", function(result) {  
       //console.log(result); 
       //将result转为对象。
       var jsonObj = JSON.parse(result);
       //对对象执行循环操作。
       for(var jn in jsonObj)
		{
    	   var chw = $("<div></div>" ,{class:"childw"});
    	   var qs = $("<label></label>",{class:"quesT"});
    	   qs.html(jsonObj[jn].question);
    	   var an = $("<label></label>",{class:"anT"});
    	   an.html( "The answer is: "+jsonObj[jn].tAnswers.answer);
    	   qs.appendTo(chw);
    	   var i = 0;
    	   var str = new String("ABCDEFGHIJK");
			for(var jn2 in jsonObj[jn].choices)
			{
				//console.log("option:" +jsonObj[jn].choices[jn2].choise);
				var op1 = $("<label></label>",{class:"opT"});
				
					op1.text("("+ str.charAt(i)+ ") " + jsonObj[jn].choices[jn2].choise);
					op1.appendTo(chw);
				
				i++;
				
			}
			an.appendTo(chw);
			chw.appendTo($("#dv2"));
			
		}
       //在每个循环里都执行一次插入节点的操作。
    });
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}
function test3(){
	test2();
	refresh();
}
var num=0;
function AddOptionBt(){
	num++;
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	var op = $("<p></p>" ,{class:"neip"});
	var insideD = $("<label></label>");
	var insideE = $("<button>Edit</button>")
	var insideA = $("<input name=tChoises["+num+"].choise/>",{onblur:"exitInput($(this))"});
	var insideB = $("<label></label>");
	var insideC = $("<button>openEditor</button>");
	insideC.addClass("editorStart");
	op.addClass("neip");
	insideA.attr("onblur","exitInput($(this))");
	//attr才能实现这个，但是我在html上，在创建时候添加也行啊，而且class用的是Addclass，不明白为什么.
	insideC.attr("onclick","editorOpen($(this))");
	insideA.appendTo(op);
	insideC.appendTo(op);
	insideB.appendTo(op);
	op.appendTo($("#cinQues"));
}
function deleteDiv(){
	$("#cinQues p:last").remove();
	num--;
}
function goInput(t){
	console.log("go input");
}
function exitInput(t){
	//console.log("exit input");
	//t.next().next().html("$\{ " + t.next().next().children("span").text() + t.val() +" \}$");
	//refresh();
	//MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}
//if the answer is

function tmpClick(t){
//	console.log("in");
//	//console.log($("#tmpPage").text());
//	var str = $("#flg").text();
//	$("#tmpPage" + str).text( "$\{" + $(".tmpInput").val() + "\}$" );
//	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
//	$("#tmpPage" + str).css("background-color","#FFFFFF");
//	var num = Number(str);
//	num++;
//	$("#flg").text(num);
//	//$("#tmpPage").removeAttr("id");
//	$(".tmpInput").remove();
//	$(".tmpBt").remove();
	console.log("go editor");
	$("#editor").appendTo(t.parent());
	$("#editor").show();
}
function clickNeipP(t){
	//弹出一个input和两个button,一个button关联打开editor,未必会需要打开editor。
	//---另一个button关联提交修改。再加一个，三个button。第三个button，进入编辑模式。
}
function editAndSaveBt(t){
	//需要做什么呢。插入一个input按钮和一个打开editor的button.
	if($("#editorFlg").text() === "0")
	{
		//进入修改模式.
		t.html("Save");
		$("#editorFlg").text("1");
		var tmpInput = $("<input />");
		var tmpEditorBt = $("<button>openEditor</button>");
		
		tmpInput.addClass("editAndSaveInput");
		tmpEditorBt.addClass("editorOpenBt");
		tmpEditorBt.attr("onclick","tmpClick($(this))");
		tmpInput.appendTo(t.parent());
		tmpEditorBt.appendTo(t.parent());
		//进入修改模式，将lable中的text()引入到input中.
		var oldInputTxt = t.parent().children("label").html();
		//用一连串的规则去拼目标字符串。
		var string2 = oldInputTxt.replace(/<span.*?>.*?<\/span>/g,"");		
		var string3 = string2.replace(/<\/span>/g,"");
		var string4 = string3.replace(/<\/nobr>/g,"");		
		var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
		var string6 = string5.replace(/<\/script>/g,"}$");
		var string7 = string6.replace(/<label.*?>/g,"");		
		var string8 = string7.replace(/<\/label>/g,"");		
		var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
		t.parent().children("input").val(string9);
	} else {
		t.html("Edit");
		var txtInput = t.parent().children("input").val();
		t.parent().children("label").text(txtInput);
		$("#editorFlg").text("0");
		$(".editAndSaveInput").remove();
		$(".editorOpenBt").remove();
		//将input的值放入到lable中,清空本身。
	}
}