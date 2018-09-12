<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addPlanForPlusChildBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delPlanForPlusChildBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addPlanForPlusChildBtn').bind('click', function(){   
 		 var tr =  $("#add_planForPlusChild_table_template tr").clone();
	 	 $("#add_planForPlusChild_table").append(tr);
	 	 resetTrNum('add_planForPlusChild_table');
	 	 return false;
    });  
	$('#delPlanForPlusChildBtn').bind('click', function(){   
		$("#add_planForPlusChild_table").find("input[name$='ck']:checked").parent().parent().remove();  
        resetTrNum('add_planForPlusChild_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addPlanForPlusChildBtn" href="#">添加</a> <a id="delPlanForPlusChildBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="planForPlusChild_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						工作
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						备注
				  </td>
	</tr>
	<tbody id="add_planForPlusChild_table">
	<c:if test="${fn:length(planForPlusChildList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="planForPlusChildList[0].id" type="hidden"/>
					<input name="planForPlusChildList[0].createName" type="hidden"/>
					<input name="planForPlusChildList[0].createBy" type="hidden"/>
					<input name="planForPlusChildList[0].createDate" type="hidden"/>
					<input name="planForPlusChildList[0].updateName" type="hidden"/>
					<input name="planForPlusChildList[0].updateBy" type="hidden"/>
					<input name="planForPlusChildList[0].updateDate" type="hidden"/>
					<input name="planForPlusChildList[0].sysOrgCode" type="hidden"/>
					<input name="planForPlusChildList[0].pid" type="hidden"/>
				  <td align="left">
					  	<input name="planForPlusChildList[0].name" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">姓名</label>
					</td>
				  <td align="left">
					  	<input name="planForPlusChildList[0].work" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">工作</label>
					</td>
				  <td align="left">
					  	<input name="planForPlusChildList[0].remark" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">备注</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(planForPlusChildList)  > 0 }">
		<c:forEach items="${planForPlusChildList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="planForPlusChildList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="planForPlusChildList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="planForPlusChildList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="planForPlusChildList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="planForPlusChildList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="planForPlusChildList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="planForPlusChildList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="planForPlusChildList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="planForPlusChildList[${stuts.index }].pid" type="hidden" value="${poVal.pid }"/>
				   <td align="left">
					  	<input name="planForPlusChildList[${stuts.index }].name" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.name }"/>
					  <label class="Validform_label" style="display: none;">姓名</label>
				   </td>
				   <td align="left">
					  	<input name="planForPlusChildList[${stuts.index }].work" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.work }"/>
					  <label class="Validform_label" style="display: none;">工作</label>
				   </td>
				   <td align="left">
					  	<input name="planForPlusChildList[${stuts.index }].remark" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.remark }"/>
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
