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
    <!-- ztree -->
    <link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css"></link>
    <script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
    <script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="plug-in/ztree/js/ztreeCreator.js"></script>
    <style type="text/css">


        #citySel{

            background-color: white;
        }
        .step{
            background-color: white;
            height: 700px;

        }

        .tab-wrapper{
            position: relative;
            top:20px;

            width: 370px;
        }

        .combo_self{height: 26px !important;width:164px !important;padding-top:0px !important;}
        .layout-header .btn {
            margin:0px;
            float: none !important;
        }
        .btn-default {
            height: 35px;
            line-height: 25px;
            font-size:14px;
            position: relative;
            buttom: 5px;
            left: 160px;
        }
        .get-members{
            /*border: 1px solid red;*/
            position: relative;
            left:380px;
            bottom: 400px;
            height: 350px;
            width: 270px;
            background: white;
            overflow:auto;
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
                    row.state = "close";
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

<body >


<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action=" " tiptype="1" beforeSubmit="setContentc" >
<fieldset class="step"style="padding-bottom: 20px;">
    <legend>添加项目</legend>
    <input type="hidden" id="btn_sub" class="btn_sub"/>
    <input type="hidden" id="id" name="id"/>
    <div class="tab-wrapper">
        <!-- tab内容 -->


        <div class="con-wrapper" id="con-wrapper1" style="display: block;">
            <div class="row form-wrapper">
                <div class="row show-grid">
                    <div class="col-xs-3 form" style="background-color: white">
                        <label class="Validform_label" >项目名称</label>
                        <input id="pname" name="pname" maxlength="50" type="text" class="form-control"  datatype="*" />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>
                <div class="row show-grid">

                    <div class="col-xs-3 form" style="background-color: white">
                        <label class="Validform_label">项目经理</label>
                        <input id="username" name="username" maxlength="32" type="text" class="form-control"  datatype="*" />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>



                <div class="row show-grid">
                    <div class="col-xs-3 form form" style="background-color: white">
                        <label class="Validform_label" >开始时间</label>
                        <input id="dateby" name="dateby" type="text"
                               style="background-color:white;"
                               class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"errormsg="该字段不为空"datatype="*"/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">

                    <div class="col-xs-3 form form" style="background-color: white">
                        <label class="Validform_label" >结束时间</label>
                        <input id="dateend" name="dateend" type="text"
                               style="background-color:white;"
                               class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dateby\')}'})"errormsg="该字段不为空"datatype="*"/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 form" style="background-color: white">
                        <label class="Validform_label" >天数</label>
                        <input id="date" name="date" maxlength="32" type="text" class="form-control"style="background-color:white;"  datatype="*" readonly/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 form" style="background-color: white">
                        <label class="Validform_label" >级别</label>
                        <input id="level" name="level" maxlength="32" type="text" class="form-control"  />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>


                <div class="row show-grid">
                    <div class="col-xs-3 form" style="background-color: white">
                        <label class="Validform_label" >备注</label>
                        <input id="remarks" name="remarks" maxlength="32" type="text" class="form-control"  ignore="ignore" />
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>


                <%--<div class="row show-grid"style="display: none;">--%>
                    <%--<div class="col-xs-3 form">--%>
                        <%--<label class="Validform_label" >状态</label>--%>
                        <%--<input id="statese" name="statese" maxlength="32" type="text" class="form-control"  ignore="ignore" value="0"/>--%>
                        <%--<span class="Validform_checktip" style="float:left;height:0px;"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>


                <div class="row show-grid">

                    <div class="col-xs-3 form">
                        <label class="Validform_label" >进度</label>
                        <input id="process" name="process" maxlength="32" type="text" class="form-control"  ignore="ignore" value="0"/>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>

                    </div>
                </div>
                <br/>
                <br/>
                <button type="button" onclick="neibuClick();" class="btn btn-default" >提交</button>
            </div>

          </div>

        </div>

        <div  class="get-members">
            <span style="background-color: white">选择成员：</span>
            <t:selectZTree id="citySel" url="crrcDepartztreeController.do?getTreeData" windowWidth="165px" windowHeight="27px" selectCascadeChildren="true" cancelCascadeParent="true" cancelCascadeChildren="true" selectCascadeParent="true"></t:selectZTree>
            <%--<fieldset class="step"style="padding-bottom: 20px;">--%>

                <%--<table>--%>

                    <%--<tr>--%>

                        <%--<td class="value"><span class="Validform_checktip"></span></td>--%>
                    <%--</tr>--%>


                <%--</table>--%>
            <%--</fieldset>--%>
            <%--<fieldset>--%>

                <%--<div style="clear:both"></div>--%>
                <%--<div class="zTreeDemoBackground left">--%>
                    <%--<ul id="treeDemo" class="ztree"></ul>--%>
                <%--</div>--%>
            <%--</fieldset>--%>
                <%--<ul id="orgTree" class="ztree"></ul>--%>

            <%----%>
                <%--<div id="orgTree" class="ztree" style="clear:both"></div>--%>

        </div>




