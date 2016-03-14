<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专用发票作废</title>
<script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=basePath %>/css/pagination.css"  type="text/css">
<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
th,td{width: 100px; height: 35px;text-align:center;}
</style>

<script type="text/javascript">
function concelFp(lsh){
	$.ajax({
		type:"GET",
        url: "<%=basePath %>/einvoice/zyfpzf",
        data: {'lsh':lsh},
        async: false,
        success: function (data) {
        	var kpxx = data.kpxx;
        	var skconfig = data.skconfig;
        	var setResult = SetParameter(skconfig.aqm,skconfig.keypwd,skconfig.url,skconfig.port);
        	if(setResult){
	        	var sInvoiceVoidInfo = 
	        		"<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"10009\" comment=\"发票作废\">\r\n<body yylxdm=\"1\">\r\n<kpzdbs>0601</kpzdbs>\r\n<fplxdm>"+kpxx.fplx+"</fplxdm>\r\n<zflx>1</zflx>\r\n<fpdm>"+kpxx.fpdm+"</fpdm>\r\n<fphm>"+kpxx.fphm+"</fphm>\r\n<hjje>"+kpxx.hjje+"</hjje>\r\n<zfr>"+kpxx.kpr+"</zfr>\r\n</body>\r\n</business>";		
	        	try {
	        		invoiceVoidRet = sk.Operate(sInvoiceVoidInfo);
	        		/* var pos=ret.indexOf("<returncode>"); */
	        		var invoiceVoidRetReturncode = getTotalMidValue(invoiceVoidRet, "<returncode>","</returncode>");
	        		var invoiceVoidRetReturnmsg = getTotalMidValue(invoiceVoidRet, "<returnmsg>","</returnmsg>");	
	        		if(invoiceVoidRetReturncode==0&&invoiceVoidRetReturnmsg=="成功"){
	        			alert(invoiceVoidRet);	
	        		}else{
	        			alert("发票领购信息核对失败，失败原因："+invoiceVoidRetReturnmsg);
	        		}
	        	} catch (e) {
	        		alert(e.message + ",errno:" + e.number);
	        	}
        	}
        	
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	if(XMLHttpRequest.responseText=="timeOut"){
        		location.reload();
        	}else{
        		alert("Error");
        	}
        }
	});
}

function SetParameter(aqm,keypwd,ip,port) {
	
	//01设置初始化参数
	/* var sInputInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20001\" comment=\"参数设置\">\r\n<body yylxdm=\"1\">\r\n<servletip>192.168.6.14</servletip>\r\n<servletport>1008</servletport>\r\n<keypwd>123456</keypwd>\r\n<aqm>8a3e00af8a8197e4b81dc694d607ca22c587d4edf9caf387b17a49ed1ff9077607e2c6b3860422db744cf1ff1c4844957dc10cb9a5951d45d773ac564cc9f51bc1f767dd26b9ef5f8723d921ed1db14bb5c3ff90c9a801485718bd3a1032dd54c60d1137d4e3bf144ed69d990307f623f6894a7c51a60fbbe1ea8e60d2216a5b03dcef0de6ef11bdb905e9e315eb0b8edfb0d0e37b72f8619ae9171f8091d2cd802bf504d1fcf6bf1a652b559bfc505368b7160de2854508d821fa3450e5dc1e846511e163c057fd003645388eddd7be077bcb39a8bc744816b52581862a641bb0e699cde6a803c494695f0d20b7b9593978ae9b649dd0b10b87d7bbb2a04891</aqm>\r\n</body>\r\n</business>"; */
	var sInputInfo = "<?xml version=\"1.0\" encoding=\"gbk\"?>\r\n<business id=\"20001\" comment=\"参数设置\">\r\n<body yylxdm=\"1\">\r\n<servletip>"+ip+"</servletip>\r\n<servletport>"+port+"</servletport>\r\n<keypwd>"+keypwd+"</keypwd>\r\n<aqm>"+aqm+"</aqm>\r\n</body>\r\n</business>";
	try {
		ret = sk.Operate(sInputInfo);
		/* var pos=ret.indexOf("<returncode>"); */
		var returncode = getTotalMidValue(ret, "<returncode>","</returncode>");
		var returnmsg = getTotalMidValue(ret, "<returnmsg>","</returnmsg>");	
		if(returncode==0&&returnmsg=="成功"){
			return true	;
		}else{
			alert("参数设置失败，失败原因："+returnmsg);
			return false ;
		}
	} catch (e) {
		alert(e.message + ",errno:" + e.number);
		return false;
	}
}

