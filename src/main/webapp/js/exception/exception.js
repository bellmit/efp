
$(function(){
	//获取异常信息
	var msg = window.top.exceptionMsg;
	var ex = window.top.exceptionInfo;
	var flag=true;
	//获取堆栈信息
	var stack="";
	if(ex.stackTrace!=null){
		for(var i=0;i<ex.stackTrace.length;i++){
			stack=stack+ex.stackTrace[i].className+"."+ex.stackTrace[i].methodName
				+"("+ex.stackTrace[i].fileName+":"+ex.stackTrace[i].lineNumber+")";
		}
	}
	var str = stack.split(")");
	var exceptionStack = "";
	for(var i=0;i<str.length-1;i++){
		exceptionStack = exceptionStack + str[i]+");"+"</br>";
	}
	//将异常信息打印到页面
	$("#exceptionId").html(msg);
	$("#exceptionStack").html(exceptionStack);
		//重置dialog框的属性 
	$("#btn").click(function(){
		 if(flag) {
		    window.parent.$('#common_dialog').panel('resize',{height:570})
		    $("#exceptionStack").show();
		    }
		    else {
		    $("#exceptionStack").hide();
		    window.parent.$('#common_dialog').panel('resize',{height:140});
		    }
		    flag=!flag; 
	});
});

//关闭异常页面
function closedialog(){
	window.parent.closeDialog();
}

