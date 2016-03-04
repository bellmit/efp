<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath(); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath %>/qzpt/page/relogin.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=basePath %>/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath %>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery.md5.js"></script>
</head>
<body>
<form role="form">
	<div class="container"  style="padding: 20px 10px 20px 10px;">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>修改密码</h5>
							</div>
							<div class="ibox-content">
								<form id="userForm" method="post" class="form-horizontal" action="">
									<div class="form-group">
										<div class="col-sm-8">
											<input id="oldpass" name="oldpass" placeholder="当前密码" type="password" class="form-control" >
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-8">
											<input id="newpass" name="newpass" placeholder="新密码" type="password" class="form-control" >
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-8">
											<input id="newpassagain" name="newpassagain" placeholder="重复新密码" type="password" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-1">
											<button class="btn btn-white" type="reset" onclick="javascript:history.go(-1);">取消</button>
											<button id="submit_button" class="btn btn-primary" type="button" onclick="changePassword();">确认保存</button>
										</div>
										
									</div>
									<div class="form-group">
										<div class="col-sm-8">
										<div id="msg_text" class="alert alert-danger text-center" style="display: none"></div>
										</div>
									</div>
								</form>
							</div>
						</div>
						</div>
					</div>
				</div>
	</div>
</form>
</body>
<script type="text/javascript">
	function changePassword(){
		var oldpass=$("#oldpass").val();
		var newpass=$("#newpass").val();
		var newpassagain=$("#newpassagain").val();
		
		if(oldpass==null||oldpass==""){
			$("#msg_text").text("请输入原密码！");
			$("#msg_text").show();
			return;
		}
		if(newpass==null||newpass==""){
			$("#msg_text").text("请输入新密码！");
			$("#msg_text").show();
			return;
		}
		if(newpassagain==""||newpassagain==null){
			$("#msg_text").text("请再输入一遍新密码！");
			$("#msg_text").show();
			return;
		}
		if(oldpass==newpassagain){
			$("#msg_text").text("新密码不能与原密码相同！");
			$("#msg_text").show();
			return;
		}
		if(newpass!=newpassagain){
			$("#msg_text").text("输入两次新密码不一致！");
			$("#msg_text").show();
			return;
		}
		$.post(
				"<%=basePath %>/user/changePwd",
			{oldpass:$.md5(oldpass),newpass:$.md5(newpass),newpassagain:$.md5(newpassagain)},
			function(data) {
				if(data.status=="error") {
					//账号错误
					$("#msg_text").text(data.msg);
					$("#msg_text").show();
				}else {
					//成功
					location.href = "<%=basePath %>/qzpt/page/index.html";
				}
			}
		);
	}
</script>
</html>