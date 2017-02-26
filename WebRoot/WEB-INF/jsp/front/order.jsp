<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>申请--一言白条</title>

	<meta name="description" content="3 styles with inline editable feature" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="/static/ace/css/bootstrap.css" />
	<link rel="stylesheet" href="/static/ace/css/font-awesome.css" />

	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="/static/ace/css/jquery-ui.custom.css" />
	<link rel="stylesheet" href="/static/ace/css/jquery.gritter.css" />
	<link rel="stylesheet" href="/static/ace/css/select2.css" />
	<link rel="stylesheet" href="/static/ace/css/datepicker.css" />
	<link rel="stylesheet" href="/static/ace/css/bootstrap-editable.css" />

	<!-- text fonts -->
	<link rel="stylesheet" href="/static/ace/css/ace-fonts.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="/static/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="/static/ace/css/ace-part2.css" class="ace-main-stylesheet" />
	<![endif]-->

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="/static/ace/css/ace-ie.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->
	<script src="/static/ace/js/ace-extra.js"></script>

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="/static/ace/js/html5shiv.js"></script>
	<script src="/static/ace/js/respond.js"></script>
	<![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<!-- /section:basics/sidebar.mobile.toggle -->
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<a href="#" class="navbar-brand">
				<small>
					<i class="fa fa-leaf"></i>
					一言白条
				</small>
			</a>

			<!-- /section:basics/navbar.layout.brand -->

			<!-- #section:basics/navbar.toggle -->

			<!-- /section:basics/navbar.toggle -->
		</div>
		<!-- /section:basics/navbar.dropdown -->
	</div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>


	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="clearfix">

					</div>

					<div class="hr dotted"></div>

					<div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="/static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						<div id="user-profile-3" class="user-profile row">
							<div class="col-sm-offset-1 col-sm-10">
								<div class="well well-sm">
									<div class="inline middle blue bigger-110">
										<div class="inline middle blue bigger-110"> 您正在申请：  ${pd.CHANNELNAME} --> ${pd.PRODUCTNAME}   <a href="#" onclick="showrate('${pd.CHANNEL_ID}','${pd.CHANNELNAME}');return false;">查看费率</a> </div>

									</div><!-- /.well -->

									<div class="space"></div>

									<form action="/front/saveOrder.do" name="Form" id="Form" method="post">
										<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}"/>
										<div id="zhongxin" style="padding-top: 13px;">
											<table id="table_report" class="table table-striped table-bordered table-hover">
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">渠道ID:</td>
													<td><input type="text" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="255" placeholder="这里输入订单ID" title="订单ID" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
													<td><input type="text" name="ORDERID" id="ORDERID" value="${pd.ORDERID}" maxlength="255" placeholder="这里输入订单ID" title="订单ID" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">订单名称:</td>
													<td><input type="text" name="ORDERNAME" id="ORDERNAME" value="${pd.ORDERNAME}" maxlength="255" placeholder="这里输入订单名称" title="订单名称" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">用户id:</td>
													<td><input type="text" name="USERID" id="USERID" value="${pd.USERID}" maxlength="255" placeholder="这里输入用户id" title="用户id" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">订单金额:</td>
													<td><input type="number" name="ORDERVALUE" id="ORDERVALUE" value="${pd.ORDERVALUE}" maxlength="32" placeholder="这里输入订单金额" title="订单金额" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">订单时间:</td>
													<td><input type="text" name="ORDERTIME" id="ORDERTIME" value="${pd.ORDERTIME}" maxlength="32" placeholder="订单时间" title="订单时间" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">产品类型:</td>
													<td><input type="number" name="PRODUCTTYPE" id="PRODUCTTYPE" value="${pd.PRODUCTTYPE}" type="text"     placeholder="产品类型" title="产品类型" style="width:98%;"/></td>
												</tr>

												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">分期数:</td>
													<td><input type="number" name="INSTALMENT" id="INSTALMENT" value="${pd.INSTALMENT}" maxlength="32" placeholder="这里输入分期数" title="分期数" style="width:98%;"/></td>
												</tr>
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">分期费率:</td>
													<td><input type="number" name="INSTALMENTRATE" id="INSTALMENTRATE" value="${pd.INSTALMENTRATE}" maxlength="32" placeholder="这里输入分期费率" title="分期费率" style="width:98%;"/></td>
												</tr>

											</table>
										</div>

										<div class="clearfix form-actions">
											<div class="col-md-offset-4 col-md-9">
												<button class="btn btn-info" type="button" onclick="save();">
													<i class="ace-icon fa fa-check bigger-110"></i>
													白条支付
												</button>

												&nbsp; &nbsp;
												<button class="btn" type="reset">
													<i class="ace-icon fa fa-undo bigger-110"></i>
													关闭
												</button>
											</div>
										</div>
									</form>
								</div><!-- /.span -->
							</div><!-- /.user-profile -->
						</div>

						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->

	<div class="footer">
		<div class="footer-inner">
			<!-- #section:basics/footer -->
			<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">ZLHT</span>
							Application &copy; 2016-2017
						</span>
			</div>

			<!-- /section:basics/footer -->
		</div>
	</div>

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='/static/ace/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
	window.jQuery || document.write("<script src='/static/ace/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='/static/ace/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script src="/static/ace/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/static/ace/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript" src="/static/js/jquery.tips.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
	function save(){
		if($("#ORDERID").val()==""){
			$("#ORDERID").tips({
				side:3,
				msg:'请输入订单ID',
				bg:'#AE81FF',
				time:2
			});
			$("#ORDERID").focus();
			return false;
		}
		if($("#ORDERNAME").val()==""){
			$("#ORDERNAME").tips({
				side:3,
				msg:'请输入订单名称',
				bg:'#AE81FF',
				time:2
			});
			$("#ORDERNAME").focus();
			return false;
		}
		if($("#ORDERVALUE").val()==""){
			$("#ORDERVALUE").tips({
				side:3,
				msg:'请输入订单金额',
				bg:'#AE81FF',
				time:2
			});
			$("#ORDERVALUE").focus();
			return false;
		}
		if($("#ORDERSTATUS").val()==""){
			$("#ORDERSTATUS").tips({
				side:3,
				msg:'请输入订单状态',
				bg:'#AE81FF',
				time:2
			});
			$("#ORDERSTATUS").focus();
			return false;
		}
		if($("#ORDERTIME").val()==""){
			$("#ORDERTIME").tips({
				side:3,
				msg:'请输入订单时间',
				bg:'#AE81FF',
				time:2
			});
			$("#ORDERTIME").focus();
			return false;
		}
		if($("#CREATETIME").val()==""){
			$("#CREATETIME").tips({
				side:3,
				msg:'请输入创建时间',
				bg:'#AE81FF',
				time:2
			});
			$("#CREATETIME").focus();
			return false;
		}
		if($("#INSTALMENT").val()==""){
			$("#INSTALMENT").tips({
				side:3,
				msg:'请输入分期数',
				bg:'#AE81FF',
				time:2
			});
			$("#INSTALMENT").focus();
			return false;
		}
		if($("#INSTALMENTRATE").val()==""){
			$("#INSTALMENTRATE").tips({
				side:3,
				msg:'请输入分期费率',
				bg:'#AE81FF',
				time:2
			});
			$("#INSTALMENTRATE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	//绑定利率
	function showrate(productid,productname){
		var diag = new Dialog();
		diag.Drag=true;
		diag.Title ="查看利率";
		diag.URL = '<%=basePath%>instalment/list.do?TYPEID='+productid+'&TYPENAME='+productname+"&showonly=1";
		diag.Width = 650;
		diag.Height = 455;
		diag.Modal = true;				//有无遮罩窗口
		diag. ShowMaxButton = true;	//最大化按钮
		diag.ShowMinButton = true;		//最小化按钮
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
		};
		diag.show();
	}

	//绑定利率
	function showdialog(productid,productname){
		var diag = new Dialog();
		diag.Drag=true;
		diag.Title ="查看利率";
		diag.URL = '<%=basePath%>instalment/list.do?TYPEID='+productid+'&TYPENAME='+productname+"&showonly=1";
		diag.Width = 650;
		diag.Height = 455;
		diag.Modal = true;				//有无遮罩窗口
		diag. ShowMaxButton = true;	//最大化按钮
		diag.ShowMinButton = true;		//最小化按钮
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
		};
		diag.show();
	}
</script>

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="/static/ace/css/ace.onpage-help.css" />
<script type="text/javascript" src="/plugins/attention/drag/dialog.js"></script>
<link type="text/css" rel="stylesheet" href="/plugins/attention/drag/style.css"  />

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="/static/ace/js/ace/elements.onpage-help.js"></script>
<script src="/static/ace/js/ace/ace.onpage-help.js"></script>

</body>
</html>
