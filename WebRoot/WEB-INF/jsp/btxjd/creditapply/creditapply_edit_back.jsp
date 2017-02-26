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
					
					<form action="creditapply/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CREDITAPPLY_ID" id="CREDITAPPLY_ID" value="${pd.CREDITAPPLY_ID}"/>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">借贷类型   白条  现金贷:</td>
								<td><input type="text" name="APPLYTYPE" id="APPLYTYPE" value="${pd.APPLYTYPE}" maxlength="20" placeholder="这里输入借贷类型   白条  现金贷" title="借贷类型   白条  现金贷" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">借贷额度:</td>
								<td><input type="number" name="APPLYQUOTA" id="APPLYQUOTA" value="${pd.APPLYQUOTA}" maxlength="32" placeholder="这里输入借贷额度" title="借贷额度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">借贷流程状态1申请2授信3审核4放款:</td>
								<td><input type="number" name="APPLYSTATUS" id="APPLYSTATUS" value="${pd.APPLYSTATUS}" maxlength="32" placeholder="这里输入借贷流程状态1申请2授信3审核4放款" title="借贷流程状态1申请2授信3审核4放款" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">申请时间:</td>
								<td><input class="span10 date-picker" name="CREATEDATETIME" id="CREATEDATETIME" value="${pd.CREATEDATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="申请时间" title="申请时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户身份证号:</td>
								<td><input type="text" name="USERIDNUM" id="USERIDNUM" value="${pd.USERIDNUM}" maxlength="255" placeholder="这里输入用户身份证号" title="用户身份证号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">身份证正面照地址:</td>
								<td><input type="text" name="USERIDPHOTO" id="USERIDPHOTO" value="${pd.USERIDPHOTO}" maxlength="255" placeholder="这里输入身份证正面照地址" title="身份证正面照地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">身份证反面照地址:</td>
								<td><input type="text" name="USERIDOBVERSEPHOTO" id="USERIDOBVERSEPHOTO" value="${pd.USERIDOBVERSEPHOTO}" maxlength="255" placeholder="这里输入身份证反面照地址" title="身份证反面照地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">社保账号:</td>
								<td><input type="text" name="SOCIETYNUM" id="SOCIETYNUM" value="${pd.SOCIETYNUM}" maxlength="255" placeholder="这里输入社保账号" title="社保账号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">京东账号:</td>
								<td><input type="text" name="JINGDONGNUM" id="JINGDONGNUM" value="${pd.JINGDONGNUM}" maxlength="255" placeholder="这里输入京东账号" title="京东账号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">淘宝账号:</td>
								<td><input type="text" name="TAOBAONUM" id="TAOBAONUM" value="${pd.TAOBAONUM}" maxlength="255" placeholder="这里输入淘宝账号" title="淘宝账号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">流程处理时间:</td>
								<td><input class="span10 date-picker" name="UPDATETIME" id="UPDATETIME" value="${pd.UPDATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="流程处理时间" title="流程处理时间" style="width:98%;"/></td>
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
			if($("#APPLYTYPE").val()==""){
				$("#APPLYTYPE").tips({
					side:3,
		            msg:'请输入借贷类型   白条  现金贷',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPLYTYPE").focus();
			return false;
			}
			if($("#APPLYQUOTA").val()==""){
				$("#APPLYQUOTA").tips({
					side:3,
		            msg:'请输入借贷额度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPLYQUOTA").focus();
			return false;
			}
			if($("#APPLYSTATUS").val()==""){
				$("#APPLYSTATUS").tips({
					side:3,
		            msg:'请输入借贷流程状态1申请2授信3审核4放款',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPLYSTATUS").focus();
			return false;
			}
			if($("#CREATEDATETIME").val()==""){
				$("#CREATEDATETIME").tips({
					side:3,
		            msg:'请输入申请时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATEDATETIME").focus();
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
			if($("#UPDATETIME").val()==""){
				$("#UPDATETIME").tips({
					side:3,
		            msg:'请输入流程处理时间',
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