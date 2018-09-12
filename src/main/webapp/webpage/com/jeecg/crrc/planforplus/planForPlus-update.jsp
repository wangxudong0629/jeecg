<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>项目加号</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="planForPlusController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${planForPlusPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">名称:</label>
			</td>
			<td class="value">
		     	 <input id="pname" name="pname" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.pname}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名称</label>
			</td>
			<td align="right"style="display: none;">
				<label class="Validform_label">父级ID:</label>
			</td>
			<td class="value"style="display: none;">
		     	 <input id="number" name="number" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.number}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">父级ID</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">项目经理:</label>
			</td>
			<td class="value">
		     	 <input id="username" name="username" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.username}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">项目经理</label>
			</td>
			<td align="right">
				<label class="Validform_label">开始时间:</label>
			</td>
			<td class="value">
					  <input id="dateby" name="dateby" type="text" style="width: 150px"   ignore="ignore"readonly  value='<fmt:formatDate value='${planForPlusPage.dateby}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开始时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">结束时间:</label>
			</td>
			<td class="value">
					  <input id="dateend" name="dateend" type="text" style="width: 150px" readonly  ignore="ignore"  value='<fmt:formatDate value='${planForPlusPage.dateend}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">结束时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">天数:</label>
			</td>
			<td class="value">
		     	 <input id="date" name="date" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.date}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">级别:</label>
			</td>
			<td class="value">
		     	 <input id="level" name="level" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.level}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">级别</label>
			</td>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="remarks" name="remarks" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.remarks}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
		     	 <input id="statese" name="statese" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.statese}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">进度:</label>
			</td>
			<td class="value">
		     	 <input id="process" name="process" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${planForPlusPage.process}'readonly/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">进度</label>
			</td>
		</tr>
	
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="planForPlusController.do?planForPlusChildList&id=${planForPlusPage.id}" icon="icon-search" title="添加人员活动信息" id="planForPlusChild"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_planForPlusChild_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="planForPlusChildList[#index#].name" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">姓名</label>
				  </td>
				  <td align="left">
					  		<input name="planForPlusChildList[#index#].work" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">工作</label>
				  </td>
				  <td align="left">
					  		<input name="planForPlusChildList[#index#].remark" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/crrc/planforplus/planForPlus.js"></script>	
