<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待开专用发票</title>
<script type="text/javascript"
	src="<%=basePath%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery.js"></script>
</head>
<body>
	<h4>待开专用发票查询</h4>
	<form action="<%=basePath%>/fpkj/special" method="post">
		开始时间：<input id="beginDate" name="beginDate" class="Wdate"
			onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
			value="${param.beginDate }" style="width: 100px;" />~ 结束时间 <input
			id="endDate" name="endDate" class="Wdate"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"
			value="${param.endDate }" style="width: 100px;" /><br /> 主订单号码：<input
			type="text" id="zddh" name="zddh" value="${param.zddh }" /> 开票地区：<select
			id="kpdq" name="kpdq">
			<option value="">-请选择-</option>
			<option value="0"
				<c:if test="${param.kpdq == 0 }">selected="selected"</c:if>>北京</option>
			<option value="1"
				<c:if test="${param.kpdq == 1 }">selected="selected"</c:if>>上海</option>
		</select> <br /> <input type="submit" value="查询" /> <input id="kp"
			type="button" value="多张开票" onclick="multi_kjfp()" />

	</form>
	<br />
	<table border="1" cellspacing="0">
		<tr>
			<th><input type="checkbox" id="chkAll" onclick="checkAll()"></th>
			<th>序号</th>
			<th>主订单号</th>
			<th>副订单号</th>
			<th>会员名</th>
			<th>收货人</th>
			<th>申请时间</th>
			<th>开票地区</th>
			<th>发票种类</th>
			<th>发票抬头</th>
			<th>发票内容</th>
			<th>合计金额</th>
			<th>合计税额</th>
			<th>价税合计</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${kpxxList}" var="fp" varStatus="f">
			<tr>
				<td><input type="checkbox" id="fpqqlsh" name="fpqqlsh"
					class="chkbox_ex" value="${fp.fpqqlsh}"></td>
				<td>${f.count }</td>
				<td><c:out value="${fp.zddh}" /></td>
				<td><c:out value="${fp.fddh}" /></td>
				<td><c:out value="${fp.hym}" /></td>
				<td><c:out value="${fp.shr}" /></td>
				<td><fmt:formatDate value="${fp.sqsj}" pattern="yyyy-MM-dd" /></td>
				<td><c:if test="${fp.kpdq == 0 }">北京</c:if> <c:if
						test="${fp.kpdq == 1 }">上海</c:if></td>
				<td><c:if test="${fp.fplx == '004' }">增值税普通发票</c:if> <c:if
						test="${fp.fplx == '007' }">增值税专用发票</c:if> <c:if
						test="${fp.fplx == '026' }">增值税电子发票</c:if></td>
				<td><c:out value="${fp.gmfmc}" /></td>
				<td><c:out value="${fp.spzl}" /></td>
				<td><c:out value="${fp.hjje}" /></td>
				<td><c:out value="${fp.hjse}" /></td>
				<td><c:out value="${fp.jshj}" /></td>
				<td><a href="javascript:void(0)" onclick="kjfp(${fp.fpqqlsh});">开票</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
	function kjfp(fpqqlsh){
		alert(fpqqlsh);
		$.ajax({
 	        type:"POST",
 	        url:"<%=basePath%>/fpkj/kp",
			data:{"ph":fpqqlsh},
			dataType:'json', 
			success : function(data) {
			alert(data.xml);
			try{
				ret = sk.Operate(data.xml);
				alert(ret);
		    }
			catch(e){
				alert(e.message + ",errno:" + e.number);
		    }	
		}
	});
	}
	
	function multi_kjfp(){
		var fpList = new Array(); 
		var chks = $('.chkbox_ex');
	    for(i=0;i<chks.length;i++){
			if(chks[i].checked == true){
				fpList[i]=chks[i].value;
			}
		} 
	    if(fpList.length<1){
	    	alert("请选择至少一张发票！");
	    	return;
	    }
		alert(fpList);
		var param = {"arr":fpList};
		alert(param);
		$.ajax({
 	        type:"POST",
 	        url:"<%=basePath%>/fpkj/dzkp",
			data:param,
		dataType:'text', 
		success : function(data) {
			alert(data.msg);
			var xml = data.xml;
			for(var i=0;i<xml.length;i++){
				alert(xml[i]);
				try{
					ret = sk.Operate(xml[i]);
					alert(ret);
			    }
				catch(e){
					alert(e.message + ",errno:" + e.number);
			    }	
			}
		}
	});
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
</html>