//获取指定的字符串
function getTotalMidValue(source, priStr, suxStr) {
	if (source == null)
		return null;
	var iFirst = source.indexOf(priStr);		
	var iLast = source.lastIndexOf(suxStr);		
	if (iFirst < 0 || iLast < 0)
		return null;	
	var beginIndex = iFirst + priStr.length;	
	return source.substring(beginIndex, iLast);
}	
</script>


</head>
<body>
<hr>
<div style="white-space: nowrap;">
<form id="searchForm" action="<%=basePath%>/einvoice/zyfpzf_q" method="post" class="form-horizontal" role="form">
	<input id="currentPage" name="currentPage" type="hidden"/>
	<div class="form-inline form-group">
		<div class="form-group col-sm-4">
		<label for="beginDate" class="col-sm-3 control-label">开票起止日期：</label>
			 <div class="col-sm-3">
				<input id="beginDate" name="beginDate" class="form-control" placeholder="开始时间"
				onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" 
				value="${param.beginDate}" style="width: 100px;"/>
				-<input id="endDate" name="endDate" class="form-control" placeholder="结束时间" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})" 
				value="${param.endDate}" style="width: 100px;"/>
			 </div>
		</div>
		<div class="form-group col-sm-4">
			<label for="hyid4q" class="col-sm-3 control-label">会员ID：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="hyid4q" name="hyid4q" value="${param.hyid4q}" placeholder="会员ID">
			</div>
		</div>
		<div class="form-group col-sm-4">
			<label for="fphm4q" class="col-sm-3 control-label">发票号码：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="fphm4q" name="fphm4q" value="${param.fphm4q}" placeholder="发票号码">
			</div>
		</div>
	</div>
	<div class="form-inline form-group">
		<div class="form-group col-sm-4">
		<label for="ddh4q" class="col-sm-3 control-label">订单号：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="ddh4q" id="ddh4q" value="${param.ddh4q}" placeholder="订单号">
			</div>
		</div>
		<div class="form-group col-sm-4">
		<label for="sjh4q" class="col-sm-3 control-label">手机号：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="sjh4q" id="sjh4q" value="${param.ddh4q}" placeholder="订单号">
			</div>
		</div>
		<div class="form-group col-sm-4">
			<div class=" col-sm-4">
				<input type="submit" style="width:80px" value="查询">
			</div>
		</div>
	</div>
</form><br/>
<table border="1" cellspacing="0">
	<tr>
		<td>订单号</td>
		<td>会员名</td>
		<td>会员ID</td>
		<td>收货人</td>
		<td>收货人电话</td>
		<td>申请时间</td>
		<td>发票抬头</td>
		<td>发票内容</td>
		<td>合计金额</td>
		<td>合计税额</td>
		<td>价税合计</td>
		<td>发票状态</td>
		<td>开票日期</td>
		<td>发票代码</td>
		<td>发票号码</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${fpxxList}" var="fp">
		<tr>
			<td><c:out value="${fp.zddh}"/></td>
			<td><c:out value="${fp.hym}"/></td>
			<td><c:out value="${fp.hyid}"/></td>
			<td><c:out value="${fp.shr}"/></td>
			<td><c:out value="${fp.shrdh}"/></td>
			<td><c:out value="${fp.sqsj}"/></td>
			<td><c:out value="${fp.gmfmc}"/></td>
			<td><c:out value="${fp.spzl}"/></td>
			<td><c:out value="${fp.hjje}"/></td>
			<td><c:out value="${fp.hjse}"/></td>
			<td><c:out value="${fp.jshj}"/></td>
			<td><c:out value="${fp.kplx}"/></td>
			<td><c:out value="${fp.kprq}"/></td>
			<td><c:out value="${fp.fpdm}"/></td>
			<td><c:out value="${fp.fphm}"/></td>
			<td><a href="javascript:void(0)" onclick="concelFp('${fp.fpqqlsh}');">作废</a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="pagination" id="Pagination" style="width: 100%;text-align: center;margin-top: 20px"></div>
</body>
<!-- 分页 -->
<script type="text/javascript">
	 var pageIndex = '${page.pageIndex}';
     var pageSize = '${page.pageSize}';
     var totalPages = '${page.totalPages}';
     var totalCounts = '${page.totalCounts}';
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
     });
     
     function gotoPage(page) {
    	 $("#currentPage").val(page);
         $("#searchForm").submit(); 
     }
//      function gotoPage(page) {
<%--          window.location = "<%=basePath%>/report/queryFPlist?pageIndex=" + page;             --%>
//      }

</script> 
</html>