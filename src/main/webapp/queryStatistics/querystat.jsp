<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计发票</title>
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
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="hideOrShow();" plain="true">查询条件</a>
   
   <div id="div_search" class="div_search">
		<form id='searchForm' action="">
			<div class="" style="line-height: 30px;">
				<label>开票起止日期:</label>
				<input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="开始时间" value=""
	         	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
	         	<label>-</label>
	         	<input type="text" class="form-control" id="endDate" name="endDate" placeholder="结束时间" value=""
	         	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})">
				<c:if test="${user.yhlx=='0'}">
					<label>开票地区：</label>
						<select name="kpdq4q" id="kpdq4q">
							<option value="">请选择</option>
							<option value="北京" <c:if test="${kpdq4save=='北京'}">selected="selected"</c:if>>北京</option>
							<option value="上海" <c:if test="${kpdq4save=='上海'}">selected="selected"</c:if>>上海</option>
						</select>
				</c:if>
				
				<label>发票种类：</label>
				<select name="fpzl4q" id="fpzl4q">
					<option value="">请选择</option>
					<option value="007" <c:if test="${fpzl4save=='007'}">selected="selected"</c:if>>增值税普通发票</option>
					<option value="004" <c:if test="${fpzl4save=='004'}">selected="selected"</c:if>>增值税专用发票</option>
					<option value="026" <c:if test="${fpzl4save=='026'}">selected="selected"</c:if>>增值税电子发票</option>
				</select>
				<label>发票内容：</label>
				<select name="fplx4q" id="fplx4q" >
					<option value="">请选择</option>
					<option <c:if test="${fplx4save=='服务费'}">selected="selected"</c:if>>服务费</option>
					<option <c:if test="${fplx4save=='咨询费'}">selected="selected"</c:if>>咨询费</option>
					<option <c:if test="${fplx4save=='培训费'}">selected="selected"</c:if>>培训费</option>
				</select>
				<label>发票抬头：</label>
				<select name="fptt4q" id="fptt4q">
					<option value="">请选择</option>
					<option value ="个人" <c:if test="${fptt4save=='个人'}">selected="selected"</c:if>>个人</option>
					<option value ="公司" <c:if test="${fptt4save=='公司'}">selected="selected"</c:if>>公司</option>
				</select>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchfpList();" plain="true">查找</a>
				
			</div>
		</form>
   </div>
</div>
<form id="expForm" action="<%=basePath%>/report/exportExcel" method="post">
<input type="hidden" id ="fplshs" name="fplshs" value="">
<input type="hidden" id = "dateS4save" name="dateS4save" value="">
<input type="hidden" id = "dateE4save" name="dateE4save" value="">
<input type="hidden" id = "kpdq4save" name="kpdq4save" value="">
<input type="hidden" id = "fpzl4save" name="fpzl4save" value="">
<input type="hidden" id = "fplx4save" name="fplx4save" value="">
<input type="hidden" id = "fptt4save" name="fptt4save" value="">
<table id="datagrid"></table>
</form>

<script type="text/javascript">
initDataGridComponent();
var datagrid;
function initDataGridComponent(){
	var qParams = form2Json('searchForm');
	datagrid = $("#datagrid").datagrid({
				title : "统计发票",
				checkOnSelect:true,
				selectOnCheck:false,
				singleSelect:false,
				rownumbers:true,
				idField:'fpqqlsh',
				url:"<%=basePath %>/report/queryFPstat",
				pagination : true,
				pageSize : 50,
				pageNumber:1,
				pageList: [10,20,50,100],
				queryParams: qParams,
				columns:[[
				     {field:'fpqqlsh',title:'fpqqlsh',width:100,checkbox:true},
				     {field:'kpdq',title:'发票地区',width:150,editor:'text'},
		             {field:'fpkh',title:'发票客户',width:100,editor:'text'},
		             {field:'kprq',title:'开票日期',width:100,editor:'text',formatter:dateFormatter},
		             {field:'fpzl',title:'发票种类',width:100,editor:'text',formatter:formatFpzl},
			         {field:'fpdm',title:'发票代码',width:100,editor:'text'}, 
		             {field:'fphm',title:'发票号码',width:100,editor:'text'},
		             {field:'ttlx',title:'抬头类型',width:100,editor:'text'},
			         {field:'gfmc',title:'购方名称',width:100,editor:'text'},
		             {field:'fpnr',title:'发票内容',width:100,editor:'text'},
			         {field:'bz',title:'备注',width:100,editor:'text'},
		             {field:'bszt',title:'报送状态',width:100,editor:'text'},
			         {field:'fpzt',title:'发票状态',width:100,editor:'text'},
			         {field:'hjje',title:'合计金额',width:100,editor:'text'},
		             {field:'hjse',title:'合计税额',width:100,editor:'text'},
		             {field:'jshj',title:'价税合计',width:100,editor:'text'},
				]],
				onClickRow:function(index){	
					
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
/**
 * 发票种类
 */
function formatFpzl(value,row,index){
	if (value=="004"){
		return "专票";
	} else if(value=="007") {
		return "普票";
	} else if(value=="026") {
		return "电子发票";
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
 * 打印
 */
function print(){
	var row = datagrid_zp.datagrid('getSelected');
	
	if(row ==null || row == ''){
		alert('请选择一项进行打印!');
		return;
	}
	alert(row.zddh)
	
	if(confirm("确定要打印么？")){
	 	getParameter();
		if(!SetParameter()){
			return;
		}
		PrintInvoice(fpqqlsh,fpdm, fphm);
	}
}
/**
 * 查询
 */
function searchfpList(){
	$('#dateS4save').val($('#dateS4q').val());
	$('#dateE4save').val($('#dateE4q').val());
	$('#kpdq4save').val($('#kpdq4q').val());
	$('#fpzl4save').val($('#fpzl4q').val());
	$('#fplx4save').val($('#fplx4q').val());
	$('#fptt4save').val($('#fptt4q').val());
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