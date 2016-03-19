<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专用发票冲红</title>
<script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/easyui.css"/>
<script type="text/javascript" src="<%=basePath %>/js/jquery/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/easyui_global.css"/>
<OBJECT ID=sk CLASSID="clsid:003BD8F2-A6C3-48EF-9B72-ECFD8FC4D49F"
	codebase="NISEC_SKSCX.ocx#version=1,0,0,1"> </OBJECT>
</head>
<body>
<div id="toolbar_div" class="toolbar_div" >
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-tip" onclick="chFp();" plain="true">冲红</a>
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="hideOrShow();" plain="true">查询</a>
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="printSet();" plain="true">打印设置</a>
   
   <div id="div_search" class="div_search">
		<form id='searchForm' action="">
			<div class="" style="line-height: 30px;">
				<label>开票起止日期:</label>
				<input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="开始时间" value=""
	         	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
	         	<label>-</label>
	         	<input type="text" class="form-control" id="endDate" name="endDate" placeholder="结束时间" value=""
	         	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})">
				<label>会员ID：</label>
				<input type="text" id="hyid4q" name="hyid4q" placeholder="会员ID">
				<label>发票号码：</label>
				<input type="text" id="fphm4q" name="fphm4q" placeholder="发票号码">
				<label>订单号：</label>
				<input type="text" id="ddh4q" name="ddh4q" placeholder="订单号">
				<label>手机号：</label>
				<input type="text" id="sjh4q" name="sjh4q" placeholder="手机号">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchfpList();" plain="true">查找</a>
			</div>
		</form>
   </div>
   <div id="div_printset" class="div_search">
       <form id='setForm' action="">
       	   <div class="" style="line-height: 30px;">
       	   	   <input type="hidden" id='_fplx' name='fplx' value='004'>
		     	<label>上边距:</label>
				<input type="text" id="topside" name="topside" placeholder="上边距"/>
				<label>左边距:</label>
				<input type="text" id="leftside" name="leftside" placeholder="左边距"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="setSave();" plain="true">保存</a>
       	   </div>
       </form>
   </div>
</div>
<table id="datagrid"></table>
<div id="div_window" class="easyui-window" title="提示" closable='false' collapsible='false' minimizable='false' maximizable='false' 
	 style=" width:300px;height:200px;padding:5px; top:100px; left:400px;" closed='true'>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:0px; border: 0px;text-align: center;">
			<span style="font-size: medium; font-weight: 600;">请正确放置发票纸</span> <br>
			<div style="padding: 20px 20px 20px 60px; text-align: left;">
			<span>发票代码：</span><span id='div_fpdm' name='div_fpdm' style="color: red;"></span><br>
			<span>发票号码：</span><span id='div_fphm' name='div_fphm' style="color: red;"></span>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="text-align: center;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:printDivWindow();" >确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeDivWindow();" >取消</a>
		</div>
	</div>
</div>
<input id ="newFpqqlsh" type="hidden">
</body>
<script type="text/javascript">
var skconf = null;
var printconf = null;
var printable = false;
var searchCount = 0;
var setCount = 0;
getParameter();
function getParameter(){
	var params = {'fplx' : '004'};
	$.ajax({
		type:"POST",
        url: "<%=basePath %>/print/getParameter",
        data: params,
        async: false,
        success: function (data) {
        	if(data.code == '-1'){
        		window.top.location.href="../login/login.html";
        	}else if(data.code == '-2'){
        		printable = false;
        	}else{
        		skconf = data.skconf;
        		printconf = data.printconf;
        		printable = true;
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		window.top.location.reload();
        	}else{
        		window.parent.$.messager.alert('消息',"Error");
        	}
        }
	});
}


function SetParameter() {
	if(skconf == null){
		window.parent.$.messager.alert('消息',"初始化参数失败");
		return false;
	}
	//01设置初始化参数
	var sInputInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20001\" comment=\"参数设置\">\r\n<body yylxdm=\"1\">\r\n<servletip>" + skconf.url + "</servletip>\r\n<servletport>"+ skconf.port +"</servletport>\r\n<keypwd>123456</keypwd>\r\n<aqm>"+skconf.aqm+"</aqm>\r\n</body>\r\n</business>";		
	try {
		ret = sk.Operate(sInputInfo);
		var returncode = getTotalMidValue(ret, "<returncode>","</returncode>");
		var returnmsg = getTotalMidValue(ret, "<returnmsg>","</returnmsg>");	
		if(returncode==0&&returnmsg=="成功"){
			return true;
		}else{
			window.parent.$.messager.alert('消息',"参数设置失败，失败原因："+returnmsg);
			return false;
		}
	} catch (e) {
		window.parent.$.messager.alert('消息',e.message + ",errno:" + e.number);
		return false;
	}
}
 
