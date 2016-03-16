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
		var chks = $('.chkbox_ex');
		for(i=0;i<chks.length;i++){
			if(chks[i].checked == true){
				chks[i].name = 'lsh4ept';
			}
		}
		$('#expForm').submit();
	}
	function checkAll(){
		var chks = $('.chkbox_ex');
		for(i=0;i<chks.length;i++){
			chks[i].checked=$('#chkAll').attr('checked');
		}
	}
	//取消全选的勾
	function ccAll(){
		$('#chkAll').attr('checked',false);
	}
</script>


</head>
<body>
<div id="toolbar_div" class="toolbar_div" >
   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-filesave" onclick="exportData();" plain="true">导出</a>
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
<input type="hidden" name="dateS4save" value="${dateS4save}">
<input type="hidden" name="dateE4save" value="${dateE4save}">
<input type="hidden" name="kpdq4save" value="${kpdq4save}">
<input type="hidden" name="ddh4save" value="${ddh4save}">
<input type="hidden" name="fpzl4save" value="${fpzl4save}">
<input type="hidden" name="fplx4save" value="${fplx4save}">
<input type="hidden" name="fptt4save" value="${fptt4save}">
<table id="datagrid"></table>
</form>

<script type="text/javascript">
initDataGridComponent();
var datagrid;
function initDataGridComponent(){
	var qParams = form2Json('searchForm');
	datagrid = $("#datagrid").datagrid({
				title : "统计发票",
				singleSelect:false,
				rownumbers:true,
				idField:'id',
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
		             {field:'kprq',title:'开票日期',width:100,editor:'text'},
		             {field:'fpzl',title:'发票种类',width:100,editor:'text'},
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

</script>
</body>
</html>