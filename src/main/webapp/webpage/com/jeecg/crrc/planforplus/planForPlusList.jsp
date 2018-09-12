<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="planForPlusList" checkbox="false" pagination="false"   fitColumns="true" title="计划详情" sortName="createDate" actionUrl="planForPlusController.do?datagrid&id=${plan.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="pname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父级ID"  field="number" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目经理"  field="username"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="dateby"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="dateend"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="天数"  field="date"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="级别"  field="level"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="statese"  queryMode="single" dictionary="plan_state" extendParams="styler:fmtype" width="120"></t:dgCol>
   <t:dgCol title="进度"  field="process" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="确定"  funname="neibuClick(id)" urlclass="ace_button"  />
   <t:dgDelOpt title="删除" url="planForPlusController.do?doDel&id={id}"  urlclass="ace_button" />
   <%--<t:dgToolBar title="录入" icon="icon-add" url="planForPlusController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="添加工作信息" icon="icon-edit" url="planForPlusController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>--%>
   <t:dgToolBar title="添加工作信息" icon="icon-add" url="workController.do?goAdd&id=${plan.id}" funname="add"  width="768"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="planForPlusController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="planForPlusController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <t:dgToolBar title="上传文件" icon="icon-add" url="papersController.do?goAdd&id=${plan.id}" funname="add"  width="768"></t:dgToolBar>
   <%--<t:dgToolBar title="删除文件"  icon="icon-remove" url="papersController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
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
    var s = '<div class="orderInfoHidden" style=";padding:2px;">'
        + '		<div class="easyui-tabs" style="height:290px;width:1060px;">'
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
        height: '250',
        pageSize: 6,
        pageList: [6, 12, 24, 36, 42],
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
function neibuClick(id) {
// alert(id);
    // alert('dateend');
    // alert('statese');
    $.ajax({
        type: "POST",//请求方式
        url: "planController.do?doindex",//发送请求的地址
        data:{id:id},
        // id:'id',  dateend:'dateend',  statese:'statese'
        dataType: "json",//预期服务器返回的类型
        // async: true,//设置为同步传输
        success: function(data) {
            // alert(data.msg);
            //  $.messager.alert('提示',data.msg,'info');
            window.location.href="planController.do?vg&id=${plan.id}";


        }
    });
}
function fmtype(val,row,index){
    //可添加更多CSS样式
    var s1 = 'background-color:#3a87ad;color:#FFF;';
    var s2 = 'background-color:#f89406;color:#FFF;';
    var s3 = 'background-color:red';
    if (val =='1') {
        return s1
    }
    if (val =='0') {
        // alert(dateend);
        return s2

    }
    return s3
}
function haoqi() {
    // setTimeout(function () {//延时触发easyui datagrid detailviewclick事件，不用计时器无法展开，不懂什么问题~
        $('.datagrid-row-expander').click(); //没效果注意修改这里的选择器
    // }, 10);
}
// setTimeout(function () {//延时触发easyui datagrid detailviewclick事件，不用计时器无法展开，不懂什么问题~
//     $('.datagrid-row-expander').click(); //没效果注意修改这里的选择器
// }, 1000);
 </script>