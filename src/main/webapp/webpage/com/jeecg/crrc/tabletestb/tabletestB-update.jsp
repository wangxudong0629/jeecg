<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>B表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">

 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="tabletestBController.do?doUpdate" method="POST" tiptype="6" dialog="true">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${tabletestB.id}"/>
	<div class="form-group">
		<label for="dpname" class="col-sm-3 control-label">项目名：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="dpname" name="dpname" value='${tabletestB.dpname}' type="text"datatype="*" maxlength="32" class="form-control input-sm" placeholder="请输入项目名"  />
			</div>
		</div>
	</div>
		<div class="form-group">
			<label for="dpsta" class="col-sm-3 control-label">项目描述：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="dpsta" name="dpsta" value='${tabletestB.dpsta}' type="text"datatype="*" maxlength="32" class="form-control input-sm" placeholder="请输入项目描述" />
				</div>
			</div>
		</div>
	<div class="form-group">
		<label for="dpcreatdate" class="col-sm-3 control-label">创建时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="dpcreatdate" name="dpcreatdate" type="text" class="form-control input-sm laydate-date" placeholder="请输入创建时间"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${tabletestB.dpcreatdate}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="dpenddate" class="col-sm-3 control-label">结束时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="dpenddate" name="dpenddate" type="text" class="form-control input-sm laydate-date" placeholder="请输入结束时间"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${tabletestB.dpenddate}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
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