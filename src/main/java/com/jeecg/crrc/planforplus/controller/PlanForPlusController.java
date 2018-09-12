package com.jeecg.crrc.planforplus.controller;
import com.jeecg.crrc.plan.entity.PlanEntity;
import com.jeecg.crrc.planforplus.entity.PlanForPlusEntity;
import com.jeecg.crrc.planforplus.service.PlanForPlusServiceI;
import com.jeecg.crrc.planforplus.page.PlanForPlusPage;
import com.jeecg.crrc.planforplus.entity.PlanForPlusChildEntity;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;


/**   
 * @Title: Controller
 * @Description: 项目加号
 * @author onlineGenerator
 * @date 2018-08-23 18:52:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/planForPlusController")
public class PlanForPlusController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PlanForPlusController.class);

	@Autowired
	private PlanForPlusServiceI planForPlusService;
	@Autowired
	private SystemService systemService;

	/**
	 * 项目加号列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/planforplus/planForPlusList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid2")
	public void datagrid2(PlanForPlusEntity planForPlus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlanForPlusEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, planForPlus, request.getParameterMap());
		try {
			//自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.planForPlusService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(params = "datagrid")
	public void datagrid(PlanEntity plan,PlanForPlusEntity planForPlus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlanForPlusEntity.class, dataGrid);
		List<PlanEntity> planList = systemService.getList(PlanEntity.class);
//		long time = System.currentTimeMillis();
//		Date b = new Date(time);
//		Calendar c = Calendar.getInstance();
//		c.setTime(b);
//		c.add(Calendar.DAY_OF_MONTH, 1);
//		System.out.println("当前时间加一天"+ c.getTimeInMillis());
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < planList.size(); i++) {
			Date enddate=planList.get(i).getDateend();
//			System.out.println(planList.get(i).getDateend());
			cal.setTime(enddate);//设置起时间
//			System.out.println("111111111::::"+cal.getTime());
			cal.add(Calendar.DATE, 1);//增加一tian
//			System.out.println(cal.getTime());
			if( planList.get(i).getProcess().equals("100.0")){
				systemService.executeSql("update plan set statese=1 where id='" +  planList.get(i).getId() + "'");
				systemService.executeSql("update plan_for_plus set statese=1 where id='" +  planList.get(i).getId() + "'");
			}else
			if ((cal.getTime()).getTime() < System.currentTimeMillis()&&planList.get(i).getStatese().equals("0")) {
				System.out.println(planList.get(i).getDateend().getTime());
				System.out.println(System.currentTimeMillis());


				systemService.executeSql("update plan set statese=2 where id='" + planList.get(i).getId() + "'");
				systemService.executeSql("update plan_for_plus set statese=2 where id='" + planList.get(i).getId() + "'");

			}
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, planForPlus, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.planForPlusService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

		List<PlanEntity> treeList = new ArrayList<PlanEntity>();

//		System.out.println("id为"+plan.getId());
//		System.out.println("id2为"+id);
		StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
		treeList = this.systemService.findHql(hql3.toString());
//		System.out.println("名称为"+plan.getPname());
		List<PlanEntity> parentList = new ArrayList<PlanEntity>();
		PlanEntity plans = new PlanEntity();
		plans = systemService.getEntity(PlanEntity.class, plan.getId());
		if(null!=plans.getNumber()) {
			parentList.add(plans);
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
			parentList.remove(plans);
//			System.out.println("父节点个数："+parentList.size());
		}
		for(PlanEntity ps:parentList){
			List<PlanEntity> treeNodes = new ArrayList<PlanEntity>();
			dfsTree(treeList, treeNodes, ps.getId(), 0);

			//末端子节点
			List<PlanEntity> childList = new ArrayList<PlanEntity>();
			for (PlanEntity treeNode : treeNodes) {
				int flag = 1;
				for (PlanEntity treeNode2 : treeNodes) {
					if (treeNode.getId().equals(treeNode2.getNumber())) {
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					childList.add(treeNode);
				}
			}
//			System.out.println("###"+childList.size());
			int n = childList.size();
			int m = 0;
			for(PlanEntity cd:childList){
				if (cd.getStatese().equals("1")) {
					m++;
				}
			}
//			System.out.println("m="+m);
			String d=String.format("%.1f",((float)m/(float)n)*100);
//			System.out.println("d="+d);
			systemService.executeSql("update plan set process ='" + d + "'where id='"+ps.getId()+"'");
			systemService.executeSql("update plan_for_plus set process ='" + d + "'where id='"+ps.getId()+"'");
		}
	}
	@RequestMapping(params = "planForPlusChildEntityDatagrid")
	public void planForPlusChildEntityDatagrid(PlanForPlusChildEntity planForPlusChildEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlanForPlusChildEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, planForPlusChildEntity);
		cq.add();
		this.planForPlusService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除项目加号
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PlanForPlusEntity planForPlus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		planForPlus = systemService.getEntity(PlanForPlusEntity.class, planForPlus.getId());
		String message = "项目加号删除成功";
		try{
			planForPlusService.delMain(planForPlus);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目加号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除项目加号
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "项目加号删除成功";
		try{
			for(String id:ids.split(",")){
				PlanForPlusEntity planForPlus = systemService.getEntity(PlanForPlusEntity.class,
				id
				);
				planForPlusService.delMain(planForPlus);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目加号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加项目加号
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PlanForPlusEntity planForPlus,PlanForPlusPage planForPlusPage, HttpServletRequest request) {
		List<PlanForPlusChildEntity> planForPlusChildList =  planForPlusPage.getPlanForPlusChildList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			planForPlusService.addMain(planForPlus, planForPlusChildList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目加号添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新项目加号
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PlanForPlusEntity planForPlus,PlanForPlusPage planForPlusPage, HttpServletRequest request) {
		List<PlanForPlusChildEntity> planForPlusChildList =  planForPlusPage.getPlanForPlusChildList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			planForPlusService.updateMain(planForPlus, planForPlusChildList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新项目加号失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 项目加号新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PlanForPlusEntity planForPlus, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(planForPlus.getId())) {
			planForPlus = planForPlusService.getEntity(PlanForPlusEntity.class, planForPlus.getId());
			req.setAttribute("planForPlusPage", planForPlus);
		}
		return new ModelAndView("com/jeecg/crrc/planforplus/planForPlus-add");
	}
	
	/**
	 * 项目加号编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PlanForPlusEntity planForPlus, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(planForPlus.getId())) {

			planForPlus = planForPlusService.getEntity(PlanForPlusEntity.class, planForPlus.getId());
			req.setAttribute("planForPlusPage", planForPlus);
		}
		return new ModelAndView("com/jeecg/crrc/planforplus/planForPlus-update");
	}
	
	
	/**
	 * 加载明细列表[添加人员活动信息]
	 * 
	 * @return
	 */
	@RequestMapping(params = "planForPlusChildList")
	public ModelAndView planForPlusChildList(PlanForPlusEntity planForPlus, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = planForPlus.getId();
		//===================================================================================
		//查询-添加人员活动信息
	    String hql0 = "from PlanForPlusChildEntity where 1 = 1 AND pid = ? ";
	    try{
	    	List<PlanForPlusChildEntity> planForPlusChildEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("planForPlusChildList", planForPlusChildEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/crrc/planforplus/planForPlusChildList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(PlanForPlusEntity planForPlus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(PlanForPlusEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, planForPlus);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<PlanForPlusEntity> list=this.planForPlusService.getListByCriteriaQuery(cq, false);
    	List<PlanForPlusPage> pageList=new ArrayList<PlanForPlusPage>();
        if(list!=null&&list.size()>0){
        	for(PlanForPlusEntity entity:list){
        		try{
        		PlanForPlusPage page=new PlanForPlusPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from PlanForPlusChildEntity where 1 = 1 AND pid = ? ";
        	        List<PlanForPlusChildEntity> planForPlusChildEntityList = systemService.findHql(hql0,id0);
            		page.setPlanForPlusChildList(planForPlusChildEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"项目加号");
        map.put(NormalExcelConstants.CLASS,PlanForPlusPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("项目加号列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<PlanForPlusPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), PlanForPlusPage.class, params);
				PlanForPlusEntity entity1=null;
				for (PlanForPlusPage page : list) {
					entity1=new PlanForPlusEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            planForPlusService.addMain(entity1, page.getPlanForPlusChildList());
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
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"项目加号");
		map.put(NormalExcelConstants.CLASS,PlanForPlusPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("项目加号列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "planForPlusController");
		return new ModelAndView("common/upload/pub_excel_upload");
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
