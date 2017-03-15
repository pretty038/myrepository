$(document).ready(function(){
	$("#dv2").on("mouseover",".childw p",function(){
		$(this).css("background-color","#C8E0F0");
	});
	$("#dv2").on("mouseout",".childw p",function(){
		$(this).css("background-color","#FFFFFF");
	});
});
function formUp(){
	var formdata = new FormData($("#tabUp"));
	$(".childw:first p label").each(function(){
		//console.log($(this).children("label").text());
		//console.log($(this).children("input").prop("name"));
		var tmpname = $(this).prop("title");
		var tmpvalue = $(this).text();
		//用一连串的规则去拼目标字符串。
		var string2 = tmpvalue.replace(/<span.*?>.*?<\/span>/g,"");		
		var string3 = string2.replace(/<\/span>/g,"");
		var string4 = string3.replace(/<\/nobr>/g,"");		
		var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
		var string6 = string5.replace(/<\/script>/g,"}$");
		var string7 = string6.replace(/<label.*?>/g,"");		
		var string8 = string7.replace(/<\/label>/g,"");		
		var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
		console.log(tmpname,string9);
		//formdata.append($(this).children("input").prop("name"),$(this).children("label").text());
		formdata.append(tmpname,string9);
		
	});
	for(var valuetmp in formdata.values())
		console.log(valuetmp);
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
//test2()用来展示select得到的数据。
function test2(){
	$.get("/apcompany/data/select", function(result) {  
       //console.log(result); 
       //将result转为对象。
       var jsonObj = JSON.parse(result);
       //对对象执行循环操作。
       for(var jn in jsonObj)
		{
    	   var chw = $("<div></div>" ,{class:"childw"});
    	   //chw.attr("title",jsonObj[jn].id);
    	   //两个button
    	   var pBt = $("<p></p>");
    	   var btAddOp=$("<button>AddOption</button>");
    	   var btDelOp=$("<button>DelOption</button>");
    	   btAddOp.attr("onclick","AddOptionBt($(this))");
    	   btDelOp.attr("onclick","deleteDiv($(this))");
    	   btAddOp.appendTo(pBt);
    	   btDelOp.appendTo(pBt);
    	   pBt.appendTo(chw);
    	   //一段标签由 p包含一个label和一个button组成.
    	   var pQs = $("<p></p>");
    	   var qs = $("<label></label>",{class:"quesT"});
    	   qs.attr("title",jsonObj[jn].id);
    	   var btQs = $("<button>Edit</button>");
    	   btQs.attr("onclick","editAndSaveBt($(this))");
    	   btQs.addClass("editAndsave");
    	   var btCommitQs = $("<button>Commit</button>");
    	   btCommitQs.addClass=$("btCommit");
    	   btCommitQs.attr("onclick","commitChange($(this),'a')");   	   
    	   qs.html(jsonObj[jn].question);
    	   qs.appendTo(pQs);
    	   btQs.appendTo(pQs);
    	   btCommitQs.appendTo(pQs);
    	   $("<br />").appendTo(pQs);   	   
    	   pQs.appendTo(chw);
    	 //下面是答案标签
    	   var pAn = $("<p></p>");
    	   var an = $("<label></label>",{class:"anT"});
    	   an.attr("title",jsonObj[jn].tAnswers.id);
    	   var btAn = $("<button>Edit</button>");
    	   btAn.attr("onclick","editAndSaveBt($(this))");
    	   btAn.addClass("editAndsave");
    	   var btCommitAn = $("<button>Commit</button>");
    	   btCommitAn.addClass=$("btCommit");
    	   btCommitAn.attr("onclick","commitChange($(this),'c')");    	   
    	   an.html( "The answer is: "+jsonObj[jn].tAnswers.answer);
    	   an.appendTo(pAn);
    	   btAn.appendTo(pAn);
    	   btCommitAn.appendTo(pAn);
    	   $("<br />").appendTo(pAn); 
    	   //pAn.appendTo(chw);
    	   
    	   //下面是选项标签.
    	   var i = 0;
    	   var str = new String("ABCDEFGHIJK");
			for(var jn2 in jsonObj[jn].choices)
			{
				var tmpP = $("<p></p>");
				//console.log("option:" +jsonObj[jn].choices[jn2].choise);
				var op1 = $("<label></label>",{class:"opT"});
				op1.attr("title",jsonObj[jn].choices[jn2].id);
				var opButton = $("<button>Edit</button>");
				opButton.addClass("editAndsave");
				opButton.attr("onclick","editAndSaveBt($(this))");
				var btCommitTmp = $("<button>Commit</button>");
		    	btCommitTmp.addClass=$("btCommit");
		    	btCommitTmp.attr("onclick","commitChange($(this),'b')");		    	
				op1.html("("+ str.charAt(i)+ ") " + jsonObj[jn].choices[jn2].choise);
				op1.appendTo(tmpP);
				opButton.appendTo(tmpP);
				btCommitTmp.appendTo(tmpP);
				$("<br />").appendTo(tmpP);
				tmpP.appendTo(chw);				
				i++;
				
			}
			
			pAn.appendTo(chw);
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
function AddOptionBt(t){
	num++;
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	var op = $("<p></p>" ,{class:"neip"});
	var insideD = $("<label>选项</label>");
	var insideE = $("<button>Edit</button>")
	//var insideA = $("<input name=tChoises["+num+"].choise/>",{onblur:"exitInput($(this))"});
	//var insideB = $("<label></label>");
	//var insideC = $("<button>openEditor</button>");
	insideD.addClass("inputRes");
	insideD.attr("title","tChoises[" + num +"].choise");
	insideE.addClass("editAndsave");
	insideE.attr("onclick","editAndSaveBt($(this))");
	op.addClass("neip");
	insideD.appendTo(op);
	insideE.appendTo(op);
	$("<br/>").appendTo(op);
	//insideA.attr("onblur","exitInput($(this))");
	//attr才能实现这个，但是我在html上，在创建时候添加也行啊，而且class用的是Addclass，不明白为什么.
	//insideC.attr("onclick","editorOpen($(this))");
	//insideA.appendTo(op);
	//insideC.appendTo(op);
	//insideB.appendTo(op);
	//op.appendTo($("#cinQues"));
	op.insertBefore(t.parent().parent().children("p:last"));
}
function deleteDiv(t){
	t.parent().parent().children("p:last").prev().remove();
	//$("#cinQues p:last").remove();
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
function commitChange(t,u){
	//先生成数据格式。
	//u的值为  'a','b','c'分别代表问题，选项，答案。
	var formdata = new FormData($("#tabUp"));	
	//div 的title当id。
	//p标签下面的label标签的title
	//p标签下面的label的text当
	var tmpname = $(this).parent().children("label").prop("title");
	formdata.append("id",tmpname);
	var tmpvalue = $(this).parent().children("label").text();
	//用一连串的规则去拼目标字符串。
	var string2 = tmpvalue.replace(/<span.*?>.*?<\/span>/g,"");		
	var string3 = string2.replace(/<\/span>/g,"");
	var string4 = string3.replace(/<\/nobr>/g,"");		
	var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
	var string6 = string5.replace(/<\/script>/g,"}$");
	var string7 = string6.replace(/<label.*?>/g,"");		
	var string8 = string7.replace(/<\/label>/g,"");		
	var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
	
	//$(this),父标签中的label的title情形，tQuestions.question tChoises[0].choise tAnswers.answer
	//但是这样的话，我就得把id考虑进去。title就不能放那些东西了。
	//根据第二个参数进行判断吧。
	var urlString = "";
	if(u === 'a')
	{	 
		formdata.append("question",string9);
		urlString="../../apcompany/data/";
	} else if(u === 'b') {
		formdata.append("choise",string9);
		urlString="../../apcompany/data/";
	} else if(u === 'c') {
		formdata.append("answer",string9);
		urlString="../../apcompany/data/";
	}
	console.log(urlString);
	//ajax返回数据
	$.ajax({
        url:urlString,
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
        }
	});
	
}