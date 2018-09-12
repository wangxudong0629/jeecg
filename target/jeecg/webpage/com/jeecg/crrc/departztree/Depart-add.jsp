<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>公司组织架构</title>
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
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="crrcDepartController.do?doAdd" tiptype="1" >
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" id="id" name="id"/>
			<div class="tab-wrapper">
			    <!-- tab -->
			    <ul class="nav nav-tabs">
			      <li role="presentation" class="active"><a href="javascript:void(0);">添加公司</a></li>
			    </ul>
			    <!-- tab内容 -->
			    <div class="con-wrapper" id="con-wrapper1" style="display: block;">
			      <div class="row form-wrapper">
							<div class="row show-grid">

			          		<div class="col-xs-3 form">
								<label class="Validform_label" >名字</label>
								<input id="dName" name="dName" maxlength="50" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
							</div>
			          </div>
			        
							<div class="row show-grid">

			          		<div class="col-xs-3 form">
								<label class="Validform_label" >组织电话</label>
								<input id="dIphone" name="dIphone" maxlength="50" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>

			          </div>
							</div>

					  <div class="row show-grid">
			          		<div class="col-xs-3 form">
								<label class="Validform_label" >组织邮件</label>
								<input id="dEmail" name="dEmail" maxlength="50" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>

			          </div>
							</div>
			          
			        
							<div class="row show-grid">

			          		<div class="col-xs-3 form">
								<label class="Validform_label" >备注</label>
								<input id="dRemarks" name="dRemarks" maxlength="500" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
							</div>
			          </div>


			          
			        
			          <div class="col-xs-3 text-center" style="display: none">
			          	<b>权限：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dAuthority" name="dAuthority" maxlength="50" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">权限</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid"style="display: none">
			          <div class="col-xs-3 text-center" >
			          	<b>预留1：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dNotel1" name="dNotel1" maxlength="32" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">预留1</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center" style="display: none">
			          	<b>预留2：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dNotel2" name="dNotel2" maxlength="32" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">预留2</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid"style="display: none">
			          <div class="col-xs-3 text-center" style="display: none">
			          	<b>预留3：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dNotel3" name="dNotel3" maxlength="32" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">预留3</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center" style="display: none">
			          	<b>预留4：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dNotel4" name="dNotel4" maxlength="32" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">预留4</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid"style="display: none">
			          <div class="col-xs-3 text-center" style="display: none">
			          	<b>预留5：</b>
			          </div>
			          		<div class="col-xs-3"style="display: none">
								<input id="dNotel5" name="dNotel5" maxlength="32" type="text" class="form-control"  ignore="ignore" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">预留5</label>
			          </div>
							<div class="col-xs-2 text-center"><b></b></div>
			         		<div class="col-xs-4"></div>
							</div>
			          
			        
			        
			       
			          <div class="row" id = "sub_tr" >
				        <div class="col-xs-12 layout-header">
				          <div class="col-xs-6"></div>
				          <div class="col-xs-6"style="width:100%; height:100%"><button type="button" onclick="neibuClick();" class="btn btn-default">提交</button></div>
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


  function neibuClick() {

      $.ajax({
          type: "POST",//请求方式
          url: "crrcDepartztreeController.do?doAdd",//发送请求的地址
          data: {id:$("#id").val(), dName:$("#dName").val()
              , dIphone:$("#dIphone").val()
              , dEmail:$("#dEmail").val(), dRemarks:$("#dRemarks").val()},
          dataType: "json",//预期服务器返回的类型
          // async: false,//设置为同步传输
          success: function(data) {
              // alert(data.msg);
              $.messager.alert('提示',data.msg,'info');
          }
      });
  }

</script>
 </body>
<script src = "webpage/com/jeecg/crrc/depart/crrcDepart.js"></script>		
</html>