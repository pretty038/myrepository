//这个东西要怎么搞呢？我进入界面，可以选择是要进入添加标题界面还是展示界面。或者可以分开，这就是一个单纯的输入界面。
var arraySelect = new Array();
var arrayNonSelect = new Array();
function initNonrelativeLabel(){
	$.get("/apcompany/data/labelAll", function(result) {
		var jsonObj = JSON.parse(result);
		arrayNonSelect[0] = new Array();
		arrayNonSelect[1] = new Array();
		for(var jn in jsonObj){
			if(jsonObj[jn].type === 1){
				//arrayNonSelect[0].push(jsonObj[jn].id,jsonObj[jn].labelname);
				arrayNonSelect[0][arrayNonSelect[0].length] = new Array(jsonObj[jn].id,jsonObj[jn].labelname);
			}else if(jsonObj[jn].type === 2){
				arrayNonSelect[1][arrayNonSelect[1].length] = new Array(jsonObj[jn].id,jsonObj[jn].labelname);
			}
		}
	});	
}
function initRelativeLabel(){
	$.get("/apcompany/data/labelRelAll",function(result){
		var jsonObj = JSON.parse(result);
		var array = new Array();
		var i = 0;
		for(var jn in jsonObj){
			//console.log(jsonObj[jn].id + ";" + jsonObj[jn].labelname + ";" + jsonObj[jn].parentsid);			
			array[i] = new Array();
			array[i][0] = jsonObj[jn].id;
			array[i][1] = jsonObj[jn].labelname;
			array[i][2] = jsonObj[jn].parentsid;
			array[i][3] = jsonObj[jn].depth;//代表深度。
			i++;
		}
		array.sort(function(x,y){
			var m = x[3] - y[3];
			if(m === 0)
				return x[0] - y[0];
			else 
				return m;
		});
		//console.log(array);
		//下一步应该是根据这个东西来生成各种button
		//首先是生成级别为1的option，
		var tmpdepth = 1;
		var newlevel = 0;
		arraySelect[0] = new Array();
		var k = 0;
		for(var i = 0;i < array.length;i++){
			if(array[i][3] != tmpdepth){
				//如果不等，说明层次发生了变化，需要新建一个Array，然后才能赋值。
				//k = 0;array[depth-1] = new Array
				tmpdepth++;
				arraySelect[tmpdepth-1] = new Array();
				k = 0;
				
			}
			//四个元素分别是id，labelname，parentsid，children的路径，自身的绝对路径.
			arraySelect[tmpdepth - 1][k] = new Array(array[i][0],array[i][1],array[i][2],"",(tmpdepth - 1) + ":" +k);		
			//console.log(arraySelect[tmpdepth - 1][k][1]);
			//如果相等说明还在这个层次。
			if(tmpdepth != 1){
				//第一层比较特别，不需要去查找上层的父节点，并把自己的绝对地址加进去。
				for(var j=0;j < arraySelect[tmpdepth-2].length;j++){
					if(arraySelect[tmpdepth - 1][k][2] === arraySelect[tmpdepth-2][j][0]){
						//如果该节点是新添加节点的父节点，就把绝对地址加进去。
						arraySelect[tmpdepth-2][j][3] += ("+"+(tmpdepth-1)+":"+k);
					}
				}
			}
			//赋值完成后，k要++
			k++;
		}
		//下面就要根据arraySelect来生成select。不同的select要用div隔开，不然会互相干扰change事件。
		//下面再定义select的change函数，补齐后面的select。
	});
}
$(document).ready(function(){
	//下面我要添加非相关标签的select和相关标签的select。
	initNonrelativeLabel();
	initRelativeLabel();
	test2();
	//接下来我要设计一下编辑界面，如果要编辑某个选项，怎么弄？
});

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
    	   //我要添加标签了，一个是非相关标签，一个非相关标签。
    	   var labelDiv = $("<div></div>",{class:"labelDiv"})
    	   //利用ajax取回字符串，然后将
    	   var strNonRelLabel = "0104200801101111111101";
    	   var tmpSt=new Array(0,2,4,10,11,13,14,15,16,17,18,19,20);
    	   var tmpstrLabel = new Array("科目","出版商","年份","是真题","题号","难度","题类","题型","用计算器","含数表","含图片","是证明题","题部分");
    	   var tmpEnd = new Array(2,4,9,11,13,14,15,16,17,18,19,20,22);
    	   for(var i = 0;i < 13;i++){
    		   var tmpSE = strNonRelLabel.slice(tmpSt[i],tmpEnd[i]);
    		   switch(i){
    		   case 0:
    			   //需要添加前两个标签。
    			   if(strNonRelLabel.slice(tmpSt[i],tmpEnd[i]) === "--"){
    				   //不设置该标签。
    			   } else {
    				   //arrayNonSelect[i].length写法不能识别，抑郁。
    				   for(var j = 0;j < arrayNonSelect[0].length;j++){
    					   if(arrayNonSelect[0][j][0] === Number(strNonRelLabel.slice(tmpSt[i],tmpEnd[i])))
    					   {
    						   var tmpSubjectLabel = $("<label></label>");
    						   tmpSubjectLabel.text(tmpstrLabel[i] + ":" +arrayNonSelect[i][j][1]);
    						   tmpSubjectLabel.appendTo(labelDiv);
    					   }
    				   }
    			   }
    			   break;
    		   case 1:
    			   if(strNonRelLabel.slice(tmpSt[i],tmpEnd[i]) === "--"){
    				   //不设置该标签。
    			   } else {
    				   //arrayNonSelect[i].length写法不能识别，抑郁。
    				   for(var j = 0;j < arrayNonSelect[1].length;j++){
    					   if(arrayNonSelect[1][j][0] === Number(strNonRelLabel.slice(tmpSt[i],tmpEnd[i])))
    					   {
    						   var tmpSubjectLabel = $("<label></label>");
    						   tmpSubjectLabel.text(tmpstrLabel[i] + ":" +arrayNonSelect[i][j][1]);
    						   tmpSubjectLabel.appendTo(labelDiv);
    					   }
    				   }
    			   }
    			   break;
    		   case 2:case 5:case 6:case 7:case 4:case 12:
    			   //添加日期
    			   if(strNonRelLabel.slice(tmpSt[i],tmpEnd[i]) === "--"){
    				   //不设置该标签。
    			   } else {    				   
    						   var tmpNonRelLabel = $("<label></label>");
    						   tmpNonRelLabel.text( tmpstrLabel[i] + ":" +strNonRelLabel.slice(tmpSt[i],tmpEnd[i]));
    						   tmpNonRelLabel.appendTo(labelDiv);    					  
    				   
    			   }
    		       break;   	  		   
    		   case 3:case 8:case 9:case 10:case 11:
    			   //添加，都是些default之类的，这里要用三个，-，代表该属性不存在，用占位符表示
    			   //回头需要给insert文件添加占位符。
    			   if(strNonRelLabel.slice(tmpSt[i],tmpEnd[i]) === "--"){
    				   //不设置该标签。
    			   } else {    				   
    						   var tmpNonRelLabel = $("<label></label>");
    						   if(strNonRelLabel.slice(tmpSt[i],tmpEnd[i]) === "1"){
    							   tmpNonRelLabel.text( tmpstrLabel[i]);
    							   
    						   } else {
    							   tmpNonRelLabel.text("不" + tmpstrLabel[i]);
    						   }
    						   tmpNonRelLabel.appendTo(labelDiv);       					  
    				   
    			   }
    			   break;
    		   }
    	   }
    	   //利用ajax取回相关标签。
    	   var strRelLabel = "15";
    	   var tmpSelect = new Array();
    	   var flagstr = strRelLabel;
    	   //在这里，我只要将原来的标签的路径给贴出来就行了，我不弄自动对齐了，因为这个功能不一定有用，而且复杂。
    	   for(var i = 0;i < arraySelect.length;i++){
    			for(var j = 0;j < arraySelect[i].length;j++){
    				//console.log(arraySelect[i][j][0] + ":" + flagstr);
    				if(arraySelect[i][j][0] === Number(flagstr)){
    					tmpSelect.push(i+":" + j);
    					console.log(arraySelect[i][j][2]);
    					if(arraySelect[i][j][2] != 0){
    						flagstr = arraySelect[i][j][2];
    						i=0;
    						j=0;    						
    					}
    				}    					   
    			}
    	   }
    	   console.log(tmpSelect);
    	   var tmpLabelRel = $("<label></label>");
    	   for(var i=tmpSelect.length-1;i < tmpSelect.length;i--){
    		   var tmpstrrel = tmpSelect[i].split(":");
    		   var tmpBtext = $("<b></b>");
    		   var tmp1 = Number(tmpstrrel[0]);
    		   var tmp2 = Number(tmpstrrel[1]);
    		   console.log(tmpSelect[i]);
    		   console.log("tmpstr22:" + tmp1);
    		   console.log(tmpstrrel[0] + ":" + tmpstrrel[1]);
    		   if(i!=0)
    			   tmpBtext.text(arraySelect[tmp1][tmp2][1] + "--->");
    		   else
    			   tmpBtext.text(arraySelect[tmp1][tmp2][1]);
    		   tmpBtext.appendTo(tmpLabelRel);
    	   }
    	   tmpLabelRel.appendTo(labelDiv);
    	   labelDiv.appendTo(chw);
    	   //问题标签,一段标签由 p包含一个label和一个button组成.
    	   var pQs = $("<p></p>");
    	   var qs = $("<label></label>",{class:"quesT"});
    	   qs.attr("title",jsonObj[jn].id);
 	   
    	   qs.html(jsonObj[jn].question);
    	   qs.appendTo(pQs);

    	   $("<br />").appendTo(pQs);   	   
    	   pQs.appendTo(chw);
    	 //下面是答案标签
    	   var pAn = $("<p></p>");
    	   var an = $("<label></label>",{class:"anT"});
    	   an.attr("title",jsonObj[jn].tAnswers.id);  	   
    	   an.html( "The answer is: "+jsonObj[jn].tAnswers.answer);
    	   an.appendTo(pAn);
    	   $("<br />").appendTo(pAn); 
    	   
    	   //下面是选项标签.
    	   var i = 0;
    	   var str = new String("ABCDEFGHIJK");
			for(var jn2 in jsonObj[jn].choices)
			{
				var tmpP = $("<p></p>");
				//console.log("option:" +jsonObj[jn].choices[jn2].choise);
				var op1 = $("<label></label>",{class:"opT"});
				op1.attr("title",jsonObj[jn].choices[jn2].id);		    	
				op1.html("("+ str.charAt(i)+ ") " + jsonObj[jn].choices[jn2].choise);
				op1.appendTo(tmpP);
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
	var tmpFormData = $("<form></form>");
	tmpFormData.attr("enctype","multipart/form-data");
	tmpFormData.insertAfter($("#tabUp"));
	var formdata = new FormData(tmpFormData);
	//div 的title当id。
	//p标签下面的label标签的title
	//p标签下面的label的text当
	var tmpname = t.parent().children("label").prop("title");
	formdata.append("id",tmpname);
	var tmpvalue = t.parent().children("label").text();
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
		urlString="../../apcompany/data/updateQuesion";
	} else if(u === 'b') {
		string10 = string9.substr(3);
		formdata.append("choise",string10);
		urlString="../../apcompany/data/updateChoise";
	} else if(u === 'c') {
		formdata.append("answer",string9);
		urlString="../../apcompany/data/updateAnswers";
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
	$("#tabUp").next().remove();
	
}