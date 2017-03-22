var labelLink = new SingleLink();
$(document).ready(function(){
//	$("#dv2").on("mouseover",".childw p",function(){
//		$(this).css("background-color","#C8E0F0");
//	});
//	$("#dv2").on("mouseout",".childw p",function(){
//		$(this).css("background-color","#FFFFFF");
//	});
});
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
			array[i][3] = 0 ;//代表深度。
			i++;
		}
		//下面要对所有的元素进行深度赋值.
		var ljson = i;
		var labelNum = i;
		var parentNum = new Array();
		parentNum.push("0");
		var depth = 1;
		while(labelNum > 0){
			var k = parentNum.length;//一会用来剪切parentNum元素用。
			//对于parentNum中每个元素都要执行一遍比对所有的元素 for循环
			for(var m=0;m < k;m++){
				for(var n = 0; n < array.length;n++){
					if(array[n][2] === parentNum[m]){
						labelNum--;
						array[n][3] = depth;
						parentNum.push(array[n][0]);
					}
				}
			}
			parentNum = parentNum.splice(0,k);
			depth++;
			//如果不符合，则不做什么。
			//如果符合，则将该id推送到parentNum中,切记不要干扰本次循环。
			//----还要将labelNum -1,还要将attray对应深度+1；
		}
		for(var o in array)
			console.log(array[o][0] + ";" +array[o][1] + ";" +array[o][2] + ";" +array[o][3]);
	});
}

