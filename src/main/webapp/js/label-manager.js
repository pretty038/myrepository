$(document).ready(function(){
	$("#frame0").on("click", ".add-option", addOption);
	$("#frame0").on("click", ".del-option", function(){
		//$(this).parent().remove();
	});
	$("#frame0").on("click",".hide-and-show-option",function(){
		if($(this).text() === "-")
		{			
			$(this).text("+");
			$(this).parent().children("div").hide();
		} else if($(this).text() === "+"){			
			$(this).text("-");
			$(this).parent().children("div").show();
		}
	});
});
function addOption(t,id,labelname,parentid,depth) {
	//对于展示函数，我调用这个传入的是父id，对于button，我传入的是$(this),可以根据t[0].tagName来判定。
	//如果是button我需要添加东西。id要通过mysql传入然后传回。好更改id。
	var tmpdiv = $("<div></div>");
	var tmpinput =$("<input />");
	var tmpAddbutton = $("<button>AddOption</button>");
	var tmpCommitbutton = $("<button>CommitChange</button>");
	var tmpDelbutton = $("<button>DelOption</button>");
	var tmpHidebutton = $("<button>-</button>");
	tmpinput.appendTo(tmpdiv);
	tmpAddbutton.appendTo(tmpdiv);
	tmpCommitbutton.appendTo(tmpdiv);
	tmpDelbutton.appendTo(tmpdiv);
	tmpHidebutton.addClass("hide-and-show-option");
	tmpHidebutton.appendTo(tmpdiv);
	if(t === "DIV"){
		//在这种情况下，要给div设置id名，根据传入的t，还要设置class名，与深度有关。inpu的值也要设定
		//父id有用，这样就必须设定五个参数，第一个用来设定标志。
		tmpdiv.attr("id","frame"+id);
		tmpdiv.addClass("depth" + depth);
		tmpinput.val(labelname);
		tmpdiv.appendTo($("#frame"+parentid));
		
	} else if(t instanceof jQuery) {
		
	}
}
function showAllLabels(){
	//ajax取回所有的labels。
//	$.ajax({
//        url:"../../apcompany/data/insert",
//        type:"post",
//        data:formdata,
//        processData:false,
//        contentType:false,
//        success:function(data){
//           
//        }
//	});
}
function showLabelTree(){
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
		for(var j=0;j < array.length;j++){
			addOption("DIV", array[j][0], array[j][1], array[j][2], array[j][3]);
		}
	});
}

