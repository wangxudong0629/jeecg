<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="consumerList" checkbox="true" pagination="true" fitColumns="true" title="客户表" sortName="id" actionUrl="consumerController.do?datagrid&planId=${plan}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在公司"  field="company"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对接主管"  field="manager"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目id"  field="planId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="tel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮箱"  field="email"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="微信"  field="wechat"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="consumerController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="consumerController.do?goAdd&planId=${plan}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="consumerController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="consumerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="consumerController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/crrc/consumer/consumerList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 <%--alert('${plan}');--%>
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'consumerController.do?upload', "consumerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("consumerController.do?exportXls","consumerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("consumerController.do?exportXlsByT","consumerList");
}

 </script>