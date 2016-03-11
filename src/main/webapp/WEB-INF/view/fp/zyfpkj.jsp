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
<OBJECT ID=sk CLASSID="clsid:003BD8F2-A6C3-48EF-9B72-ECFD8FC4D49F"
	codebase="NISEC_SKSCX.ocx#version=1,0,0,1" style="display: none;"> </OBJECT>
<style type="text/css">
th,td{width: 100px; height: 35px;text-align:center;}
</style>
</head>
<body>
	<hr>
	<form id="searchForm" action="<%=basePath%>/fpkj/special" method="post" class="form-horizontal" role="form">
		<div class="form-inline form-group">
		<div class="form-group col-sm-6">
	      <label for="beginDate" class="col-sm-3 control-label">申请开票日期起：</label>
	      <div class="col-sm-3">
	         <input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="开始时间" value="${param.beginDate }"
	         	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
	      </div>
	      </div>
	      <div class="form-group col-sm-6">
	       <label for="beginDate" class="col-sm-3 control-label">申请开票日期起：</label>
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
			<option value="-1" <c:if test="${param.kpdq == -1 }">selected="selected"</c:if>>-请选择-</option>
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
	         	<input type="submit" value="查询" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="kp" type="button" value="多张开票" onclick="multi_kjfp()" />
				<input id="setParameter" type="button" value="设置环境变量" onclick="GetParameter()">
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
				<td><input type="checkbox" id="fpqqlsh" name="fpqqlsh" onclick="ccAll()"
					class="chkbox_ex" value="${fp.fpqqlsh}"></td>
				<td>${f.count }</td>
				<td><c:out value="${fp.zddh}" /></td>
				<td><c:out value="${fp.fddh}" /></td>
				<td><c:out value="${fp.hym}" /></td>
				<td><c:out value="${fp.shr}" /></td>
				<td><fmt:formatDate value="${fp.sqsj}" pattern="yyyy-MM-dd" /></td>
				<td><c:if test="${fp.kpdq == 0 }">北京</c:if> <c:if
						test="${fp.kpdq == 1 }">上海</c:if></td>
				<td><c:if test="${fp.fplx == '007' }">增值税普通发票</c:if> <c:if
						test="${fp.fplx == '004' }">增值税专用发票</c:if> <c:if
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
function GetParameter(){
	var params = {'fplx' : '004'};
	
	$.ajax({
		type:"GET",
        url: "<%=basePath %>/print/getParameter",
        data: params,
        async: false,
        success: function (data) {
        	//alert(JSON.stringify(data))
        	if(data.code == '-1'){
        		window.top.location.href="../login/login.html";
        	}else{
        		skconf = data.skconf;
        		printconf = data.printconf;
        		SetParameter(skconf.aqm,skconf.keypwd,skconf.url,skconf.port);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		window.top.location.reload();
        	}else{
        		alert("Error");
        	}
        }
	});
}
function SetParameter(aqm,keypwd,ip,port) {
	//01设置初始化参数
	//var sInputInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20001\" comment=\"参数设置\">\r\n<body yylxdm=\"1\">\r\n<servletip>192.168.6.14</servletip>\r\n<servletport>1008</servletport>\r\n<keypwd>123456</keypwd>\r\n<aqm>8a3e00af8a8197e4b81dc694d607ca22c587d4edf9caf387b17a49ed1ff9077607e2c6b3860422db744cf1ff1c4844957dc10cb9a5951d45d773ac564cc9f51bc1f767dd26b9ef5f8723d921ed1db14bb5c3ff90c9a801485718bd3a1032dd54c60d1137d4e3bf144ed69d990307f623f6894a7c51a60fbbe1ea8e60d2216a5b03dcef0de6ef11bdb905e9e315eb0b8edfb0d0e37b72f8619ae9171f8091d2cd802bf504d1fcf6bf1a652b559bfc505368b7160de2854508d821fa3450e5dc1e846511e163c057fd003645388eddd7be077bcb39a8bc744816b52581862a641bb0e699cde6a803c494695f0d20b7b9593978ae9b649dd0b10b87d7bbb2a04891</aqm>\r\n</body>\r\n</business>";
	var sInputInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20001\" comment=\"参数设置\">\r\n<body yylxdm=\"1\">\r\n<servletip>"+ip+"</servletip>\r\n<servletport>"+port+"</servletport>\r\n<keypwd>"+keypwd+"</keypwd>\r\n<aqm>"+aqm+"</aqm>\r\n</body>\r\n</business>";
	try {
		ret = sk.Operate(sInputInfo);
		/* var pos=ret.indexOf("<returncode>"); */
		var returncode = getTotalMidValue(ret, "<returncode>","</returncode>");
		var returnmsg = getTotalMidValue(ret, "<returnmsg>","</returnmsg>");	
		if(returncode==0&&returnmsg=="成功"){
			alert("参数设置成功！");	
		}else{
			alert("参数设置失败，失败原因："+returnmsg);
		}
	} catch (e) {
		alert(e.message + ",errno:" + e.number);
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

	function kjfp(fpqqlsh){
		if(!confirm("确定要开具发票吗？")){
			return;
		}
		$.ajax({
 	        type:"POST",
 	        url:"<%=basePath%>/fpkj/kp",
			data:{"ph":fpqqlsh},
			dataType:'json', 
			success : function(data) {
			alert(data.xml);
			try {
				invoiceIssueRet = sk.Operate(data.xml);
				var invoiceIssueReturncode = getTotalMidValue(invoiceIssueRet, "<returncode>","</returncode>");
				var invoiceIssueRetReturnmsg = getTotalMidValue(invoiceIssueRet, "<returnmsg>","</returnmsg>");	
				if(invoiceIssueReturncode==0&&invoiceIssueRetReturnmsg=="成功"){
					alert(invoiceIssueRet);	
					$.ajax({
				 	    type:"POST",
				 	    url:"<%=basePath%>/fpkj/callback",
						data:{"fpqqlsh":fpqqlsh,"xml":invoiceIssueRet},
						dataType:'json', 
						success : function(data) {
							alert(data);
							window.location.href = "<%=basePath%>/fpkj/special";
						}
					});
				}else{
					alert("发票领购信息核对失败，失败原因："+invoiceIssueRetReturnmsg);
				}
			} catch (e) {
				alert(e.message + ",errno:" + e.number);
			} 
		}
	});
	}
	
	function multi_kjfp(){
		if(!confirm("确定要开具发票吗？")){
			return;
		}
		var fpList = ""; 
		var chks = $('.chkbox_ex');
	    for(i=0;i<chks.length;i++){
			if(chks[i].checked == true){
				fpList = chks[i].value +",";
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
		success : function(data) {
			alert(data.xml);
			var xml = data.xml;
			for(var i=0;i<xml.length;i++){
				try {
					invoiceIssueRet = sk.Operate(xml[i]);
					var invoiceIssueReturncode = getTotalMidValue(invoiceIssueRet, "<returncode>","</returncode>");
					var invoiceIssueRetReturnmsg = getTotalMidValue(invoiceIssueRet, "<returnmsg>","</returnmsg>");	
					var fpqqlsh = getTotalMidValue(xml[i], "<fpqqlsh>","</fpqqlsh>");
					if(invoiceIssueReturncode==0&&invoiceIssueRetReturnmsg=="成功"){
						alert(fpqqlsh);	
						$.ajax({
					 	    type:"POST",
					 	    url:"<%=basePath%>/fpkj/callback",
							data:{"fpqqlsh":fpqqlsh,"xml":invoiceIssueRet},
							dataType:'json', 
							success : function(data) {
								alert(data);
								window.location.href = "<%=basePath%>/fpkj/plain";
							}
						});
					}else{
						alert("发票领购信息核对失败，失败原因："+invoiceIssueRetReturnmsg);
					}
				} catch (e) {
					alert(e.message + ",errno:" + e.number);
				} 	
			}
		}
	});
	}
	function checkAll(){
		var chks = $('.chkbox_ex');
		for(i=0;i<chks.length;i++){
			chks[i].checked=$('#chkAll').prop('checked');
		}
	}
	//取消全选的勾
	function ccAll(){
		$('#chkAll').prop('checked',false);
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
    	 $("#currentPage").val(page);
         $("#searchForm").submit(); 
     }
     
     /* function showPageInfo(){
         $("#page-info").html(pageSize + "条/页，共" + totalCounts + "条，第" + "${page.pageIndex+1}" + "页，共" + totalPages + "页");
     }	 */

</script> 
</html>