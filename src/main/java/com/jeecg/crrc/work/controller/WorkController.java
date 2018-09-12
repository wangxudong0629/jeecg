package com.jeecg.crrc.work.controller;
import com.jeecg.crrc.plan.entity.PlanEntity;
import com.jeecg.crrc.users.entity.CrrcUserEntity;
import com.jeecg.crrc.work.entity.WorkEntity;
import com.jeecg.crrc.work.service.WorkServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 工作明细
 * @author onlineGenerator
 * @date 2018-08-31 10:54:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/workController")
public class WorkController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(WorkController.class);

	@Autowired
	private WorkServiceI workService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 工作明细列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, PlanEntity plan) {
		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		request.setAttribute("plan", plan);
		return new ModelAndView("com/jeecg/crrc/work/workList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WorkEntity work,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WorkEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, work, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.workService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除工作明细
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WorkEntity work, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		work = systemService.getEntity(WorkEntity.class, work.getId());
		message = "工作明细删除成功";
		try{
			workService.delete(work);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工作明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工作明细
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作明细删除成功";
		try{
			for(String id:ids.split(",")){
				WorkEntity work = systemService.getEntity(WorkEntity.class, 
				id
				);
				workService.delete(work);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工作明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作明细
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WorkEntity work, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作明细添加成功";
		try{
			System.out.println("##########"+work.getPid());
			System.out.println("##########"+work.getRemarks());
			System.out.println("##########"+work.getName());
			System.out.println("##########"+work.getWork() );
			workService.save(work);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			systemService.executeSql("insert into plan_for_plus_child (id,pid,name,remark,work) values('"+work.getId()+"','"+work.getPid()+"','"+work.getName()+"','"+work.getRemarks()+"','"+work.getWork()+"')");
		}catch(Exception e){
			e.printStackTrace();
			message = "工作明细添加失败";
//			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工作明细
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WorkEntity work, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作明细更新成功";
		WorkEntity t = workService.get(WorkEntity.class, work.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(work, t);
			workService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工作明细更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工作明细新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WorkEntity work, HttpServletRequest req, PlanEntity plan) {
		if (StringUtil.isNotEmpty(work.getId())) {
			work = workService.getEntity(WorkEntity.class, work.getId());
			req.setAttribute("work", work);
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan", plan);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u");
		List<CrrcUserEntity> userList= this.systemService.findHql(hql.toString());
		req.setAttribute("userList", userList);
		return new ModelAndView("com/jeecg/crrc/work/work-add");
	}
	/**
	 * 工作明细编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WorkEntity work, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(work.getId())) {
			work = workService.getEntity(WorkEntity.class, work.getId());
			req.setAttribute("work", work);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u");
		List<CrrcUserEntity> userList= this.systemService.findHql(hql.toString());
		req.setAttribute("userList", userList);
		return new ModelAndView("com/jeecg/crrc/work/work-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","workController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WorkEntity work,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WorkEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, work, request.getParameterMap());
		List<WorkEntity> works = this.workService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工作明细");
		modelMap.put(NormalExcelConstants.CLASS,WorkEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工作明细列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,works);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WorkEntity work,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工作明细");
    	modelMap.put(NormalExcelConstants.CLASS,WorkEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工作明细列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<WorkEntity> listWorkEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WorkEntity.class,params);
				for (WorkEntity work : listWorkEntitys) {
					workService.save(work);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
