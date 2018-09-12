<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <style type="text/css">

        body,div,p,ul,li,font,span,td,th{
            font-size:9pt;
            line-height:260%;

        }
        table{
            border-collapse: collapse;
            border-top-width: 1px;
            border-right-width: 1px;
            border-bottom-width: 0px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: none;
            border-left-style: solid;
            border-top-color: #CCCCCC;
            border-right-color: #CCCCCC;
            border-bottom-color: #CCCCCC;
            border-left-color: #CCCCCC;
            margin-top:7px;
        ;
        }
        th,td{
            border:solid #CCCCCC;
            border-width:0 1px 1px 0;
            padding:2px;
            text-align: center;
        }
        .EditCell_TextBox {
            width: 90%;
            border:1px solid #0099CC;
        }
        .EditCell_DropDownList {
            width: 90%;
        }
        .menu
        {
            margin: 0;
            margin-top: 0px;
            width: 100%;
            padding-left:3%;
            background-color: #C0C0C0;
            position: relative;
            display: block;
            height: 35px;
            font-family: "微软雅黑","Microsoft Yahei","Hiragino Sans GB",tahoma,arial,"宋体" ;
            font-size: 15px;
            text-decoration: none;
            color: #000000;
            vertical-align: middle;
        }
    </style>
</head>

<body>

<div class="menu"><b>项目</b></div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct">
    <tr>
        <!--<td width="32" align="center" bgcolor="#EFEFEF" Name="Num"><input type="checkbox" name="checkbox" value="checkbox"  /></td>-->
        <td width="5%" bgcolor="#EFEFEF" Name="Num" EditType="TextBox">序号</td>
        <td width="10%" bgcolor="#EFEFEF" Name="ProductName" EditType="TexBox" >名称</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Manager" EditType="TextBox">负责人</td>
        <td width="10%" bgcolor="#EFEFEF" Name="StartTime" EditType="TextBox">开始时间</td>
        <td width="10%" bgcolor="#EFEFEF" Name="EndTime" EditType="TextBox">结束时间</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Day" EditType="TextBox">天数</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Level" EditType="TextBox">级别</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Remark" EditType="TextBox">备注</td>
        <td width="10%" bgcolor="#EFEFEF" Name="State" EditType="TextBox">状态</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Speed" EditType="TextBox">进度</td>
        <td width="10%" bgcolor="#EFEFEF" Name="Operation" EditType="TextBox">操作</td>
    </tr>
    <c:forEach items="${plans}" var="plan" varStatus="li">
    <tr>
        <!--<td align="center" bgcolor="#FFFFFF"><input type="checkbox" name="checkbox2" value="checkbox" /></td>-->

            <td>${li.index+1}</td>
            <td>${plan.pname}</td>
            <td>${plan.username}</td>
        <%--<td></td>--%>
        <%--<td></td>--%>
            <td>${plan.dateby}</td>
            <td>${plan.dateend}</td>
            <td>${plan.date}</td>
            <td>${plan.level}</td>
            <td>${plan.remarks}</td>
            <td>${plan.state}</td>
            <td>${plan.process}</td>

            <td></td>

    </tr>
    </c:forEach>
</table>

</body>
</html>