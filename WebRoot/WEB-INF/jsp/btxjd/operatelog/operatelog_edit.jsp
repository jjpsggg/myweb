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
					
					<form action="operatelog/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="OPERATELOG_ID" id="OPERATELOG_ID" value="${pd.OPERATELOG_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户ID:</td>
								<td><input type="text" name="USERID" id="USERID" value="${pd.USERID}" maxlength="255" placeholder="这里输入用户ID" title="用户ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户名:</td>
								<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="255" placeholder="这里输入用户名" title="用户名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">业务类型 白条  现金贷:</td>
								<td><input type="text" name="BUSSTYPE" id="BUSSTYPE" value="${pd.BUSSTYPE}" maxlength="255" placeholder="这里输入业务类型 白条  现金贷" title="业务类型 白条  现金贷" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">操作类型 授信 审核:</td>
								<td><input type="text" name="OPERATETYPE" id="OPERATETYPE" value="${pd.OPERATETYPE}" maxlength="255" placeholder="这里输入操作类型 授信 审核" title="操作类型 授信 审核" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">操作结果内容:</td>
								<td><input type="text" name="OPERATECONTENT" id="OPERATECONTENT" value="${pd.OPERATECONTENT}" maxlength="255" placeholder="这里输入操作结果内容" title="操作结果内容" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">操作时间:</td>
								<td><input class="span10 date-picker" name="OPERATETIME" id="OPERATETIME" value="${pd.OPERATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="操作时间" title="操作时间" style="width:98%;"/></td>
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
			if($("#USERID").val()==""){
				$("#USERID").tips({
					side:3,
		            msg:'请输入用户ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERID").focus();
			return false;
			}
			if($("#USERNAME").val()==""){
				$("#USERNAME").tips({
					side:3,
		            msg:'请输入用户名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERNAME").focus();
			return false;
			}
			if($("#BUSSTYPE").val()==""){
				$("#BUSSTYPE").tips({
					side:3,
		            msg:'请输入业务类型 白条  现金贷',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BUSSTYPE").focus();
			return false;
			}
			if($("#OPERATETYPE").val()==""){
				$("#OPERATETYPE").tips({
					side:3,
		            msg:'请输入操作类型 授信 审核',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OPERATETYPE").focus();
			return false;
			}
			if($("#OPERATECONTENT").val()==""){
				$("#OPERATECONTENT").tips({
					side:3,
		            msg:'请输入操作结果内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OPERATECONTENT").focus();
			return false;
			}
			if($("#REMARK").val()==""){
				$("#REMARK").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARK").focus();
			return false;
			}
			if($("#OPERATETIME").val()==""){
				$("#OPERATETIME").tips({
					side:3,
		            msg:'请输入操作时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OPERATETIME").focus();
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