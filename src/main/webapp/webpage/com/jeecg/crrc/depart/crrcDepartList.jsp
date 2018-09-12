<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="crrcDepartList" checkbox="false" pagination="true" treegrid="true" treeField="dName" fitColumns="true" title="公司组织架构" actionUrl="crrcDepartController.do?datagrid" idField="id" sortName="createDate" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名字"  field="dName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父级id"  field="dNumber" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="组织电话"  field="dIphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="组织邮件"  field="dEmail"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="dRemarks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="权限"  field="dAuthority" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留1"  field="dNotel1" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留2"  field="dNotel2" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="dNotel3" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="dNotel4" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留5"  field="dNotel5" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="crrcDepartController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="crrcDepartController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="crrcDepartController.do?goUpdate" funname="updatetree" width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="crrcDepartController.do?doBatchDel" funname="deleteALLSelecttree"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="crrcDepartController.do?goUpdate" funname="detailtree" width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/crrc/depart/crrcDepartList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		$("#crrcDepartList").treegrid({
 				 onExpand : function(row){
 					var children = $("#crrcDepartList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#crrcDepartList").treegrid('refresh', row.id);
 					 }
 				}
 		});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'crrcDepartController.do?upload', "crrcDepartList");
}

//导出
function ExportXls() {
	JeecgExcelExport("crrcDepartController.do?exportXls","crrcDepartList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("crrcDepartController.do?exportXlsByT","crrcDepartList");
}

/**
 * 获取表格对象
 * @return 表格对象
 */
function getDataGrid(){
	var datagrid = $('#'+gridname);
	return datagrid;
}
 </script>