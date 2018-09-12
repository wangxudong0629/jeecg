package com.jeecg.crrc.users.controller;
import com.jeecg.crrc.depart.controller.CrrcDepartController;
import com.jeecg.crrc.depart.entity.CrrcDepartEntity;
import com.jeecg.crrc.users.entity.CrrcUserEntity;
import com.jeecg.crrc.users.service.CrrcUserServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
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
 * @Description: 人员表
 * @author onlineGenerator
 * @date 2018-09-01 16:46:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/crrcUserController")
public class CrrcUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CrrcUserController.class);

	@Autowired
	private CrrcUserServiceI crrcUserService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 人员表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/users/crrcUserList");
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
	public void datagrid(CrrcUserEntity crrcUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CrrcUserEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcUser, request.getParameterMap());
		try{
		//自定义追加查询条件

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.crrcUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除人员表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CrrcUserEntity crrcUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		crrcUser = systemService.getEntity(CrrcUserEntity.class, crrcUser.getId());
		message = "人员表删除成功";
		try{
			crrcUserService.delete(crrcUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "人员表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除人员表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "人员表删除成功";
		try{
			for(String id:ids.split(",")){
				CrrcUserEntity crrcUser = systemService.getEntity(CrrcUserEntity.class, 
				id
				);
				crrcUserService.delete(crrcUser);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "人员表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加人员表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CrrcUserEntity crrcUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "人员表添加成功";
		try{
			crrcUserService.save(crrcUser);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			CrrcUserEntity crrcUser1=null;
			crrcUser1=systemService.getEntity(CrrcUserEntity.class,crrcUser.getId());
//			System.out.println("@@"+crrcUser.getId());
//			System.out.println("@@"+crrcUser1.getId());
//			System.out.println("@@"+crrcUser.getUName());
			CrrcDepartEntity crrcdepart=null;
			crrcdepart=systemService.getEntity(CrrcDepartEntity.class,crrcUser1.getUDepartid());
			System.out.println("##"+crrcdepart.getDName());
			systemService.executeSql("update crrc_user set u_departname='"+crrcdepart.getDName()+"' where id='"+crrcUser1.getId()+"'");

			systemService.executeSql("insert into crrc_departztree (id,de_name,de_number,de_type) values('"
					+ crrcUser1.getId() + "','"
					+ crrcUser1.getUName() + "','"+crrcUser1.getUDepartid() + "',' 1 ')");
		}catch(Exception e){
			e.printStackTrace();
			message = "人员表添加失败";
//			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新人员表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CrrcUserEntity crrcUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "人员表更新成功";
		CrrcUserEntity t = crrcUserService.get(CrrcUserEntity.class, crrcUser.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(crrcUser, t);
			crrcUserService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "人员表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 人员表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CrrcUserEntity crrcUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcUser.getId())) {
			crrcUser = crrcUserService.getEntity(CrrcUserEntity.class, crrcUser.getId());
			req.setAttribute("crrcUser", crrcUser);
		}
		StringBuffer hql = new StringBuffer(" from CrrcDepartEntity u");
		List<CrrcDepartEntity> departList= this.systemService.findHql(hql.toString());
		req.setAttribute("departList", departList);
		return new ModelAndView("com/jeecg/crrc/users/crrcUser-add");
	}
	/**
	 * 人员表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CrrcUserEntity crrcUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcUser.getId())) {
			crrcUser = crrcUserService.getEntity(CrrcUserEntity.class, crrcUser.getId());
			req.setAttribute("crrcUser", crrcUser);
		}
		StringBuffer hql = new StringBuffer(" from CrrcDepartEntity u");
		List<CrrcDepartEntity> departList= this.systemService.findHql(hql.toString());
		req.setAttribute("departList", departList);
		return new ModelAndView("com/jeecg/crrc/users/crrcUser-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","crrcUserController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CrrcUserEntity crrcUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CrrcUserEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcUser, request.getParameterMap());
		List<CrrcUserEntity> crrcUsers = this.crrcUserService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"人员表");
		modelMap.put(NormalExcelConstants.CLASS,CrrcUserEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("人员表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,crrcUsers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CrrcUserEntity crrcUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"人员表");
    	modelMap.put(NormalExcelConstants.CLASS,CrrcUserEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("人员表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CrrcUserEntity> listCrrcUserEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CrrcUserEntity.class,params);
				for (CrrcUserEntity crrcUser : listCrrcUserEntitys) {
					crrcUserService.save(crrcUser);
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
	 * 父级权限列表
	 *
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setPFunction")
	@ResponseBody
	public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(CrrcDepartEntity.class);
		if (null != request.getParameter("selfId")) {
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("crrcDepart.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("crrcDepart");
		}
		cq.addOrder("dNumber", SortDirection.asc);
		cq.add();
		List<CrrcDepartEntity> plansList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "dName", "crrcDeparts");

		CrrcDepartEntity defaultplan = new CrrcDepartEntity();
		defaultplan.setId("");
		defaultplan.setDName("其他");
		plansList.add(0, defaultplan);

		comboTrees = systemService.ComboTree(plansList, comboTreeModel, null, true);
		return comboTrees;
	}
}
