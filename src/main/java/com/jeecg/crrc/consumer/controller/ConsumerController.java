package com.jeecg.crrc.consumer.controller;
import com.jeecg.crrc.consumer.entity.ConsumerEntity;
import com.jeecg.crrc.consumer.service.ConsumerServiceI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.crrc.plan.entity.PlanEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;


/**   
 * @Title: Controller  
 * @Description: 客户表
 * @author onlineGenerator
 * @date 2018-08-15 16:07:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/consumerController")
public class ConsumerController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private ConsumerServiceI consumerService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 客户表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/consumer/consumerList");
	}
	@RequestMapping(params = "list1")
	public ModelAndView list1(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/consumer/consumerList1");
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
	public void datagrid(ConsumerEntity consumer,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConsumerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, consumer, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.consumerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除客户表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConsumerEntity consumer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		consumer = systemService.getEntity(ConsumerEntity.class, consumer.getId());
		message = "客户表删除成功";
		try{
			consumerService.delete(consumer);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客户表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户表删除成功";
		try{
			for(String id:ids.split(",")){
				ConsumerEntity consumer = systemService.getEntity(ConsumerEntity.class, 
				id
				);
				consumerService.delete(consumer);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConsumerEntity consumer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户表添加成功";
		try{
//			System.out.println(consumer.getPlanId());
			consumerService.save(consumer);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客户表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConsumerEntity consumer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户表更新成功";
		ConsumerEntity t = consumerService.get(ConsumerEntity.class, consumer.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(consumer, t);
			consumerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客户表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客户表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConsumerEntity consumer, HttpServletRequest req) {
			req.setAttribute("consumers", consumer.getPlanId());
//			System.out.println("consumers="+consumer.getPlanId());
			if (StringUtil.isNotEmpty(consumer.getId())) {
				consumer = consumerService.getEntity(ConsumerEntity.class, consumer.getId());

				req.setAttribute("consumerPage", consumer);
//				System.out.println(consumer.getCompany());
			}
			return new ModelAndView("com/jeecg/crrc/consumer/consumer-add");
		}
	/**
	 * 客户表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConsumerEntity consumer, HttpServletRequest req) {
	if (StringUtil.isNotEmpty(consumer.getId())) {
			consumer = consumerService.getEntity(ConsumerEntity.class, consumer.getId());

			req.setAttribute("consumerPage", consumer);
	}
		return new ModelAndView("com/jeecg/crrc/consumer/consumer-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","consumerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConsumerEntity consumer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConsumerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, consumer, request.getParameterMap());
		List<ConsumerEntity> consumers = this.consumerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客户表");
		modelMap.put(NormalExcelConstants.CLASS,ConsumerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,consumers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConsumerEntity consumer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客户表");
    	modelMap.put(NormalExcelConstants.CLASS,ConsumerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConsumerEntity> listConsumerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConsumerEntity.class,params);
				for (ConsumerEntity consumer : listConsumerEntitys) {
					consumerService.save(consumer);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
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
	@RequestMapping(params = "lgd")
	public ModelAndView lgd(PlanEntity plan, HttpServletRequest req) {
		List<ConsumerEntity>consumers = new ArrayList<ConsumerEntity>();
//        System.out.println("@@@@@@@@"+plan.getId());
		StringBuffer hql = new StringBuffer(" from ConsumerEntity where plan_id='"+plan.getId()+"'");
		consumers = this.systemService.findHql(hql.toString());
//        System.out.println("***********"+consumers);
		req.setAttribute("consumers", consumers);
        req.setAttribute("plan", plan.getId());
		return new ModelAndView("com/jeecg/crrc/consumer/consumerList");

	}
	
}