//获取指定的字符串
function getTotalMidValue(source, priStr, suxStr) {
	if (source == null)
		return null
	var iFirst = source.indexOf(priStr);		
	var iLast = source.lastIndexOf(suxStr);		
	if (iFirst < 0 || iLast < 0)
		return null;	
	var beginIndex = iFirst + priStr.length;	
	return source.substring(beginIndex, iLast);
}

//将表单数据转为json
function form2Json(id) {

    var arr = $("#" + id).serializeArray()
    var jsonStr = "";

    jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}'

    var json = JSON.parse(jsonStr)
    return json
}

/**
 * 打印
 */
function print(fpqqlsh,fpdm,fphm){
	if(!printable){
		window.parent.$.messager.alert('消息','您没有权限打印增值税普通发票!');
		return;
	}
	$('#div_fpdm').html(fpdm);
	$('#div_fphm').html(fphm);
	$('#newFpqqlsh').val(fpqqlsh);
	$('#div_window').window('open');
}

function PrintInvoice(fpqqlsh,fpdm, fphm,fplx){
	//01页边距设置
	var sMarginsInfo = " <?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20003\" comment=\"页边距设置\">\r\n<body yylxdm=\"1\">\r\n<fplxdm>"+fplx+"</fplxdm>\r\n<top>" + printconf.topside + "</top>\r\n<left>" + printconf.leftside + "</left>\r\n</body>\r\n</business>";		
	sMargret = sk.Operate(sMarginsInfo);
	var returncodesMarg = getTotalMidValue(sMargret, "<returncode>","</returncode>");
	var returnmsgsMarg = getTotalMidValue(sMargret, "<returnmsg>","</returnmsg>");	
	if(returncodesMarg==0&&returnmsgsMarg=="成功"){
		//02、打印发票
		var sPrintInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?><business id=\"20004\"comment=\"发票打印\"><body yylxdm=\"1\"><kpzdbs>" + skconf.kpzdbs + "</kpzdbs><fplxdm>"+fplx+"</fplxdm><fpdm>"+fpdm+"</fpdm><fphm>"+ fphm+"</fphm><dylx>0</dylx><dyfs>0</dyfs></body></business>";
		sPrintInret = sk.Operate(sPrintInfo);
		var returncodesPrintIn = getTotalMidValue(sPrintInret, "<returncode>","</returncode>");
		var returnmsgsPrintIn = getTotalMidValue(sPrintInret, "<returnmsg>","</returnmsg>");
		if(returncodesPrintIn==0&&returnmsgsPrintIn=="成功"){
			//alert("打印成功！...");
			savePrintResult(fpqqlsh,3)
		}else{
			window.parent.$.messager.alert('消息',"打印失败，失败原因："+returnmsgsPrintIn);
		}
	}else{
		window.parent.$.messager.alert('消息',"页面距设置失败，失败原因："+returnmsgsMarg);
	}		
		
}
function savePrintResult(fpqqlsh,fpzt){
	var params = {'fpqqlsh': fpqqlsh,'fpzt' : fpzt};
	$.ajax({
		type:"POST",
        url: "../print/savePrintResult",
        data: params,
        async: false,
        success: function (data) {
        	//alert(JSON.stringify(data))
        	if(data == '1'){
        		window.parent.$.messager.alert('消息',"打印成功！");
//         		searchZpList();
        	}else{
        		window.parent.$.messager.alert('消息',"打印成功,保存失败");
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		window.top.location.reload();
        	}else{
        		window.parent.$.messager.alert('消息',"Error");
        	}
        }
	});
}
function printSet(){
	if(setCount%2 == 0){
		$(".div_search").hide();
		$("#div_printset").show();
		searchCount = 0;
		$('#topside').val(printconf.topside);
		$('#leftside').val(printconf.leftside);
	}else{
		$("#div_printset").hide();
	}
	setCount += 1;
	//$("#div_printset").toggle();
}

function setSave(){
	var params = $('#setForm').serialize();
	$.ajax({
		type:"POST",
        url: "../set/dysetting",
        data: params,
        async: false,
        success: function (data) {
        	//alert(JSON.stringify(data))
        	if(data == 1){
        		printconf.topside = $('#topside').val();
        		printconf.leftside = $('#leftside').val();
        		window.parent.$.messager.alert('消息',"保存成功");
        	}else{
        		window.parent.$.messager.alert('消息',"保存失败")
        	}
        	
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		location.reload();
        	}else{
        		window.parent.$.messager.alert('消息',"Error");
        	}
        }
	});	
		
}
function chFp(){
	var row = datagrid.datagrid('getSelected');
	var xml;
	if(row ==null || row == ''){
		alert('请选择一条记录进行操作!');
		return;
	}
	if(!confirm("发票号码："+row.fphm+"；确定要冲红此发票吗？")){
		return;
	}
	if(SetParameter()){
		$.ajax({
		        type:"POST",
		        url:"<%=basePath %>/einvoice/fpch",
				data:{'fpqqlsh':row.fpqqlsh},
				async: false,
				success : function(data) {
					if(data.status=='success'){
						xml = data.xml;
						Kp(xml,row.fpqqlsh);
					}else if(data.status=='-1'){
						window.parent.$.messager.alert('消息',data.msg);
						window.top.location.href="../login/login.html"
					}
				}	
		});
	}
}

 
function Kp(str,fpqqlsh){
		alert(str);
	try {
		var resultXml = sk.Operate(str);
		var returncode = getTotalMidValue(resultXml, "<returncode>","</returncode>");
		var returnmsg = getTotalMidValue(resultXml, "<returnmsg>","</returnmsg>");	
		if(returncode==0&&returnmsg=="成功"){
			var fpdm = getTotalMidValue(resultXml, "<fpdm>","</fpdm>");	
			var fphm = getTotalMidValue(resultXml, "<fphm>","</fphm>");	
			var newLshao = getTotalMidValue(str, "<fpqqlsh>","</fpqqlsh>");
			insertCh(fpqqlsh,resultXml,newLshao,fpdm,fphm);
			return true;
		}else{
// 			updateStatus(fpqqlsh ,str,resultXml,-1);
			alert("发票领购信息核对失败，失败原因："+returnmsg);
			return false;
		}
	} catch (e) {
			alert(e.message + ",errno:" + e.number);
	}
}

