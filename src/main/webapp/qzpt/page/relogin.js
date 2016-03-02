//var timer = null;
//var saver; 

var timerID;
var nTimeout;   
var saver;

//$(function(){
////	initScreenSaver();
////	window.document.onfocusin = stopTime;
//	
//	
//});

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


//function ScreenSaver(settings){     
//	   this.settings = settings;     
//	  
//	   this.nTimeout = this.settings.timeout;     
//	   	document.body.screenSaver = this;     
//	    document.body.onmousemove = ScreenSaver.prototype.onevent;     
//	    document.body.onmousedown = ScreenSaver.prototype.onevent;     
//	    document.body.onkeydown = ScreenSaver.prototype.onevent;     
//	    document.body.onkeypress = ScreenSaver.prototype.onevent;     
//	         
//	    var pThis = this;     
//	    var fn = function(){pThis.timeout();}     
//	    this.timerID = window.setTimeout(fn, this.nTimeout);     
//	}     
//	ScreenSaver.prototype.timeout = function(){     
//		$(".lock_canel").show();
//		setFormData();
////		opener.document.getElementById("isLockScreen").value=false;  
////		isLockScreen = false;
//		 $(document).keydown(function(event){
//			if(event.keyCode==116||event.keyCode==13){
//				event.keyCode = 0;
//				return false;
//					}
//				});
//		document.oncontextmenu = function(){ 
//				return false;
//		}
//	}     
//	ScreenSaver.prototype.signal = function(){  
//	    window.clearTimeout(this.timerID);   
//	    var pThis = this;     
//	    var fn = function(){pThis.timeout();}   
//	    this.timerID = window.setTimeout(fn, this.nTimeout);     
//	}     
//	    
//	ScreenSaver.prototype.onevent = function(e){     
//	    this.screenSaver.signal();     
//	}     
//	    
//	    
//	function initScreenSaver(){     
//	    saver = new ScreenSaver({timeout:5000});     
//	}     


	
	//function startTime(){
//	timer = window.setTimeout("goPost()",5000);
//}
//function goPost(){
// 	     $(".lock_canel").show();
// 	    $(document).keydown(function(event){
//			if(event.keyCode==116||event.keyCode==13){
//				event.keyCode = 0;
//				return false;
//				}
//			});
//		document.oncontextmenu = function(){ 
//			return false;
//	}
//} 
//function stopTime(){
//	window.clearTimeout(timer);
//	startTime();
//}
	
	
	
	
function init(){        
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
		$(".lock_canel").show();
		setFormData();
	}
	
	function ScreenSaver(){
		nTimeout = 500000000;
		timerID = window.setTimeout(f, nTimeout);   
	}
	
	var f = function(){  
		 ScreenSaver.prototype.timeout();
	}
	