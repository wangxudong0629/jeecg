package com.jeecg.crrc.plan.controller;
import com.jeecg.crrc.plan.entity.PlanEntity;
import com.jeecg.crrc.plan.service.PlanServiceI;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jeecg.crrc.users.entity.CrrcUserEntity;
import com.jeecg.demo.dao.JeecgMinidaoDao;
import net.sf.json.JSONArray;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @Title: Controller
 * @Description: 项目管理
 * @author onlineGenerator
 * @date 2018-08-13 22:30:56
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/planController")
public class PlanController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private PlanServiceI planService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;

	/**
	 * 项目管理列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/plan/planList");
	}

	@RequestMapping(params = "ztreeDemo")
	public ModelAndView ztreeDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/plan/planztree");
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param plan
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(PlanEntity plan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlanEntity.class, dataGrid);
        List<PlanEntity> planList = systemService.getList(PlanEntity.class);
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
		if (StringUtil.isEmpty(plan.getId())) {
			cq.isNull("number");
		} else {
			cq.eq("number", plan.getId());
			plan.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, plan, request.getParameterMap());
		try {
			//自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.planService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}

	@RequestMapping(params = "datagrid2")
	public void datagrid2(PlanEntity plan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlanEntity.class, dataGrid);
        List<PlanEntity> planList = systemService.getList(PlanEntity.class);

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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, plan, request.getParameterMap());
		try {
			//自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		this.planService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
//		List<PlanEntity> treeList = new ArrayList<PlanEntity>();
////
//////		System.out.println("id为"+plan.getId());
//////		System.out.println("id2为"+id);
////		StringBuffer hql3 = new StringBuffer("  from  PlanEntity ");
////		treeList = this.systemService.findHql(hql3.toString());
//////		System.out.println("名称为"+plan.getPname());
//		List<PlanEntity> parentList = new ArrayList<PlanEntity>();
//		PlanEntity plans = new PlanEntity();
//		plans = systemService.getEntity(PlanEntity.class, plan.getId());
//		if(null!=plans.getNumber()) {
//			parentList.add(plans);
//			int length=1;
//			boolean flag=true;
//			for (int i=0;i<length&&flag;i++) {
//				for (PlanEntity planNode : treeList) {
//					if(null==parentList.get(i).getNumber()){
//						flag=false;

//						break;
//					}else if (planNode.getId().equals(parentList.get(i).getNumber())) {
//						parentList.add(planNode);
//						length++;
//						break;
//					}
//				}
//			}
//			parentList.remove(plans);
//			System.out.println("父节点个数："+parentList.size());
//		}
//		for(PlanEntity ps:parentList){
//			List<PlanEntity> treeNodes = new ArrayList<PlanEntity>();
//			dfsTree(treeList, treeNodes, ps.getId(), 0);
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
//			System.out.println("###"+childList.size());
//			int n = childList.size();
//			int m = 0;
//			for(PlanEntity cd:childList){
//				if (!cd.getStatese().equals("0")) {
//					m++;
//				}
//			}
//			System.out.println("m="+m);
//			String d=String.format("%.1f",((float)m/(float)n)*100);
//			System.out.println("d="+d);
//			systemService.executeSql("update plan set process ='" + d + "'where id='"+ps.getId()+"'");
//		}
	}


	/**
	 * 删除项目管理
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PlanEntity plan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		message = "项目管理删除成功";
		try {
			planService.delete(plan);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "项目管理删除失败";
//			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除项目管理
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "批量删除成功";
		try {
			for (String id : ids.split(",")) {
				PlanEntity plan = systemService.getEntity(PlanEntity.class, id);
				planService.delete(plan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "批量删除失败";

		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加项目管理
	 *
	 * @param plan
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PlanEntity plan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		if(plan.getPname()!=null&&plan.getLevel()!=null&&
                plan.getDateend()!=null&&plan.getDate()!=null&&plan.getDateby()!=null&&plan.getUsername()!=null){
		try {
			java.sql.Date sqlDate = new java.sql.Date(plan.getDateby().getTime());
			java.sql.Date sqlDate1 = new java.sql.Date(plan.getDateend().getTime());
			String id=UUIDGenerator.generate();


			String sql="";
			sql+="insert into plan (id,pname,";
			if(null!=plan.getNumber()){
				sql+="number,";
			}
			sql+="username,dateby,dateend,date,level,remarks,statese,process) values('"
					+ id + "','"
					+ plan.getPname() + "','";
			if(null!=plan.getNumber()){
				sql+= plan.getNumber() + "','";
			}
			sql+= plan.getUsername() + "','"
					+ sqlDate + "','"
					+ sqlDate1 + "','"
					+ plan.getDate() + "','"
					+ plan.getLevel() + "','"
					+ plan.getRemarks() + "','"
					+ plan.getStatese() + "','"
					+ plan.getProcess() + "')";
			systemService.executeSql(sql);


			String sql2="";
			sql2+="insert into plan_for_plus (id,pname,";
			if(null!=plan.getNumber()){
				sql2+="number,";
			}
			sql2+="username,dateby,dateend,date,level,remarks,statese,process) values('"
					+ id + "','"
					+ plan.getPname() + "','";
			if(null!=plan.getNumber()){
				sql2+= plan.getNumber() + "','";
			}
			sql2+= plan.getUsername() + "','"
					+ sqlDate + "','"
					+ sqlDate1 + "','"
					+ plan.getDate() + "','"
					+ plan.getLevel() + "','"
					+ plan.getRemarks() + "','"
					+ plan.getStatese() + "','"
					+ plan.getProcess() + "')";
			systemService.executeSql(sql2);

		/*
			systemService.executeSql("insert into plan (id,pname,number,username,dateby,dateend,date,level,remarks,statese,process) values('"
					+id+"','"
					+plan.getPname()+"','"
					+plan.getNumber()+"','"
					+plan.getUsername()+"','"
					+sqlDate+"','"
					+sqlDate1+"','"
					+plan.getDate()+"','"
					+plan.getLevel()+"','"
					+plan.getRemarks()+"','"
					+plan.getStatese()+"','"
					+plan.getProcess()+"')");
			systemService.executeSql("insert into plan_for_plus (id,pname,number,username,dateby,dateend,date,level,remarks,statese,process) values('"
					+id+"','"
					+plan.getPname()+"','"
					+plan.getNumber()+"','"
					+plan.getUsername()+"','"
					+sqlDate+"','"
					+sqlDate1+"','"
					+plan.getDate()+"','"
					+plan.getLevel()+"','"
					+plan.getRemarks()+"','"
					+plan.getStatese()+"','"
					+plan.getProcess()+"')");*/
		} catch (Exception e) {
			e.printStackTrace();
			message = "添加失败";

		}
		}else {
		    message="添加失败";
        }
		System.out.println(message);
		j.setMsg(message);
		return j;
	}
	@RequestMapping(params = "doAdd1")
	@ResponseBody
	public AjaxJson doAdd1(PlanEntity plan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "添加成功";
//		if(plan.getNumber().equals("")) {
//			plan.setNumber("");
//		}
			if (plan.getPname() != null && plan.getLevel() != null &&
					plan.getDateend() != null && plan.getDate() != null && plan.getDateby() != null && plan.getUsername() != null) {
				try {
					java.sql.Date sqlDate = new java.sql.Date(plan.getDateby().getTime());
					java.sql.Date sqlDate1 = new java.sql.Date(plan.getDateend().getTime());
					String id = UUIDGenerator.generate();


					String sql = "";
					sql += "insert into plan (id,pname,";
					if (null != plan.getNumber()&&!plan.getNumber().equals("")) {
						sql += "number,";
					}
					sql += "username,dateby,dateend,date,level,remarks,statese,process) values('"
							+ id + "','"
							+ plan.getPname() + "','";
					if (null != plan.getNumber()&&!plan.getNumber().equals("")) {
						sql += plan.getNumber() + "','";
					}
					sql += plan.getUsername() + "','"
							+ sqlDate + "','"
							+ sqlDate1 + "','"
							+ plan.getDate() + "','"
							+ plan.getLevel() + "','"
							+ plan.getRemarks() + "','"
							+ plan.getStatese() + "','"
							+ plan.getProcess() + "')";
					systemService.executeSql(sql);


					String sql2 = "";
					sql2 += "insert into plan_for_plus (id,pname,";
					if (null != plan.getNumber()&&!plan.getNumber().equals("")) {
						sql2 += "number,";
					}
					sql2 += "username,dateby,dateend,date,level,remarks,statese,process) values('"
							+ id + "','"
							+ plan.getPname() + "','";
					if (null != plan.getNumber()&&!plan.getNumber().equals("")) {
						sql2 += plan.getNumber() + "','";
					}
					sql2 += plan.getUsername() + "','"
							+ sqlDate + "','"
							+ sqlDate1 + "','"
							+ plan.getDate() + "','"
							+ plan.getLevel() + "','"
							+ plan.getRemarks() + "','"
							+ plan.getStatese() + "','"
							+ plan.getProcess() + "')";
					systemService.executeSql(sql2);

		/*
			systemService.executeSql("insert into plan (id,pname,number,username,dateby,dateend,date,level,remarks,statese,process) values('"
					+id+"','"
					+plan.getPname()+"','"
					+plan.getNumber()+"','"
					+plan.getUsername()+"','"
					+sqlDate+"','"
					+sqlDate1+"','"
					+plan.getDate()+"','"
					+plan.getLevel()+"','"
					+plan.getRemarks()+"','"
					+plan.getStatese()+"','"
					+plan.getProcess()+"')");
			systemService.executeSql("insert into plan_for_plus (id,pname,number,username,dateby,dateend,date,level,remarks,statese,process) values('"
					+id+"','"
					+plan.getPname()+"','"
					+plan.getNumber()+"','"
					+plan.getUsername()+"','"
					+sqlDate+"','"
					+sqlDate1+"','"
					+plan.getDate()+"','"
					+plan.getLevel()+"','"
					+plan.getRemarks()+"','"
					+plan.getStatese()+"','"
					+plan.getProcess()+"')");*/
				} catch (Exception e) {
					e.printStackTrace();
					message = "添加失败";

				}
			} else {
				message = "添加失败";
			}

		System.out.println(message);
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新项目管理
	 *
	 * @param plan
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PlanEntity plan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "更新成功";
		PlanEntity t = planService.get(PlanEntity.class, plan.getId());
		if(plan.getPname()!=null&&plan.getLevel()!=null&&
				plan.getDateend()!=null&&plan.getDate()!=null&&plan.getDateby()!=null&&plan.getUsername()!=null){
		try {
			MyBeanUtils.copyBeanNotNull2Bean(plan, t);
			if (StringUtil.isEmpty(t.getNumber())) {
				t.setNumber(null);
			}
			planService.saveOrUpdate(t);
			java.sql.Date sqlDate = new java.sql.Date(plan.getDateby().getTime());
			java.sql.Date sqlDate1 = new java.sql.Date(plan.getDateend().getTime());
//			System.out.println(plan.getProcess());
			systemService.executeSql("update plan_for_plus set pname='"+plan.getPname()+"',number='"+plan.getNumber()+
					"',username='"+plan.getUsername()+"',dateby='"+sqlDate+"',dateend='"+sqlDate1+"',date='"+plan.getDate()+"',level='"+plan.getLevel()
					+"',remarks='"+plan.getRemarks()+"',statese='"+plan.getStatese()+"',process='"+plan.getProcess()+"'where id='"+plan.getId()+"'");
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
//			throw new BusinessException(e.getMessage());
		}
        }else {
            message="更新失败";
        }
		j.setMsg(message);
		return j;
	}


	/**
	 * 项目管理新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PlanEntity plan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(plan.getId())) {
			plan = planService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("planPage", plan);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u where u_positionid='项目经理'");
		List<CrrcUserEntity> manager= this.systemService.findHql(hql.toString());
		req.setAttribute("manager", manager);
		return new ModelAndView("com/jeecg/crrc/plan/plan-add");
	}
	@RequestMapping(params = "goAdd1")
	public ModelAndView goAdd1(PlanEntity plan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(plan.getId())) {
			plan = planService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("planPage", plan);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u where u_positionid='项目经理'");
		List<CrrcUserEntity> manager= this.systemService.findHql(hql.toString());
		req.setAttribute("manager", manager);
		return new ModelAndView("com/jeecg/crrc/plan/plan-add1");
	}

	/**
	 * 项目管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PlanEntity plan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(plan.getId())) {
			plan = planService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("planPage", plan);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u where u_positionid='项目经理'");
		List<CrrcUserEntity> manager= this.systemService.findHql(hql.toString());
		req.setAttribute("manager", manager);
		return new ModelAndView("com/jeecg/crrc/plan/plan-update");
	}
	@RequestMapping(params = "goUpdate1")
	public ModelAndView goUpdate1(PlanEntity plan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(plan.getId())) {
			plan = planService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("planPage", plan);
		}
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u where u_positionid='项目经理'");
		List<CrrcUserEntity> manager= this.systemService.findHql(hql.toString());
		req.setAttribute("manager", manager);
		return new ModelAndView("com/jeecg/crrc/plan/plan-update1");
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "planController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PlanEntity plan, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PlanEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, plan, request.getParameterMap());
		List<PlanEntity> plans = this.planService.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "项目管理");
		modelMap.put(NormalExcelConstants.CLASS, PlanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("项目管理列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, plans);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PlanEntity plan, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "项目管理");
		modelMap.put(NormalExcelConstants.CLASS, PlanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("项目管理列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
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
				List<PlanEntity> listPlanEntitys = ExcelImportUtil.importExcel(file.getInputStream(), PlanEntity.class, params);
				for (PlanEntity plan : listPlanEntitys) {
					planService.save(plan);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	@RequestMapping(params = "getTreeDemoData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeDemoData(PlanEntity plan, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> map1 = null;//父对象
		try {
			List<PlanEntity> planList1 = new ArrayList<PlanEntity>();//父对象集合
			StringBuffer hql = new StringBuffer(" from PlanEntity p ");
			planList1 = this.systemService.findHql(hql.toString());
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();//所有集合
			for (PlanEntity tsplan : planList1) {
				map1 = new HashMap<String, Object>();
				map1.put("chkDisabled", false);
				map1.put("click", true);
				map1.put("id", tsplan.getId());
				map1.put("name", tsplan.getPname());
				map1.put("nocheck", false);
				map1.put("struct", "TREE");
				map1.put("title", tsplan.getPname());

				if (tsplan.getplanEntity() != null) {
					map1.put("parentId", tsplan.getplanEntity().getId());
				} else {
					map1.put("parentId", "0");
				}
				dataList.add(map1);
			}
			j.setObj(dataList);
//			for (int i=0;;i++){
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 加载ztree
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getTreeData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeData(PlanEntity plan, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			List<PlanEntity> planList = new ArrayList<PlanEntity>();
			StringBuffer hql = new StringBuffer(" from PlanEntity t");
			//hql.append(" and (parent.id is null or parent.id='')");
			planList = this.systemService.findHql(hql.toString());
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			for (PlanEntity tsplan : planList) {
				String sqls = null;
				Object[] paramss = null;
				map = new HashMap<String, Object>();
				map.put("id", tsplan.getId());
				map.put("name", tsplan.getPname());
				map.put(tsplan.getId(), tsplan);
				if (tsplan.getplanEntity() != null) {
					map.put("pId", tsplan.getplanEntity().getId());
					map.put("open", false);
				} else {
					map.put("pId", "1");
					map.put("open", false);
				}
				sqls = "select count(1) from plan p where p.number = ?";
				paramss = new Object[]{tsplan.getId()};
				long counts = this.systemService.getCountForJdbcParam(sqls, paramss);
				if (counts > 0) {
					dataList.add(map);
				} else {
					PlanEntity de = this.systemService.get(PlanEntity.class, tsplan.getId());
					if (de != null) {
						map.put("id", de.getId());
						map.put("name", de.getPname());
						map.put(tsplan.getId(), tsplan);
						if (tsplan.getplanEntity() != null) {
							map.put("pId", tsplan.getplanEntity().getId());
							map.put("open", false);
						} else {
							map.put("pId", "1");
							map.put("open", false);
						}
						dataList.add(map);
					} else {
						map.put("open", false);
						dataList.add(map);
					}
				}
			}
			j.setObj(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 计划列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "update")
	public ModelAndView update(PlanEntity plan, HttpServletRequest req) {
		List<PlanEntity> planList = systemService.getList(PlanEntity.class);
		req.setAttribute("planList", planList);
		if (StringUtil.isNotEmpty(plan.getId())) {
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan", plan);
		}
		return new ModelAndView("com/jeecg/crrc/plan/plan-add");
	}

	@RequestMapping(params = "add")
	public ModelAndView add(PlanEntity plan, HttpServletRequest req) {
		List<PlanEntity> planList = systemService.getList(PlanEntity.class);
		req.setAttribute("planList", planList);
		req.setAttribute("pid", plan.getId());
		return new ModelAndView("com/jeecg/crrc/plan/plan-update");
	}

	/**
	 * 删除计划
	 *
	 * @param plan
	 * @param request
	 * @return
	 */


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
		CriteriaQuery cq = new CriteriaQuery(PlanEntity.class);
		if (null != request.getParameter("selfId")) {
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("planEntity.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("planEntity");
		}

		cq.addOrder("number", SortDirection.asc);

		cq.add();
		List<PlanEntity> plansList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "pname", "planEntitys");

		PlanEntity defaultplan = new PlanEntity();
		defaultplan.setId("");
		defaultplan.setPname("其他");
		plansList.add(0, defaultplan);

		comboTrees = systemService.ComboTree(plansList, comboTreeModel, null, true);
		return comboTrees;
	}

	@RequestMapping(params = "we")
	public ModelAndView we(PlanEntity plan, PlanEntity plans2, HttpServletRequest req) throws Exception {
		List<PlanEntity> plans = new ArrayList<PlanEntity>();
		StringBuffer hql = new StringBuffer(" from PlanEntity where number='" + plan.getId() + "'");
		plans = this.systemService.findHql(hql.toString());
//		System.out.println(plans.size());
		if (plans.size() != 0) {
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan", plan);
			return new ModelAndView("com/jeecg/crrc/plan/project3");
		} else {
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan", plan);
			return new ModelAndView("com/jeecg/crrc/planforplus/planForPlusMainList");

		}
	}

	@RequestMapping(params = "vg")
	public ModelAndView vg(PlanEntity plan,  HttpServletRequest req) throws Exception {
			plan = systemService.getEntity(PlanEntity.class, plan.getId());
			req.setAttribute("plan", plan);
			return new ModelAndView("com/jeecg/crrc/planforplus/planForPlusList");

		}


	@RequestMapping(params = "rng")
	public ModelAndView rng(PlanEntity plan, HttpServletRequest req) {
//        String sqls = null;
//
//        sqls = "select * from plan p where p.number = '"+plan.getId()+"'";
//
//        List<PlanEntity> planList = systemService.findListbySql(sqls);
//        System.out.println("..........##########"+planList);
////        String li=null;
////        for(PlanEntity pl:planList){
////			System.out.println("qwe12344523452345"+pl.getPname());
////		}
//
//        req.setAttribute("plans", planList);

		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		req.setAttribute("plans2", plan);
		return new ModelAndView("com/jeecg/crrc/planforplus/planForPlusList2");

	}

	@RequestMapping(params = "ig")
	public ModelAndView ig(PlanEntity plan, HttpServletRequest req) {
		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		req.setAttribute("plans", plan);
//		System.out.println(plan.getId());
		StringBuffer hql = new StringBuffer(" from CrrcUserEntity u where u_positionid='项目经理'");
		List<CrrcUserEntity> manager= this.systemService.findHql(hql.toString());
		System.out.println("??????"+manager.iterator().next().getUName());
		req.setAttribute("manager", manager);
		return new ModelAndView("com/jeecg/crrc/plan/project");

	}

	/**
	 * 添加项目
	 *
	 * @return
	 */
	@RequestMapping(params = "toAddproject")
	public ModelAndView toAddproject(HttpServletRequest req) {
		//JSONArray jsonArray=JSONArray.fromObject(getZtreeData());
		//req.setAttribute("regions", jsonArray.toString().replaceAll("pid","pId"));
		return new ModelAndView("com/jeecg/crrc/plan/project1");
	}
	/**
	 * Ztree
	 * 获取所有的省市区数据
	 * @return
	 */
	public List<Map<String, String>> getZtreeData(){
		return jeecgMinidaoDao.getAllRegions();
	}


	@RequestMapping(params = "jQgantt")
	public ModelAndView gantt(PlanEntity plan,HttpServletRequest request) {
		request.setAttribute("plan", plan);
		return new ModelAndView("com/jeecg/jQueryGantt/dhxgantt");
	}
    @RequestMapping(params = "gantt")
    public ModelAndView gantt1(PlanEntity plan,HttpServletRequest request) {
		request.setAttribute("plan", plan);
        return new ModelAndView("com/jeecg/jQueryGantt/gantt");
    }
    @RequestMapping(params = "updateGanttData")
    public void updateGanttData(String ganttData) {
        String a[]=ganttData.split(",");
        for(int i=0;i<a.length;i+=3){
            systemService.executeSql("update plan set dateby = '" + a[i+1] + "',dateend =  '" + a[i+2] + "'where id='" + a[i] + "'");
            systemService.executeSql("update plan_for_plus set dateby = '" + a[i+1] + "',dateend =  '" + a[i+2] + "'where id='" + a[i] + "'");
        }
    }

	@ResponseBody
	@RequestMapping(params = "getGanttData")
	public Map<String,Object> getGanttData(PlanEntity plan,HttpServletResponse response,HttpServletRequest request) {
   /*List<PlanEntity> planList = new ArrayList<PlanEntity>();
   StringBuffer hql = new StringBuffer(" from PlanEntity");
   planList = this.systemService.findHql(hql.toString());*/

		String flag="";
		if(null!=plan.getNumber()){
			flag+=plan.getNumber();
		}

		List<PlanEntity> treeList = new ArrayList<PlanEntity>();
		List<PlanEntity> treeNodes = new ArrayList<PlanEntity>();
		StringBuffer hql3 = new StringBuffer(" from PlanEntity");
		treeList = this.systemService.findHql(hql3.toString());
        plan = systemService.getEntity(PlanEntity.class, plan.getId());
		if(null==plan){
			dfsTree(treeList, treeNodes, null, 0);
		}else if(null==plan.getNumber()&&"".equals(flag)){
            treeNodes.add(plan);
            for (PlanEntity tplan : treeList) {
                if(null!=tplan.getNumber()&&tplan.getNumber().equals(plan.getId()))
                {
                    treeNodes.add(tplan);
                }
            }
        }else {
			treeNodes.add(plan);
			dfsTree(treeList,treeNodes,plan.getId(),0);
			treeNodes.get(0).setNumber(null);
//			System.out.println(plan.getPname()+":"+treeNodes.size());
		}
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = null;

		List<Map<String,Object>> taskList = new ArrayList<Map<String,Object>>();
		Map<String,Object> taskMap = new HashMap<String,Object>();

		for (PlanEntity tplan : treeNodes) {
			int day=(int) ((tplan.getDateend().getTime() - tplan.getDateby().getTime()) / (1000*3600*24))+1;
			dataMap = new HashMap<String,Object>();
			dataMap.put("id",tplan.getId());
			dataMap.put("text", tplan.getPname());
			dataMap.put("start_date", tplan.getDateby());
			dataMap.put("duration", day);
			if(tplan.getDateend().getTime()<(new Date().getTime())) {
				dataMap.put("color", "#FF7F00");
			}else{
				dataMap.put("color", "skyblue");
			}
			dataMap.put("progressColor", "lightgreen");
			dataMap.put("progress", ""+(Double.parseDouble(tplan.getProcess())*0.01));
			if(null!=tplan.getNumber()){
				dataMap.put("parent", tplan.getNumber());
			}
			dataMap.put("open", true);
			dataList.add(dataMap);
		}

		List<Map<String,Object>> linkList = new ArrayList<Map<String,Object>>();
		Map<String,Object> linkMap = null;

		for (PlanEntity tplan : treeNodes) {
			if(null!=tplan.getNumber()) {
				linkMap = new HashMap<String,Object>();
				linkMap.put("id", tplan.getId());
				linkMap.put("source", tplan.getNumber());
				linkMap.put("target", tplan.getId());
				linkMap.put("type", "1");
				linkList.add(linkMap);
			}
		}

		taskMap.put("data",dataList);
		taskMap.put("links",linkList);
		return taskMap;
	}


	/**
	 * 更新状态
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doindex")
	@ResponseBody
	public AjaxJson doindex(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		PlanEntity plan = new PlanEntity();
		plan = systemService.getEntity(PlanEntity.class, id);
//		System.out.println();
		String t = plan.getStatese();
//		System.out.println(t);
		Date nowdate = new Date();
//		System.out.println(nowdate);
		Date de = plan.getDateend();
//		System.out.println(de);
		int D = (int) ((de.getTime() - nowdate.getTime()) / (1000 * 3600 * 24)) + 1;
		if (t.equals("1") ) {
			message = "已确认完成，请勿重复确认";
		} else if (D > 0) {
			try {
				systemService.executeSql("update plan set statese =1 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan set process =100.0 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan_for_plus set statese =1 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan_for_plus set process =100.0 where id='" + plan.getId() + "'");

				message = "确认成功";
			} catch (Exception e) {
				e.printStackTrace();
				message = "确认失败";
			}
		} else {
			try {
				systemService.executeSql("update plan set statese =1 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan set process =100.0 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan_for_plus set statese =1 where id='" + plan.getId() + "'");
				systemService.executeSql("update plan_for_plus set process =100.0 where id='" + plan.getId() + "'");
				message = "确认成功";

			} catch (Exception e) {
				e.printStackTrace();
				message = "确认失败";
			}
		}

		j.setMsg(message);
		j.setObj(id);
		System.out.println(j.getMsg());
		System.out.println(j.getObj());
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
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PlanEntity plan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		Long childCount = systemService.getCountForJdbcParam("select count(1) from plan where number = ?", plan.getId());
		systemService.executeSql("delete from plan where id=?", plan.getId());
		systemService.executeSql("delete from plan_for_plus where id=?", plan.getId());
		j.setMsg("删除成功");
		return j;
	}

	@RequestMapping(params = "del2")
	@ResponseBody
	public AjaxJson del2(PlanEntity plan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String oriid = plan.getId();
		String id = plan.getId();
		plan = systemService.getEntity(PlanEntity.class, plan.getId());
		Long childCount = systemService.getCountForJdbcParam("select count(1) from plan where number = ?", plan.getId());
		StringBuffer hql = new StringBuffer(" from PlanEntity where number='" + plan.getId() + "'");
		List<PlanEntity> planlist = this.systemService.findHql(hql.toString());
		while (!planlist.isEmpty()) {

			for (int x = 0; x < planlist.size(); x++) {
				systemService.executeSql("delete from plan where id=?", planlist.get(x).getId());
				systemService.executeSql("delete from plan_for_plus where id=?", planlist.get(x).getId());
			}
			id = (planlist.get(0).getId());
			StringBuffer hql2 = new StringBuffer(" from PlanEntity where number='" + id + "'");
			planlist = this.systemService.findHql(hql2.toString());
		}
		systemService.executeSql("delete from plan where id='" + oriid + "'");
		systemService.executeSql("delete from plan_for_plus where id='" + oriid + "'");
		j.setMsg("删除成功");
		return j;
	}
	@RequestMapping(params = "checkifhaveparent")
	@ResponseBody
	public AjaxJson checkifhaveparent(PlanEntity plan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
//    StringBuffer hql = new StringBuffer(" from PlanEntity where id='"+plan.getId()+"'");
//    List<PlanEntity>planlist = this.systemService.findHql(hql.toString());
//    if(null==planlist.get(0).getNumber()){
//    j.setMsg("0");System.out.println("sssssssssssssssssssssssssss"+j.getMsg());}//如果是最顶级  设置为0  表示不可删除

//    else{
		StringBuffer hql2 = new StringBuffer(" from PlanEntity where number='" + plan.getId() + "'");
		List<PlanEntity> planlist2 = this.systemService.findHql(hql2.toString());
		System.out.println(planlist2.isEmpty());
		if (planlist2.isEmpty()) {
			j.setMsg("1");//如果是最低级  设置为1  表示删除
			System.out.println("sssssssssssssssssssssssssss" + j.getMsg());
		} else {
			j.setMsg("2");//如果不是顶级也不是最低级  设置为2 表示删除其即其子节点
			System.out.println("sssssssssssssssssssssssssss" + j.getMsg());
		}
//  }
		return j;
	}

}
