<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/context/mytags.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> </title>
    <t:base type="jquery,easyui,tools"></t:base>
    <script type="text/javascript" src="plug-in/echart/echarts.js"></script>
    <style type="text/css">

        .container{
            width: auto;
            height:auto;
        }
        .div1{
            width: 75%;
            height:520px;
            float: left;
            position : relative;
            display: inline;
            /*margin-left:30px;*/
            background: rgba(238, 238, 238, 0.8) none repeat scroll 0 0 !important;

        }
        .div2{
            width: 55%;
            height:450px;
            float: left;
            position : relative;
            display: inline;
            margin-left:30px;
            background: rgba(238, 238, 238, 0.8) none repeat scroll 0 0 !important;
        }
        .form{
            line-height:40px;
            font-size: 15px;
            width: 88%;
            height: 40px;
            margin-left:10px;
            margin-top: 11px;
            border: 6px solid rgba(231, 234, 237, 0.5);
            background: #e7e7e7;
            border-radius:5px 5px 5px 5px;
            text-indent: 30px;
        }
        input{
            width:200px;
            float:right;
            background-color: #fbfbff;

        }
        #uSex{

            width:200px;.
            float:right;
            background-color: #fbfbff;


        }
        .bule{
            /*text-align: center;*/
            height: 26px;
            border-radius:5px 5px 5px 5px;
        }


        /*  dl-main-nav开始  */
        .dl-main-nav {
            height: 40px;
            overflow: hidden;
            position: relative;
            background-color: #204077;
        }
        /*  nav-list开始  */
        ul {
            list-style: none;
        }

        ul, ol {
            padding: 0;
            margin: 0;
        }

        .dl-main-nav .nav-list {
            border-bottom: 1px solid #FFF;
            border-bottom-width: 1px;
            overflow: hidden;
            border-width: 0;
            float:left;
        }

        /*  nav-item开始  */
        .nav-list .nav-item {
            float: left;
            color: #D4D4D4;
        }

        .nav-item {
            overflow: hidden;
            font-size: 14px;
            color: white;
            position: relative;
            width: 130px;
            padding: 6px 0 0 8px;
            cursor: pointer;
        }

        li {
            line-height: 22px;
        }

        /*  nav-item-inner-1开始  */
        .nav-list .dl-selected .nav-item-inner {
            -webkit-border-radius: 2px 2px 0 0;
            -moz-border-radius: 2px 2px 0 0;
            border-radius: 2px 2px 0 0;
            border: 1px solid #FFF;
            background-color: #E8E9EE;
            color: #43478e;
            font-weight: bold;
            height: 30px;
        }

        .dl-selected .nav-home, .dl4-hide-list .nav-home {
            background-position: 4px -424px;
        }

        .nav-item-inner {
            padding: 4px 0 0 31px;
            height: 30px;
            border: 1px solid transparent;
        }

        .nav-item-inner, .dl4-hide-list .dl-hover {
            background: transparent;
        }

        /*  nav-item-inner-2开始  */
        .nav-list .dl-selected .nav-item-mask {
            display: none;
        }

        .nav-item-mask {
            display: none;
            width: 128px;
            height: 25px;
            position: absolute;
            background-color: white;
            top: 8px;
            opacity: .15;
            filter: alpha(opacity=15);
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
            border: 1px solid #333;
        }



        /*  dl-main-nav结束  */
        /*******************************************************************/
        /* dl-tab-conten开始  */
        .tab-nav-bar {
            background-color: #E8E9EE;
            position: relative;
            width: 100%;
            z-index: 1;
            overflow: hidden;
            height: 21px;
            background-position: 0 20px;
            background-repeat: repeat-x;
        }

        .z-index1{
            z-index: 1;
        }
        .hidepage{
            display: none;
        }
        /* dl-tab-conten结束  */
        /*  content结束  */
    </style>