</fieldset>
</t:formvalid>

<script type="text/javascript">
    $(function(){
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
        // 	//其他模式显示提交按钮
        //
        // }
        $("#sub_tr").show();
    });

    var neibuClickFlag = false;
    function neibuClick() {
        var  a = $("#citySel").val();
       alert(a) ;
        // neibuClickFlag = true;
        // $('#btn_sub').trigger('click');
    $.ajax({
        type: "POST",//请求方式
        url: "planController.do?doAdd",//发送请求的地址
        data: {id:$("#id").val(), pname:$("#pname").val()
            , username:$("#username").val()
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
    }
    $('#dateend').blur(function () {
        var t =document.getElementById('date');
        var tb =document.getElementById('dateby');
        var td=document.getElementById('dateend');
        var s1 = new Date(tb.value.replace(/-/g, "/"));
        var s2 = new Date(td.value.replace(/-/g, "/"));
        var time=((s2.getTime()-s1.getTime())/(1000*3600*24))+1;

            t.value=time;


    });
    <%--$(function() {--%>
        <%--//ztree--%>
        <%--var setting = {--%>
            <%--check: {--%>
                <%--enable: true--%>
            <%--},--%>
            <%--view: {--%>
                <%--dblClickExpand: true--%>
            <%--},--%>
            <%--data: {--%>
                <%--simpleData: {--%>
                    <%--enable: true--%>
                <%--}--%>
            <%--}--%>
        <%--};--%>
        <%--var zNodes=jQuery.parseJSON('${regions}');--%>
        <%--$.fn.zTree.init($("#treeDemo"), setting, zNodes);--%>
    <%--});--%>
    //加载树

   // $( function () {
   //     var orgTree ;
   //     alert("1111111111111");
   //     var zNodes;
   //      jQuery.ajax({
   //          async : false,
   //          cache:false,
   //          type: 'POST',
   //          dataType : "json",
   //          url: 'crrcDepartztreeController.do?getTreeDemoData',//请求的action路径
   //          error: function () {//请求失败处理函数
   //              alert('请求失败');
   //          },
   //          success:function(data){ //请求成功后处理函数。
   //              console.log(data.obj)
   //              zNodes = data.obj;   //把后台封装好的简单Json格式赋给zNodes
   //              alert(zNodes);
   //          }
   //      });
   //      var setting = {
   //          check: {
   //              enable: false,
   //
   //          },
   //          view: {
   //              nameIsHTML: true,
   //              selectedMulti: true,
   //              showIconFont:true,
   //              showIcon: null
   //          },
   //          data: {
   //              simpleData: {
   //                  enable: true
   //              }
   //          }
   //      };
   //
   //     var ztreeCreator = new ZtreeCreator('orgTree',"crrcDepartztreeController.do?getTreeData",zNodes).setCheckboxType(true).initZtree(setting,function(treeObj){orgTree = treeObj});
   //     alert(ztreeCreator);
   //     //$.fn.zTree.init($("#orgTree"), setting, zNodes);
   //  });
</script>
</body>
<script src = "webpage/com/jeecg/crrc/plan/plan.js"></script>
</html>