
var timerID;
var nTimeout;   
var saver;
var currentUser = "";
var basePath = "";


function setFormData(){
	var result = callCommFunc(com.bwplat.portal.user.service.UserService.findCurrentUserAccount);
	$("#userAccount").attr('disabled',true);
	$("#userAccount").val(result);
}


function reLogin(){
	var password = $("#password").val();
	var pwd = $.md5(password)
	var flag = callCommFunc(com.bwplat.portal.user.service.UserService.validatePassword,pwd);
	if(flag){
		$(".lock_canel").hide();
		$("#userAccount").val("");
//		initScreenSaver();
		init();
	}else{
		window.top.messageAlert('提示','密码输入错误！');
	}
}

function login(){
	if(currentUser == ""){
		window.top.location.href = "../../login/login.html";
	}else{
		$("#currentUser",window.top.document).html(currentUser);
	}
}

function getCurrentUser(){
	
	$.ajax({
		type:"POST",
        url: "../../user/getCurrentUser",
        async:false,
        success: function (data) {
//        	alert(JSON.stringify(data))
        	currentUser = data.currentUser;
        	basePath = data.basePath;
        },
        error:function(e) {
        	alert("加载失败!");
        	window.top.location.href="../../login/login.html";
        }
	});
	$("#currentUser",window.top.document).html(currentUser);
}
	
function init(){
	getCurrentUser();
	login();
	document.body.onmousemove = ScreenSaver.prototype.onevent;
	document.body.onmousedown = ScreenSaver.prototype.onevent;
	document.body.onkeydown = ScreenSaver.prototype.onevent;
	document.body.onkeypress = ScreenSaver.prototype.onevent;
}
	ScreenSaver.prototype.onevent = function(e){
		 window.clearTimeout(timerID);
		 saver = new ScreenSaver();
	}
	
	ScreenSaver.prototype.timeout = function(){
		window.top.location.href = basePath + "/login/login.html";
//		$(".lock_canel").show();
//		setFormData();
	}
	
	function ScreenSaver(){
		nTimeout = 30*60*1000;//500000000;
		timerID = window.setTimeout(f, nTimeout);
	}
	
	var f = function(){
		 ScreenSaver.prototype.timeout();
	}
	