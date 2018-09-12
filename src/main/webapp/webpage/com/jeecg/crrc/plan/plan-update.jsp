<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>项目管理</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <t:base type="jquery,aceform,DatePicker,validform,ueditor"></t:base>
    <link rel="stylesheet" href="plug-in/easyui/themes/metrole/easyui.css" type="text/css">
    <style type="text/css">
        .combo_self {
            height: 26px !important;
            width: 164px !important;
            padding-top: 0px !important;
        }

        .layout-header .btn {
            margin: 0px;
            float: none !important;
        }

        .btn-default {
            height: 35px;
            line-height: 35px;
            font-size: 14px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".combo").removeClass("combo").addClass("combo combo_self");
            $(".combo").each(function () {
                $(this).parent().css("padding-top", "10px !important;");
            });
        });

        /**树形列表数据转换**/
        function convertTreeData(rows, textField) {
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                row.text = row[textField];
                if (row.children) {
                    row.state = "open";
                    convertTreeData(row.children, textField);
                }
            }
        }

        /**树形列表加入子元素**/
        function joinTreeChildren(arr1, arr2) {
            for (var i = 0; i < arr1.length; i++) {
                var row1 = arr1[i];
                for (var j = 0; j < arr2.length; j++) {
                    if (row1.id == arr2[j].id) {
                        var children = arr2[j].children;
                        if (children) {
                            row1.children = children;
                        }

                    }
                }
            }
        }
    </script>
    <script type="text/javascript">
        //编写自定义JS代码
    </script>
</head>

<body>
<t:formvalid formid="formobj" dialog="false" usePlugin="password" layout="div" action=" " tiptype="1">
<fieldset class="step"style="padding-bottom: 20px;">
    <legend>编辑</legend>
    <input type="hidden" id="btn_sub" class="btn_sub"/>
    <input type="hidden" name="id" id="id" value='${planPage.id}'>


    <div class="tab-wrapper">
        <!-- tab -->

        <!-- tab内容 -->
        <div class="con-wrapper" id="con-wrapper1" style="display: block;">
            <div class="row form-wrapper">
                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >名称</label>
                        <input id="pname" name="pname" maxlength="50" type="text" class="form-control" datatype="*"
                               value='${planPage.pname}'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid" style="display: none">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >父级ID</label>
                        <input id="number" name="number" maxlength="32" type="text" class="form-control"
                               value='${planPage.number}'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >负责人</label>
                        <select class="getUser" id="getUser" style="width:175px;height: 30px">
                            <option>${planPage.getUsername()}</option>
                            <c:forEach items="${manager}" var="p">
                                <option id="username">${p.getUName()}</option>
                            </c:forEach>
                        </select>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 form">
                        <label class="Validform_label" >开始时间</label>
                        <input id="dateby" name="dateby" type="datetime"
                               style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"
                               class="form-control" onClick="WdatePicker()"
                               value='<fmt:formatDate value='${planPage.dateby}' type="date" pattern="yyyy-MM-dd"/>'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >结束时间</label>
                        <input id="dateend" name="dateend" type="datetime"
                               style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"
                               class="form-control" onClick="WdatePicker({minDate:'#F{$dp.$D(\'dateby\')}'})"
                               value='<fmt:formatDate value='${planPage.dateend}' type="date" pattern="yyyy-MM-dd"/>'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >天数</label>
                        <input id="date" name="date" maxlength="32" type="text" class="form-control" datatype="*"
                               value='${planPage.date}' readonly/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 form">
                        <label class="Validform_label" >级别</label>
                        <input id="level" name="level" maxlength="32" type="text" class="form-control"
                               value='${planPage.level}'/>
                        <span class="Validform_checktip" style="float:left;height:0px;">非常紧急填写：A；一般填写：B；不紧急填写：C</span>

                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >备注</label>
                        <input id="remarks" name="remarks" maxlength="32" type="text" class="form-control"
                                value='${planPage.remarks}'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >状态</label>
                        <input id="statese" name="statese" maxlength="32" type="text" class="form-control" ignore="ignore"
                               value='${planPage.statese}' readonly/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid" style="display: none">
                    <div class="col-xs-3">
                        <input id="process" name="process" maxlength="32" type="text" class="form-control"
                               ignore="ignore" value='${planPage.process}'/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">进度</label>
                    </div>
                </div>


                <div class="row" id="sub_tr" style="display: none;">
                    <div class="col-xs-12 layout-header">
                        <div class="col-xs-6"></div>
                        <div class="col-xs-6"style="width: 100%;height: 100%">
                            <button type="button" onclick="neibuClick();" class="btn btn-default">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
    </div>
</fieldset>
</t:formvalid>

<script type="text/javascript">
    $(function () {
        // //查看模式情况下,删除和上传附件功能禁止使用
        // if(location.href.indexOf("load=detail")!=-1){
        // 	$(".jeecgDetail").hide();
        // }
        //
        // if(location.href.indexOf("mode=read")!=-1){
        // 	//查看模式控件禁用
        // 	$("#formobj").find(":input").attr("disabled","disabled");
        // }
        // if(location.href.indexOf("mode=onbutton")!=-1){
        //其他模式显示提交按钮
        $("#sub_tr").show();
        // }
    });

    var neibuClickFlag = false;

    function neibuClick() {
        neibuClickFlag = true;
        $('#btn_sub').trigger('click');
        // alert(123);
        <%--$.messager.show({--%>
            <%--title : title,--%>
            <%--msg : data.msg,--%>
            <%--timeout : 2000,--%>
            <%--showType : 'slide'--%>
        <%--});--%>
        <%--alert(${j.getMsg()});--%>
        $.ajax({
            type: "POST",//请求方式
            url: "planController.do?doUpdate",//发送请求的地址
            data: {id:$("#id").val(), pname:$("#pname").val()
                , number:$("#number").val(), username:$("#username").val()
                , dateby:$("#dateby").val(), dateend:$("#dateend").val()
                , date:$("#date").val(), level:$("#level").val()
                , remarks:$("#remarks").val(), statese:$("#statese").val()
                , process:$("#process").val()},
            dataType: "json",//预期服务器返回的类型
            // async: false,//设置为同步传输
            success: function(data) {
                // alert(data.msg);
                $.messager.alert('提示',data.msg,'info');
            }
                });


        // alert(arr);
        // return arr;//返回数组

    }

    $('#dateend').blur(function () {
        var t =document.getElementById('date');
        var tb =document.getElementById('dateby');
        var td=document.getElementById('dateend');
        var s1 = new Date(tb.value.replace(/-/g, "/"));
        var s2 = new Date(td.value.replace(/-/g, "/"));
        var time=((s2.getTime()-s1.getTime())/(1000*3600*24))+1;
        if (time==NaN){
            t.value=" "
        }else{
            t.value=time;
        }

    });


</script>
</body>
<script src="webpage/com/jeecg/crrc/plan/plan.js"></script>
</html>