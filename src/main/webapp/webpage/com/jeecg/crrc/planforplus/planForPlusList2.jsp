<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="planForPlusList"  checkbox="true"  extendParams="view: detailview,detailFormatter:detailFormatterFun,onExpandRow: onExpandRowFun" fitColumns="true" title="计划详情" sortName="createDate" actionUrl="planForPlusController.do?datagrid2&number=${plans2.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="pname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父级ID"  field="number"  queryMode="single" hidden="true" width="120"></t:dgCol>
   <t:dgCol title="项目经理"  field="username"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="dateby"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="dateend"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="天数"  field="date"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="级别"  field="level"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="statese"  queryMode="single" dictionary="plan_state" extendParams="styler:fmtype" width="120"></t:dgCol>
   <t:dgCol title="进度"  field="process" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" url="planForPlusController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>--%>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="planForPlusController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="planForPlusController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="planForPlusController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="planForPlusController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/crrc/planforplus/planForPlusList.js"></script>
<script src="plug-in/easyui/extends/datagrid-detailview.js"></script>
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'planForPlusController.do?upload', "planForPlusList");
}

//导出
function ExportXls() {
	JeecgExcelExport("planForPlusController.do?exportXls","planForPlusList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("planForPlusController.do?exportXlsByT","planForPlusList");
}
//返回行明细内容的格式化函数。
function detailFormatterFun() {
    var s = '<div class="orderInfoHidden" style="padding:2px;">'
        + '		<div class="easyui-tabs" style="height:230px;width:800px;">'
        + '			<div title="工作明细" style="padding:2px;">'
        + '				<table class="jfrom_order_linetablelines" ></table>'
        + '			</div>'
        + '		</div>'
        + '	</div>';
    return s;
}
//当展开一行时触发
function onExpandRowFun(index, row) {
    //把加上的子表tabs和datagrid初始化
    var tabs = $(this).datagrid('getRowDetail', index).find('div.easyui-tabs');
    tabs.tabs();
    var jfrom_order_linetablelines = $(this).datagrid('getRowDetail', index).find('table.jfrom_order_linetablelines');
    var jfrom_order_linedurl = 'planForPlusController.do?planForPlusChildEntityDatagrid&field=name,work,remarks&pid=' + row.id;
    jfrom_order_linetablelines.datagrid({
        singleSelect: true,
        loadMsg: '正在加载',
        fitColumns: true,
        height: '180',
        pageSize: 50,
        pageList: [50, 150, 200, 250, 300],
        border: false,
        url: jfrom_order_linedurl,
        idField: 'id',
        rownumbers: true,
        pagination: true,
        columns: [[{
            title: '姓名',
            field: 'name',
            align: 'left',
            width: 50
        },
            {
                title: '工作内容',
                field: 'work',
                align: 'left',
                width: 50
            },
            {
                title: '备注',
                field: 'remarks',
                align: 'left',
                width: 50
            }]]
    });
}
function fmtype(val,row,index){
    //可添加更多CSS样式
    var s1 = 'background-color:#3a87ad;color:#FFF;';
    var s2 = 'background-color:#f89406;color:#FFF;';
    var s3 = 'background-color:red;';
    if (val =='1') {
        return s1
    }
    if (val =='0') {
        // alert(dateend);
        return s2

    }
    return s3
}
 </script>