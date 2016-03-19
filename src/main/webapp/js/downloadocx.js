
try{
	var micx=new ActiveXObject('NISECSKSCX.NISECSKSCXCtrl.1');
}catch(e)  {
	window.parent.$.messager.alert('消息',"您未安装打印控件,请先安装...");
	location.href='../download/skfwqzjjk-bzbV1.1.0.9.EXE';
}
