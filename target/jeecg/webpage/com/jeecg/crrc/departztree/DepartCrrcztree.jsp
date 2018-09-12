<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>ztree</title>
    <t:base type="jquery,easyui"></t:base>
    <link rel="stylesheet" href="plug-in/ztree/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="plug-in/ztree/js/ztreeCreator.js"></script>
</head>
<body >

<div id="pwin"></div>
<div class="easyui-layout" fit="true" scroll="no">
    <div  data-options="region:'west',title:'人员机构管理',split:true" style="width:200px;overflow: auto;">
<div>
        <div style="width:105px;margin-left: 8px;margin-top: 2px;float: left">
            <a  icon="icon-add" class="easyui-linkbutton l-btn l-btn-plain"  onclick="addOneNode()">
                <span class="bigger-110 no-text-shadow" style="width: 50px;">添加公司</span>
            </a>
        </div>
        <div style="width:40px;margin-left: 8px;margin-top: 2px;float: left">
            <a   class="easyui-linkbutton l-btn l-btn-plain"  onclick="refreshNode()">
                <span class="bigger-110 no-text-shadow" style="width: 50px;">刷新</span>
            </a>
        </div>
    </div>
        <div id="orgTree" class="ztree" style="clear:both"></div>
    </div>
    <div data-options="region:'center',border:false" style="text-align: center;">
        <iframe id="listFrame" src="" frameborder="no" width="100%" height="100%"></iframe>
        <div id="cc" tabPosition="top" border=flase style="width:100%;height:100%;margin:0px;padding:0px;overflow-x:hidden;width:auto;" class="easyui-tabs" fit="true"></div>
    </div>
    <div class="hidden">
        <div id="orgMenu" class="easyui-menu" data-options="onClick:menuHandler" style="width: 120px;">
            <div data-options="name:'add'">添加子机构</div>
            <%--<div data-options="name:'add2'">添加人员</div>--%>
            <%--<div data-options="name:'edit'">编辑当前</div>--%>
            <div data-options="name:'remove'">删除当前</div>

        </div>
    </div>