</head>
<body style="overflow: Scroll;overflow-x:hidden;position: fixed">
<div class="content">
    <div class="dl-main-nav">
        <ul class="nav-list">
            <li class="nav-item dl-selected" id="showProject">
                <div class="nav-item-inner">人员信息</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>
        <ul class="nav-list">
            <li class="nav-item" id="showPlan">
                <div class="nav-item-inner">工作明细</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>

    </div>
    <div class="dl-inner-tab">
        <div class="bui-nav-tab" style="height:  650px;">
            <div class="tab-content-container ">
                <div class="tab-content mainshowProject">
                    <div class="container">
                        <div class="div1" style="border:1px solid #C0C0C0;border-radius:5px 5px 5px 5px">
                            <div class="form">
                                <label class="Validform_label"> 人员名称: </label>
                                <input type="text"  name="pname" class="bule" readonly value="${user1.getUName()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">人员年龄: </label>
                                <input type="text"  name="username" class="bule" readonly value="${user1.getUAge()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label"> 人员性别: </label>
                                <%--<input type="text"  name="stime" class="bule"readonly value="${user1.getUSex()}" />--%>
                                <t:dictSelect id="uSex" field="uSex" typeGroupCode="User_sex" defaultVal="${user1.getUSex()}"></t:dictSelect>
                            </div>
                            <div class="form">
                                <label class="Validform_label"> 电话: </label>
                                <input type="text" name="etime" class="bule"readonly value="${user1.getUIphone()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">邮件: </label>
                                <input type="text" name="mobile" class="bule"readonly value="${user1.getUEmail()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">职位: </label>
                                <input type="text" name="fax" class="bule" readonly value="${user1.getUPositionid()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">部门: </label>
                                <input type="text" name="process" class="bule" readonly value="${user1.getUDepartname()}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">居住地址: </label>
                                <input type="text" name="process" class="bule" readonly value="${user1.getUAddress()}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-content mainshowPlan hidepage">
                    <iframe name="roomFrame" src="crrcDepartztreeController.do?rng&id=${user1.getId()}" width="100%" height="650px" frameborder="0"></iframe>
                </div>

            </div>
        </div>
    </div>
</div>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".nav-item").click(function () {
                $(".nav-item").removeClass("dl-selected");
                $(this).addClass("dl-selected");
                var content = $(this).attr("id");
                $(".tab-content").removeClass("z-index1");
                $(".tab-content").addClass("hidepage");
                $(".main"+content).removeClass("hidepage");
            });
        });
        <%--var echartsWarp= document.getElementById('main');--%>
        <%--var resizeWorldMapContainer = function () {//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽--%>
            <%--echartsWarp.style.width = window.innerWidth-620+'px';--%>
            <%--echartsWarp.style.height = window.innerHeight-20+'px';--%>
            <%--// echartsWarp.style.width =$("#tu").width-20+'px';--%>
            <%--// echartsWarp.style.height =$("#tu").height-20+'px';--%>
        <%--};--%>
        <%--resizeWorldMapContainer ();//设置容器高宽--%>
        <%--var myChart = echarts.init(echartsWarp);--%>

        <%--option = {--%>
            <%--tooltip : {--%>
                <%--formatter: "{a} <br/>{b} : {c}%"--%>
            <%--},--%>
            <%--toolbox: {--%>
                <%--feature: {--%>
                    <%--restore: {},--%>
                    <%--saveAsImage: {}--%>
                <%--}--%>
            <%--},--%>
            <%--series: [--%>
                <%--{--%>
                    <%--name: '业务指标',--%>
                    <%--type: 'gauge',--%>
                    <%--detail: {formatter:'{value}%'},--%>
                    <%--data: [{value:0, name: '进度'}]--%>
                <%--}--%>
            <%--]--%>
        <%--};--%>

        <%--setInterval(function () {--%>
            <%--option.series[0].data[0].value =${plan.process};--% >
            <%--myChart.setOption(option, true);--%>
        <%--},1000);--%>


        <%--myChart.setOption(option);--%>
    </script>
</body>
</html>