function insertCh(fpqqlsh,resultXml,newLshao,fpdm,fphm){
	$.ajax({
 	    type:"POST",
 	    url:"<%=basePath %>/einvoice/insertCh",
		data:{"fpqqlsh":fpqqlsh,"resultXml":resultXml,"newLshao":newLshao},
		dataType:'json',
		async: false,
		success: function (data) {
        	if(data.status == 'success'){
        		print(newLshao,fpdm,fphm);
        	}else{
        		alert("冲红成功,保存发票信息失败!");
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		window.top.location.reload();
        	}else{
        		alert("error!冲红成功,保存发票信息失败!");
        	}
        }
	});
} 
</script>
<script type="text/javascript">
initDataGridComponent();
var datagrid;
function initDataGridComponent(){
	var qParams = form2Json('searchForm');
	datagrid = $("#datagrid").datagrid({
				title : "专用发票冲红",
				height: "450",
				singleSelect:true,
				rownumbers:true,
				idField:'fpqqlsh',
				url:"",
				pagination : true,
				pageSize : 100,
				pageNumber:1,
				pageList: [10,20,50,100],
				queryParams: qParams,
				columns:[[
				     {field:'fpqqlsh',title:'fpqqlsh',width:150,editor:'text',hidden:true},
				     {field:'zddh',title:'订单号',width:150,editor:'text'},
		             {field:'hym',title:'会员名',width:100,editor:'text'},
		             {field:'hyid',title:'会员ID',width:100,editor:'text'},
			         {field:'shr',title:'收货人',width:100,editor:'text'}, 
		             {field:'shrdh',title:'收货人电话',width:100,editor:'text'},
		             {field:'sqsj',title:'申请时间',width:100,editor:'text',formatter:dateFormatter},
			         {field:'gmfmc',title:'发票抬头',width:100,editor:'text'},
		             {field:'spzl',title:'发票内容',width:100,editor:'text'},
			         {field:'hjje',title:'合计金额',width:100,editor:'text'},
		             {field:'hjse',title:'合计税额',width:100,editor:'text'},
			         {field:'jshj',title:'价税合计',width:100,editor:'text'},
			         {field:'kplx',title:'发票状态',width:100,editor:'text',formatter:formatKplx},
		             {field:'kprq',title:'开票日期',width:100,editor:'text',formatter:dateFormatter},
		             {field:'fpdm',title:'发票代码',width:100,editor:'text'},
		             {field:'fphm',title:'发票号码',width:100,editor:'text'}
				]],
				onClickRow:function(index){	
					
				},
				onLoadSuccess:function(){
					$('#datagrid').datagrid('clearSelections');
				}
	});
	
}



/**
 * 开票类型
 */
function formatKplx(value,row,index){
	if (value=="1"){
		return "红字发票";
	} else {
		return "蓝字发票";
	}
}
function dateFormatter(value) {
	if(value==null || value =='undefined'){
		return '';
	}
	var dateValue = new Date(value);
	return dateValue.format("yyyy-mm-dd");
}
/**
 * 显示或隐藏
 */
function hideOrShow(){
	$("#div_search").toggle();
}

/**
 * 查询
 */
function searchfpList(){
	$("#datagrid").datagrid({url:'<%=basePath%>/einvoice/zyfpch_q'});
	var qParams = form2Json('searchForm');
	$("#datagrid").datagrid("load", qParams);
}

function closeDivWindow(){
	$('#div_window').window('close');
}

function printDivWindow(){
	$('#div_window').window('close');
	if(!SetParameter()){
		return;
	}
	PrintInvoice($('#newFpqqlsh').val(),$('#div_fpdm').html(), $('#div_fphm').html(),'004');
}
Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "m+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "M+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

</script>
<script type="text/javascript" src="../js/downloadocx.js"></script>
</html>