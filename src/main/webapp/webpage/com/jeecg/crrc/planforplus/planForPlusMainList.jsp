<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true"style="position: fixed">
    <div region="center" style="padding:0px;border:0px;overflow-x:hidden;">
        <iframe id="mainList" src="${webRoot}/planController.do?vg&id=${plan.id}" frameborder="0" height="20%" width="100%"></iframe><%--
        <div id="accDiv" class="easyui-accordion" style="padding-right:15px;overflow-x:hidden;box-sizing: border-box;">--%>
        <iframe id="customerList" height="45%" src="${webRoot}/workController.do?list&id=${plan.id}" frameborder="0" width="100%" ></iframe>
        <iframe id="customerList" height="34.5%" src="${webRoot}/papersController.do?list&id=${plan.id}" frameborder="0" width="100%" ></iframe>
<%--
        </div>--%>
    </div>
</div>
<script type="text/javascript">
    function getCustomerList(id){
        $("#customerList")[0].contentWindow.getCustomerList(id);
    }

    $(function(){
        var abc = parseInt(document.body.clientWidth);
        $("#accDiv").css("width", abc);
    });


</script>