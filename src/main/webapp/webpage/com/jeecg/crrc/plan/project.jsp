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
            width: 35%;
            height:450px;
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
<body style="overflow:Scroll;overflow-x:hidden;position: fixed">
<div class="content">
    <div class="dl-main-nav">
        <ul class="nav-list">
            <li class="nav-item dl-selected" id="showProject">
                <div class="nav-item-inner">项目详情</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>
        <ul class="nav-list">
            <li class="nav-item" id="showPlan">
                <div class="nav-item-inner">计划</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>
        <ul class="nav-list">
            <li class="nav-item" id="showConsumer">
                <div class="nav-item-inner">客户信息</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>
        <ul class="nav-list">
            <li class="nav-item" id="showPapers">
                <div class="nav-item-inner">文件</div>
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
                                <label class="Validform_label"> 项目名称: </label>
                                <input type="text"  name="pname" class="bule" readonly value="${plans.pname}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">项目经理: </label>
                                <select class="getUser" id="getUser" style="width:175px;height: 30px">
                                    <option>--请选择--</option>
                                    <c:forEach items="${manager}" var="p">
                                        <option id="username">${p.getUName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form">
                                <label class="Validform_label"> 项目开始时间: </label>
                                <input type="text"  name="stime" class="bule"readonly value="<fmt:formatDate value='${plans.dateby}' type="date" pattern="yyyy-MM-dd"/>"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label"> 项目结束时间: </label>
                                <input type="text" name="etime" class="bule"readonly value="<fmt:formatDate value='${plans.dateend}' type="date" pattern="yyyy-MM-dd"/>"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">项目级别: </label>
                                <input type="text" name="mobile" class="bule"readonly value="${plans.level}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">备注: </label>
                                <input type="text" name="fax" class="bule" readonly value="${plans.remarks}"/>
                            </div>
                            <div class="form">
                                <label class="Validform_label">进度: </label>
                                <input type="text" name="process" class="bule" readonly value="${plans.process}%"/>
                            </div>
                        </div>
                        <div class="div2" id="tu" style="border:1px solid #C0C0C0;border-radius:5px 5px 5px 5px;position: relative;">
                            <button onclick="showGanttFull()" style="line-height: 18px;height:25px;font-size: 20px;float: right;position: absolute;right: 0">□</button>
                            <iframe name="roomFrame" src="planController.do?jQgantt&id=${plans.id}" width="100%" height="100%" frameborder="0"></iframe>
                        </div>
                    </div>
                </div>
                <div class="tab-content mainshowPlan hidepage">
                    <iframe name="roomFrame" src="planController.do?rng&id=${plans.id}" width="100%" height="650px" frameborder="0"></iframe>
                </div>
                <div class="tab-content mainshowConsumer hidepage">

                    <iframe name="roomFrame" src="consumerController.do?lgd&id=${plans.id}" width="100%" height="650px" frameborder="0"></iframe>
                </div>
                <div class="tab-content mainshowPapers hidepage">
                    <iframe name="roomFrame" src="papersController.do?list&id=${plans.id}" width="100%" height="650px" frameborder="0"></iframe>
                </div>

                <div id="showGanttFull" style="display: none">
                    <iframe name="roomFrame" src="planController.do?gantt&id=${plans.id}&number=0" width="100%" height="100%" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var $parent = self.parent.$;
        function showGanttFull() {
            $parent('#pwin').window({
                modal: true,
                height: $(window).height() -30,
                width: $(window).width()+170,
                content: $('#showGanttFull').html(),
                title: '${plan.pname}',
            });
        }
        $(function () {
            $(".nav-item").click(function () {
                $(".nav-item").removeClass("dl-selected");
                $(this).addClass("dl-selected");
                var content = $(this).attr("id");
                alert("content"+content);
                $(".tab-content").removeClass("z-index1");
                $(".tab-content").addClass("hidepage");
                $(".main"+content).removeClass("hidepage");
                alert(".main+content---"+content);
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
            <%--option.series[0].data[0].value =${plan.process};--%>
            <%--myChart.setOption(option, true);--%>
        <%--},1000);--%>


        <%--myChart.setOption(option);--%>

        <%--function modSampleHeight(){--%>
                <%--var headHeight = 100;--%>
                <%--var sch = document.getElementById("gantt_here");--%>
                <%--sch.style.height = (parseInt(document.body.offsetHeight)-headHeight)+"px";--%>
                <%--var contbox = document.getElementById("contbox");--%>
                <%--contbox.style.width = (parseInt(document.body.offsetWidth)-300)+"px";--%>

                <%--gantt.setSizes();--%>
        <%--}--%>

        <%--$(function() {--%>
            <%--loadGantt();--%>
        <%--});--%>
        <%--function loadGantt() {--%>
            <%--jQuery.ajax({--%>
                <%--async : false,--%>
                <%--cache:false,--%>
                <%--type: 'POST',--%>
                <%--dataType : "json",--%>
                <%--&lt;%&ndash;data:{id:${plan.id}},&ndash;%&gt;--%>
                <%--url: 'planController.do?getGanttData',//请求的action路径--%>
                <%--error: function () {//请求失败处理函数--%>
                <%--},--%>
                <%--success:function(tasks){ //请求成功后处理函数。--%>
                    <%--showGantt(tasks);--%>
                <%--}--%>
            <%--});--%>
        <%--};--%>

        <%--function updateGantt() {--%>
            <%--var ganttData=$("#updatePlan").html();--%>
            <%--jQuery.ajax({--%>
                <%--async : false,--%>
                <%--cache:false,--%>
                <%--type: 'POST',--%>
                <%--data:'ganttData='+ganttData,--%>
                <%--dataType : "json",--%>
                <%--url: 'planController.do?updateGanttData'--%>
            <%--});--%>
        <%--};--%>

        <%--function  showGantt(demo_tasks) {--%>

            <%--//日--%>
            <%--gantt.config.scale_unit = "month";--%>
            <%--gantt.config.date_scale = "%F, %Y";--%>

            <%--gantt.config.scale_height = 50;--%>
            <%--gantt.config.subscales = [--%>
                <%--{unit:"day", step:1, date:"%j" }--%>
            <%--];--%>
            <%--gantt.config.min_column_width = 20;--%>

            <%--gantt.config.xml_date = "%Y-%m-%d";--%>

            <%--//将今天线划出--%>
            <%--var date_to_str = gantt.date.date_to_str(gantt.config.task_date);--%>
            <%--var today = new Date();--%>
            <%--gantt.addMarker({--%>
                <%--start_date: today,--%>
                <%--css: "today",--%>
                <%--text: "今天",--%>
                <%--title:"今天: "+ date_to_str(today)--%>
            <%--});--%>


            <%--gantt.templates.scale_cell_class = function(date){--%>
                <%--if(date.getDay()==0||date.getDay()==6){--%>
                    <%--return "weekend";--%>
                <%--}--%>
            <%--};--%>
            <%--gantt.templates.task_cell_class = function(item,date){--%>
                <%--if(date.getDay()==0||date.getDay()==6){--%>
                    <%--return "weekend" ;--%>
                <%--}--%>
            <%--};--%>

            <%--gantt.templates.task_text=function(start,end,task){--%>
                <%--return task.text+" "+parseInt(task.progress*100)+"%"+"${userName }";--%>
            <%--};--%>

            <%--gantt.config.columns = [--%>
                <%--{name:"text", label:"计划名称", width:"200px", tree:true },--%>
                <%--{name:"start_time", label:"开始日期", template:function(obj){--%>
                        <%--return gantt.templates.date_grid(obj.start_date);--%>
                    <%--}, align: "center", width:120 },--%>
                <%--{name:"end_time", label:"结束日期", template:function(obj){--%>
                        <%--var t=obj.end_date.getTime()-1000*60*60*24;--%>
                        <%--var lt=new Date(t);--%>
                        <%--return gantt.templates.date_grid(lt);--%>
                    <%--}, align: "center", width:120 },--%>
                <%--{name:"duration", label:"天数", align:"center", width:60},--%>

                <%--{name:"add", label:"", width:44 }--%>
            <%--];--%>

            <%--gantt.init("gantt_here");--%>
            <%--modSampleHeight();--%>
            <%--gantt.parse(demo_tasks);--%>



            <%--/*gantt.attachEvent("onTaskClick", function(id, e) {--%>
                <%--var task = gantt.getTask(id);--%>
                <%--gantt.message(task.text + " is onClick!");--%>
            <%--});*/--%>
            <%--gantt.attachEvent("onAfterTaskDrag", function (id, mode) {--%>
                <%--var task = gantt.getTask(id);--%>
                <%--if (mode == gantt.config.drag_mode.progress) {--%>
                    <%--var pr = Math.floor(task.progress * 100 * 10) / 10;--%>
                    <%--// gantt.message(task.text + " is now " + pr + "% completed!");--%>
                <%--} else {--%>
                    <%--var s = gantt.templates.date_grid(task.start_date);--%>
                    <%--var e = gantt.templates.date_grid(task.end_date);--%>
                    <%--//gantt.message(task.id+task.text + " 开始时间：" + s + " 结束时间： " + e);--%>
                    <%--$("#updatePlan").append(task.id+"," + s + "," + e+",");--%>
                <%--}--%>
            <%--});--%>
            <%--/* gantt.attachEvent("onBeforeTaskChanged", function (id, mode, old_event) {--%>
                 <%--var task = gantt.getTask(id);--%>
                 <%--if (mode == gantt.config.drag_mode.progress) {--%>
                     <%--if (task.progress < old_event.progress) {--%>
                         <%--gantt.message(task.text + " progress can't be undone!");--%>
                         <%--return false;--%>
                     <%--}--%>
                 <%--}--%>
                 <%--return true;--%>
             <%--});--%>
             <%--gantt.attachEvent("onBeforeTaskDrag", function (id, mode) {--%>
                 <%--var task = gantt.getTask(id);--%>
                 <%--var message = task.text + " ";--%>

                 <%--if (mode == gantt.config.drag_mode.progress) {--%>
                     <%--message += "progress is being updated";--%>
                 <%--} else {--%>
                     <%--message += "is being ";--%>
                     <%--if (mode == gantt.config.drag_mode.move)--%>
                         <%--message += "moved";--%>
                     <%--else if (mode == gantt.config.drag_mode.resize)--%>
                         <%--message += "resized";--%>
                 <%--}--%>

                 <%--gantt.message(message);--%>
                 <%--return true;--%>
             <%--});*/--%>


            <%--gantt.detachEvent(eventId);--%>
        <%--}--%>

    </script>
</body>
</html>