<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base href="<%=basePath%>" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>${title }</title>
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
	    <link rel="stylesheet" href="assets/css/daterangepicker.css" />
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
 .datepicker.dropdown-menu {
            z-index:2500 !important;
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
     <a href="index"class="navbar-brand"> <small> <i class="icon-leaf"></i> 路桥管理 </small> </a> 
     <!-- /.brand --> 
    </div> 
    <!-- /.navbar-header --> 
    <div class="navbar-header pull-right" role="navigation"> 
     <ul class="nav ace-nav"> 
      <li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo"  /> <span class="user-info"> <small>欢迎,</small> ${currentUser.name} </span> <i class="icon-caret-down"></i> </a> 
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
			<div style="float:left;font-size:22px;display:block;">${title }</div>
		</section>


	<section class="content">
			<div class="row" id="" style="margin:20px 50px;"> 
			  <form id="project_form" action="${formURL }" method="POST"  class="form-horizontal" role="form" style="width:50%;margin-left:auto;margin-right:auto;border:2px solid #d2d6de; padding:20px 50px;">
			    <fieldset>
			    
				<div class="form-group">
							<c:if test="${msg!=null}">
								<p style="color:red;text-align:center;font-size:16px;">修改失败请重试</p>
							</c:if>
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">所属模块</label>
						    <div class="col-sm-9">
								<select name="module" class="form-control col-xs-10 col-sm-5">
									<c:forEach var="module" items="${modules}">
										<option value="${module.id}"
										<c:if test="${project.module==module.id}">selected="selected"</c:if>
										>${module.name}</option> -->
									</c:forEach>
								</select>
								<span class="require_tip" title="必选项">*</span>	
							</div>					
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">项目名称</label>
						    <div class="col-sm-9">
						    <input id="project_id_input" name ="projectId" type="hidden" value="${tempId}" />
						    <input  name ="name" type="text" value="${project.name }"  placeholder="输入项目名称" class="col-xs-12">		
							<span class="require_tip" title="必选项">*</span>
							</div>
				</div>
				<div class="form-group">
					    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">项目管理员</label>
					    <div class="col-sm-9">
							<select name="manager" id="project_manager_select" class="multiselect"  multiple="multiple" >
								<c:forEach var="manager" items="${managers}">
								  	<option value="${manager.id}" ${manager.remark}>${manager.name}</option>
								</c:forEach>
							</select>
							<span class="require_tip" title="必选项">*</span>
							<button type="button" data-toggle="modal" data-target="#myModal" id="add_manager_btn" style="float:right;"  title="添加管理员" class="col-sm-1 btn btn-xs btn-success">								<i class="icon-plus bigger-120"></i>							</button>
						</div>					
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">项目经理</label>
						    <div class="col-sm-9">
								<select name="pmanager" class="multiselect"  multiple="multiple"  id="project_pmanager_select">
									<c:forEach var="pmanager" items="${pmanagers}">
									  	<option value="${pmanager.id}" ${pmanager.remark} >${pmanager.name}</option>
									</c:forEach>
								</select>
								<span class="require_tip" title="必选项">*</span>
								<button type="button" data-toggle="modal" data-target="#myModal" id="add_pmanager_btn" style="float:right;" title="添加项目经理" class="col-sm-1 btn btn-xs btn-success">								<i class="icon-plus bigger-120"></i>							</button>
							</div>					
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" style="text-align:right;">施工起止时间</label>
					<div class="input-group col-sm-9">
					<c:if test="${project!=null&&project.startDate!=null && project.startDate!=''}">
							 <input class="form-control col-xs-12 col-sm-5"
						name="daterange" value="${project.startDate} to ${project.endDate }" />
					</c:if>
						<c:if test="${project==null||project.startDate==null||project.startDate==''}">
							 <input class="form-control col-xs-12 col-sm-5"
						name="daterange" value="" />
					</c:if>
						<span class="input-group-addon">
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>

				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">施工地址</label>
						    <div class="col-sm-9">
						    <input type="text" name="address" value="${project.address }"  placeholder="输入施工地址" class="col-xs-12">		
							
							</div>
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">开始桩号(K)</label>
						    <div class="col-sm-9">
						    <input type="number" name="startNode" value="${project.startNode }"  placeholder="输入开始桩号" class="col-xs-12">		
							</div>
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">结束桩号(K)</label>
						    <div class="col-sm-9">
						    <input type="number" name="endNode" value="${project.endNode }" placeholder="输入结束桩号" class="col-xs-12">		
							</div>
				</div>
				<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">项目描述</label>
						    <div class="col-sm-9">
						    <textarea name="description"  class="form-control limited" id="form-field-9" >${project.description }</textarea>	
						    
							</div>
				</div>
					<div class="clearfix">
						<div style="width:50%;margin-left:auto;margin-right:auto;">
						  <button class="btn btn-warning" type="button" onclick="javascript: location.href='/luqiao/project/list';">返回</button>
						  <button class="btn btn-success" type="submit" >确认</button>
						</div>
					</div>

					<div class="space-4"></div>
				</fieldset>
			</form>
			</div>			 				
		</section>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加项目系统管理员
					</h4>
				</div>
				<div class="modal-body">
					 <form id="model_user_form"  class="form-horizontal" role="form" style="margin-left:auto;margin-right:auto;border:2px solid #d2d6de; padding:20px 50px;">
			    		<fieldset>
			    			<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">姓名</label>
							    <div class="col-sm-9">
							    <input type="hidden" id="user_id_input" name="id" />	
							    <input name="name" id="staff_name_input" type="text" placeholder="输入员工姓名" class="col-xs-12" />	
							    <span class="require_tip" title="必选项">*</span>	
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">性别</label>
							    <div class="col-sm-9">
								    <p style="margin-top:3px;"><input name="sex" value="0" checked="checked" type="radio" class="ace" />
										<span class="lbl"> 男</span>
										<input name="sex"  value="1" type="radio" class="ace" />
										<span class="lbl"> 女</span>
									</p>
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">手机号</label>
							    <div class="col-sm-9">
							    <input type="text" name="mobile" autocomplete="off" placeholder="输入手机号" class="col-xs-12" />
							    <span class="require_tip" title="必选项">*</span>		
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">初始密码</label>
							    <div class="col-sm-9">
							    <input type="password" name="password" autocomplete="off" placeholder="请输入初始密码" class="col-xs-12" />
							    <span class="require_tip" title="必选项">*</span>
								</div>
							</div>
							<div class="form-group">
						    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">角色</label>
							    <div class="col-sm-9">
									<select id="role_select" class="multiselect"  multiple="multiple" name="role" >
										<option value="2">项目管理员</option>
										<option value="3">项目经理</option>
									</select>
									<span class="require_tip" title="必选项">*</span>
								</div>	
							</div>
						   <div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">身份证</label>
							    <div class="col-sm-9">
							    <input type="text" name="idcard" placeholder="输入身份证" class="col-xs-12" />		
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">学历</label>
							    <div class="col-sm-9">
							    <input type="text" name="education" placeholder="输入学历" class="col-xs-12"/>		
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right" style="text-align:right;">月工资</label>
							    <div class="col-sm-9">
							    <input type="hidden" name="basePriceType" value="1"/>
							    <input type="number" name="unit_price" placeholder="输入月工资" class="col-xs-12" />		
								</div>
							</div>
						   <div class="form-group">
						   		<label class="col-sm-3 control-label no-padding-right" style="text-align:right;">类型</label>
							    <div class="col-sm-9">
									<select name="type" class="form-control col-xs-10 col-sm-5">
										<option value="0" selected="selected">内部</option>
										<option value="1">外聘</option>
									</select>
								</div>	
						   </div>
						    <div class="form-group">
						   		<label class="col-sm-3 control-label no-padding-right" style="text-align:right;">状态</label>
							    <div class="col-sm-9">
									<select name="state" class="form-control col-xs-10 col-sm-5">
										<option value="0" selected="selected">在职</option>
										<option value="1">离职</option>
									</select>
								</div>	
						   </div>
							<div class="form-group">				
								<label class="col-sm-3 control-label no-padding-right" style="text-align:right;">进场时间</label>
								<div class="input-group col-sm-9">
									<input class="form-control date-picker" name="hiredate" placeholder="选择进场时间"  type="text" data-date-format="dd-mm-yyyy" />
									<span class="input-group-addon">
										<i class="icon-calendar bigger-110"></i>
									</span>
								</div>

							</div>
			    	</fieldset> 
			    	 </form>
				</div>
				<div class="modal-footer">
					<button type="button" id="user_form_dismiss" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="user_form_submit" class="btn btn-primary">
						确认
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
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
	<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	<script src="assets/js/bootstrap-tab.js"></script>
	<script src="assets/js/date-time/moment.min.js"></script>s
	<script src="assets/js/date-time/daterangepicker.min.js"></script>
	<script src="assets/js/bootstrapValidator.js"></script>
	<script src="assets/js/bootstrap-multiselect.js"></script>	
	  <script src="assets/js/bootstrap-typeahead.js"></script>
<script>
$(function() {
	//$('#role_select').multiselect();
    $('input[name="daterange"]').daterangepicker(  
            {  
                // startDate: moment().startOf('day'),  
                //endDate: moment(),  
                //minDate: '01/01/2012',    //最小时间  
               // maxDate : moment(), //最大时间   
                dateLimit : {  
                    days : 30  
                }, //起止时间的最大间隔  
                //showDropdowns : true,  
                showWeekNumbers : false, //是否显示第几周  
                //timePicker : true, //是否显示小时和分钟  
                //timePickerIncrement : 60, //时间的增量，单位为分钟  
                timePicker12Hour : false, //是否使用12小时制来显示时间  
                /* ranges : {  
                    //'最近1小时': [moment().subtract('hours',1), moment()],  
                    '今日': [moment().startOf('day'), moment()],  
                    '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],  
                    '最近7日': [moment().subtract('days', 6), moment()],  
                    '最近30日': [moment().subtract('days', 29), moment()]  
                },   */
                opens : 'left', //日期选择框的弹出位置  
                buttonClasses : [ 'btn btn-default' ],  
                applyClass : 'btn-small btn-primary blue',  
                cancelClass : 'btn-small',  
                format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式  
                separator : ' to ',  
                locale : {  
                    applyLabel : '确定',  
                    cancelLabel : '取消',  
                    fromLabel : '起始时间',  
                    toLabel : '结束时间',  
                    customRangeLabel : '自定义',  
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],  
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',  
                            '七月', '八月', '九月', '十月', '十一月', '十二月' ],  
                    firstDay : 1  
                }  
            }, function(start, end, label) {//格式化日期显示框  
             $("#project_form").bootstrapValidator('updateStatus', 'daterange', 'VALID') 
             $('input[name="daterange"]').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));  
           });  
});
	var $table = $('#table');
