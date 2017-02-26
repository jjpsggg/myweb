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
					
					<form action="electroniccontract/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ELECTRONICCONTRACT_ID" id="ELECTRONICCONTRACT_ID" value="${pd.ELECTRONICCONTRACT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">借贷申请id:</td>
								<td><input type="text" name="CREDITAPPLY_ID" id="CREDITAPPLY_ID" value="${pd.CREDITAPPLY_ID}" maxlength="255" placeholder="这里输入借贷申请id" title="借贷申请id" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同状态:</td>
								<td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="255" placeholder="这里输入合同状态" title="合同状态" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合同创建时间:</td>
								<td><input class="span10 date-picker" name="CREATETIME" id="CREATETIME" value="${pd.CREATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="合同创建时间" title="合同创建时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核时间:</td>
								<td><input type="text" name="CHECKTIME" id="CHECKTIME" value="${pd.CHECKTIME}" maxlength="255" placeholder="这里输入审核时间" title="审核时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">原始合同:</td>
								<td><input type="text" name="ORIFILE" id="ORIFILE" value="${pd.ORIFILE}" maxlength="255" placeholder="这里输入原始合同" title="原始合同" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户签完合同:</td>
								<td><input type="text" name="AGREENFILE" id="AGREENFILE" value="${pd.AGREENFILE}" maxlength="255" placeholder="这里输入用户签完合同" title="用户签完合同" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户签署合同时间:</td>
								<td><input class="span10 date-picker" name="AGREENTIME" id="AGREENTIME" value="${pd.AGREENTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="用户签署合同时间" title="用户签署合同时间" style="width:98%;"/></td>
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
			if($("#CREDITAPPLY_ID").val()==""){
				$("#CREDITAPPLY_ID").tips({
					side:3,
		            msg:'请输入借贷申请id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREDITAPPLY_ID").focus();
			return false;
			}
			if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入合同状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
			return false;
			}
			if($("#CREATETIME").val()==""){
				$("#CREATETIME").tips({
					side:3,
		            msg:'请输入合同创建时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATETIME").focus();
			return false;
			}
			if($("#CHECKTIME").val()==""){
				$("#CHECKTIME").tips({
					side:3,
		            msg:'请输入审核时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKTIME").focus();
			return false;
			}
			if($("#ORIFILE").val()==""){
				$("#ORIFILE").tips({
					side:3,
		            msg:'请输入原始合同',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORIFILE").focus();
			return false;
			}
			if($("#AGREENFILE").val()==""){
				$("#AGREENFILE").tips({
					side:3,
		            msg:'请输入用户签完合同',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#AGREENFILE").focus();
			return false;
			}
			if($("#AGREENTIME").val()==""){
				$("#AGREENTIME").tips({
					side:3,
		            msg:'请输入用户签署合同时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#AGREENTIME").focus();
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