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
<div id="navbar" class="navbar navbar-default" style="background-color:white">

	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container" >
		<!-- /section:basics/sidebar.mobile.toggle -->
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<img src = "/static/ace/css/logo.gif"/>

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

									<form  action="save.do" name="Form" id="Form" method="post" class="form-horizontal">
										<input type="hidden" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" />
										<div class="tabbable">
											<ul class="nav nav-tabs padding-16">
												<li class="active">
													<a data-toggle="tab" href="#edit-basic">
														<i class="green ace-icon fa fa-pencil-square-o bigger-125"></i>
														申请信息
													</a>
												</li>
											</ul>

											<div class="tab-content profile-edit-tab-content">
												<div id="edit-basic" class="tab-pane in active">
													<h4 class="header blue bolder smaller">实名信息</h4>
													<input type="hidden" name="USERIDPHOTO" id="USERIDPHOTO" value="${pd.USERIDPHOTO}" />
													<input type="hidden" name="USERIDOBVERSEPHOTO" id="USERIDOBVERSEPHOTO" value="${pd.USERIDOBVERSEPHOTO}" />
													<div class="row">
														<div class="col-xs-12 col-sm-4">
																	<span class="profile-picture">
																		<img  width="200px" height="160px" src="${pd.USERIDPHOTO}" />
																	</span>
														</div>

														<div class="vspace-12-sm"></div>

														<div class="col-xs-12 col-sm-8">
															<div class="form-group">
																<label class="col-sm-4 control-label no-padding-right">身份证号：</label>
																<div class="col-sm-8">
																	<input type="text" name="USERID" id="USERID" value="${pd.USERID}" maxlength="50" placeholder="身份证号" title="身份证号" style="width:98%;" readonly/>
																</div>
															</div>

															<div class="space-4"></div>
															<div class="form-group">
																<label class="col-sm-4 control-label no-padding-right">真实姓名：</label>
																<div class="col-sm-8">
																	<input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="50" placeholder="这里输入用户姓名" title="用户姓名" style="width:98%;" readonly/>
																</div>
															</div>

															<div class="space-4"></div>
															<div class="form-group">
																<label class="col-sm-4 control-label no-padding-right">手机号：</label>
																<div class="col-sm-8">
																	<input type="text" name="USERMOBILE" id="USERMOBILE" value="${pd.USERMOBILE}" maxlength="50" placeholder="这里输入用户手机号" title="用户手机号" style="width:98%;" readonly/>
																</div>
															</div>

															<div class="space-4"></div>
															<div class="form-group">
																<label class="col-sm-4 control-label no-padding-right">EMAIL：</label>
																<div class="col-sm-8">
																	<input type="text" name="USEREMAIL" id="USEREMAIL" value="${pd.USEREMAIL}" maxlength="50" placeholder="这里输入用户EMAIL" title="用户EMAIL" style="width:98%;" readonly/>
																</div>
															</div>
														</div>
													</div>


													<div class="space"></div>
													<h4 class="header blue bolder smaller">资质信息</h4>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">居住城市</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="USERCITY" id="USERCITY" value="${pd.USERCITY}" maxlength="255" placeholder="这里输入用户城市" title="用户城市" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">居住地址</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="USERADDRESS" id="USERADDRESS" value="${pd.USERADDRESS}" maxlength="255" placeholder="这里输入用户居住地址" title="用户居住地址" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">紧急联系人姓名</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="USERURGENTNAME" id="USERURGENTNAME" value="${pd.USERURGENTNAME}" maxlength="50" placeholder="这里输入紧急联系人姓名" title="紧急联系人姓名" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">紧急联系人手机号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="USERURGENTMOBILE" id="USERURGENTMOBILE" value="${pd.USERURGENTMOBILE}" maxlength="50" placeholder="这里输入紧急联系人手机号" title="紧急联系人手机号" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">婚姻状况</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<label class="inline">
																			<input name="MARITALSTATUS" type="radio" class="ace" onclick="setType('是');"/>
																			<span class="lbl middle"> 是</span>
																		</label>
																		<input name="MARITALSTATUS" id="MARITALSTATUS" type="hidden" />
																	&nbsp; &nbsp; &nbsp;
																	<label class="inline">
																		<input name="MARITALSTATUS" id="MARITALSTATUS1" type="radio" class="ace" onclick="setType('否');"/>
																		<span class="lbl middle"> 否</span>
																	</label>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<%--<div class="form-group">--%>
													<%--<label class="col-sm-3 control-label no-padding-right">申请渠道</label>--%>
													<%--<input name="CHANNEL_ID" type="hidden"  name="CHANNEL_ID" />--%>
													<%--<div class="col-sm-9">--%>
													<%--<span class="input-icon input-icon-right">--%>
													<%--<input type="text" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="50" placeholder="这里输入申请渠道" title="申请渠道" style="width:98%;"/>--%>
													<%--</span>--%>
													<%--</div>--%>
													<%--</div>--%>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">社保账号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="SOCIETYNUM" id="SOCIETYNUM" value="${pd.SOCIETYNUM}" maxlength="255" placeholder="这里输入社保账号" title="社保账号" style="width:98%;"/>
																	</span>
														</div>
													</div>
													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">京东账号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="JINGDONGNUM" id="JINGDONGNUM" value="${pd.JINGDONGNUM}" maxlength="255" placeholder="这里输入京东账号" title="京东账号" style="width:98%;"/>
																	</span>
														</div>
													</div>
													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">淘宝账号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="TAOBAONUM" id="TAOBAONUM" value="${pd.TAOBAONUM}" maxlength="255" placeholder="这里输入淘宝账号" title="淘宝账号" style="width:98%;"/>
																	</span>
														</div>
													</div>
													<div class="space-4"></div>



													<div class="space"></div>
													<h4 class="header blue bolder smaller">银行卡信息</h4>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">选择银行</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="BANKNAME" id="BANKNAME" value="${pd.BANKNAME}" maxlength="255" placeholder="这里输入银行名称" title="银行名称" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">银行卡号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																		<input type="text" name="BANKACCOUNT" id="BANKACCOUNT" value="${pd.BANKACCOUNT}" maxlength="255" placeholder="这里输入银行卡号" title="银行卡号" style="width:98%;"/>
																	</span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">预留手机号</label>

														<div class="col-sm-9">
																	<span class="input-icon input-icon-right">
																	<input type="text" name="MOBILENUM" id="MOBILENUM" value="${pd.MOBILENUM}" maxlength="255" placeholder="这里输入预留手机号" title="预留手机号" style="width:98%;"/></span>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right">手机验证码</label>

														<div class="col-sm-3">
															<input type="text" name="VERIFYNUM" id="VERIFYNUM" value="${pd.TAOBAONUM}" maxlength="255" placeholder="这里输入验证号" title="验证码" style="width:98%;"/>
														</div>
														<div class="col-sm-6">
															<button type="button" class="width-35 btn btn-sm btn-primary pull-left">发送验证码</button>
														</div>
													</div>

													<div class="space-4"></div>

													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right"></label>

														<div class="col-sm-9">
															<div style="float:left"><input type="checkbox" name="checbox1" id="checbox1"/></div>
															<div style="float:left">我已经阅读协议</div>
															<div style="float:left"><a href="#" class="user-signup-link">《一言白条服务协议》</a></div>
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="clearfix form-actions">
											<div class="col-md-offset-4 col-md-9">
												<button class="btn btn-info" type="button" onclick="save();">
													<i class="ace-icon fa fa-check bigger-110"></i>
													提交申请
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
							<span class="blue">中联恒天控股有限公司 liangziCredit Co..Ltd Copyright©2017版权所有 京ICP备15035725号</span>
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
	jQuery(function($) {
//				ace.settings.main_container_fixed(null, true);//@ ace-extra.js
	});
	function setType(type){
		$("#MARITALSTATUS").val(type);
	}
	function save(){
		if($("#USERID").val()==""){
			$("#USERID").tips({
				side:3,
				msg:'请输入商城用户ID',
				bg:'#AE81FF',
				time:2
			});
			$("#USERID").focus();
			return false;
		}
		if($("#USERNAME").val()==""){
			$("#USERNAME").tips({
				side:3,
				msg:'请输入用户姓名',
				bg:'#AE81FF',
				time:2
			});
			$("#USERNAME").focus();
			return false;
		}
		if($("#USERMOBILE").val()==""){
			$("#USERMOBILE").tips({
				side:3,
				msg:'请输入用户手机号',
				bg:'#AE81FF',
				time:2
			});
			$("#USERMOBILE").focus();
			return false;
		}
		if($("#USERCITY").val()==""){
			$("#USERCITY").tips({
				side:3,
				msg:'请输入用户城市',
				bg:'#AE81FF',
				time:2
			});
			$("#USERCITY").focus();
			return false;
		}
		if($("#USERADDRESS").val()==""){
			$("#USERADDRESS").tips({
				side:3,
				msg:'请输入用户居住地址',
				bg:'#AE81FF',
				time:2
			});
			$("#USERADDRESS").focus();
			return false;
		}
		if($("#USERURGENTNAME").val()==""){
			$("#USERURGENTNAME").tips({
				side:3,
				msg:'请输入紧急联系人姓名',
				bg:'#AE81FF',
				time:2
			});
			$("#USERURGENTNAME").focus();
			return false;
		}
		if($("#USERURGENTMOBILE").val()==""){
			$("#USERURGENTMOBILE").tips({
				side:3,
				msg:'请输入紧急联系人手机号',
				bg:'#AE81FF',
				time:2
			});
			$("#USERURGENTMOBILE").focus();
			return false;
		}
		if($("#MARITALSTATUS").val()==""){
			$("#MARITALSTATUS1").tips({
				side:3,
				msg:'请输入婚姻状况',
				bg:'#AE81FF',
				time:2
			});
			$("#MARITALSTATUS1").focus();
			return false;
		}

		if($("#USERIDNUM").val()==""){
			$("#USERIDNUM").tips({
				side:3,
				msg:'请输入用户身份证号',
				bg:'#AE81FF',
				time:2
			});
			$("#USERIDNUM").focus();
			return false;
		}
		if($("#USERIDPHOTO").val()==""){
			$("#USERIDPHOTO").tips({
				side:3,
				msg:'请输入身份证正面照地址',
				bg:'#AE81FF',
				time:2
			});
			$("#USERIDPHOTO").focus();
			return false;
		}
		if($("#USERIDOBVERSEPHOTO").val()==""){
			$("#USERIDOBVERSEPHOTO").tips({
				side:3,
				msg:'请输入身份证反面照地址',
				bg:'#AE81FF',
				time:2
			});
			$("#USERIDOBVERSEPHOTO").focus();
			return false;
		}
		if($("#SOCIETYNUM").val()==""){
			$("#SOCIETYNUM").tips({
				side:3,
				msg:'请输入社保账号',
				bg:'#AE81FF',
				time:2
			});
			$("#SOCIETYNUM").focus();
			return false;
		}
		if($("#JINGDONGNUM").val()==""){
			$("#JINGDONGNUM").tips({
				side:3,
				msg:'请输入京东账号',
				bg:'#AE81FF',
				time:2
			});
			$("#JINGDONGNUM").focus();
			return false;
		}
		if($("#TAOBAONUM").val()==""){
			$("#TAOBAONUM").tips({
				side:3,
				msg:'请输入淘宝账号',
				bg:'#AE81FF',
				time:2
			});
			$("#TAOBAONUM").focus();
			return false;
		}
		if($("#BANKNAME").val()==""){
			$("#BANKNAME").tips({
				side:3,
				msg:'请输入银行名称',
				bg:'#AE81FF',
				time:2
			});
			$("#BANKNAME").focus();
			return false;
		}

		if($("#BANKACCOUNT").val()==""){
			$("#BANKACCOUNT").tips({
				side:3,
				msg:'请输入银行卡号',
				bg:'#AE81FF',
				time:2
			});
			$("#BANKACCOUNT").focus();
			return false;
		}

		if($("#MOBILENUM").val()==""){
			$("#MOBILENUM").tips({
				side:3,
				msg:'请输入预留手机号',
				bg:'#AE81FF',
				time:2
			});
			$("#MOBILENUM").focus();
			return false;
		}

		if($("#VERIFYNUM").val()==""){
			$("#VERIFYNUM").tips({
				side:3,
				msg:'请输入手机验证码',
				bg:'#AE81FF',
				time:2
			});
			$("#VERIFYNUM").focus();
			return false;
		}
		if(!$('#checbox1').is(':checked')){
			$("#checbox1").tips({
				side:3,
				msg:'请先确认阅读协议',
				bg:'#AE81FF',
				time:2
			});
			$("#checbox1").focus();
			return false;
		}

		$("#Form").submit();
		$("#user-profile-3").hide();
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
