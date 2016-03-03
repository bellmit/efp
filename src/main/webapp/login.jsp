<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath()+"/"; %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 登录 </title>
    <!-- <link rel="shortcut icon" href="/static/img/facio.ico" type="image/x-icon"> -->
    <link href="<%=basePath %>bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>bootstrap/css/font-awesome.css" rel="stylesheet">
    <link href="<%=basePath %>bootstrap/css/animate.css" rel="stylesheet">
    <link href="<%=basePath %>bootstrap/css/style.css" rel="stylesheet"> 
    <link href="<%=basePath %>validator/jquery.validator.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name"><img src="<%=basePath %>images/common/logo.png"></h1>
            </div>
            
            <form id="loginForm" class="m-t" role="form" method="post" action="">
                <div class="form-group">
                    <input type="text" id="username" name="userName" class="form-control" placeholder="Username" >
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                    <input type="hidden" id="d" name="userPass">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

               <div id="msg_text" class="alert alert-danger text-center" style="display: none"></div>
            </form>
            <p class="m-t"> <small><b>Copyright</b> 百望股份有限公司 © 2014-2015</small></p>
        </div>
    </div>

</body>
 <!-- Mainly scripts -->
	<script src="<%=basePath %>scripts/common/jquery-1.11.1.js"></script>
	<script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>validator/jquery.validator.js"></script>
	<script type="text/javascript" src="<%=basePath %>validator/local/zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>scripts/common/md5.js"></script>
<script>
  //表单验证
  $(function(){
    $('#loginForm').validator(
    	{
    	
    		    timely: 2,
    		    theme: "yellow_right_effect",
    		    fields: {
    		        "userName": {
    		            rule: "required",
    		            tip: "输入用户名",
    		            ok: "",
    		            msg: {required: "必须填写!"}
    		        },
    		        "password": {
    		            rule: "required",
    		            tip: "输入密码",
    		            ok: "",
    		            msg: {required: "必须填写!"}
    		        }
    		    },
    		    valid: function(form) {
    		    	submitchk();
    		    }
    		
    	}		
    );
  })
</script>
<script type="text/javascript">
function submitchk(){
	var user_pass = $("#password").val();
	if(user_pass !="" || user_pass !=null) {
		
		var hash = hex_md5(user_pass);
		$("#d").val(hash);
	}
	$.post(
			"<%=basePath %>user/loginJudge",
		$("#loginForm").serialize(),
		function(data) {
			if(data.flag==1||data.flag==2) {
				//账号错误
				$("#msg_text").text("用户名或密码错误");
				$("#msg_text").show();
			}else if(data.flag==11){
				$("#msg_text").text("用户已锁定，不能登录");
				$("#msg_text").show();
			}else {
				//成功
				location.href = "<%=basePath %>users/loginSuccess";
			}
		}
	);
}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var userName = GetCookie("user_name");
		var userPass = GetCookie("user_pass");
		if(null != userName && userName!="" && null !=userPass && userPass!=""){
			$.post(
					"<%=basePath %>user/loginByCookie",{userName:userName,userPass:userPass},
				function(data) {
					if(data.flag==1||data.flag==2) {
						//账号错误
						$("#user_pass_msg").text("用户名或密码错误");
					}else if(data.flag==10){
						//验证码错误
						$("#user_pass_msg").text("");
					}else {
						//成功
						location.href = "<%=basePath %>users/loginSuccess";
					}
				}
			);
		}
	});
	function GetCookie(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	}
</script>
</html>