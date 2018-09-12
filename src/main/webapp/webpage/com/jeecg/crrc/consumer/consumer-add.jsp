<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="consumerController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${consumerPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							所在公司:
						</label>
					</td>
					<td class="value">
					     	 <input id="company" name="company" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所在公司</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							对接主管:
						</label>
					</td>
					<td class="value">
					     	 <input id="manager" name="manager" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">对接主管</label>
						</td>
				</tr>
				<tr style="display: none">
					<td align="right">
						<label class="Validform_label">
							项目id:
						</label>
					</td>
					<td class="value">
						<input id="planId" name="planId" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${consumers}" readonly="true"/>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">项目id</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="tel" name="tel" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="m" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							邮箱:
						</label>
					</td>
					<td class="value">
					     	 <input id="email" name="email" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="e" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							微信:
						</label>
					</td>
					<td class="value">
					     	 <input id="wechat" name="wechat" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/crrc/consumer/consumer.js"></script>		