</script>
	 <script type="text/javascript">
	 function populateForm(data) { 
			$.each(data, function(key, value){  
			    var $ctrl = $("#model_user_form").find('[name='+key+']'); 
			    if($ctrl.is('select')){
			        $("option",$ctrl).each(function(){
			            if (this.value==value) { this.selected=true; }
			        });
			    }
			    else {
			        switch($ctrl.attr("type"))  
			        {  
			            case "text": case "number": case "password":  case "hidden":  case "textarea":  
			                $ctrl.val(value);   
			                break;   
			            case "radio" : case "checkbox":   
			                $ctrl.each(function(){
			                   if($(this).attr('value') == value) {  $(this).attr("checked",value); } });   
			                break;
			        } 
    }})} 
     (function($){
    	 $("#add_pmanager_btn").click(function(){
    		 $("#myModalLabel").html("添加项目经理");
    	 });
		 $("#add_manager_btn").click(function(){
			 $("#myModalLabel").html("添加项目系统管理员");
    	 });
    	 $('#model_user_form')
         .find('[name="role"]')
             .multiselect({
                 // Re-validate the multiselect field when it is changed
                 onChange: function(element, checked) {
                     $('#model_user_form').bootstrapValidator('revalidateField', 'role');
                 }
             })
             .end()
         .bootstrapValidator({
             excluded: ':disabled',
             /**
             feedbackIcons: {
                 valid: 'glyphicon glyphicon-ok',
                 invalid: 'glyphicon glyphicon-remove',
                 validating: 'glyphicon glyphicon-refresh'
             },**/
             fields: {
                 role: {
                     validators: {
                         callback: {
                             message: '必须为员工选一角色',
                             callback: function(value, validator) {
                                 // Get the selected options
                                 var options = validator.getFieldElements('role').val();
                                 return (options != null
                                         && options.length >= 1);
                             }
                         }
                     }
                 },
                 name:{
    				 validators: {
 	                    notEmpty: {
 	                        message: '此字段不能为空'
 	                    },
	                    stringLength: {
  	                         min: 0,
  	                         max: 20,
  	                         message: '此字段最大长度为20位'
  	                     },
 	                    callback: {
                            message: '此用户名已经存在',
                            callback: function(value, validator) {
                         	    var result;
                               	$.ajax({
 				                    cache: false,
 				                    type: "POST",
 				                    url:"user/hasName",
 				                    data:{name:value,id:$("#user_id_input").val()},
 				                    async: false,
 				                    error: function(request) {
 				                        alert("请求出错");
 				                    },
 				                    success: function(data) {
 				                       result = data.success;
 				                    	
 				                    },
 				                });	
                                return !result;
                            }
                        }
	 	              }
	 			 },
				 mobile:{
					 validators: {
		                    notEmpty: {
		                        message: '此字段不能为空'
		                    },
		                    stringLength: {
		                        min: 11,
		                        max: 11,
		                        message: '手机号必须为11位'
		                    },
		                    regexp: {
		                        regexp: /^[0-9]+$/,
		                        message: '请填写数字'
		                    },
	 	                    callback: {
	                            message: '此手机号已经存在',
	                            callback: function(value, validator) {
	                         	    var result;
	                               	$.ajax({
	 				                    cache: false,
	 				                    type: "POST",
	 				                    url:"user/hasMobile",
	 				                    data:{name:value,id:$("#user_id_input").val()},
	 				                    async: false,
	 				                    error: function(request) {
	 				                        alert("请求出错");
	 				                    },
	 				                    success: function(data) {
	 				                       result = data.success;
	 				                    	
	 				                    },
	 				                });	
	                                return !result;
	                            }
	                        }
		                }
				 },
				 password:{
					 validators: {
		                    notEmpty: {
		                        message: '此字段不能为空'
		                    },
		                    stringLength: {
		                         min: 6,
		                         max: 35,
		                         message: '密码长度6~35位'
		                     }
		                }
				   }
	             }
         });
    	 $("#project_form")
    	 .find('[name="manager"]').multiselect({
             // Re-validate the multiselect field when it is changed
             onChange: function(element, checked) {
                 $('#project_form').bootstrapValidator('revalidateField', 'manager');
             }
         })
         .end()
         .find('[name="pmanager"]').multiselect({
             // Re-validate the multiselect field when it is changed
             onChange: function(element, checked) {
                 $('#project_form').bootstrapValidator('revalidateField', 'pmanager');
             }
         })
         .end()
    	 $("#project_form").bootstrapValidator({
    		 excluded: ':disabled',
    		 fields: {
    			 name:{
    				 validators: {
    	                    notEmpty: {
    	                        message: '此字段不能为空'
    	                    },
    	                    stringLength: {
   	                         min: 0,
   	                         max: 20,
   	                         message: '此字段最大长度为20位'
   	                     }
    	                }
    			 },
    			 manager:{
    				 validators: {
                         callback: {
                             message: '此字段不能为空且只能选一人',
                             callback: function(value, validator) {
                                 // Get the selected options
                                 var options = validator.getFieldElements('manager').val();
                                 return (options != null
                                         && options.length == 1);
                             }
                         }
                     }
    			 },
    			 pmanager:{
    				 validators: {
                         callback: {
                             message: '此字段不能为空且只能选一人',
                             callback: function(value, validator) {
                                 // Get the selected options
                                 var options = validator.getFieldElements('pmanager').val();
                                 return (options != null
                                         && options.length == 1);
                             }
                         }
                     }
    			 },
    			 startNode:{
    				 validators: {
	                    regexp: {
	                        regexp: /^[0-9]+$/,
	                        message: '请填写数字'
	                    },
	                    stringLength: {
	                         min: 0,
	                         max: 10,
	                         message: '桩号最大长度为10位'
	                     }
    			 	}
    			 },
    			 endNode:{
    				 validators: {
	                    regexp: {
	                        regexp: /^[0-9]+$/,
	                        message: '请填写数字'
	                    },
	                    stringLength: {
	                         min: 0,
	                         max: 10,
	                         message: '桩号最大长度为10位'
	                     }
    			 	}
    			 },
    			 description:{
    				 validators: {
    					 stringLength: {
    	                        min: 0,
    	                        max: 100,
    	                        message: '最长为100个字符'
    	                    }
   	                }
     			 }
    		 }
    	 });
	$.fn.datepicker.dates['zh-CN'] = {
		days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
		daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
		daysMin:  ["日", "一", "二", "三", "四", "五", "六"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
		today: "今日",
		clear: "清除",
		format: "yyyy年mm月dd日",
		titleFormat: "yyyy年mm月",
		weekStart: 1
	};
	var USER_FORM_URL="user/addManager/"+$("#project_id_input").val();
	var userDict = new Array(); 
	$("#staff_name_input").typeahead({
		onSelect: function(item) {
			var row = userDict[item.value];
			$("#model_user_form").bootstrapValidator('cleanForm');
			USER_FORM_URL=$("#project_id_input").val()+"/user/addManagerRelationship/"+item.value;
			populateForm(row);
		},
		ajax: {
			url: "user/search",
			timeout: 500,
			displayField: "name",
			triggerLength: 1,
			method: "get",
			loadingClass: "loading-circle",
			preDispatch: function (query) {
				return {
					param: query
				}
			},
			preProcess: function (data) {
				$.each(data,function(index,item){
					userDict[item.id]=item;
				});
				return data;
			}
		}
	});
	
	$("#user_form_submit").click(function(){
		$("#model_user_form").bootstrapValidator('validate');
		if($("#model_user_form").data("bootstrapValidator").isValid()){
			$.ajax({
	            cache: false,
	            type: "POST",
	            url:USER_FORM_URL,
	            data:$('#model_user_form').serialize(),
	            async: false,
	            error: function(request) {
	                alert("请求出错");
	            },
	            success: function(data) {
	               var obj = eval(data);
	               if(obj.success){
	            	 $($("#role_select").val()).each(function(index,item){
	            		 if(item==2){
	            			 $("#project_form").find('[name="manager"]').find("option[value='"+obj.msg.id+"']").remove();
	            			 $("#project_form").find('[name="manager"]').multiselect('destroy');
	      	              	 $("#project_manager_select").append("<option value='"+obj.msg.id+"'>"+obj.msg.name+"</option>");
	      	                 $("#project_form").find('[name="manager"]').multiselect({
	                             onChange: function(element, checked) {
	                                 $('#project_form').bootstrapValidator('revalidateField', 'manager');
	                             }
	                         }).end()
	            		 }else if(item==3){
	            			 $("#project_form").find('[name="pmanager"]').find("option[value='"+obj.msg.id+"']").remove();
	            			 $("#project_form").find('[name="pmanager"]').multiselect('destroy');
	                  		 $("#project_pmanager_select").append("<option value='"+obj.msg.id+"'>"+obj.msg.name+"</option>");
	                  		 $("#project_form").find('[name="pmanager"]').multiselect({
	                             // Re-validate the multiselect field when it is changed
	                             onChange: function(element, checked) {
	                                 $('#project_form').bootstrapValidator('revalidateField', 'pmanager');
	                             }
	                         }).end()
	            		 
	            		 }
	           		 });
	               	 $("#user_form_dismiss").click();
	               	 $('form')[1].reset();
	               }
	            }
	        });
		}
		
	});
}(jQuery));
		$('.date-picker').datepicker({
				autoclose:true,
				todayHighlight: true,
                   language:"zh-CN", 
                   format:"yyyy-mm-dd" 
				}).next().on(ace.click_event, function(){

				$(this).prev().focus();
			});

	</script>
</html>