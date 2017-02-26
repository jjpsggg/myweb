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
					
					<form action="creditcheck/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CREDITCHECK_ID" id="CREDITCHECK_ID" value="${pd.CREDITCHECK_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商城用户ID:</td>
								<td><input type="text" name="USERID" id="USERID" value="${pd.USERID}" maxlength="50" placeholder="这里输入商城用户ID" title="商城用户ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户姓名:</td>
								<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="50" placeholder="这里输入用户姓名" title="用户姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户手机号:</td>
								<td><input type="text" name="USERMOBILE" id="USERMOBILE" value="${pd.USERMOBILE}" maxlength="50" placeholder="这里输入用户手机号" title="用户手机号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户城市:</td>
								<td><input type="text" name="USERCITY" id="USERCITY" value="${pd.USERCITY}" maxlength="255" placeholder="这里输入用户城市" title="用户城市" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户居住地址:</td>
								<td><input type="text" name="USERADDRESS" id="USERADDRESS" value="${pd.USERADDRESS}" maxlength="255" placeholder="这里输入用户居住地址" title="用户居住地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">紧急联系人姓名:</td>
								<td><input type="text" name="USERURGENTNAME" id="USERURGENTNAME" value="${pd.USERURGENTNAME}" maxlength="50" placeholder="这里输入紧急联系人姓名" title="紧急联系人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">紧急联系人手机号:</td>
								<td><input type="text" name="USERURGENTMOBILE" id="USERURGENTMOBILE" value="${pd.USERURGENTMOBILE}" maxlength="50" placeholder="这里输入紧急联系人手机号" title="紧急联系人手机号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">婚姻状况:</td>
								<td><input type="text" name="MARITALSTATUS" id="MARITALSTATUS" value="${pd.MARITALSTATUS}" maxlength="8" placeholder="这里输入婚姻状况" title="婚姻状况" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">借贷申请id:</td>
								<td><input type="text" name="CREDITAPPLY_ID" id="CREDITAPPLY_ID" value="${pd.CREDITAPPLY_ID}" maxlength="255" placeholder="这里输入借贷申请id" title="借贷申请id" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">身份证号:</td>
								<td><input type="text" name="IDENTIFYID" id="IDENTIFYID" value="${pd.IDENTIFYID}" maxlength="255" placeholder="这里输入身份证号" title="身份证号" style="width:98%;"/></td>
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
				$("#MARITALSTATUS").tips({
					side:3,
		            msg:'请输入婚姻状况',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MARITALSTATUS").focus();
			return false;
			}
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
			if($("#IDENTIFYID").val()==""){
				$("#IDENTIFYID").tips({
					side:3,
		            msg:'请输入身份证号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#IDENTIFYID").focus();
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