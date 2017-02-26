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

					<form action="instalment/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="INSTALMENT_ID" id="INSTALMENT_ID" value="${pd.INSTALMENT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">分期类型ID:</td>
								<td><input type="text" name="TYPEID" id="TYPEID" value="${pd.TYPEID}" maxlength="255"  style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分期类型名称:</td>
								<td><input type="text" name="TYPENAME" id="TYPENAME" value="${pd.TYPENAME}" maxlength="255" placeholder="这里输入分期类型名称" title="分期类型名称" style="width:98%;" readonly /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分期期次:</td>
								<td><input type="number" name="ITEM" id="ITEM" value="${pd.ITEM}" maxlength="32" readonly style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分期费率:</td>
								<td><input type="number" name="FEERATE" id="FEERATE" value="${pd.FEERATE}" maxlength="32" placeholder="这里输入分期费率" title="分期费率" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">手续费:</td>
								<td><input type="number" name="COMMISSION" id="COMMISSION" value="${pd.COMMISSION}" maxlength="32" placeholder="这里输入手续费" title="手续费" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">逾期罚息费率:</td>
								<td><input type="number" name="OVERDUEFEERATE" id="OVERDUEFEERATE" value="${pd.OVERDUEFEERATE}" maxlength="32" placeholder="这里输入逾期罚息费率" title="逾期罚息费率" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">逾期手续费:</td>
								<td><input type="number" name="OVERDUECOMMISSION" id="OVERDUECOMMISSION" value="${pd.OVERDUECOMMISSION}" maxlength="32" placeholder="这里输入逾期手续费" title="逾期手续费" style="width:98%;"/></td>
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
			if($("#TYPEID").val()==""){
				$("#TYPEID").tips({
					side:3,
		            msg:'请输入分期类型ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TYPEID").focus();
			return false;
			}
			if($("#TYPENAME").val()==""){
				$("#TYPENAME").tips({
					side:3,
		            msg:'请输入分期类型名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TYPENAME").focus();
			return false;
			}
			if($("#ITEM").val()==""){
				$("#ITEM").tips({
					side:3,
		            msg:'请输入分期期次',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ITEM").focus();
			return false;
			}
			if($("#FEERATE").val()==""){
				$("#FEERATE").tips({
					side:3,
		            msg:'请输入分期费率',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#FEERATE").focus();
			return false;
			}
			if($("#COMMISSION").val()==""){
				$("#COMMISSION").tips({
					side:3,
		            msg:'请输入手续费',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMMISSION").focus();
			return false;
			}
			if($("#OVERDUEFEERATE").val()==""){
				$("#OVERDUEFEERATE").tips({
					side:3,
		            msg:'请输入逾期罚息费率',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OVERDUEFEERATE").focus();
			return false;
			}
			if($("#OVERDUECOMMISSION").val()==""){
				$("#OVERDUECOMMISSION").tips({
					side:3,
		            msg:'请输入逾期手续费',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OVERDUECOMMISSION").focus();
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