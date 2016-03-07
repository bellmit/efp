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
<link rel="stylesheet" href="<%=basePath %>/css/pagination.css"  type="text/css">
<script type="text/javascript" src="<%=basePath%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=basePath%>/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
th,td{width: 100px; height: 35px;text-align:center;}
</style>
</head>
<body>
	<hr>
	<form action="<%=basePath%>/fpkj/special" method="post" class="form-horizontal" role="form">
		<div class="form-inline form-group">
		<div class="form-group col-sm-6">
	      <label for="beginDate" class="col-sm-3 control-label">开始日期：</label>
	      <div class="col-sm-3">
	         <input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="开始时间" value="${param.beginDate }"
	         	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
	      </div>
	      </div>
	      <div class="form-group col-sm-6">
	       <label for="beginDate" class="col-sm-3 control-label">结束日期：</label>
	      <div class="col-sm-3">
	         <input type="text" class="form-control" id="endDate" name="endDate" placeholder="结束时间" value="${param.endDate }"
	         	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})">
	      </div>
	   </div>
	 </div>
	 <div class="form-inline form-group">
		 <div class="form-group col-sm-6">
	      <label for="zddh" class="col-sm-3 control-label">订单号：</label>
	      <div class="col-sm-3">
	         <input type="text" class="form-control" id="zddh" name="zddh" placeholder="订单号" value="${param.zddh }">
	      </div>
	   </div>
	   <div class="form-group col-sm-6">
	  	 <label for="kpdq" class="col-sm-3 control-label">开票地区：</label>
	      <div class="col-sm-3">
			<select id="kpdq" name="kpdq">
			<option value="">-请选择-</option>
			<option value="0"
				<c:if test="${param.kpdq == 0 }">selected="selected"</c:if>>北京</option>
			<option value="1"
				<c:if test="${param.kpdq == 1 }">selected="selected"</c:if>>上海</option>
			</select>
	      </div>
	      <input id="currentPage" name="currentPage" type="hidden"/>
	   </div>
	</div>
	<div class="form-inline form-group">
		<div class="form-group col-sm-6">
	      <div class="col-sm-offset-1 col-sm-5">
	         	<input type="submit" value="查询" /> 
				<input id="kp" type="button" value="多张开票" onclick="multi_kjfp()" />
	      </div>
	   </div>
	</div>
	</form>
	<hr>
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
	<div class="text-center"><div class="text-center pagination" id="Pagination"></div></div>
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
<!-- 分页 -->
<script type="text/javascript">
	 var pageIndex = ${page.pageIndex};
     var pageSize = ${page.pageSize};
     var totalPages = ${page.totalPages};
     var totalCounts = ${page.totalCounts};
     $(document).ready(function(){ 
     $("#Pagination").pagination(totalCounts,{
                 items_per_page: pageSize,
                 current_page:pageIndex,
                 prev_text:'<',    
                 next_text:'>',            
                 callback:function(page){
                     gotoPage(page);
                 }
         });            
         /* showPageInfo(); */
     });
     
     function gotoPage(page) {
         window.location = "<%=basePath%>/fpkj/plain?pageIndex=" + page;            
     }
     
     /* function showPageInfo(){
         $("#page-info").html(pageSize + "条/页，共" + totalCounts + "条，第" + "${page.pageIndex+1}" + "页，共" + totalPages + "页");
     }	 */

</script> 
</html>