package com.jeecg.crrc.depart.controller;
import com.jeecg.crrc.depart.entity.CrrcDepartEntity;
import com.jeecg.crrc.depart.service.CrrcDepartServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

/**   
 * @Title: Controller  
 * @Description: 公司组织架构
 * @author onlineGenerator
 * @date 2018-09-03 01:35:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/crrcDepartController")
public class CrrcDepartController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CrrcDepartController.class);

	@Autowired
	private CrrcDepartServiceI crrcDepartService;
	@Autowired
	private SystemService systemService;
	
	


	/**
	 * 公司组织架构列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/depart/crrcDepartList");
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
	public void datagrid(CrrcDepartEntity crrcDepart,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CrrcDepartEntity.class, dataGrid);
		if(StringUtil.isEmpty(crrcDepart.getId())){
			cq.isNull("DNumber");
		}else{
			cq.eq("DNumber", crrcDepart.getId());
			crrcDepart.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcDepart, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.crrcDepartService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除公司组织架构
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CrrcDepartEntity crrcDepart, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		crrcDepart = systemService.getEntity(CrrcDepartEntity.class, crrcDepart.getId());
		message = "公司组织架构删除成功";
		try{
			crrcDepartService.delete(crrcDepart);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公司组织架构删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除公司组织架构
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公司组织架构删除成功";
		try{
			for(String id:ids.split(",")){
				CrrcDepartEntity crrcDepart = systemService.getEntity(CrrcDepartEntity.class, 
				id
				);
				crrcDepartService.delete(crrcDepart);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "公司组织架构删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公司组织架构
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CrrcDepartEntity crrcDepart, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公司组织架构添加成功";
		try{
			if(StringUtil.isEmpty(crrcDepart.getDNumber())){
				crrcDepart.setDNumber(null);
			}
			crrcDepartService.save(crrcDepart);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			String sql="";
			sql+="insert into crrc_departztree (id,de_name,";
			if(null!=crrcDepart.getDNumber()){
				sql+="de_number,";
			}
			sql+="de_type) values('"
					+ crrcDepart.getId() + "','"
					+ crrcDepart.getDName() + "','";
			if(null!=crrcDepart.getDNumber()){
				sql+= crrcDepart.getDNumber() + "','";
			}
			sql+= 0 +  "')";
			systemService.executeSql(sql);
		}catch(Exception e){
			e.printStackTrace();
			message = "公司组织架构添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新公司组织架构
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CrrcDepartEntity crrcDepart, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公司组织架构更新成功";
		CrrcDepartEntity t = crrcDepartService.get(CrrcDepartEntity.class, crrcDepart.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(crrcDepart, t);
			if(StringUtil.isEmpty(t.getDNumber())){
				t.setDNumber(null);
			}
			crrcDepartService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "公司组织架构更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 公司组织架构新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CrrcDepartEntity crrcDepart, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcDepart.getId())) {
			crrcDepart = crrcDepartService.getEntity(CrrcDepartEntity.class, crrcDepart.getId());
			req.setAttribute("crrcDepartPage", crrcDepart);
		}
		return new ModelAndView("com/jeecg/crrc/depart/crrcDepart-add");
	}
	/**
	 * 公司组织架构编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CrrcDepartEntity crrcDepart, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcDepart.getId())) {
			crrcDepart = crrcDepartService.getEntity(CrrcDepartEntity.class, crrcDepart.getId());
			req.setAttribute("crrcDepartPage", crrcDepart);
		}
		return new ModelAndView("com/jeecg/crrc/depart/crrcDepart-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","crrcDepartController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CrrcDepartEntity crrcDepart,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CrrcDepartEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcDepart, request.getParameterMap());
		List<CrrcDepartEntity> crrcDeparts = this.crrcDepartService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"公司组织架构");
		modelMap.put(NormalExcelConstants.CLASS,CrrcDepartEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公司组织架构列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,crrcDeparts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CrrcDepartEntity crrcDepart,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"公司组织架构");
    	modelMap.put(NormalExcelConstants.CLASS,CrrcDepartEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公司组织架构列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CrrcDepartEntity> listCrrcDepartEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CrrcDepartEntity.class,params);
				for (CrrcDepartEntity crrcDepart : listCrrcDepartEntitys) {
					crrcDepartService.save(crrcDepart);
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
	
	
}
