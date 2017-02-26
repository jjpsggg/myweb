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

						<form action="btproduct/${msg }.do" name="Form" id="Form" method="post">
							<input type="hidden" name="BTPRODUCT_ID" id="BTPRODUCT_ID" value="${pd.BTPRODUCT_ID}"/>
							<div id="zhongxin" style="padding-top: 13px;">
								<table id="table_report" class="table table-striped table-bordered table-hover">
									<tr>
										<td style="width:100px;text-align: right;padding-top: 13px;">白条产品名称:</td>
										<td><input type="text" name="PRODUCTNAME" id="PRODUCTNAME" value="${pd.PRODUCTNAME}" maxlength="255" placeholder="这里输入白条产品名称" title="白条产品名称" style="width:98%;"/>
											<input type="hidden" name="PRODUCTTYPE" id="PRODUCTTYPE" value="${pd.PRODUCTTYPE}"/></td>
									</tr>

									<tr>
										<td style="width:100px;text-align: right;padding-top: 13px;">产品状态:</td>

										<td><select class="chosen-select form-control" name="STATUS" id="STATUS" data-placeholder="请选择产品状态" style="vertical-align:top;"  title="产品状态" style="width:98%;" >
											<option value="有效" <c:if test="${'有效' == pd.STATUS || pd.STATUS == ''}">selected</c:if>>有效</option>
											<option value="无效" <c:if test="${'无效' == pd.STATUS }">selected</c:if>>无效</option>
										</select></td>
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
		if($("#PRODUCTNAME").val()==""){
			$("#PRODUCTNAME").tips({
				side:3,
				msg:'请输入白条产品名称',
				bg:'#AE81FF',
				time:2
			});
			$("#PRODUCTNAME").focus();
			return false;
		}

		if($("#STATUS").val()==""){
			$("#STATUS").tips({
				side:3,
				msg:'请输入产品状态',
				bg:'#AE81FF',
				time:2
			});
			$("#STATUS").focus();
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