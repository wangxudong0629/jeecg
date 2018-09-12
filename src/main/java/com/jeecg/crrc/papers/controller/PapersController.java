package com.jeecg.crrc.papers.controller;
import com.jeecg.crrc.papers.entity.PapersEntity;
import com.jeecg.crrc.papers.service.PapersServiceI;

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
 * @Description: 文件表
 * @author onlineGenerator
 * @date 2018-08-25 12:48:56
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/papersController")
public class PapersController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PapersController.class);

	@Autowired
	private PapersServiceI papersService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 文件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, PlanEntity plan) {
		List<PlanEntity> plans = new ArrayList<PlanEntity>();
		StringBuffer hql = new StringBuffer(" from PlanEntity where number='" + plan.getId() + "'");
		plans = this.systemService.findHql(hql.toString());
//		System.out.println("plans的长度"+plans.size());
		if (plans.size() != 0) {
			List<PlanEntity> treeList = new ArrayList<PlanEntity>();
			List<PlanEntity> parentList = new ArrayList<PlanEntity>();
			PlanEntity t=new PlanEntity();
			StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
         	treeList = this.systemService.findHql(hql3.toString());
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
		if(null!=plan.getNumber()) {
			parentList.add(plan);
			int length=1;
			boolean flag=true;
			for (int i=0;i<length&&flag;i++) {
				for (PlanEntity planNode : treeList) {
					if(null==parentList.get(i).getNumber()){
						flag=false;
						break;
					}else if (planNode.getId().equals(parentList.get(i).getNumber())) {
						parentList.add(planNode);
						length++;
						break;
					}
				}
			}
			parentList.remove(plan);
//			System.out.println("父节点个数："+parentList.size());
			for(PlanEntity p :parentList){
				if(p.getNumber()==null){
					t=p;
				}
			}
		}

			request.setAttribute("plan", t);
			return new ModelAndView("com/jeecg/crrc/papers/papersList1");
		} else {
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			request.setAttribute("plan", plan);
			return new ModelAndView("com/jeecg/crrc/papers/papersList");
		}
	}
	@RequestMapping(params = "list1")
	public ModelAndView list1(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/papers/papersList2");
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
	public void datagrid(PapersEntity papers,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PapersEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, papers, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.papersService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
//	@RequestMapping(params = "datagrid1")
//	public void datagrid1(PapersEntity papers,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		List<PlanEntity> treeNodes = new ArrayList<PlanEntity>();
//		List<PlanEntity> treeList = new ArrayList<PlanEntity>();
//		StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
//		treeList = this.systemService.findHql(hql3.toString());
//			dfsTree(treeList, treeNodes, papers.getPrid(), 0);
//
//			//末端子节点
//			List<PlanEntity> childList = new ArrayList<PlanEntity>();
//			for (PlanEntity treeNode : treeNodes) {
//				int flag = 1;
//				for (PlanEntity treeNode2 : treeNodes) {
//					if (treeNode.getId().equals(treeNode2.getNumber())) {
//						flag = 0;
//						break;
//					}
//				}
//				if (flag == 1) {
//					childList.add(treeNode);
//				}
//			}
//
//		System.out.println("plans的长度"+childList.size());
//		List<PapersEntity> paperse = new ArrayList<PapersEntity>();
//
//		CriteriaQuery cq = new CriteriaQuery(PapersEntity.class, dataGrid);
//
//		for(PlanEntity tplan :childList){
//			papers = this.systemService.findUniqueByProperty(PapersEntity.class,"prid",tplan.getId());
//			System.out.println("        "+papers);
//			paperse.add(papers);
//
//
//		}
//
//		System.out.println("&*&*&*&*"+paperse);
//
//			//查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, papers, request.getParameterMap());
//		cq.add();
//		System.out.println("123456789"+dataGrid);
//		this.papersService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
//	}
	
	/**
	 * 删除文件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PapersEntity papers, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		papers = systemService.getEntity(PapersEntity.class, papers.getId());
		message = "文件表删除成功";
		try{
			papersService.delete(papers);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除文件表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文件表删除成功";
		try{
			for(String id:ids.split(",")){
				PapersEntity papers = systemService.getEntity(PapersEntity.class, id);
				papersService.delete(papers);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "文件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加文件表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PapersEntity papers,String citySel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文件上传成功";
		System.out.println("@#@#"+citySel);
		if(papers.getUrl()!=null&&!papers.getUrl().equals("")) {
			try {
				papersService.save(papers);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "文件上传失败";

			}
		}else{
			message = "文件上传失败，请添加文件后再上传";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新文件表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PapersEntity papers, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文件表更新成功";
		PapersEntity t = papersService.get(PapersEntity.class, papers.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(papers, t);
			papersService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文件表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 文件表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PapersEntity papers, HttpServletRequest req,PlanEntity plan) {
		if (StringUtil.isNotEmpty(papers.getId())) {
			List<PlanEntity> treeList = new ArrayList<PlanEntity>();
			List<PlanEntity> parentList = new ArrayList<PlanEntity>();
			PlanEntity t=new PlanEntity();
			StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
			treeList = this.systemService.findHql(hql3.toString());
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			if(null!=plan.getNumber()) {
				parentList.add(plan);
				int length=1;
				boolean flag=true;
				for (int i=0;i<length&&flag;i++) {
					for (PlanEntity planNode : treeList) {
						if(null==parentList.get(i).getNumber()){
							flag=false;
							break;
						}else if (planNode.getId().equals(parentList.get(i).getNumber())) {
							parentList.add(planNode);
							length++;
							break;
						}
					}
				}
				parentList.remove(plan);
//				System.out.println("父节点个数："+parentList.size());
				for(PlanEntity p :parentList){
					if(p.getNumber()==null){
						t=p;
					}
				}
			}
//			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan",plan);
			req.setAttribute("plans",t);
			papers = papersService.getEntity(PapersEntity.class, papers.getId());
			req.setAttribute("papers", papers);
		}
		return new ModelAndView("com/jeecg/crrc/papers/papers-add");
	}
	/**
	 * 文件表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PapersEntity papers, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(papers.getId())) {

			papers = papersService.getEntity(PapersEntity.class, papers.getId());
			req.setAttribute("papers", papers);
		}
		return new ModelAndView("com/jeecg/crrc/papers/papers-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","papersController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PapersEntity papers,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PapersEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, papers, request.getParameterMap());
		List<PapersEntity> paperss = this.papersService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"文件表");
		modelMap.put(NormalExcelConstants.CLASS,PapersEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,paperss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PapersEntity papers,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"文件表");
    	modelMap.put(NormalExcelConstants.CLASS,PapersEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PapersEntity> listPapersEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PapersEntity.class,params);
				for (PapersEntity papers : listPapersEntitys) {
					papersService.save(papers);
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
	 * @param id papers主键id
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

public List<PlanEntity> dfsTree(List<PlanEntity> treeList, List<PlanEntity> treeNodes, String pid, int level) {
		for (PlanEntity showPlan : treeList) {
		if (null == pid) {
		if (null == showPlan.getNumber()) {
		treeNodes.add(showPlan);
		dfsTree(treeList, treeNodes, showPlan.getId(), level + 1);
		}
		} else if (pid.equals(showPlan.getNumber())) {
		treeNodes.add(showPlan);
		dfsTree(treeList, treeNodes, showPlan.getId(), level + 1);
		}
		}
//		System.out.println("########"+treeNodes.size());
		return treeNodes;
		}
	
}
