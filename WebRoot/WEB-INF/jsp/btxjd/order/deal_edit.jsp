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
					
					<form action="order/savedeal.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}"/>
						<input type="hidden" name="CONTRACT_ID" id="CONTRACT_ID" value="${pd.CONTRACT_ID}"/>
						<input type="hidden" name="ELECCONTRACT_ID" id="ELECCONTRACT_ID" value="${pd.ELECCONTRACT_ID}"/>
						<input type="hidden" name="PRODUCTTYPE" id="PRODUCTTYPE" value="${pd.PRODUCTTYPE}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">

								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">用户名:</td>
									<td><input type="text" name="USERID" id="USERID" value="${pd.USERID}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">订单号:</td>
									<td><input type="text" name="ORDERID" id="ORDERID" value="${pd.ORDERID}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">订单名称:</td>
									<td><input type="text" name="ORDERNAME" id="ORDERNAME" value="${pd.ORDERNAME}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">订单金额:</td>
									<td><input type="text" name="ORDERVALUE" id="ORDERVALUE" value="${pd.ORDERVALUE}" readonly style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">订单时间:</td>
									<td><input class="span10 date-picker" name="ORDERTIME" id="ORDERTIME" value="${pd.ORDERTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="订单时间" title="订单时间" style="width:98%;"/></td>
								</tr>

								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">分期数:</td>
									<td><input type="number" name="INSTALMENT" id="INSTALMENT" value="${pd.ITEM}" maxlength="32" placeholder="这里输入分期数" title="分期数" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">分期费率:</td>
									<td><input type="number" name="INSTALMENTRATE" id="INSTALMENTRATE" value="${pd.FEERATE}" maxlength="32" placeholder="这里输入分期费率" title="分期费率" style="width:98%;"/></td>
								</tr>

								<tr>
									<td style="text-align: CENTER;padding-top: 13px;" colspan="2">
										最高额度：${maxquota.TOTALQUOTA}  最高白条额度：${maxquota.BTTOTALQUOTA}  最高现金贷额度：${maxquota.XJDTOTALQUOTA}<br>
										已批白条额度：${quota.BTQUOTA} 									已批现金贷额度：${quota.XJDQUOTA}<br>
										可批总额度：${maxquota.TOTALQUOTA-quota.BTQUOTA-quota.XJDQUOTA}  白条可批总额度：${maxquota.BTTOTALQUOTA-quota.BTQUOTA} 现金贷可批总额度：${maxquota.XJDTOTALQUOTA-quota.XJDQUOTA}
										<input type="hidden" name="QUOTA1" id="QUOTA1" value="${maxquota.TOTALQUOTA-quota.BTQUOTA-quota.XJDQUOTA}">
										<c:if test = "(quota.PRODUCTTYPE==1)"><input type="hidden" name="QUOTA2" id="QUOTA2"value="${maxquota.BTTOTALQUOTA-quota.BTQUOTA}" > </c:if>
										<c:if test = "(quota.PRODUCTTYPE==2)"><input type="hidden" name="QUOTA2" id="QUOTA2"value="${maxquota.XJDTOTALQUOTA-quota.XJDQUOTA}" > </c:if>

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
								<td style="width:120px;text-align: right;padding-top: 13px;">放款机构:</td>
								<td>
									<select class="chosen-select form-control" name="ORGANIZATION_ID" id="ORGANIZATION_ID" data-placeholder="请选择放款机构" style="vertical-align:top;" style="width:98%;" >
										<c:forEach items="${listorg}" var="listorg">
											<option value="${listorg.ORGANIZATION_ID }">${listorg.ORGANIZATIONNAME}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">放款操作:</td>
								<td>
									<select name="ORDERSTATUS" id="ORDERSTATUS">
										<option value="3">放款</option>
										<option value="-1">拒绝放款</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:120px;text-align: right;padding-top: 13px;">备注原因：</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="50" placeholder="备注原因" title="备注原因" style="width:98%;"/></td>
							</tr>

							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">操作</a>
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