<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
							<thead>
								<tr>
									<th class="center" colspan="6">${pd.TYPENAME}</th>
								</tr>
								<tr>
									<th class="center">期次</th>
									<th class="center">费率</th>
									<th class="center">手续费</th>
									<th class="center">逾期罚息费率</th>
									<th class="center">逾期手续费</th>
									<th class="center">操作</th>
								</tr>
							</thead>

							<tbody>
							<!-- 开始循环 -->
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>${var.ITEM}</td>
											<td class='center'>${var.FEERATE} %</td>
											<td class='center'>${var.COMMISSION} %</td>
											<td class='center'>${var.OVERDUEFEERATE} %</td>
											<td class='center'>${var.OVERDUECOMMISSION} %</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.INSTALMENT_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="del('${var.INSTALMENT_ID}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>

														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.INSTALMENT_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="del('${var.INSTALMENT_ID}');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
											</td>
										</tr>

									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>


						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<form action="instalment/save.do" name="Form" id="Form" method="post">
			<input type="hidden" name="INSTALMENT_ID" id="INSTALMENT_ID" value="${pd.INSTALMENT_ID}"/>
			<input type="hidden" name="TYPEID" id="TYPEID" value="${pd.TYPEID}" maxlength="255" />
			<input type="hidden" name="TYPENAME" id="TYPENAME" value="${pd.TYPENAME}" maxlength="255"/>
			<div id="zhongxin" style="padding-top: 13px;">
				<table id="table_report" class="table table-striped table-bordered table-hover">

					<tr>
						<td style="width:120px;text-align: right;padding-top: 13px;">期次:</td>

						<td>
							<c:if test="${listTerm.size()>0}">
							<div class="pull-left" style="width:38%"><select class="chosen-select form-control" name="ITEM" id="ITEM" data-placeholder="请选择期次" style="vertical-align:top;" >
								<c:forEach items="${listTerm}" var="listTerm">
									<option value="${listTerm.ITEM }" <c:if test="${listTerm.ITEM == pd.ITEM }">selected</c:if>>${listTerm.ITEM}</option>
								</c:forEach>
							</select>

								</div>

							</c:if>
							<c:if test="${listTerm.size()<1}">
							当前渠道产品无对应放款机构，请先设置放款机构！
							</c:if>
						</td>

					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 13px;">分期费率:</td>
						<td><input type="number" name="FEERATE" id="FEERATE" value="${pd.FEERATE}" maxlength="32" placeholder="这里输入分期费率" title="分期费率" style="width:38%;"/>%</td>
					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 13px;">手续费:</td>
						<td><input type="number" name="COMMISSION" id="COMMISSION" value="${pd.COMMISSION}" maxlength="32" placeholder="这里输入手续费" title="手续费" style="width:38%;float:left"/> <div  style="float:left;padding-top: 8px;" >  % </div>   <div  style="float:left;padding-top: 8px;" > 当前分期下最高放款利率：<span id="maxRate">-</span> %</div></td>
					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 13px;">逾期罚息费率:</td>
						<td><input type="number" name="OVERDUEFEERATE" id="OVERDUEFEERATE" value="${pd.OVERDUEFEERATE}" maxlength="32" placeholder="这里输入逾期罚息费率" title="逾期罚息费率" style="width:38%;"/>%</td>
					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 13px;">逾期手续费:</td>
						<td><input type="number" name="OVERDUECOMMISSION" id="OVERDUECOMMISSION" value="${pd.OVERDUECOMMISSION}" maxlength="32" placeholder="这里输入逾期手续费" title="逾期手续费" style="width:38%;"/>%</td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="10">
							<a class="btn btn-mini btn-primary" onclick="save();">新增</a>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		</form>

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(function(){
			getMaxRate($("#TYPEID").val(),$("#ITEM").val());
			$("#ITEM").change(function(){
				getMaxRate($("#TYPEID").val(),$("#ITEM").val());
			});
		});
		function getMaxRate(str,item){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>instalment/listMax.do?tm='+new Date().getTime(),
				data: {TYPEID:str,ITEM:item},
				dataType:'json',
				cache: false,
				success: function(data){
					$("#maxRate").text(data.result.COMMISSION);
				}
			});
		}
		function nextPage(){
			location.href=location.href;
		}
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {

			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});

			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true});
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}


			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});

		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>instalment/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 <%--nextPage(${page.currentPage});--%>
					 }
				}
				diag.close();
			 };
			 diag.show();
		}

		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>instalment/delete.do?INSTALMENT_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}

		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>instalment/goEdit.do?INSTALMENT_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}

		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons:
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>instalment/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											<%--nextPage(${page.currentPage});--%>
									 });
								}
							});
						}
					}
				}
			});
		};

		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>instalment/excel.do';
		}

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

			var result = true;
			$("#simple-table tr").find("td:eq(0)").each(function(){
				if($("#ITEM").val()==$(this).text()){
					result = false;
				}
			});
			if(!result){
				$(".chosen-container").tips({
					side:3,
					msg:'分期期次已经存在，不能添加',
					bg:'#AE81FF',
					time:2
				});
				$("#ITEM").focus();
				return  result;
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
//			$("#Form").submit();
			top.jzts();
			var url = "instalment/save.do";
			$.ajax({
				type: "POST",
				url: url,
				data: $("#Form").serialize(), // serializes the form's elements.
				success: function(data)
				{
					nextPage(${page.currentPage});
				}
			});
		}
	</script>


</body>
</html>