</div>
</body>
</html>
<script>
    //加载树
    var orgTree ;
    function loadTree() {
        var zNodes;
        jQuery.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            url: 'crrcDepartztreeController.do?getTreeDemoData',//请求的action路径
            error: function () {//请求失败处理函数
                alert('请求失败');
            },
            success:function(data){ //请求成功后处理函数。
                console.log(data.obj)
                zNodes = data.obj;   //把后台封装好的简单Json格式赋给zNodes
            }
        });
        var ztreeCreator = new ZtreeCreator('orgTree',"crrcDepartztreeController.do?getTreeData",zNodes)
            .setCallback({onClick:zTreeOnLeftClick,onRightClick:zTreeOnRightClick})
            .initZtree({},function(treeObj){orgTree = treeObj});
    };

    //左击
    function zTreeOnLeftClick(event, treeId, treeNode) {
        curSelectNode = treeNode;
        var parentId = treeNode.id;
        var url1 = "crrcDepartztreeController.do?we&id=" + curSelectNode.id;
        var url2 = "crrcDepartztreeController.do?ig&id=" + curSelectNode.id;
        if(curSelectNode.parentId=="0"){
            $("#listFrame").attr("src", url2);
        }else {
            $("#listFrame").attr("src", url1);
        }
    };
    /**
     * 树右击事件
     */
    function zTreeOnRightClick(e, treeId, treeNode) {
        if (treeNode) {
            orgTree.selectNode(treeNode);
            curSelectNode=treeNode;
            var isfolder = treeNode.isFolder;
            var h = $(window).height();
            var w = $(window).width();
            var menuWidth = 120;
            var menuHeight = 75;
            var menu = null;
            if (treeNode != null) {
                menu = $('#orgMenu');
            }
            var x = e.pageX, y = e.pageY;
            if (e.pageY + menuHeight > h) {
                y = e.pageY - menuHeight;
            }
            if (e.pageX + menuWidth > w) {
                x = e.pageX - menuWidth;
            }
            menu.menu('show', {
                left : x,
                top : y
            });
        }
    };
    //菜单对应项
    function menuHandler(item) {
        if ('add' == item.name) {
            addNode();
        } else if ('remove' == item.name) {
            delNode();
        } else if ('sort' == item.name) {
            sortNode();
        // } else if ('edit' == item.name) {
        //     editNode(1);
        // } else if ('add2' == item.name) {
        //     addNode2();
        }
    };
    function refreshNode() {
        loadTree();
    };
    //添加一级节点
    function addOneNode() {
        var url = "crrcDepartztreeController.do?toAddproject";
        $("#listFrame").attr("src", url);
        // addtt('添加项目', url, '01','icon-search', 'false');
    };

    //添加机构
    function addNode() {
        var selectNode = getSelectNode();
        if (!selectNode) 	return;
        var url = "crrcDepartztreeController.do?goAdd1&id=" + selectNode.id;
        $("#listFrame").attr("src", url);

    };
    // //添加人员
    // function addNode2() {
    //     var selectNode = getSelectNode();
    //     if (!selectNode) 	return;
    //     var url = "crrcDepartztreeController.do?goAdd&id=" + selectNode.id;
    //     $("#listFrame").attr("src", url);
    //
    // };

    // //编辑节点
    // function editNode(type) {
    //     var selectNode = getSelectNode();
    //     if (!selectNode) 	return;
    //     //根节点 不能编辑
    //     if(selectNode.id=="0"  ){
    //         var url = "crrcDepartztreeController.do?goUpdate1&id=" + selectNode.id;
    //         $("#listFrame").attr("src", url);
    //     }else {
    //     var url = "crrcDepartztreeController.do?goUpdate&id=" + selectNode.id;
    //     $("#listFrame").attr("src", url);
    //     }
    // };


    //删除
    function delNode() {
        var selectNode = getSelectNode();

        var nodeId = selectNode.id;

        var url = "crrcDepartztreeController.do?checkifhaveparent&id=" + nodeId;
        jQuery.ajax({
            async: false,
            cache: false,
            type: 'GET',
            dataType: "json",
            url: url,//请求的action路径

            success: function (data) { //请求成功后处理函数。
                if (data.success) {
                    // if (data.msg == "0") {
                    //     // $.topCall.warn('该节点为根节点，不可删除');
                    //     $.messager.alert('提示','总项目不可删除','info');
                    //     return;
                    // }else {
                    if(data.msg=="1"){
                        var url = "crrcDepartztreeController.do?del&id=" + nodeId;
                        $.messager.confirm('温馨提示', '确定删除该组织？',function (data) {
                            if (data) {

                            }
                            else {
                                return;
                            }
                            jQuery.ajax({
                                async: false,
                                cache: false,
                                type: 'GET',
                                dataType: "json",
                                url: url,//请求的action路径
                                success: function (data) { //请求成功后处理函数。
                                    if (data.success) {
                                        orgTree.removeNode(selectNode);
                                        $("#listFrame").attr("src", "about:blank");
                                        $.messager.alert('提示', data.msg, 'info');
                                    } else {
                                        $.messager.alert('提示', "操作失败", 'info');
                                    }
                                }
                            });
                        })

                    }else if(data.msg=="2"){
                        var url = "crrcDepartztreeController.do?del2&id=" + nodeId;
                        $.messager.confirm('温馨提示', '确定删除该组织以及其子组织？',function (data) {
                            if (data) {

                            }
                            else {
                                return;
                            }
                            jQuery.ajax({
                                async: false,
                                cache: false,
                                type: 'GET',
                                dataType: "json",
                                url: url,//请求的action路径
                                success: function (data) { //请求成功后处理函数。
                                    if (data.success) {
                                        orgTree.removeNode(selectNode);
                                        $("#listFrame").attr("src", "about:blank");
                                        $.messager.alert('提示', data.msg, 'info');
                                    } else {
                                        $.messager.alert('提示', "操作失败", 'info');
                                    }
                                }
                            });
                        })
                    }



                }
                // }
            }
        });

    };
    //选择资源节点。
    function getSelectNode() {
        orgTree = $.fn.zTree.getZTreeObj("orgTree");
        var nodes = orgTree.getSelectedNodes();
        var node = nodes[0];
        return node;
    };
    /**
     * 获取表格对象
     * @return 表格对象
     */
    function getDataGrid(){
        var datagrid = $('#'+gridname);
        return datagrid;
    }

</script>
<script>
    $(function() {
        loadTree();
    });

</script>
