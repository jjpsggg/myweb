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
					
					<form action="credit/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CREDIT_ID" id="CREDIT_ID" value="${pd.CREDIT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单号:</td>
								<td><input type="text" name="ORDERID" id="ORDERID" value="${pd.ORDERID}" maxlength="255" placeholder="这里输入订单号" title="订单号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">还款期次:</td>
								<td><input type="number" name="INSTALMENTORDER" id="INSTALMENTORDER" value="${pd.INSTALMENTORDER}" maxlength="32" placeholder="这里输入还款期次" title="还款期次" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">应还本金:</td>
								<td><input type="number" name="INSTALMENTFEE" id="INSTALMENTFEE" value="${pd.INSTALMENTFEE}" maxlength="32" placeholder="这里输入应还本金" title="应还本金" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">已经还款金额:</td>
								<td><input type="number" name="PAYEDFEE" id="PAYEDFEE" value="${pd.PAYEDFEE}" maxlength="32" placeholder="这里输入已经还款金额" title="已经还款金额" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">利息:</td>
								<td><input type="number" name="INTEREST" id="INTEREST" value="${pd.INTEREST}" maxlength="32" placeholder="这里输入利息" title="利息" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">借款开始时间:</td>
								<td><input class="span10 date-picker" name="STARTTIME" id="STARTTIME" value="${pd.STARTTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="借款开始时间" title="借款开始时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">应还款时间:</td>
								<td><input class="span10 date-picker" name="ENDTIME" id="ENDTIME" value="${pd.ENDTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="应还款时间" title="应还款时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">实际还款时间:</td>
								<td><input class="span10 date-picker" name="PAYEDTIME" id="PAYEDTIME" value="${pd.PAYEDTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="实际还款时间" title="实际还款时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">还款方式:</td>
								<td><input type="text" name="PAYEDTYPE" id="PAYEDTYPE" value="${pd.PAYEDTYPE}" maxlength="255" placeholder="这里输入还款方式" title="还款方式" style="width:98%;"/></td>
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
		            msg:'请输入订单号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDERID").focus();
			return false;
			}
			if($("#INSTALMENTORDER").val()==""){
				$("#INSTALMENTORDER").tips({
					side:3,
		            msg:'请输入还款期次',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INSTALMENTORDER").focus();
			return false;
			}
			if($("#INSTALMENTFEE").val()==""){
				$("#INSTALMENTFEE").tips({
					side:3,
		            msg:'请输入应还本金',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INSTALMENTFEE").focus();
			return false;
			}
			if($("#PAYEDFEE").val()==""){
				$("#PAYEDFEE").tips({
					side:3,
		            msg:'请输入已经还款金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAYEDFEE").focus();
			return false;
			}
			if($("#INTEREST").val()==""){
				$("#INTEREST").tips({
					side:3,
		            msg:'请输入利息',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INTEREST").focus();
			return false;
			}
			if($("#STARTTIME").val()==""){
				$("#STARTTIME").tips({
					side:3,
		            msg:'请输入借款开始时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STARTTIME").focus();
			return false;
			}
			if($("#ENDTIME").val()==""){
				$("#ENDTIME").tips({
					side:3,
		            msg:'请输入应还款时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENDTIME").focus();
			return false;
			}
			if($("#PAYEDTIME").val()==""){
				$("#PAYEDTIME").tips({
					side:3,
		            msg:'请输入实际还款时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAYEDTIME").focus();
			return false;
			}
			if($("#PAYEDTYPE").val()==""){
				$("#PAYEDTYPE").tips({
					side:3,
		            msg:'请输入还款方式',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAYEDTYPE").focus();
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