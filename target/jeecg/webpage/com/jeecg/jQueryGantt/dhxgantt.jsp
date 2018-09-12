<%@ taglib prefix="c" uri="/jodd" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.commons.lang.time.DateFormatUtils"%>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>甘特图</title>
    <script src="plug-in/jquery/jquery-1.8.3.js"></script>
    <script src="plug-in/gantt-master/codebase/dhtmlxgantt.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="plug-in/gantt-master/codebase/skins/dhtmlxgantt_terrace.css" type="text/css" media="screen" title="no title" charset="utf-8">
    <script src="plug-in/gantt-master/codebase/locale/locale_cn.js"></script>
    <script src="plug-in/gantt-master/codebase/ext/dhtmlxgantt_marker.js"></script>
    <style type="text/css" media="screen">
        html, body{
            margin:0px;
            padding:0px;
            height:100%;
            overflow:hidden;
        }
        .weekend{ background: #f4f7f4 !important;}
        .gantt_selected .weekend{
            background:#FFF3A1 !important;
        }
    </style>
</head>
<body onresize="modSampleHeight()">
<%--<!--[if lte IE 7]>
<style type="text/css">div{display:none;}</style>
<h4 style='text-align:center; font-family:Arial; margin-top:50px;'>Unfortunately dhtmlxGantt 2.0 doesn't support IE6 and IE7 browsers.<br>Please open these demos in different browser or in IE8+.</h3>
<![endif]-->--%>
<script>
    function modSampleHeight(){
        var headHeight = 100;
        var sch = document.getElementById("gantt_here");
        sch.style.height = parseInt(document.body.offsetHeight)+"px";
        var contbox = document.getElementById("contbox");
        contbox.style.width = (parseInt(document.body.offsetWidth)-300)+"px";

        gantt.setSizes();
    }
</script>
<div id="contbox" style="float:left;display:none;color:white;margin:22px 75px 0 75px; overflow:hidden;font: 17px Arial,Helvetica;color:white">
    <%--<p id="updatePlan" style="color: skyblue"></p>--%>
    <%--<button onclick="updateGantt()">保存</button>--%>
</div>
<div id="gantt_here" style='width:100%; height:100%;'></div>
<script type="text/javascript">

    $(function() {
        loadGantt();
    });
    function loadGantt() {
        jQuery.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            data:{id:'${plan.getId()}'},
            url: 'planController.do?getGanttData',//请求的action路径
            error: function () {//请求失败处理函数
            },
            success:function(tasks){ //请求成功后处理函数。
                showGantt(tasks);
            }
        });
    };

    function updateGantt() {
        var ganttData=$("#updatePlan").html();
        jQuery.ajax({
            async : false,
            cache:false,
            type: 'POST',
            data:'ganttData='+ganttData,
            dataType : "json",
            url: 'planController.do?updateGanttData'
        });
    };

    function  showGantt(demo_tasks) {

        //日
        gantt.config.scale_unit = "month";
        gantt.config.date_scale = "%F, %Y";

        gantt.config.scale_height = 50;
        gantt.config.subscales = [
            {unit:"day", step:1, date:"%j" }
        ];
        gantt.config.min_column_width = 20;

        gantt.config.xml_date = "%Y-%m-%d";

        //将今天线划出
        var date_to_str = gantt.date.date_to_str(gantt.config.task_date);
        var today = new Date();
        gantt.addMarker({
            start_date: today,
            css: "today",
            text: "今天",
            title:"今天: "+ date_to_str(today)
        });


        gantt.templates.scale_cell_class = function(date){
            if(date.getDay()==0||date.getDay()==6){
                return "weekend";
            }
        };
        gantt.templates.task_cell_class = function(item,date){
            if(date.getDay()==0||date.getDay()==6){
                return "weekend" ;
            }
        };

        gantt.templates.task_text=function(start,end,task){
            return task.text+" "+parseInt(task.progress*100)+"%"+"${userName }";
        };

        gantt.config.columns = [
            // {name:"text", label:"计划名称", width:"200px", tree:true },
            // {name:"start_time", label:"开始日期", template:function(obj){
            //         return gantt.templates.date_grid(obj.start_date);
            //     }, align: "center", width:120 },
            // {name:"end_time", label:"结束日期", template:function(obj){
            //         var t=obj.end_date.getTime()-1000*60*60*24;
            //         var lt=new Date(t);
            //         return gantt.templates.date_grid(lt);
            //     }, align: "center", width:120 },
            // {name:"duration", label:"天数", align:"center", width:60},
            //
            // {name:"add", label:"", width:44 }
        ];

        gantt.init("gantt_here");
        modSampleHeight();
        gantt.parse(demo_tasks);



        /*gantt.attachEvent("onTaskClick", function(id, e) {
            var task = gantt.getTask(id);
            gantt.message(task.text + " is onClick!");
        });*/
        gantt.attachEvent("onAfterTaskDrag", function (id, mode) {
            var task = gantt.getTask(id);
            if (mode == gantt.config.drag_mode.progress) {
                var pr = Math.floor(task.progress * 100 * 10) / 10;
               // gantt.message(task.text + " is now " + pr + "% completed!");
            } else {
                var s = gantt.templates.date_grid(task.start_date);
                var e = gantt.templates.date_grid(task.end_date);
                //gantt.message(task.id+task.text + " 开始时间：" + s + " 结束时间： " + e);
                $("#updatePlan").append(task.id+"," + s + "," + e+",");
            }
        });
       /* gantt.attachEvent("onBeforeTaskChanged", function (id, mode, old_event) {
            var task = gantt.getTask(id);
            if (mode == gantt.config.drag_mode.progress) {
                if (task.progress < old_event.progress) {
                    gantt.message(task.text + " progress can't be undone!");
                    return false;
                }
            }
            return true;
        });
        gantt.attachEvent("onBeforeTaskDrag", function (id, mode) {
            var task = gantt.getTask(id);
            var message = task.text + " ";

            if (mode == gantt.config.drag_mode.progress) {
                message += "progress is being updated";
            } else {
                message += "is being ";
                if (mode == gantt.config.drag_mode.move)
                    message += "moved";
                else if (mode == gantt.config.drag_mode.resize)
                    message += "resized";
            }

            gantt.message(message);
            return true;
        });*/


        gantt.detachEvent(eventId);
    }
</script>
</body>
</html>