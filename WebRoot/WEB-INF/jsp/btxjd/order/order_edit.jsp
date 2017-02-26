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
					
					<form action="order/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
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
								<td style="width:75px;text-align: right;padding-top: 13px;">订单状态:</td>
								<td><input type="number" name="ORDERSTATUS" id="ORDERSTATUS" value="${pd.ORDERSTATUS}" maxlength="32" placeholder="这里输入订单状态" title="订单状态" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单时间:</td>
								<td><input class="span10 date-picker" name="ORDERTIME" id="ORDERTIME" value="${pd.ORDERTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="订单时间" title="订单时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input class="span10 date-picker" name="CREATETIME" id="CREATETIME" value="${pd.CREATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="创建时间" title="创建时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分期数:</td>
								<td><input type="number" name="INSTALMENT" id="INSTALMENT" value="${pd.INSTALMENT}" maxlength="32" placeholder="这里输入分期数" title="分期数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分期费率:</td>
								<td><input type="number" name="INSTALMENTRATE" id="INSTALMENTRATE" value="${pd.INSTALMENTRATE}" maxlength="32" placeholder="这里输入分期费率" title="分期费率" style="width:98%;"/></td>
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
		$(top.hangge());
		//保存
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
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>