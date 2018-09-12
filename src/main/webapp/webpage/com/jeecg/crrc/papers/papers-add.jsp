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


	<style>
		#citySel{

			background-color: white;
		}
		.get-members-p{
			position:relative;
			left: 26%;
			bottom: 10px;
		}
		.get-members{

			height: 100px;

			width: 57%;

		}


	</style>
</head>
 <body style="overflow:hidden;overflow-y:auto; width: 100% ;height: 400px">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="papersController.do?doAdd" method="POST">

		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="url" class="col-sm-3 control-label">文件：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:webUploader name="url" outJs="true" auto="true" showImgDiv="filediv_url" ></t:webUploader>
					<div class="form" id="filediv_url"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="paname" class="col-sm-3 control-label">文件名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="paname" name="paname" type="text" maxlength="32" class="form-control input-sm" placeholder="文件上传完成后点击" onfocus ="setStyle()"  datatype="*" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="username" class="col-sm-3 control-label">上传人：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="username" name="username" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入上传人"  datatype="*" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="patime" class="col-sm-3 control-label">上传时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input id="patime" name="patime" type="text" class="form-control "  ignore="ignore"  readonly/>

				</div>
			</div>
		</div>
		<div class="get-members-p">
			<span style="position: relative;right: 103px;top:25px;font-weight: bold">指定人可见:</span>
			<div class="get-members">

				<t:selectZTree id="citySel"  url="crrcDepartztreeController.do?getTreeData" windowWidth="100%" windowHeight="34px" selectCascadeChildren="true" cancelCascadeParent="true" cancelCascadeChildren="true" selectCascadeParent="true"></t:selectZTree>


			</div>

		</div>

		<div class="form-group"style="display: none">
			<label for="prid" class="col-sm-3 control-label">项目ID：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="prid" name="prid" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入项目ID"  ignore="ignore" value="${plan.id}"/>
				</div>
			</div>
		</div>
		<div class="form-group"style="display: none">
			<label for="prid" class="col-sm-3 control-label">父级ID：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="faid" name="faid" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入父级ID"  ignore="ignore" value="${plans.id}"/>
				</div>
			</div>
		</div>
		<div class="form-group"style="display: none">
			<label for="pname" class="col-sm-3 control-label">项目名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="pname" name="pname" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入项目名称"  ignore="ignore" value="${plan.pname}"/>
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

function today(){//构建方法
    var today=new Date();//new 出当前时间
    var h=today.getFullYear();//获取年
    var m=today.getMonth()+1;//获取月
    var d=today.getDate();//获取日
    var H = today.getHours();//获取时
    var M = today.getMinutes();//获取分
    var S = today.getSeconds();//获取秒
    return h+"-"+m+"-"+d+" "+H+":"+M+":"+S; //返回 年-月-日 时:分:秒
}
document.getElementById("patime").value = today();//将获取到的 年-月-日 时:分:秒 赋给input文本输入框

// function qwe() {
//     alert($("#urlWU_FILE_0 td:eq(0)").text());
//     alert(123);
// }

function setStyle(){

    document.getElementById("paname").value =$(".item td:eq(0)").text();
}
</script>
</body>
</html>