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
					
					<form action="shopuser/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="SHOPUSER_ID" id="SHOPUSER_ID" value="${pd.SHOPUSER_ID}"/>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">白条额度:</td>
								<td><input type="number" name="BTQUOTA" id="BTQUOTA" value="${pd.BTQUOTA}" maxlength="32" placeholder="这里输入白条额度" title="白条额度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">白条消费:</td>
								<td><input type="number" name="BTUSEDQUOTA" id="BTUSEDQUOTA" value="${pd.BTUSEDQUOTA}" maxlength="32" placeholder="这里输入白条消费" title="白条消费" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">现金贷额度:</td>
								<td><input type="number" name="XJDQUOTA" id="XJDQUOTA" value="${pd.XJDQUOTA}" maxlength="32" placeholder="这里输入现金贷额度" title="现金贷额度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">现金贷欠款:</td>
								<td><input type="number" name="XJDOVERDRAFT" id="XJDOVERDRAFT" value="${pd.XJDOVERDRAFT}" maxlength="32" placeholder="这里输入现金贷欠款" title="现金贷欠款" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">白条欠款:</td>
								<td><input type="number" name="BTOVERDRAFT" id="BTOVERDRAFT" value="${pd.BTOVERDRAFT}" maxlength="32" placeholder="这里输入白条欠款" title="白条欠款" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input class="span10 date-picker" name="CREATEDATE" id="CREATEDATE" value="${pd.CREATEDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="创建时间" title="创建时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">更新时间:</td>
								<td><input class="span10 date-picker" name="UPDATETIME" id="UPDATETIME" value="${pd.UPDATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="更新时间" title="更新时间" style="width:98%;"/></td>
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
			if($("#BTQUOTA").val()==""){
				$("#BTQUOTA").tips({
					side:3,
		            msg:'请输入白条额度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BTQUOTA").focus();
			return false;
			}
			if($("#BTUSEDQUOTA").val()==""){
				$("#BTUSEDQUOTA").tips({
					side:3,
		            msg:'请输入白条消费',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BTUSEDQUOTA").focus();
			return false;
			}
			if($("#XJDQUOTA").val()==""){
				$("#XJDQUOTA").tips({
					side:3,
		            msg:'请输入现金贷额度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#XJDQUOTA").focus();
			return false;
			}
			if($("#XJDOVERDRAFT").val()==""){
				$("#XJDOVERDRAFT").tips({
					side:3,
		            msg:'请输入现金贷欠款',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#XJDOVERDRAFT").focus();
			return false;
			}
			if($("#BTOVERDRAFT").val()==""){
				$("#BTOVERDRAFT").tips({
					side:3,
		            msg:'请输入白条欠款',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BTOVERDRAFT").focus();
			return false;
			}
			if($("#CREATEDATE").val()==""){
				$("#CREATEDATE").tips({
					side:3,
		            msg:'请输入创建时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATEDATE").focus();
			return false;
			}
			if($("#UPDATETIME").val()==""){
				$("#UPDATETIME").tips({
					side:3,
		            msg:'请输入更新时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#UPDATETIME").focus();
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