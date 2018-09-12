package com.jeecg.crrc.money.controller;
import com.jeecg.crrc.money.entity.MoneyEntity;
import com.jeecg.crrc.money.service.MoneyServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.crrc.plan.entity.PlanEntity;
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

import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import java.util.HashMap;
/**   
 * @Title: Controller  
 * @Description: 经费表
 * @author onlineGenerator
 * @date 2018-08-29 13:47:19
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/moneyController")
public class MoneyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MoneyController.class);

	@Autowired
	private MoneyServiceI moneyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 经费表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/money/moneyList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(MoneyEntity money,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MoneyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, money, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.moneyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除经费表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MoneyEntity money, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		money = systemService.getEntity(MoneyEntity.class, money.getId());
		message = "经费表删除成功";
		try{
			moneyService.delete(money);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "经费表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除经费表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经费表删除成功";
		try{
			for(String id:ids.split(",")){
				MoneyEntity money = systemService.getEntity(MoneyEntity.class, 
				id
				);
				moneyService.delete(money);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "经费表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加经费表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MoneyEntity money, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经费表添加成功";
		try{
			moneyService.save(money);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "经费表添加失败";
//			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		System.out.println("#####"+money.getPrid());
		if (money.getPrid()==null||"".equals(money.getPrid().trim())){
			systemService.executeSql("update money set pname ='其他 'where prid='" + money.getPrid() + "'");
			systemService.executeSql("update money set prname ='其他 'where prid='" + money.getPrid() + "'");
			money = systemService.getEntity(MoneyEntity.class, money.getId());
		}else {
			PlanEntity plan = new PlanEntity();
			List<PlanEntity> parentList = new ArrayList<PlanEntity>();
			List<PlanEntity> treeList = new ArrayList<PlanEntity>();
			StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
			treeList = this.systemService.findHql(hql3.toString());
			String t = null;
			plan = systemService.getEntity(PlanEntity.class, money.getPrid());
			if (null != plan.getNumber()) {
				parentList.add(plan);
				int length = 1;
				boolean flag = true;
				for (int i = 0; i < length && flag; i++) {
					for (PlanEntity planNode : treeList) {
						if (null == parentList.get(i).getNumber()) {
							flag = false;
							t = parentList.get(i).getPname();
							break;
						} else if (planNode.getId().equals(parentList.get(i).getNumber())) {
							parentList.add(planNode);
							length++;
							break;
						}
					}
				}
				parentList.remove(plan);
				systemService.executeSql("update money set prname ='" + t + "'where prid='" + money.getPrid() + "'");
//			System.out.println("t="+t);
			} else if (null == plan.getNumber()) {
				systemService.executeSql("update money set prname ='" + plan.getPname() + "'where prid='" + money.getPrid() + "'");
//			System.out.println("prname="+plan.getPname());
			}
			systemService.executeSql("update money set pname ='" + plan.getPname() + "'where prid='" + money.getPrid() + "'");
			money = systemService.getEntity(MoneyEntity.class, money.getId());
//		System.out.println("%%%%%"+money.getPname());
//		System.out.println("%%%%%"+money.getPrname());
		}
		j.setObj(money);
		return j;
	}
	
	/**
	 * 更新经费表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MoneyEntity money, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经费表更新成功";
		MoneyEntity t = moneyService.get(MoneyEntity.class, money.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(money, t);
			moneyService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "经费表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 经费表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MoneyEntity money, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(money.getId())) {
			money = moneyService.getEntity(MoneyEntity.class, money.getId());
			req.setAttribute("moneyPage", money);
		}
		return new ModelAndView("com/jeecg/crrc/money/money-add");
	}
	/**
	 * 经费表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MoneyEntity money, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(money.getId())) {
			money = moneyService.getEntity(MoneyEntity.class, money.getId());
			req.setAttribute("moneyPage", money);
		}
		return new ModelAndView("com/jeecg/crrc/money/money-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","moneyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MoneyEntity money,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MoneyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, money, request.getParameterMap());
		List<MoneyEntity> moneys = this.moneyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"经费表");
		modelMap.put(NormalExcelConstants.CLASS,MoneyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经费表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,moneys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MoneyEntity money,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"经费表");
    	modelMap.put(NormalExcelConstants.CLASS,MoneyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经费表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<MoneyEntity> listMoneyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MoneyEntity.class,params);
				for (MoneyEntity money : listMoneyEntitys) {
					moneyService.save(money);
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
	
	/**
	 * 获取文件附件信息
	 * 
	 * @param id money主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	
}
