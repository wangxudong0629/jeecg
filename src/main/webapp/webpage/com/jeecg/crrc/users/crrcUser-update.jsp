<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人员表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="crrcUserController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${crrcUser.getId()}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					用户名：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uLoginname" type="text" class="form-control" maxlength="50" value = "${crrcUser.getULoginname()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					密码：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<input name="uPassword" type="password" maxlength="50" value = "${crrcUser.getUPassword()}" class="form-control" ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					姓名：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uName" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUName()}"  ignore="ignore" readonly />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					年龄：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uAge" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUAge()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					性别：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<div style="padding-top:5px">
	            	<%--<t:dictSelect field="uSex" defaultVal = "${crrcUser.uSex}" extendJson="{class:'i-checks'}" type="radio" hasLabel="false"  title="性别"  typeGroupCode="" ></t:dictSelect>--%>
						<input type="radio"checked name="uSex" value='1'/><label>男</label>
						<input name="uSex"  type="radio" value="0"/><label>女</label>
					</div>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					电话：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uIphone" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUIphone()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					邮件：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uEmail" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUEmail()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					部门：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<%--<t:dictSelect field="uDepartid" defaultVal = "${crrcUser.uDepartid}" type="select" hasLabel="false" title="部门" extendJson="{class:'form-control'}"   typeGroupCode="" ></t:dictSelect>--%>
						<select class="getdepart" id="uDepartid" name="uDepartid"
								style="margin-left: 10px;width: 185px">
							<%--<option value="${crrcUser.UDepartid()}">${crrcUser.getUDepartname()}</option>--%>
							<c:forEach items="${departList}" var="p">
								<option value="${p.getId()}">${p.getDName()}</option>
								<%-- 如果所选是之前的值 可以自动填写！ --%>
							</c:forEach>
						</select>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					职位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<%--<t:dictSelect field="uPositionid" defaultVal = "${crrcUser.uPositionid}" type="select" hasLabel="false" title="职位" extendJson="{class:'form-control'}"   typeGroupCode="" ></t:dictSelect>--%>
						<select class="getUser" id="uPositionid"name="uPositionid"
								style="margin-left: 10px;width: 185px">
							<%--<c:forEach items="${departList}" var="p">--%>
							<option value="${crrcUser.getUPositionid()}">${crrcUser.getUPositionid()}</option>
							<option value="总经理">总经理</option>
							<option value="项目经理">项目经理</option>
							<option value="部员">部员</option>
							<option value="管理人员">管理人员</option>
							<%-- 如果所选是之前的值 可以自动填写！ --%>
							<%--</c:forEach>--%>
						</select>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					身份证：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uIdetitycord" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUIdetitycord()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					工号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uPnumber" type="text" class="form-control" maxlength="50" value = "${crrcUser.getUPnumber()}"  ignore="ignore" readonly />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					地址：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uAddress" type="text" class="form-control" maxlength="500" value = "${crrcUser.getUAddress()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					入职时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uHiredate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${crrcUser.getUHiredate()}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					离职时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uLeavedate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${crrcUser.getULeavedate()}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					人员等级：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uLevel" type="text" class="form-control" maxlength="50" value = "${crrcUser.getULevel()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					备注：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uRemarks" type="text" class="form-control" maxlength="500" value = "${crrcUser.getURemarks()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					删除：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dFlag" type="text" class="form-control" maxlength="32" value = "${crrcUser.getDFlag()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					权限：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uAuthority" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUAuthority()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					部门名：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uDepartname" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUDepartname()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					预留2：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uNotel2" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUNotel2()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					预留3：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uNotel3" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUNotel3()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					预留4：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uNotel4" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUNotel4()}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6"style="display: none">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					预留5：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="uNotel5" type="text" class="form-control" maxlength="32" value = "${crrcUser.getUNotel5()}"  ignore="ignore"  />
				</div>
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