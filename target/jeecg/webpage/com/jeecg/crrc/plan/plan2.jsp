
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="planList" checkbox="false" pagination="true"  fitColumns="true" title="项目管理" actionUrl="planController.do?datagrid2&number=${plans2.id}" idField="id"   queryMode="group">
            <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
            <t:dgCol title="名称"  field="pname"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="父级ID"  field="number"  hidden="true"  queryMode="single"  formatterjs="treeFormater" width="120"></t:dgCol>
            <t:dgCol title="项目经理"  field="username"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="开始时间"  field="dateby"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="结束时间"  field="dateend"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="天数"  field="date"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="级别"  field="level"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="备注"  field="remarks"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="状态"  field="statese"  queryMode="single"  width="120" dictionary="plan_state" extendParams="styler:fmtype"></t:dgCol>
            <t:dgCol title="进度"  field="process"  queryMode="single"  width="120" ></t:dgCol>
            <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
            <%--<t:dgDelOpt title="删除" url="planController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
            <%--<t:dgToolBar title="录入" icon="icon-add" url="planController.do?goAdd" funname="add"></t:dgToolBar>--%>
            <t:dgToolBar title="编辑" icon="icon-edit" url="planController.do?goUpdate" funname="updatetree" width="100%" height="100%"></t:dgToolBar>
            <t:dgToolBar title="批量删除"  icon="icon-remove" url="planController.do?doBatchDel" funname="deleteALLSelecttree"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="icon-search" url="planController.do?goUpdate" funname="detailtree" width="100%" height="100%"></t:dgToolBar>
            <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
            <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
            <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script src = "webpage/com/jeecg/crrc/plan/planList.js"></script>
<script type="text/javascript">
    // $(document).ready(function(){
    //     $("#planList").treegrid({
    //         onExpand : function(row){
    //             var children = $("#planList").treegrid('getChildren',row.id);
    //             if(children.length<=0){
    //                 row.leaf=true;
    //                 $("#planList").treegrid('refresh', row.id);
    //             }
    //         }
    //     });
    // });



    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'planController.do?upload', "planList");
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
            // var t = dateend.value;
            // row.getData
            // if (){}
            return s2
        }
        return s3
    }
    //导出
    function ExportXls() {
        JeecgExcelExport("planController.do?exportXls","planList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("planController.do?exportXlsByT","planList");
    }



</script>