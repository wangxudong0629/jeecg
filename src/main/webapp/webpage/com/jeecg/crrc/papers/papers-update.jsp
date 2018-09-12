<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文件表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,layer,validform,webuploader,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="papersController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${papers.id}"/>
	<div class="form-group">
		<label for="paname" class="col-sm-3 control-label">文件名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="paname" name="paname" value='${papers.paname}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入文件名称"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="username" class="col-sm-3 control-label">上传人：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="username" name="username" value='${papers.username}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入上传人"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-3 control-label">文件：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<t:webUploader name="url" outJs="true" auto="true" showImgDiv="filediv_url" pathValues="${papers.url}"></t:webUploader>
				<div class="form" id="filediv_url"></div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="patime" class="col-sm-3 control-label">上传时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="patime" name="patime" type="text" class="form-control input-sm laydate-datetime" placeholder="请输入上传时间"  ignore="ignore"   value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='both' value='${papers.patime}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span> </span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="prid" class="col-sm-3 control-label">项目ID：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="prid" name="prid" value='${papers.prid}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入项目ID"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="pname" class="col-sm-3 control-label">项目名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="pname" name="pname" value='${papers.pname}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入项目名称"  ignore="ignore" />
			</div>
		</div>
	</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
});
</script>
</body>
</html>