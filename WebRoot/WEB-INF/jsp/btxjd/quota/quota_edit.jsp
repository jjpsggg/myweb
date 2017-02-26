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
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="quota/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="QUOTA_ID" id="QUOTA_ID" value="${pd.QUOTA_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">渠道名称:</td>
								<td>
								<select class="chosen-select form-control" name="CHANNEL_ID" id="CHANNEL_ID" data-placeholder="请选择渠道" style="vertical-align:top;" style="width:98%;"
								<c:if test="${msg.equals('edit')}">disabled</c:if>
								>
									<c:forEach items="${product}" var="product">
										<option pid="${product.PRODUCT_ID }" pname="${product.PRODUCTNAME }" cname="${product.CHANNELNAME }" value="${product.CHANNEL_ID }" <c:if test="${product.CHANNEL_ID == pd.CHANNEL_ID }">selected</c:if>>${product.CHANNELNAME }-> ${product.PRODUCTNAME }</option>
									</c:forEach>
									<input type="hidden" name="PRODUCT_ID" id="PRODUCT_ID" value="${pd.PRODUCT_ID}" />
									<input type="hidden" name="PRODUCTNAME" id="PRODUCTNAME" value="${pd.PRODUCTNAME}" />
									<input type="hidden" name="CHANNELNAME" id="CHANNELNAME" value="${pd.CHANNELNAME}" />
								</select>
							</tr>

							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">额度值:</td>
								<td><input type="number" name="QUOTAVALUE" id="QUOTAVALUE" value="${pd.QUOTAVALUE}" maxlength="32" placeholder="这里输入额度值" title="额度值" style="width:98%;"/></td>
							</tr>

							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#CHANNELNAME").val($("#CHANNEL_ID option:first").attr("cname"));
				$("#PRODUCTNAME").val($("#CHANNEL_ID option:first").attr("pname"));
				$("#PRODUCT_ID").val($("#CHANNEL_ID option:first").attr("pid"));
				$("#CHANNEL_ID").change(function(){
					$("#CHANNELNAME").val($("#CHANNEL_ID option:selected").attr("cname"));
					$("#PRODUCTNAME").val($("#CHANNEL_ID option:selected").attr("pname"));
					$("#PRODUCT_ID").val($("#CHANNEL_ID option:selected").attr("pid"));
				});
			});
		$(top.hangge());
		//保存
		function save(){
			if($("#CHANNELNAME").val()==""){
				$("#CHANNELNAME").tips({
					side:3,
		            msg:'请输入渠道名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHANNELNAME").focus();
			return false;
			}
			if($("#CHANNEL_ID").val()==""){
				$("#CHANNEL_ID").tips({
					side:3,
		            msg:'请输入渠道id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHANNEL_ID").focus();
			return false;
			}
			if($("#QUOTAVALUE").val()==""){
				$("#QUOTAVALUE").tips({
					side:3,
		            msg:'请输入额度值',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#QUOTAVALUE").focus();
			return false;
			}
			if($("#PRODUCT_ID").val()==""){
				$("#PRODUCT_ID").tips({
					side:3,
		            msg:'请输入产品id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_ID").focus();
			return false;
			}
			if($("#PRODUCTNAME").val()==""){
				$("#PRODUCTNAME").tips({
					side:3,
		            msg:'请输入产品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCTNAME").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>