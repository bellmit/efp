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
<link rel="stylesheet" href="<%=basePath %>/css/pagination.css"  type="text/css">
<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
th,td{width: 100px; height: 35px;text-align:center;}
</style>

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
<hr>
<div style="white-space: nowrap;">
<form id="searchForm" action="<%=basePath %>/report/queryFPlist" method="post"class="form-horizontal" role="form">
	<input id="currentPage" name="currentPage" type="hidden"/>
	<div class="form-inline form-group">
		<div class="form-group col-sm-6">
		<label for="beginDate" class="col-sm-3 control-label">申请起止日期：</label>
			 <div class="col-sm-3">
				<input id="beginDate" name="beginDate" class="form-control" placeholder="开始时间"
				onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" 
				value="${dateS4save}" style="width: 100px;"/>
				-<input id="endDate" name="endDate" class="form-control" placeholder="结束时间" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})" 
				value="${dateE4save}" style="width: 100px;"/>
			 </div>
		</div>
		<div class="form-group col-sm-6">
			<label for="beginDate" class="col-sm-3 control-label">开票地区：</label>
			 <div class="col-sm-3">
				<select name="kpdq4q" >
					<option value="">请选择</option>
					<option value="0" <c:if test="${kpdq4save=='0'}">selected="selected"</c:if>>北京</option>
					<option value="1" <c:if test="${kpdq4save=='1'}">selected="selected"</c:if>>上海</option>
				</select>
			 </div>
		</div>
	</div>
	<div class="form-inline form-group">
		<div class="form-group col-sm-6">
		<label for="beginDate" class="col-sm-3 control-label">订单号：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="ddh4q" value="${ddh4save}" placeholder="订单号">
			</div>
		</div>
		<div class="form-group col-sm-6">
		<label for="beginDate" class="col-sm-3 control-label">发票种类：</label>
			<div class="col-sm-3">
				<select name="fpzl4q">
					<option value="">请选择</option>
					<option value="007" <c:if test="${fpzl4save=='007'}">selected="selected"</c:if>>增值税普通发票</option>
					<option value="004" <c:if test="${fpzl4save=='004'}">selected="selected"</c:if>>增值税专用发票</option>
					<option value="026" <c:if test="${fpzl4save=='026'}">selected="selected"</c:if>>增值税电子发票</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-inline form-group">
		<div class="form-group col-sm-6">
		<label for="beginDate" class="col-sm-3 control-label">发票类型：</label>
			<div class="col-sm-3">
				<select name="fplx4q"  >
					<option value="">请选择</option>
					<option <c:if test="${fplx4save=='服务费'}">selected="selected"</c:if>>服务费</option>
					<option <c:if test="${fplx4save=='咨询费'}">selected="selected"</c:if>>咨询费</option>
				</select>
			</div>
		</div>
		<div class="form-group col-sm-6">
		<label for="beginDate" class="col-sm-3 control-label">发票抬头：</label>
			<div class="col-sm-3">
				<select name="fptt4q">
					<option value="">请选择</option>
					<option value ="0" <c:if test="${fptt4save=='0'}">selected="selected"</c:if>>个人</option>
					<option value ="1" <c:if test="${fptt4save=='1'}">selected="selected"</c:if>>公司</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-inline form-group">
		<div class="form-group col-sm-6">
			<div class="col-sm-offset-1 col-sm-5">
				<input type="submit" style="width:80px" value="查询"> | <a href="javascript:void(0)" onclick="exportData()">导出excel</a>
			</div>
		</div>
	</div>
</form><br/>
<form id="expForm" action="<%=basePath%>/report/download" method="post">
<table border="1" cellspacing="0">
	<tr>
		<td><input type="checkbox" id="chkAll" onclick="checkAll()"></td>
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
			<td><c:out value="${fp.ddh}"/></td>
			<td><c:out value="${fp.sqr}"/></td>
			<td><c:out value="${fp.hym}"/></td>
			<td><c:out value="${fp.hyid}"/></td>
			<td><fmt:formatDate value="${fp.ddsj}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${fp.sqsj}" pattern="yyyy-MM-dd"/></td>
			<td><c:out value="${fp.fptt}"/></td>
			<td><c:out value="${fp.fplx}"/></td>
			<td>
			<c:if test="${fp.fpzl=='007'}">增值税普通发票</c:if>
			<c:if test="${fp.fpzl=='004'}">增值税专用发票</c:if>
			<c:if test="${fp.fpzl=='026'}">增值税电子发票</c:if>
			</td>
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
</div>
<div class="pagination" id="Pagination" style="width: 100%;text-align: center;margin-top: 20px"></div>
</body>
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