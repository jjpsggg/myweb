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
					
					<form action="creditapply/edit.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CREDITAPPLY_ID" id="CREDITAPPLY_ID" value="${pd.CREDITAPPLY_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<%--<tr>--%>
								<%--<td style="width:120px;text-align: center;padding-top: 13px;" colspan="2">初审</td>--%>
							<%--</tr>--%>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">用户名:</td>
									<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">申请时间:</td>
									<td><input type="text" name="USERMOBILE" id="USERMOBILE" value="${pd.CREATETIME}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">所属渠道:</td>
									<td><input type="text" name="USERCITY" id="USERCITY" value="${pd.CHANNELNAME}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">申请产品:</td>
									<td><input type="text" name="PRODUCTNAME" id="PRODUCTNAME" value="${pd.PRODUCTNAME}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">申请类型:</td>
									<td>
									<c:if test="${pd.APPLYTYPE == 1}"> <span class="label label-sm label-warning"> 开通申请</span></c:if>
										<c:if test="${pd.APPLYTYPE == 2}"> <span class="label label-sm label-success"> 提额申请</span></c:if>
									 </td>
								</tr>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">审核状态:</td>
								<td style="padding-top: 13px;">

									<c:if test="${pd.APPLYSTATUS == 0}">	初审中</c:if>
									<c:if test="${pd.APPLYSTATUS == -1}">初审不通过</c:if>
									<c:if test="${pd.APPLYSTATUS == 1}">	复审中</c:if>
									<c:if test="${pd.APPLYSTATUS == 2}">	复审通过</c:if>
									<c:if test="${pd.APPLYSTATUS == -2}">复审不通过</c:if>

								</td>
							</tr>

							<tr>
								<td style="text-align: CENTER;padding-top: 13px;" colspan="2">
									最高额度：${maxquota.TOTALQUOTA}  最高白条额度：${maxquota.BTTOTALQUOTA}  最高现金贷额度：${maxquota.XJDTOTALQUOTA}<br>
									已批白条额度：${pd.BTQUOTA} 									已批现金贷额度：${pd.XJDQUOTA}<br>
									可批总额度：${maxquota.TOTALQUOTA-pd.BTQUOTA-pd.XJDQUOTA}  白条可批总额度：${maxquota.BTTOTALQUOTA-pd.BTQUOTA} 现金贷可批总额度：${maxquota.XJDTOTALQUOTA-pd.XJDQUOTA}
									<input type="hidden" name="QUOTA1" id="QUOTA1" value="${maxquota.TOTALQUOTA-pd.BTQUOTA-pd.XJDQUOTA}">
									<c:if test = "(pd.PRODUCTTYPE==1)"><input type="hidden" name="QUOTA2" id="QUOTA2"value="${maxquota.BTTOTALQUOTA-pd.BTQUOTA}" > </c:if>
									<c:if test = "(pd.PRODUCTTYPE==2)"><input type="hidden" name="QUOTA2" id="QUOTA2"value="${maxquota.XJDTOTALQUOTA-pd.XJDQUOTA}" > </c:if>

								</td>

							</tr>
							<%--<tr>--%>
								<%--<td style="width:120px;text-align: right;padding-top: 13px;">当前可批额度:</td>--%>
								<%--<td><input type="text" name="QUOTA1" id="QUOTA1"--%>
								<%--<c:if test = "(pd.PRODUCTTYPE==1)"> value="${maxquota.BTTOTALQUOTA-pd.BTQUOTA}" </c:if>--%>
								<%--<c:if test = "(pd.PRODUCTTYPE==2)"> value="${maxquota.XJDTOTALQUOTA-pd.XJDQUOTA}" </c:if>--%>
										   <%--maxlength="50" placeholder="当前可批额度" title="当前可批额度" style="width:98%;"/></td>--%>
							<%--</tr>--%>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">授信额度:</td>
								<td>
									<select class="chosen-select form-control" name="APPLYQUOTA" id="APPLYQUOTA" data-placeholder="请选择额度" style="vertical-align:top;" style="width:98%;" >
										<c:forEach items="${quotalist}" var="quotalist">
											<option value="${quotalist.QUOTAVALUE }">${quotalist.QUOTAVALUE}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">初审结果:</td>
								<td>
									<select name="APPLYSTATUS" id="APPLYSTATUS">
										<option value="1">通过</option>
										<option value="-1">不通过</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">备注原因：</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="50" placeholder="备注原因" title="备注原因" style="width:98%;"/></td>
							</tr>

							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">审核</a>
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

			if($("#APPLYQUOTA").val()>$("#QUOTA1").val()){
				$("#APPLYQUOTA").tips({
					side:3,
					msg:'借贷额度超过可用总额度',
					bg:'#AE81FF',
					time:2
				});
				$("#APPLYQUOTA").focus();
				return false;
			}
			if($("#APPLYQUOTA").val()>$("#QUOTA2").val()){
				$("#APPLYQUOTA").tips({
					side:3,
					msg:'借贷额度超过额度限制',
					bg:'#AE81FF',
					time:2
				});
				$("#APPLYQUOTA").focus();
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