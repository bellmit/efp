<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath()+"/";%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>发票管理系统</title>
<%@ include  file="/html/head_css.html"%>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span><img alt="image" class="img-circle" width="48" height="48" src="<%=basePath%>/images/common/user.png" /></span>
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear">
									<span class="block m-t-xs">
									    <strong class="font-bold">${sessionInfo.userName}</strong>
									</span>
									<span class="text-muted text-xs block">
									    <c:if test="${sessionInfo.userType==9}">超级管理员</c:if>
									    <b class="caret"></b>
									</span>
							    </span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="<%=basePath %>users/updateUser?id=${sessionInfo.id }">修改信息</a></li>
								<li class="divider"></li>
								<li><a href="<%=basePath%>users/exit">注销</a></li>
							</ul>
						</div>
						<div class="logo-element">${sessionInfo.userName}</div>
					</li>
					<li id="index"><a href="loginSuccess"><i class="fa fa-th-large"></i><span class="nav-label">主菜单</span><span class="label label-info pull-right"></span></a></li>
					<li id="juser"><a href="#"><i class="fa fa-rebel"></i><span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
					   <ul class="nav nav-second-level">
						   <li class="user_list user_edit"><a href="<%=basePath%>users/findAll">查看所有管理员</a></li>
	                       <li class="user_list user_add"><a href="<%=basePath%>users/add">创建管理员</a></li>
                       </ul>
                    </li>
					<li id="jproduct"><a><i class="fa fa-cube"></i><span class="nav-label">产品管理</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li class="host_add host_add_multi"><a href="product/list">产品列表</a></li>
							<li class="host_list host_detail host_edit"><a href="product/add/">添加产品</a></li>
						</ul>
					</li>
					<li id="jloan"><a href="#"><i class="fa fa-edit"></i><span class="nav-label">贷款管理</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li class="loan_perm_listloan_perm_edit"><a href="<%=basePath %>users/loan/list/">贷款列表</a></li>
						</ul>
					</li>
					<li id="jcuser">
                    <a href="#"><i class="fa fa-edit"></i><span class="nav-label">客户管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="cuser_perm_list cuser_perm_edit">
                          <a href="<%=basePath%>users/cuser/list">客户列表</a>
                        </li>
                    </ul>
                </li>
					<li class="special_link">
						<a href="http://www.yinshuitong.com" target="_blank"><i class="fa fa-database"></i><span class="nav-label">访问银税通官网</span></a>
					</li>
				</ul>
			</div>
		</nav>
		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i></a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">欢迎使用银税通后台管理系统</span></li>
						<li><a href="<%=basePath %>users/exit"> <i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</nav>
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2></h2>
					<ol class="breadcrumb"><li><a href="#">主页</a></li></ol>
				</div>
			</div>
			<div class="wrapper wrapper-content">
				<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div id="carousel-example" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
						 <li data-target="#carousel-example" data-slide-to="0" class="active"></li>
						 <li data-target="#carousel-example" data-slide-to="1"></li>
						 <li data-target="#carousel-example" data-slide-to="2"></li>
						 </ol><!-- Wrapper for slides -->
						 <div class="carousel-inner">
						 <div class="item">
						 <img src="http://ww1.sinaimg.cn/large/44287191gw1excbq6tb3rj21400migrz.jpg" alt="..." />
						 <div class="carousel-caption">...</div>
						 </div>
						 <div class="item active">
						 <img src="http://ww3.sinaimg.cn/large/44287191gw1excbq5iwm6j21400min3o.jpg" alt="..." />
						 <div class="carousel-caption">...</div>
						 </div>
						 <div class="item">
						 <img src="http://ww2.sinaimg.cn/large/44287191gw1excbq4kx57j21400migs4.jpg" alt="..." />
						 <div class="carousel-caption">...</div>
						 </div>
						 </div>
						 <!-- Controls -->
						 <a class="left carousel-control" href="#carousel-example" data-slide="prev">
						 <span class="glyphicon glyphicon-chevron-left"></span></a>
						 <a class="right carousel-control" href="#carousel-example" data-slide="next">
						 <span class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
					</div>
	               </div>
<%-- 				<%@ include  file="/html/footer.html"%> --%>
			</div>
		</div>
	</div>
</body>
<%@ include  file="/html/head_script.html"%>
<!-- <script>
	$(function() {
		$('.carousel').carousel();
	});
</script> -->
</html>
