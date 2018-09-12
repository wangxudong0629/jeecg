<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="crrcUserList" checkbox="false" pagination="true" fitColumns="true" title="人员表" actionUrl="crrcUserController.do?datagrid" idField="id" sortName="createDate" fit="true" queryMode="group">
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
   <t:dgCol title="用户名"  field="uLoginname" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="密码"  field="uPassword" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="uName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年龄"  field="uAge"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="uSex"  queryMode="single" dictionary="User_sex" width="120"></t:dgCol>
   <t:dgCol title="部门名"  field="uDepartname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="uIphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮件"  field="uEmail"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="部门"  field="uDepartid"  queryMode="single" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="职位"  field="uPositionid"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="身份证"  field="uIdetitycord"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工号"  field="uPnumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="uAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入职时间"  field="uHiredate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离职时间"  field="uLeavedate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人员等级"  field="uLevel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="uRemarks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除"  field="dFlag" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="权限"  field="uAuthority" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留2"  field="uNotel2" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="uNotel3" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="uNotel4" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留5"  field="uNotel5" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="crrcUserController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="crrcUserController.do?goAdd" funname="add"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="crrcUserController.do?goUpdate" funname="update"  width="800" height="500"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="crrcUserController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="crrcUserController.do?goUpdate" funname="detail"  width="800" height="500"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'crrcUserController.do?upload', "crrcUserList");
}

//导出
function ExportXls() {
	JeecgExcelExport("crrcUserController.do?exportXls","crrcUserList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("crrcUserController.do?exportXlsByT","crrcUserList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

//列表文件图片 列格式化方法
function listFileImgFormat(value,type){
	var href='';
	if(value==null || value.length==0){
		return href;
	}
	var value1 = "img/server/"+value;
	if("image"==type){
 		href+="<img src='"+value1+"' width=30 height=30  onmouseover='tipImg(this)' onmouseout='moveTipImg()' style='vertical-align:middle'/>";
	}else{
 		if(value.indexOf(".jpg")>-1 || value.indexOf(".gif")>-1 || value.indexOf(".png")>-1){
 			href+="<img src='"+value1+"' onmouseover='tipImg(this)' onmouseout='moveTipImg()' width=30 height=30 style='vertical-align:middle'/>";
 		}else{
 			var value2 = "img/server/"+value+"?down=true";
 			href+="<a href='"+value2+"' class='ace_button' style='text-decoration:none;' target=_blank><u><i class='fa fa-download'></i>点击下载</u></a>";
 		}
	}
	return href;
}

</script>
