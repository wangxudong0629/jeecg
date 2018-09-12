<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>经费表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,layer,validform,webuploader,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;margin-top: 20px">
 <form id="formobj" action="moneyController.do?doUpdate" class="form-horizontal validform" role="form"  method="post">
	<input type="hidden" id="btn_sub" class="btn_sub"/>
	<input type="hidden" id="id" name="id" value="${moneyPage.id}"/>
	<%--<div class="form-group">--%>
		<%--<label for="pid" class="col-sm-3 control-label">计划名：</label>--%>
		<%--<div class="col-sm-7">--%>
			<%--<div class="input-group" style="width:100%">--%>
				<%--<input id="pid" name="pid" value='${moneyPage.pid}' type="text" maxlength="100" class="form-control input-sm" placeholder="请输入计划ID"  ignore="ignore" />--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
	<div class="form-group">
		<label for="prid" class="col-sm-3 control-label">项目ID：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="prid" name="prid" value='${moneyPage.prid}' type="text" maxlength="100" class="form-control input-sm" placeholder="请输入项目ID"  ignore="ignore" />
			</div>
		</div>
	</div>
	<%--<div class="form-group">--%>
		<%--<label for="pname" class="col-sm-3 control-label">项目名：</label>--%>
		<%--<div class="col-sm-7">--%>
			<%--<div class="input-group" style="width:100%">--%>
	      		<%--<input id="pname" name="pname" value='${moneyPage.pname}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入项目名"  ignore="ignore" />--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
	<div class="form-group">
		<label for="uname" class="col-sm-3 control-label">姓名：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="uname" name="uname" value='${moneyPage.uname}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入姓名"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="money" class="col-sm-3 control-label">经费：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="money" name="money" value='${moneyPage.money}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入经费"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="remarks" class="col-sm-3 control-label">备注：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="remarks" name="remarks" value='${moneyPage.remarks}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入备注"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-3 control-label">图片路径：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<t:webUploader name="url" outJs="true" auto="true" showImgDiv="filediv_url" type="image" buttonText='添加图片' displayTxt="false" pathValues="${moneyPage.url}"></t:webUploader>
				<div class="form" id="filediv_url"></div>
			</div>
		</div>
	</div>
</form>
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