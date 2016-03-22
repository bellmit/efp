<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询发票</title>
<script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/easyui.css"/>
<script type="text/javascript" src="<%=basePath %>/js/jquery/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/easyui_global.css"/>

<script type="text/javascript">
	function exportData(){
		var row = datagrid.datagrid('getChecked');
		if(row !=null && row != ''){
			var fplshs=""; 
		    for(i=0;i<row.length;i++){
		    	fplshs += row[i].fpqqlsh+",";
			}
		    $('#fplshs').val(fplshs);
		}
	    $('#expForm').submit();
	}
</script>


</head>
<body>
<div id="toolbar_div" class="toolbar_div" >
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="exportData();" plain="true">导出</a>
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="hideOrShow();" plain="true">查询</a>
   
   <div id="div_search" class="div_search">
		<form id='searchForm' action="">
			<div class="" style="line-height: 30px;">
				<label>开票日期:</label>
				<input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="开始时间" value=""
	         	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
	         	<label>-</label>
	         	<input type="text" class="form-control" id="endDate" name="endDate" placeholder="结束时间" value=""
	         	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})">
				<label>收货人电话：</label>
				<input type="text"  id="shrdh4q" name="shrdh4q" placeholder="收货人电话">
				<label>订单号：</label>
				<input type="text"  id="ddh4q" name="ddh4q" placeholder="订单号">
				<label>学员ID：</label>
				<input type="text"  id="hyid4q" name="hyid4q" placeholder="学员ID">
				<label>发票号码：</label>
				<input type="text"  id="fphm4q" name="fphm4q" placeholder="发票号码">
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchfpList();" plain="true">查找</a>
			</div>
			<input type="hidden" name="fplx" value="004">
		</form>
   </div>
</div>
<form id="expForm" action="<%=basePath%>/report/download" method="post">
<input type="hidden" id ="fplshs" name="fplshs" value="">
<input type="hidden" id = "dateS4save" name="beginDate" value="">
<input type="hidden" id = "dateE4save" name="endDate" value="">
<input type="hidden" id = "shrdh4save" name="shrdh4q" value="">
<input type="hidden" id = "ddh4save" name="ddh4q" value="">
<input type="hidden" id = "hyid4save" name="hyid4q" value="">
<input type="hidden" id = "fphm4save" name="fphm4q" value="">
<input type="hidden" name="fplx" value="004">
<table id="datagrid"></table>
</form>

<script type="text/javascript">
initDataGridComponent();
var datagrid;
function initDataGridComponent(){
	var qParams = form2Json('searchForm');
	datagrid = $("#datagrid").datagrid({
				title : "查询发票",
				height: "450",
				checkOnSelect:true,
				selectOnCheck:false,
				singleSelect:false,
				rownumbers:true,
				idField:'fpqqlsh',
				url:"<%=basePath %>/report/queryFPlist",
				pagination : true,
				pageSize : 100,
				pageNumber:1,
				pageList: [10,20,50,100],
				queryParams: qParams,
				columns:[[
				     {field:'id',title:'id',width:100,checkbox:true},
				     {field:'fpqqlsh',title:'流水号',width:150,editor:'text'},
				     {field:'ddh',title:'订单号',width:150,editor:'text'},
		             {field:'hyid',title:'学员ID',width:100,editor:'text'},
		             {field:'fpdq',title:'地区',width:100,editor:'text',formatter:fpdqFormatter},
			         {field:'ddsj',title:'订单时间',width:100,editor:'text',formatter:dateFormatter}, 
			         {field:'kfsj',title:'付款时间',width:100,editor:'text',formatter:dateFormatter}, 
		             {field:'sqsj',title:'发票申请时间',width:100,editor:'text',formatter:dateFormatter},
		             {field:'fptt',title:'发票抬头',width:100,editor:'text'},
			         {field:'xmmc',title:'发票内容',width:100,editor:'text'},
			         {field:'bzfp',title:'发票备注',width:100,editor:'text'},
		             {field:'fplx',title:'发票种类',width:100,editor:'text',formatter:formatFpzl},
		             {field:'sqrk',title:'申请入口',width:100,editor:'text',formatter:formatSqrk},
		             {field:'gmfnsrsbh',title:'购方纳税人识别号',width:100,editor:'text'},
		             {field:'gfdzdh',title:'购方地址、电话',width:100,editor:'text'},
		             {field:'gmfyhzh',title:'购方开户行及账号',width:100,editor:'text'},
			         {field:'hjje',title:'发票金额',width:100,editor:'text'},
			         {field:'shr',title:'收货人',width:100,editor:'text'},
		             {field:'shrdh',title:'收货人电话',width:100,editor:'text'},
		             {field:'jsdz',title:'寄送地址',width:100,editor:'text'},
		             {field:'fphm',title:'发票号码',width:100,editor:'text'},
		             {field:'fhr',title:'发货人',width:100,editor:'text'},
		             {field:'wlgs',title:'物流公司 ',width:100,editor:'text'},
		             {field:'wldh',title:'物流单号',width:100,editor:'text'},
		             {field:'yjsj',title:'邮寄时间',width:100,editor:'text',formatter:dateFormatter},
				]],
				onClickRow:function(index){	
					
				},
				onLoadSuccess:function(){
					$('#datagrid').datagrid('clearSelections');
				}
	});
	
}



/**
 * 发票种类
 */
function formatFpzl(value,row,index){
	if (value=="004"){
		return "专票";
	} else if(value=="007") {
		return "普票";
	}
}
function fpdqFormatter(value,row,index){
	if (value=="00"){
		return "北京";
	} else if(value=="01") {
		return "上海";
	}
}
function formatSqrk(value,row,index){
	if (value=="00"){
		return "前台";
	} else if(value=="01") {
		return "后台";
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
	$('#dateS4save').val($('#beginDate').val());
	$('#dateE4save').val($('#endDate').val());
	$('#shrdh4save').val($('#shrdh4q').val());
	$('#ddh4save').val($('#ddh4q').val());
	$('#hyid4save').val($('#hyid4q').val());
	$('#fphm4save').val($('#fphm4q').val());
	var qParams = form2Json('searchForm');
	$("#datagrid").datagrid("load", qParams);
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
</body>
</html>