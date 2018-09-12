<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="crrcDepartztreeList" checkbox="false" pagination="true" treegrid="true" treeField="deName" fitColumns="true" title="组织机构总表" actionUrl="crrcDepartztreeController.do?datagrid" idField="id" sortName="createDate" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="deName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父级"  field="deNumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="deType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留1"  field="deNotel1"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留2"  field="deNotel2"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="deNotel3"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="deNotel4"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="crrcDepartztreeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="crrcDepartztreeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="crrcDepartztreeController.do?goUpdate" funname="updatetree" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="crrcDepartztreeController.do?doBatchDel" funname="deleteALLSelecttree"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="crrcDepartztreeController.do?goUpdate" funname="detailtree" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/crrc/departztree/crrcDepartztreeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		$("#crrcDepartztreeList").treegrid({
 				 onExpand : function(row){
 					var children = $("#crrcDepartztreeList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#crrcDepartztreeList").treegrid('refresh', row.id);
 					 }
 				}
 		});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'crrcDepartztreeController.do?upload', "crrcDepartztreeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("crrcDepartztreeController.do?exportXls","crrcDepartztreeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("crrcDepartztreeController.do?exportXlsByT","crrcDepartztreeList");
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