//var isLockScreen = true;
var openWindow;
$(function(){
//	var flag = $("#isLockScreen").val();
//	 if(flag==""){
		 var url = callCommFunc(com.bwplat.portal.user.service.UserService.openNewWindow);
		 openWindow = window.open(url,'_blank','width=' + screen.width-0.5 + ',height=' + ((window.screen.availHeight<window.screen.height?screen.availHeight:screen.height)-56) + ',fullscreen =no,scrollbars=1,toolbar=0,resizable=1,left=0,top=0');
		 
//		 var opened=window.open('about:blank','_self');
//		 window.opener=null;
//		 window.open('','_self');
//		 window.close();
//	 }else{
////		 openWindow.close();
//		 window.location.href= 'http://127.0.0.1:8080/logout?service=http://127.0.0.1:8080';
//	 }
})

function colseChild(){
	openWindow.close();
}