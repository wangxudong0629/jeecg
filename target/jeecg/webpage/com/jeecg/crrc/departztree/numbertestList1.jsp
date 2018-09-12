<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<t:base type="jquery,easyui,tools"></t:base>
<style type="text/css">
 #body{
  height:1000px;
  width:100%;

 }

 .body_one{
  height:900px;
  width:100%;
 }
 .ssplus{

     position: relative;
     left: 30px;
     top:45px;
     height:199px;
     width: 200px;
     float:left;
     overflow:auto;
     overflow:hidden;
     margin-right: 40px;
     margin-bottom: 40px;
     box-shadow: 1px 1px 5px 5px #888888;
     border-radius: 10px;
     background:url("images/number2.jpg");
     transition: All 0.4s ease-in-out;
     -webkit-transition: All 0.4s ease-in-out;
     -moz-transition: All 0.4s ease-in-out;
     -o-transition: All 0.4s ease-in-out;

 }
 .ssplus:hover{
     transform: scale(1.1);
     -webkit-transform: scale(1.1);
     -moz-transform: scale(1.1);
     -o-transform: scale(1.1);
     -ms-transform: scale(1.1);
 }
 .ss{

      height:199px;
      width: 200px;
      float:left;
      overflow-y:scroll;
      overflow-x:hidden;
      padding-right:50px;
      margin-right: 18px;
      margin-bottom: 18px;

 }
 ul{

  list-style:none;
  height:70px;
  width:100px;
  position:relative;
  top:25px;
  left:80px;
  bottom:80px;
 }
 li{
  width:100px;;
  font-size:10px;
  text-indent:-5em;
  margin-bottom: 10px;
 }

</style>

<body onload="show()">
<div class="easyui-layout" fit="true" >
  <div region="center" style="padding:0px;border:0px">

   <div class="body_one">

   </div>
  </div>
 </div>
</body>
<script type="text/javascript">


    function show(){
        //var name = URLEncoder.encode(${user.getUName()});
        var url ="crrcDepartztreeController.do?item&name=${user.getUName()}";
        alert(url);
        $.getJSON(url,function (result){
            $('.body_one').html("");

            $.each(result,function(index,demoProjectList){
                var item="";

                item="<div class=\"ssplus\"><div class=\"ss\"><ul><li>工作名称："+demoProjectList.work+"</li><li>备注：&emsp;&emsp;"+demoProjectList.remarks+"</li></ul></div></div>"
                $('.body_one').append(item);
            // <li>开始时间："+demoProjectList.uname+"</li><li>结束时间："
            //     +demoProjectList.uname+"</li>
            });
            alert(item);
        });

    }
</script>
   
