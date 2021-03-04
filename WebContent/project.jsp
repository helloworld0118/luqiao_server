<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <base href="<%=basePath%>" />
		<meta charset="utf-8" />
		<title>项目管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->


		<!-- ace styles -->

		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.bg-blue {
				height:150px;
			    background-color: #4DA9EB !important;
			}
			.small-box .icon {
  -webkit-transition: all 0.3s linear;
  -o-transition: all 0.3s linear;
  transition: all 0.3s linear;
  position: absolute;
  top: -10px;
  right: 10px;
  z-index: 0;
  font-size: 90px;
  color: rgba(0, 0, 0, 0.15);
}
.small-box:hover {
  text-decoration: none;
  color: #f9f9f9;
}
.small-box:hover .icon {
  font-size: 95px;
}	
		</style>
	<script type="text/javascript">
if(top.location != location){ 
    top.location.href= location.href; 
}
</script>
	</head>
	<body>
	 <div class="navbar navbar-default" id="navbar"> 
   <div class="navbar-container" id="navbar-container"> 
    <div class="navbar-header pull-left"> 
     <a href="index" class="navbar-brand"> <small> <i class="icon-leaf"></i> 路桥管理 </small> </a> 
     <!-- /.brand --> 
    </div> 
    <!-- /.navbar-header --> 
    <div class="navbar-header pull-right" role="navigation"> 
     <ul class="nav ace-nav"> 
      <li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo"  alt="" /> 
      <span class="user-info"> <small>欢迎,</small> ${currentUser.name} </span> <i class="icon-caret-down"></i> </a> 
       <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close"> 
         <li><a target="_blank" href="https://shimo.im/docs/5GfrtE2v3cMZBPU6"> <i class="icon-cog"></i> 帮助文档
							</a></li>
        <li class="divider"></li> 
        <li><a href="logout"> <i class="icon-off"></i> 注销 </a></li> 
       </ul></li> 
     </ul> 
     <!-- /.ace-nav --> 
    </div> 
		</div>
		<!-- /.container -->
	</div>
	<div class="main-container" id="main-container" style="background:#fff">
	<section class="content-header" style="margin:20px 50px;border-bottom: 3px solid #d2d6de;height:40px;">
			<div style="float:left;font-size:22px;display:block;">项目列表</div>
			<c:if test="${isSuperAdmin==true }">
				<div style="float:right;font-size:14px;display:block;margin-top:15px;"><a href="project/add">添加项目</a></div>
			</c:if>
		</section>


	<section class="content">
			<c:if test="${models.size()==0}">
				<p style="color:red;text-align:center;font-size:16px;">还未添加项目</p>
			</c:if>
			<c:forEach var="model" items="${models}" >
				<div class="row" id="" style="margin:20px 50px;"> 
			  		<c:forEach var="project" items="${model.projects}">
			  		 <div class="col-lg-3" data-href="project/${project.id}">
						<div class="box box-widget widget-user small-box bg-blue" style="cursor:pointer;"> 				
							<div class="widget-user-header" > 							
							   <p class="widget-user-username" style="margin-bottom:60px;padding-left:20px;padding-top:20px;color: #fff;font-size: 18px;text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);">${project.name}</p> 						
							</div> 						
							<div class="icon" > 		
								<i class="icon-folder-open-alt"></i> 				
							</div> 					
							<div>	
							    <a data-href="project/edit/${project.id}" style="height:44px;color:#fff;bottom:-10px;padding: 12px !important;position: relative;z-index: 10;text-decoration: none;text-align: center;">
								<i class="icon-cog bigger-160"><span  style="font-size:16px;margin-left:5px;">编辑</span></i> 
								</a>
								<c:if test="${isSuperAdmin==true }">
									<a  data-href="project/del/${project.id}" style="float:right;height:44px;color:#fff;bottom:0px;padding: 12px !important;position: relative;z-index: 10;text-decoration: none;text-align: center;">
										<i class="icon-trash bigger-160"><span  style="font-size:16px;margin-left:5px;">删除</span></i>			
									</a>
								</c:if>
							</div>
						</div>
					  </div>
			  		</c:forEach>
				</div> 
			 </c:forEach>
		</section>
		</div>
	</body>
	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	<script src="assets/js/bootstrap-tab.js"></script>
	<script src="assets/js/bootstrap-contextmenu.js"></script>
	<script type="text/javascript">
	 $(function(){
		 $("a").click(function(event){
			 if($(this).attr("data-href")){
				 location.href=$(this).attr("data-href");
				 event.stopPropagation();
			 }
		 });
		 $("div").click(function(){
			 if($(this).attr("data-href")){
				location.href="/luqiao/"+$(this).attr("data-href");
			 }
		 });
	 });
	</script>
</html>