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
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery.js"></script>

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
<h4>查询条件</h4>
<form action="<%=basePath %>/report/query" method="post">
	
	申请起止日期：<input id="beginDate" name="beginDate" class="Wdate" 
	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" 
	value="${param.beginDate }" style="width: 100px;"/>-
	<input id="endDate" name="endDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})" 
	value="${param.endDate }" style="width: 100px;"/>&nbsp;&nbsp;&nbsp;
	开票地区：
	<select name="kpdq4q">
		<option value="">请选择</option>
		<option value="0">北京</option>
		<option value="1">上海</option>
	</select>
	<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	订单号：<input type="text" name="ddh4q" value="${ddh4q}">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	发票种类：<select name="fpzl4q">
		<option value="">请选择</option>
		<option value="007">增值税普通发票</option>
		<option value="004">增值税专用发票</option>
		<option value="026">增值税电子发票</option>
	</select>
	<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	发票类型：
	<select name="fplx4q">
		<option value="">请选择</option>
		<option >服务费</option>
		<option >咨询费</option>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	发票抬头：
	<select name="fptt4q">
		<option value="">请选择</option>
		<option >个人</option>
		<option >公司</option>
	</select>
	<input type="submit" value="查询">  <a href="javascript:void(0)" onclick="exportData()">导出excel</a>
</form><br/>
<form id="expForm" action="<%=basePath%>/report/download" method="post">
<table border="1" cellspacing="0" >
	<tr>
		<td><input type="checkbox" id="chkAll" onclick="checkAll()"></td>
<!-- 		<td>序号</td> -->
		<td>订单号</td>
		<td>申请人</td>
		<td>会员名</td>
		<td>会员ID</td>
		<td>订单时间</td>
		<td>申请时间</td>
		<td>发票抬头</td>
		<td>发票类型</td>
		<td>发票种类</td>
		<td>申请入口</td>
		<td>金额</td>
		<td>收货人</td>
		<td>收货人电话</td>
		<td>寄送地址</td>
		<td>邮寄时间</td>
		<td>发票号码</td>
		<td>发货人</td>
		<td>物流公司 </td>
		<td>物流单号</td>
		<td>退款状态</td>
		<td>发票状态</td>
	</tr>
	<c:forEach items="${fpxxList}" var="fp">
		<tr><td><input type="checkbox" class="chkbox_ex" value="${fp.fpqqlsh}" onclick="ccAll()"></td>
<%-- 			<td><c:out value="${fp.number}"/></td> --%>
			<td><c:out value="${fp.ddh}"/></td>
			<td><c:out value="${fp.sqr}"/></td>
			<td><c:out value="${fp.hym}"/></td>
			<td><c:out value="${fp.hyid}"/></td>
			<td><fmt:formatDate value="${fp.ddsj}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${fp.sqsj}" pattern="yyyy-MM-dd"/></td>
			<td><c:out value="${fp.fptt}"/></td>
			<td><c:out value="${fp.fplx}"/></td>
			<td><c:out value="${fp.fpzl}"/></td>
			<td><c:out value="${fp.sqrk}"/></td>
			<td><c:out value="${fp.je}"/></td>
			<td><c:out value="${fp.shr}"/></td>
			<td><c:out value="${fp.shrdh}"/></td>
			<td><c:out value="${fp.jsdz}"/></td>
			<td><fmt:formatDate value="${fp.yjsj}" pattern="yyyy-MM-dd"/></td>
			<td><c:out value="${fp.fphm}"/></td>
			<td><c:out value="${fp.fhr}"/></td>
			<td><c:out value="${fp.wlgs}"/></td>
			<td><c:out value="${fp.wldh}"/></td>
			<td><c:out value="${fp.tkzt}"/></td>
			<td><c:out value="${fp.fpzt}"/></td>
		</tr>
	</c:forEach>
</table>
<input type="hidden" name="dateS4save" value="${dateS4save}">
<input type="hidden" name="dateE4save" value="${dateE4save}">
<input type="hidden" name="kpdq4save" value="${kpdq4save}">
<input type="hidden" name="ddh4save" value="${ddh4save}">
<input type="hidden" name="fpzl4save" value="${fpzl4save}">
<input type="hidden" name="fplx4save" value="${fplx4save}">
<input type="hidden" name="fptt4save" value="${fptt4save}">
</form>
</body>
</html>