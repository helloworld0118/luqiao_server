<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>路桥系统</title>
<base href="<%=basePath%>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
<script type="text/javascript">
if(top.location != location){ 
    top.location.href= location.href; 
}
</script>
</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content" style="margin-top: 80px;">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="white">路桥系统管理</span>
							</h1>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 登录
										</h4>

										<div class="space-6"></div>

										<form id="login_form" action="login" method="post" onsubmit="return check();">
											<fieldset>
												<c:if test="${msg!=''}">
													<p style="color: red; text-align: center;">
														<c:out value="${msg}" />
													<p>
												</c:if>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" name="code"
														placeholder="企业编码" /> <i class="icon-book"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" name="mobile"
														placeholder="mobile" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" name="password"
														placeholder="Password" /> <i class="icon-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">

													<button type="submit"
														class="width-100 pull-center btn btn-lm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

									</div>
									<!-- /widget-main -->
									<!-- 
									<div class="toolbar clearfix">
										<div style="float: right; width: auto; margin-right: 15px;">
											<a href="#" onclick="show_box('forgot-box'); return false;"
												class="forgot-password-link"> 忘记密码 <i
												class="icon-arrow-right"></i>
											</a>
										</div>
									</div>
									 -->
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i> 重置密码
										</h4>

										<div class="space-6"></div>
										<p>填写手机号码获取新密码</p>

										<form>
											<fieldset>
											<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" name="code"
														placeholder="企业编码" /> <i class="icon-book"></i>
														</span></label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="手机号码" /> <i
														class="icon-comment"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i> 发送
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> <i class="icon-arrow-left"></i>
											返回登录

										</a>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /forgot-box -->


						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->
	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrapValidator.js"></script>
	<!-- inline scripts related to this page -->

	<script type="text/javascript">
	function check(){
		var code = $("input[name='code']").val();
		var mobile = $("input[name='mobile']").val();
		var password = $("input[name='password']").val();
		$("form").find('span[class="tip"]').remove();
		if(code==""){
			$("input[name='code']").after("<span class='tip' style='color:red;font-size:12px;'>此字段不能为空</span>");
			return false;
		}
		if(mobile==""){
			$("input[name='mobile']").after("<span class='tip'style='color:red;font-size:12px;'>此字段不能为空</span>");
			return false;
		}else if(mobile.length!=11){
			$("input[name='mobile']").after("<span class='tip'style='color:red;font-size:12px;'>手机号必须为11位</span>");
			return false;
		}
		if(password==""){
			$("input[name='password']").after("<span class='tip' style='color:red;font-size:12px;'>此字段不能为空</span>");
			return false;
		}
		return true;
	}
	$(function(){
		$("input[name='code']").focus(function(){
			$("form").find('span[class="tip"]').remove();
		});
	})
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}
	</script>
</body>
</html>
