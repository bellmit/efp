<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户信息</title>
<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="row">
					<div class="col-lg-10">
						<div class="ibox float-e-margins">
							<div class="col-lg-offset-1 ibox-title">
								<h5>修改信息</h5>
							</div>
							<div class="ibox-content">
								<form id="userForm" method="post" class="form-horizontal" action="save">
									<div class="form-group">
										<label for="name" class="col-sm-2 control-label">用户名 <span class="red-fonts">*</span></label>
										<div class="col-sm-8">
											<input type="hidden" name="id" value="${user.id}" />
											<input id="username" name="userName" placeholder="User name" type="text" class="form-control" value="${user.userName}" readonly="readonly">
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label for="userType" class="col-lg-2 control-label">类型<span class="red-fonts">*</span></label>
										<div class="col-sm-8">
											<select id="userType" name="userType"
												class="form-control m-b">
												<option value="0" <c:if test="${user.userType==0 }">selected</c:if>>北京地区</option>
												<option value="1" <c:if test="${user.userType==1 }">selected</c:if>>上海地区</option>
											</select>
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label for="xsfnsrsbh" class="col-sm-2 control-label">纳税人识别号<span class="red-fonts">*</span></label>
										<div class="col-sm-8">
											<input id="xsfnsrsbh" name="xsfnsrsbh" placeholder="纳税人识别号" type="text" class="form-control" value="${user.xsfnsrsbh}">
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label">是否启用</label>
										<div class="col-sm-8">
											<div class="radio i-checks">
												<label> <input type="radio" value="0" name="islock"
													<c:if test="${user.islock==false }">checked</c:if>>启用
												</label>
											</div>
											<div class="radio i-checks">
												<label><input type="radio" value="1" name="islock"
													<c:if test="${user.islock==true }">checked</c:if>>禁用
												</label>
											</div>
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button class="btn btn-white" type="reset" onclick="javascript:history.go(-1);">取消</button>
											<button id="submit_button" class="btn btn-primary" type="submit">确认保存</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
</body>
</html>