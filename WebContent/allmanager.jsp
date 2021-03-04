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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-table.css" />
    <link rel="stylesheet" href="assets/css/daterangepicker.css" />
    <style type="text/css">
      .page-list .btn-group{display: none;}
      .fixed-table-toolbar .input-group{margin-top:10px;padding-left:0px;}
	  .fa-remove:before, .fa-close:before, .fa-times:before {
			font-size: 15px;
			
	 }
 	 .list-inline li{
 	 	width:23%;
 	 }
	 table tr:nth-child(even){background:#fff;}
		 
</style>
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />


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
</head>
		

	<body >

	<div class="allmanger-container" style="padding:20px;" >
			<div class="allmanger-header" >
							<div class="col-sm-12" style="padding:0px;">
										<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">
													<i class="ace-icon fa fa-comment blue"></i>
													项目信息
												</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<section class="content">
														<table class="table table-bordered table-striped">
													  	 <tbody style="border: 1px solid #ddd" >
													  	   <tr style="font-size:16px;">
													  	   	<td>项目名称:<span>&nbsp;${model.name }</td>
													  	   	<td>施工创建时间:<span>&nbsp;${model.createDate }</td>
													  	   	<td>项目所属模块:<span>&nbsp;${model.moduleName }</td>
													  	   	<td>项目地址:<span>&nbsp;${model.address }</td>
													  	   </tr>
													   	 </tbody>
													   </table>
													</section>
												</div><!-- /.widget-main -->
											</div><!-- /.widget-body -->
										</div><!-- /.widget-box -->
									</div><!-- /.col -->
			</div>

	  

		<div class="content" style="margin-top:30px;background:#f9f9f9">
									<div class="col-sm-12" style="padding:0px;">
										<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">
													<i class="ace-icon fa fa-comment blue"></i>
													 今日项目概览
												</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<table class="table table-bordered table-striped">
													   <tbody style="border: 1px solid #ddd" >
													   <tr>
													   	    <td rowspan="3" style="text-align:center;vertical-align:middle;font-size:20px;font-weight: bold;">今日金额</td>
													   		<td rowspan="3" style="text-align:center;vertical-align:middle;font-size:18px;font-weight: bold;" id="today_sum_price"></td>
													   		<td>人工</td>
													   		<td id="today_labor_sum_price">元</td>
													   		<td id="today_labor_price_percent">%</td>
													   		<td id="today_labor_sum_nums"> 人</td>
													   		<td id="today_labor_nums_percent">%</td>
													   </tr>
													   <tr>
													   		<td>材料</td>
													   		<td id="today_material_sum_price">元</td>
													   		<td id="today_material_price_percent">%</td>
													   		<td id="today_material_sum_nums"> 人</td>
													   		<td id="today_material_nums_percent">%</td>
													   </tr>
													   <tr>
													   		<td>机械</td>
													   		<td id="today_mechanics_sum_price">元</td>
													   		<td id="today_mechanics_price_percent">%</td>
													   		<td id="today_mechanics_sum_nums">人</td>
													   		<td id="today_mechanics_nums_percent">%</td>
													   </tr>
													   </tbody>
													</table>
													<section class="content">
													<div class="row" style=""> 
														<div class="col-lg-4" id="chart_1" style="height:250px;border: 1px solid #ddd"></div>
														<div class="col-lg-4" id="chart_2" style="height:250px;border: 1px solid #ddd"></div>
														<div class="col-lg-4" id="chart_3" style="height:250px;border: 1px solid #ddd"></div>
													</div>
													</section>
												</div><!-- /.widget-main -->
											</div><!-- /.widget-body -->
										</div><!-- /.widget-box -->
									</div><!-- /.col -->
									<div class="col-sm-12" style="padding:0px;">
										<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">
													<i class="ace-icon fa fa-comment blue"></i>
													 整体项目概览
												</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<table class="table table-bordered table-striped">
													   <tbody style=" border: 1px solid #ddd" >
													   <tr>
													   	    <td rowspan="3" style="text-align:center;vertical-align:middle;font-size:20px;font-weight: bold;">总金额</td>
													   		<td rowspan="3" style="text-align:center;vertical-align:middle;font-size:18px;font-weight: bold;" id="allday_sum_price"></td>
													   		<td>人工</td>
													   		<td id="allday_labor_sum_price">元</td>
													   </tr>
													   <tr>
													   		<td>材料</td>
													   		<td id="allday_material_sum_price">元</td>
													   </tr>
													   <tr>
													   		<td>机械</td>
													   		<td id="allday_mechanics_sum_price">元</td>
													   </tr>
													   </tbody>
													</table>
													<section class="content">
													<div class="row" style=""> 
														<div class="col-lg-4" id="chart_4" style="height:250px;border: 1px solid #ddd"></div>
														<div class="col-lg-4" id="chart_5" style="height:250px;border: 1px solid #ddd"></div>
														<div class="col-lg-4" id="chart_6" style="height:250px;border: 1px solid #ddd"></div>
													</div>
													</section>
												</div><!-- /.widget-main -->
											</div><!-- /.widget-body -->
										</div><!-- /.widget-box -->
									</div><!-- /.col -->
									<div class="col-sm-12" style="padding:0px;">
										<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">
													<i class="ace-icon fa fa-comment blue"></i>
													  材料使用情况
												</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<section class="content">
													<div class="row" style=""> 
														<div class="col-lg-12" id="chart_7" style="height:350px;border: 1px solid #ddd"></div>
													</div>
													</section>
												</div>
											</div>
										</div>
									</div><!-- /.col -->
		</div>
	</div>
	<button class="btn btn-primary btn-lg"  style="display:none" id="btn_sheet" data-toggle="modal" data-target="#myModal">
	
</button>
<!-- 模态框（Modal） -->
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
	<script src="assets/js/echarts.min.js"></script>
	
<script type="text/javascript">

function initTable(day,obj,name,type){
	  $("#"+day+"_"+name+"_sum_price").html(obj.price+"元")
      $("#"+day+"_"+name+"_price_percent").html('<i style="color:'+obj.priceColor+'" class="'+obj.priceIcon+'"></i>'+obj.priceChange+'%')
      $("#"+day+"_"+name+"_sum_nums").html(obj.nums+" "+type);
      $("#"+day+"_"+name+"_nums_percent").html('<i style="color:'+obj.numsColor+'" class="'+obj.numsIcon+'"></i>'+obj.numsChange+'%')
    
}
	$(function(){
		$.ajax({
	        cache: true,
	        type: "GET",
	        url:"project/todayInfo",
	        async: false,
	        error: function(request) {
	        },
	        success: function(data) {
	           var labor = data.labor;
	           var material = data.material;
		       var mechanics = data.mechanics;
		       var sumPrice = parseInt(labor.price)+parseInt(material.price)+parseInt(mechanics.price);
		       $("#today_sum_price").html(sumPrice+"元");
		       initTable("today",labor,"labor","人");
		       initTable("today",material,"material","车");
		       initTable("today",mechanics,"mechanics","车");
		       $(data.list).each(function(index,item){
	        	   var chart = echarts.init(document.getElementById('chart_'+(index+1)));
	        	   var option = {
	   	        	    title : {
	   	        	        text: item.title,
	   	        	        subtext: item.subTitle,
	   	        	        x:'left'
	   	        	    },
	   	        	    tooltip : {
	   	        	        trigger: 'item',
	   	        	        formatter: "{b} : {c}元 ({d}%)"
	   	        	    },
	   	        	    /**
	   	        	    legend: {
	   	        	    	 orient: 'vertical',
	   	        	         left: 'left',
	   	        	         data: ['人工','材料','机械']
	   	        	    },**/
	   	        	    series : [
	   	        	        {
	   	        	            type: 'pie',
	   	        	            radius : '60%',
	   	        	            center: ['50%', '60%'],
	   	        	            data:item.data,
	   	        	            itemStyle: {
	   	        	                emphasis: {
	   	        	                    shadowBlur: 10,
	   	        	                    shadowOffsetX: 0,
	   	        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	   	        	                }
	   	        	            }
	   	        	        }
	   	        	    ]
	   	        	};
	        	   chart.setOption(option);
	           });
	        }
	    });
		
		$.ajax({
	        cache: true,
	        type: "GET",
	        url:"project/alldayInfo",
	        async: false,
	        error: function(request) {
	        },
	        success: function(data) {
	           var labor = data.labor;
	           var material = data.material;
		       var mechanics = data.mechanics;
		       var sumPrice = parseInt(labor.price)+parseInt(material.price)+parseInt(mechanics.price);
		       $("#allday_sum_price").html(sumPrice+"元");
		       initTable("allday",labor,"labor","人");
		       initTable("allday",material,"material","车");
		       initTable("allday",mechanics,"mechanics","车");
		       $(data.list).each(function(index,item){
	        	   var chart = echarts.init(document.getElementById('chart_'+(index+4)));
	        	   var option = {
	   	        	    title : {
	   	        	        text: item.title,
	   	        	        subtext: item.subTitle,
	   	        	        x:'left'
	   	        	    },
	   	        	    tooltip : {
	   	        	        trigger: 'item',
	   	        	        formatter: "{b} : {c}元 ({d}%)"
	   	        	    },
	   	        	    /**
	   	        	    legend: {
	   	        	    	 orient: 'vertical',
	   	        	         left: 'left',
	   	        	         data: ['人工','材料','机械']
	   	        	    },**/
	   	        	    series : [
	   	        	        {
	   	        	            type: 'pie',
	   	        	            radius : '60%',
	   	        	            center: ['50%', '60%'],
	   	        	            data:item.data,
	   	        	            itemStyle: {
	   	        	                emphasis: {
	   	        	                    shadowBlur: 10,
	   	        	                    shadowOffsetX: 0,
	   	        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	   	        	                }
	   	        	            }
	   	        	        }
	   	        	    ]
	   	        	};
	        	   chart.setOption(option);
	           });
	        }
	    });
		
		$.ajax({
            cache: false,
            type: "GET",
            url:'project/materialInfo',
            async: false,
            error: function(request) {
            },
            success: function(data) {
            	var option = {
                        title: {
                        },
                        tooltip: {
                        	 trigger: 'item',
                        },
                        legend: {
                            data:['今日数据','截止到目前'],
                            left: 'right'
                        },
                        xAxis: {
                             data: data.nameData
                        },
                        yAxis: {},
                        series:[{
    	                    name: '今日数据',
    	                    type: 'bar',
    	                    barGap: 0,
    	                    data: data.todayData
                        },{
    	                    name: '截止到目前',
    	                    type: 'bar',
    	                    barGap: 0,
    	                    data: data.alldayData
                        }]
                }
            	var chart = echarts.init(document.getElementById('chart_7'));
            	chart.setOption(option);
            }
        });	
		
	})
</script>


</body>
</html>
