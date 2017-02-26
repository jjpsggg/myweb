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
					
					<form action="eleccontract/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ELECCONTRACT_ID" id="ELECCONTRACT_ID" value="${pd.ELECCONTRACT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同编号:</td>
								<td><input type="text" name="CONTRACTID" id="CONTRACTID" value="${pd.CONTRACTID}" maxlength="100" placeholder="这里输入合同编号" title="合同编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同名:</td>
								<td><input type="text" name="CONTRACTNAME" id="CONTRACTNAME" value="${pd.CONTRACTNAME}" maxlength="255" placeholder="这里输入合同名" title="合同名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同状态 1企业自动签署 2用户签署 3归档:</td>
								<td><input type="number" name="ORDERSTATUS" id="ORDERSTATUS" value="${pd.ORDERSTATUS}" maxlength="32" placeholder="这里输入合同状态 1企业自动签署 2用户签署 3归档" title="合同状态 1企业自动签署 2用户签署 3归档" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同号:</td>
								<td><input type="text" name="CONTRACT_ID" id="CONTRACT_ID" value="${pd.CONTRACT_ID}" maxlength="255" placeholder="这里输入合同号" title="合同号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户id:</td>
								<td><input type="text" name="SHOPUSER_ID" id="SHOPUSER_ID" value="${pd.SHOPUSER_ID}" maxlength="255" placeholder="这里输入用户id" title="用户id" style="width:98%;"/></td>
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
			if($("#CONTRACTID").val()==""){
				$("#CONTRACTID").tips({
					side:3,
		            msg:'请输入合同编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTRACTID").focus();
			return false;
			}
			if($("#CONTRACTNAME").val()==""){
				$("#CONTRACTNAME").tips({
					side:3,
		            msg:'请输入合同名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTRACTNAME").focus();
			return false;
			}
			if($("#ORDERSTATUS").val()==""){
				$("#ORDERSTATUS").tips({
					side:3,
		            msg:'请输入合同状态 1企业自动签署 2用户签署 3归档',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDERSTATUS").focus();
			return false;
			}
			if($("#CONTRACT_ID").val()==""){
				$("#CONTRACT_ID").tips({
					side:3,
		            msg:'请输入合同号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTRACT_ID").focus();
			return false;
			}
			if($("#SHOPUSER_ID").val()==""){
				$("#SHOPUSER_ID").tips({
					side:3,
		            msg:'请输入用户id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SHOPUSER_ID").focus();
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