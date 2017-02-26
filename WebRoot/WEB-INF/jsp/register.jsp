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
					<div class="hr hr-18 dotted hr-double"></div>
					<div class="row">
						<div class="col-xs-12">

<!-- 存放生成的hmlt开头  -->
<form class="form-horizontal" role="form">
<div class="col-md-12">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">纯文本：</label>
        <div class="col-sm-9">
            <p class="form-control-static">这里是纯文字信息</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">输入框：</label>
        <div class="col-sm-9">
            <input type="text" id="form-field-1" placeholder="提示文字" class="col-xs-10 col-sm-5">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">密码框：</label>
        <div class="col-sm-9">
            <input type="password" id="form-field-1" placeholder="请输入密码" class="col-xs-10 col-sm-5">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">下拉框：</label>
        <div class="col-sm-9" style="width:66%;">
            <select class="chosen-select form-control" name="name" id="id" data-placeholder="请选择"><option value=""></option><option value="">选项一</option><option value="">选项二</option><option value="">选项三</option></select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">文件域：</label>
        <div class="col-sm-9">
            <input type="file" id="tp" name="tp" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">单选框：</label>
        <div class="col-sm-9">
            <label style="float:left;padding-left: 8px;padding-top:7px;">
                <input name="form-field" type="radio" class="ace" id="form-field-radio1">	<span class="lbl">单选1</span>

            </label>
            <label style="float:left;padding-left: 5px;padding-top:7px;">
                <input name="form-field" type="radio" class="ace" id="form-field-radio2">	<span class="lbl">单选2</span>

            </label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">复选框：</label>
        <div class="col-sm-9">
            <label style="float:left;padding-left: 8px;padding-top:7px;">
                <input name="checkbox1" type="checkbox" class="ace" id="checkbox1">	<span class="lbl">选项1</span>

            </label>
            <label style="float:left;padding-left: 5px;padding-top:7px;">
                <input name="checkbox2" type="checkbox" class="ace ace-checkbox-2" id="checkbox2">	<span class="lbl">选项2</span>

            </label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">开关：</label>
        <div class="col-sm-9">
            <label>
                <input name="switch-field-1" class="ace ace-switch ace-switch-3" type="checkbox">	<span class="lbl"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked">	<span class="lbl"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch" type="checkbox">	<span class="lbl" data-lbl="CUS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TOM"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch ace-switch-5" type="checkbox" checked="checked">	<span class="lbl"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch" type="checkbox">	<span class="lbl" data-lbl="CUS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TOM"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch ace-switch-4 btn-flat" type="checkbox" checked="checked">	<span class="lbl"></span>

            </label>
            <label>
                <input name="switch-field-1" class="ace ace-switch ace-switch-7" type="checkbox">	<span class="lbl"></span>

            </label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">富文本：</label>
        <div class="col-sm-9">
            <script id="editor" type="text/plain" style="width:96%;height:200px;"></script>
        </div>
    </div>
</div>
</form>
<!-- 存放生成的hmlt结尾 -->
							
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 百度富文本编辑框-->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	<!-- 百度富文本编辑框-->
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!-- 上传控件 -->
	<script src="static/ace/js/ace/elements.fileinput.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
			
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
			//上传
			$('#tp').ace_file_input({
				no_file:'请选择文件 ...',
				btn_choose:'选择',
				btn_change:'更改',
				droppable:false,
				onchange:null,
				thumbnail:false, //| true | large
				whitelist:'*',
				//whitelist:'gif|png|jpg|jpeg',
				//blacklist:'gif|png|jpg|jpeg'
				//onchange:''
				//
			});
		});
		
		//百度富文本
		setTimeout("ueditor()",500);
		function ueditor(){
			UE.getEditor('editor');
		}
	</script>


</body>
</html>