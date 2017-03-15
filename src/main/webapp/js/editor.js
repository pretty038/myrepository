$(document).ready(function() {
	//				$("#inp").mouseover(function() {
	//					$(this).css("border-color", "#0066CC");
	//					console.log("in");
	//				});
	//				//				$("button").mouseover(function(){
	//				//					$(this).css("background-color","#C8E0F0")
	//				//				});
	//				$("button").on("mouseover", function() {
	//					$(this).css("background-color", "#C8E0F0");
	//				});
	//				$(".quesT,.anT,.opT").on("mouseover", function() {
	//					$(this).css("background-color", "#C8E0F0");
	//				});
	//focus事件展开键盘.
});

function editorAppValue(t) {
	//document.getElementById("inp").value += (t.value + " ");
	var tmp = $("#editor").prev().prev().prev().val();
	tmp += (t.value + " ");
	$("#editor").prev().prev().prev().val(tmp);
}

function editorOverBt() {
	var txt = $("#editor").prev().prev().prev().val();
	var oldtxt = $("#editor").prev().children("span").text();
	console.log(" over new is:" + txt);
	console.log("old orgin is" + oldtxt);
	//oldtxt = oldtxt.substring(3, oldtxt.length - 3);
	console.log(" overbt old substring is:" + oldtxt);
	//旧的默认添加公式标识符的.
	//var newtxt = "${ " + oldtxt + " " + txt + " }$";
	var newtxt = oldtxt + " " + txt;
	console.log(newtxt);
	//var lbl = document.getElementById("etr2");
	//lbl.innerHTML = newtxt;
	$("#editor").prev().text(newtxt);
	$("#editor").prev().prev().prev().val("");
	MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
}


function editorOpen(t) {
	$("#editor").appendTo(t.parent());
	$("#editor").show();
}
function editorClose(t){
	$("#editor").hide();
}
