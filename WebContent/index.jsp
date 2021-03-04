<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<base href="<%=basePath%>" />
<title>路桥系统后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<style type="text/css">
.fa-remove:before, .fa-close:before, .fa-times:before {
	font-size: 15px;
}
</style>
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<!-- page specific plugin styles -->
<!-- fonts -->
<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
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
<body>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a  href="index" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> 路桥管理
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle" title="项目列表" href="#"> <i
							class="icon-tasks"></i> 项目列表
					</a>
						<ul
							class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">当前项目 ${current_project_name}</li>
							<c:if test="${model.size()==0}">
								<li style="color: red; text-align: center;">还未添加项目</li>
							</c:if>
							<c:forEach var="model" items="${models}">
								<c:forEach var="project" items="${model.projects}">
									<li><a href="project/${project.id}" title="点击进行项目切换"> <i
											class="btn btn-xs btn-primary icon-user"></i> ${project.name}
									</a></li>
								</c:forEach>
							</c:forEach>
							<li><a href="javascript:void(0)"> 关闭 </a></li>
						</ul></li>
					<li class="green"><a data-toggle="dropdown"
						class="dropdown-toggle" title="当前角色" href="href="javascript:void(0)""> <i
							class="icon-group"></i>
					</a>
						<ul
							class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-group"></i> 当前角色</li>
							<c:forEach var="role" items="${roles}">
								<li><a href="javascript:void(0)"> <i
										class="btn btn-xs btn-primary icon-user"></i> ${role.name }
								</a></li>
							</c:forEach>
							<li><a href="javascript:void(0)"> 关闭 </a></li>
						</ul></li>
					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo" /> <span
							class="user-info"> <small>欢迎,</small> ${currentUser.name}
						</span> <i class="icon-caret-down"></i>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							  <li><a target="_blank" href="https://shimo.im/docs/5GfrtE2v3cMZBPU6"> <i class="icon-cog"></i> 帮助文档
							</a></li>
							<li><a href="javascript:void(0)"  data-toggle="modal" data-target="#updateStaffModal"> <i class="icon-user"></i> 修改密码
							</a></li>
							<li class="divider"></li>
							<li><a href="logout"> <i class="icon-off"></i> 注销
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
		</div>
		<!-- /.container -->
	</div>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar">
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>
						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->
				<ul class="nav nav-list">
					<c:forEach var="menu" items="${menus }">
						<li menu-id="${menu.menu_id }" href="${menu.url}"><a class="dropdown-toggle"> <i class="${menu.icon}"></i> <span
								class="menu-text"> ${menu.name } </span> <c:if
									test="${menu.childs.size()>0}">
									<b class="arrow icon-angle-down"></b>
								</c:if>

						</a>
							<ul class="submenu">
								<c:forEach var="child" items="${menu.childs}">
									<li menu-id="${child.menu_id }" href="${child.url}"><a
										href="javascript:void(0)"> <i
											class="icon-double-angle-right"></i><span>${child.name }
										</span></a></li>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
				<!-- /.nav-list -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>
				<script type="text/javascript">
					
				</script>
			</div>
			<div class="main-content">
				<div id="tabContainer"></div>
			</div>
			<!-- /.main-content -->
			<div id="context-menu">
				<ul class="dropdown-menu" role="menu">
					<li id="context-menu-reload"><a tabindex="-1" opt="reload">刷新当前&nbsp;&nbsp;&nbsp;&nbsp;<i
							class="icon-refresh"></i></a></li>
					<li id="context-menu-remove"><a tabindex="-1" opt="close">关闭当前&nbsp;&nbsp;&nbsp;&nbsp;<i
							class="icon-remove"></i></a></li>
					<!--   <li><a tabindex="-1">Something else here</a></li>
	           <li class="divider"></li>
	           <li><a tabindex="-1">Separated link</a></li> -->
				</ul>
			</div>
		</div>
		<!-- /.main-container-inner -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	
	
	
	
	
	 <div class="modal fade" id="updateStaffModal" tabindex="-1" role="dialog" aria-labelledby="updateStaffModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="updateStaffModalLabel">
            修改密码
          </h4>
        </div>
        <div class="modal-body">
           <form id="update_password_form" class="form-horizontal" role="form" style="margin-left:auto;margin-right:auto;border:2px solid #d2d6de; padding:20px 50px;">
           <fieldset>
        	<div class="form-group">
			    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">原密码</label>
						    <div class="col-sm-9">
							    <input  name ="oldpassworld" type="password" value="" placeholder="输入原密码" class="col-xs-12">		
							</div>					
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">新密码</label>
						    <div class="col-sm-9">
						    <input  name ="newpassword" type="password" value="" placeholder="输入新密码" class="col-xs-12">		
							</div>
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">确认密码</label>
						    <div class="col-sm-9">
						    <input  name ="confpassword" type="password" value="" placeholder="输入新密码" class="col-xs-12">		
							</div>
				</div>
            </fieldset>
             </form>
        </div>
        <div class="modal-footer">
         <button type="button" id="update_password_form_dismiss" class="btn btn-default" data-dismiss="modal">关闭
		</button>
		<button type="button" id="update_password_form_submit" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    </div>
	<!-- /.main-container -->

	<!-- basic scripts -->



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
  	 <script src="assets/js/bootstrapValidator.js"></script>

	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}

		try {
			ace.settings.check('main-container', 'fixed')
		} catch (e) {
		}

		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}

		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
		$(function(){
			$("#update_password_form").bootstrapValidator({
	    		fields: {
	    			oldpassworld:{
	    				 validators: {
	 	                    notEmpty: {
	 	                        message: '此字段不能为空'
	 	                    }
	 	                }
	 			 },
	 			newpassworld:{
   				 validators: {
	                    notEmpty: {
	                        message: '此字段不能为空'
	                    },
	                    identical: {
	                        field: 'confpassworld',
	                        message: '两次密码不一致'
	                    }
	                }
			 	},
			 	confpassworld:{
   				 validators: {
	                    notEmpty: {
	                        message: '此字段不能为空'
	                    },
	                    identical: {
	                        field: 'newpassworld',
	                        message: '两次密码不一致'
	                    }
	                }
			 	}
	    		}
	    	});
			$("#update_password_form_submit").click(function(){
			   $("#update_password_form").bootstrapValidator('validate');
	    		   if($("#update_password_form").data("bootstrapValidator").isValid()){
	    			   $.ajax({
	   		            cache: false,
	   		            type: "POST",
	   		            url:"user/password",
	   		            data:$('#update_password_form').serialize(),
	   		            async: false,
	   		            error: function(request) {
	   		                alert("请求出错");
	   		            },
	   		            success: function(data) {
	   		               var obj = eval(data);
	   		               if(obj.success){
	   		               	 $("#update_password_form_dismiss").click();
	   		               }
	   		            }
	   		        });	
	    		   }
			});
		})
		$(function() {
			$("li[menu-id='allmanager']").attr("class", "tabmenu active")
			$("#tabContainer").tabs({
				data : [ {
					id : 'allmanager',
					text : '首页',
					closeable : false,
					url : "allmanager"
				} ],
				showIndex : 0,
				loadAll : false
			});
			$("#myTab").on("click","li",
					function() {
						if ($("#" + $(this).attr("tab-id"))&& $("#" + $(this).attr("tab-id")).attr("class").indexOf("active") >= 0) {
							return;
						}
						$.each($("#tab-content").children(), function(index,item) {
							$(item).attr('class', 'tab-pane');
						});
						$.each($("#sidebar").find("li"), function(index, item) {
							$(item).attr('class', 'tabmenu');
						});
						$("#" + $(this).attr("tab-id")).attr("class","tab-pane active");
						$("#sidebar").find('li[menu-id="' + $(this).attr('tab-id') + '"]').attr('class', 'tabmenu active');
						if($("#sidebar").find("li[menu-id='" + $(this).attr("tab-id") + "']").parent().parent().length>0){
							if ($("#sidebar").find("li[menu-id='" + $(this).attr("tab-id") + "']").parent().parent()[0].tagName == "LI") {
								$("#sidebar").find("li[menu-id='" + $(this).attr("tab-id")+ "']").parent().parent().attr('class', 'active open');
							}
						}
					})
			$(".sidebar li").on(
					"click",
					function() {
						if ($(this).attr("href")&& !tab_dictionary.has($(this).attr("href"))) {
							$.each($("#tab-content").children(), function(index, item) {
								$(item).attr('class', 'tab-pane');
							});
							var obj = {
								id : $(this).attr("href").replace('.html', ''),
								text : $(this).find("span").html(),
								referermenuid : "rolemanger",
								closeable : true,
								url : $(this).attr("href")
							};
							tab_dictionary.set($(this).attr("href"), obj);
							$("#tabContainer").data("tabs").addTab(obj);
							$.each($("#sidebar").find("li"), function(index,item) {
								$(item).attr('class', 'tabmenu');
							});
							$('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').attr("class", "tabmenu active");
							$("#myTab").find("div[id="+ $(this).attr("href").replace('.html', '') + "]").attr('class', 'tab-pane active');
							if ($('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').parent().parent()[0].tagName == "LI") {
								$('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').parent().parent().attr('class','active open');
							}
							$("#" + $(this).attr("href").replace('.html', '')).attr("class", "tab-pane active");

						} else if (tab_dictionary.has($(this).attr("href"))) {
							if ($("#"+ $(this).attr("href").replace('.html', '')).attr("class")&& $("#"+ $(this).attr("href").replace('.html','')).attr("class").indexOf("active") >= 0) {
								return;
							}
							$.each($("#sidebar").find("li"), function(index,item) {
								$(item).attr('class', 'tabmenu');
							});
							$.each($("#tab-content").children(), function(index, item) {
								$(item).attr('class', 'tab-pane');
							});
							$('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').attr("class", "tabmenu active");
							if ($('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').parent().parent()[0].tagName == "LI") {
								$('li[menu-id='+ $(this).attr("href").replace('.html', '') + ']').parent().parent().attr('class','active open');
							}
							$("#myTab").find("li[tab-id="+ $(this).attr("href").replace('.html', '') + "] a").tab("show");
							$("#" + $(this).attr("href").replace('.html', '')).attr("class", "tab-pane active");
							//$("#myTab").find("div[id="+$(this).attr("href").replace('.html','')+"]").attr('class','tab-pane active'); 
							//$("#tabContainer").data("tabs").reload(tab_dictionary.get($(this).attr("href")));
						}
					});

			$('#myTab li').contextmenu(
							{
								target : '#context-menu',
								before : function(e, context) {
									if ($(e.target).parent().parent().attr("tab-id") == "allmanger") {
										$("#context-menu-remove").css("display", "none");
									} else {
										$("#context-menu-remove").css("display", "block");
									}
									$("#context-menu-reload").attr("tab-id",$(e.target).parent().parent().attr("tab-id"));
									$("#context-menu-remove").attr("tab-id",$(e.target).parent().parent().attr("tab-id"));

								},
								onItem : function(context, e) {
									if ($(e.target).attr("opt") == "reload") {
										var objname = $(e.target).parent().attr("tab-id")+ ".html";
										$("#tabContainer").data("tabs").reload(tab_dictionary.get(objname));
									} else if ($(e.target).attr("opt") == "close") {
										var objname = $(e.target).parent().attr("tab-id");
										$("#tabContainer").data("tabs").remove(objname);
									}
								}
							});

		});
	</script>
</body>
</html>
