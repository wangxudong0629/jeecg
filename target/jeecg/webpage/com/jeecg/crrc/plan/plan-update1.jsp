<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
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
        .combo_self{height: 26px !important;width:164px !important;padding-top:0px !important;}
        .layout-header .btn {
            margin:0px;
            float: none !important;
        }
        .btn-default {
            height: 35px;
            line-height: 35px;
            font-size:14px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $(".combo").removeClass("combo").addClass("combo combo_self");
            $(".combo").each(function(){
                $(this).parent().css("padding-top","10px !important;");
            });
        });

        /**树形列表数据转换**/
        function convertTreeData(rows, textField) {
            for(var i = 0; i < rows.length; i++) {
                var row = rows[i];
                row.text = row[textField];
                if(row.children) {
                    row.state = "open";
                    convertTreeData(row.children, textField);
                }
            }
        }
        /**树形列表加入子元素**/
        function joinTreeChildren(arr1, arr2) {
            for(var i = 0; i < arr1.length; i++) {
                var row1 = arr1[i];
                for(var j = 0; j < arr2.length; j++) {
                    if(row1.id == arr2[j].id) {
                        var children = arr2[j].children;
                        if(children) {
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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="planController.do?doUpdate" tiptype="1" >
    <input type="hidden" id="btn_sub" class="btn_sub"/>
    <input type="hidden" name="id" value='${planPage.id}' >


    <div class="tab-wrapper">
        <!-- tab -->
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="javascript:void(0);">项目管理</a></li>
        </ul>
        <!-- tab内容 -->
        <div class="con-wrapper" id="con-wrapper1" style="display: block;">
            <div class="row form-wrapper">
                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>名称：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="pname" name="pname" maxlength="50" type="text" class="form-control" datatype="*"  ignore="checked"  value='${planPage.pname}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">名称</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>父级ID：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="number" name="number" type="text"  class="inputxt easyui-combotree"  ignore="ignore"
                               data-options="panelHeight:'220',
				                    url: 'planController.do?datagrid&field=id,pname',
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, 'pname');
				                    	return rows;
				                    },
				                     onSelect:function(node){
				                    	$('#number').val(node.id);
				                    },
				                     onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${planPage.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#number').combotree('setValue', currRow.id);
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#number').combotree('setValue', currRow.number);
				                    		}
				                    	}
				                    }"/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">父级ID</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>项目经理：</b>
                    </div>
                    <div class="col-xs-3">
                        <select class="getUser" id="getUser" style="width:175px;height: 30px">
                            <option>${planPage.getUsername()}</option>
                            <c:forEach items="${manager}" var="p">
                                <option id="username">${p.getUName()}</option>
                            </c:forEach>
                        </select>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">项目经理</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>开始时间：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="dateby" name="dateby" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${planPage.dateby}' type="date" pattern="yyyy-MM-dd"/>' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">开始时间</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>结束时间：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="dateend" name="dateend" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${planPage.dateend}' type="date" pattern="yyyy-MM-dd"/>' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">结束时间</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>天数：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="date" name="date" maxlength="32" type="text" class="form-control" ignore="ignore"  value='${planPage.date}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">天数</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>级别：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="level" name="level" maxlength="32" type="text" class="form-control" datatype="*"  ignore="checked"  value='${planPage.level}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">级别</label>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 text-center">
                        <b>备注：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="remarks" name="remarks" maxlength="32" type="text" class="form-control" ignore="ignore"  value='${planPage.remarks}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">备注</label>
                    </div>
                </div>


                <div class="row show-grid"style="display: none">
                    <div class="col-xs-3 text-center">
                        <b>状态：</b>
                    </div>
                    <div class="col-xs-3">
                        <input id="statese" name="statese" maxlength="32" type="text" class="form-control" ignore="ignore"  value='${planPage.statese}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">状态</label>
                    </div>
                </div>


                <div class="row show-grid"style="display: none">
                    <div class="col-xs-3 text-center">
                        <b>进度：</b>
                </div>
                    <div class="col-xs-3">
                        <input id="process" name="process" maxlength="32" type="text" class="form-control" ignore="ignore"  value='${planPage.process}' />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">进度</label>
                    </div>
                </div>


                <div class="row" id = "sub_tr" style="display: none;">
                    <div class="col-xs-12 layout-header">
                        <div class="col-xs-6"></div>
                        <div class="col-xs-6"><button type="button" onclick="neibuClick();" class="btn btn-default">提交</button></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
    </div>
</t:formvalid>

<script type="text/javascript">
    $(function(){
        //查看模式情况下,删除和上传附件功能禁止使用
        if(location.href.indexOf("load=detail")!=-1){
            $(".jeecgDetail").hide();
        }

        if(location.href.indexOf("mode=read")!=-1){
            //查看模式控件禁用
            $("#formobj").find(":input").attr("disabled","disabled");
        }
        if(location.href.indexOf("mode=onbutton")!=-1){
            //其他模式显示提交按钮
            $("#sub_tr").show();
        }
    });

    var neibuClickFlag = false;
    function neibuClick() {
        neibuClickFlag = true;
        $('#btn_sub').trigger('click');
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
<script src = "webpage/com/jeecg/crrc/plis/plan.js"></script>
</html>