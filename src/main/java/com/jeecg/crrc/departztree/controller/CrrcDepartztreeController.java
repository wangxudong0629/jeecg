package com.jeecg.crrc.departztree.controller;
import com.jeecg.crrc.depart.entity.CrrcDepartEntity;
import com.jeecg.crrc.departztree.entity.CrrcDepartztreeEntity;
import com.jeecg.crrc.departztree.service.CrrcDepartztreeServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeecg.crrc.users.entity.CrrcUserEntity;
import com.jeecg.crrc.work.entity.WorkEntity;
import org.jeecgframework.core.util.*;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;

/**   
 * @Title: Controller  
 * @Description: 组织机构总表
 * @author onlineGenerator
 * @date 2018-09-01 16:53:49
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/crrcDepartztreeController")
public class CrrcDepartztreeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CrrcDepartztreeController.class);

	@Autowired
	private CrrcDepartztreeServiceI crrcDepartztreeService;
	@Autowired
	private SystemService systemService;
	
	


	/**
	 * 组织机构总表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
 			return new ModelAndView("com/jeecg/crrc/departztree/crrcDepartztreeList");
	}

	@RequestMapping(params = "ztreeDemo")
	public ModelAndView ztreeDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/crrc/departztree/DepartCrrcztree");
	}

	@RequestMapping(params = "toAddproject")
	public ModelAndView toAddproject(HttpServletRequest req) {
		return new ModelAndView("com/jeecg/crrc/departztree/Depart-add");
	}
	@RequestMapping(params = "ig")
	public ModelAndView ig( HttpServletRequest req) {
		return new ModelAndView("com/jeecg/crrc/departztree/hplushomes");
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
	public void datagrid(CrrcDepartztreeEntity crrcDepartztree,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CrrcDepartztreeEntity.class, dataGrid);
		if(StringUtil.isEmpty(crrcDepartztree.getId())){
			cq.isNull("deNumber");
		}else{

			cq.eq("deNumber", crrcDepartztree.getId());
			crrcDepartztree.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcDepartztree, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.crrcDepartztreeService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除组织机构总表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CrrcDepartztreeEntity crrcDepartztree, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		crrcDepartztree = systemService.getEntity(CrrcDepartztreeEntity.class, crrcDepartztree.getId());
		message = "组织机构总表删除成功";
		try{
			crrcDepartztreeService.delete(crrcDepartztree);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "组织机构总表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除组织机构总表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "组织机构总表删除成功";
		try{
			for(String id:ids.split(",")){
				CrrcDepartztreeEntity crrcDepartztree = systemService.getEntity(CrrcDepartztreeEntity.class,
				id
				);
				crrcDepartztreeService.delete(crrcDepartztree);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "组织机构总表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加组织机构总表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CrrcDepartEntity crrcDepart, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "组织机构总表添加成功";
		String id=UUIDGenerator.generate();

		try{
			String sql="";
			sql+="insert into crrc_departztree (id,de_name,";
			if(null!=crrcDepart.getDNumber()){
				sql+="de_number,";
			}
			sql+="de_type) values('"
					+ id + "','"
					+ crrcDepart.getDName() + "','";
			if(null!=crrcDepart.getDNumber()){
				sql+= crrcDepart.getDNumber() + "','";
			}
			sql+= 0 +  "')";
			systemService.executeSql(sql);
			String sql1="";
			sql1+="insert into crrc_depart (id,d_name,";
			if(null!=crrcDepart.getDNumber()){
				sql1+="d_number,";
			}
			sql1+="d_iphone,d_email,d_remarks) values('"
					+ id + "','"
					+ crrcDepart.getDName() + "','";
			if(null!=crrcDepart.getDNumber()){
				sql1+= crrcDepart.getDNumber() + "','";
			}
			sql1+= crrcDepart.getDIphone() +  "','"+crrcDepart.getDEmail() +  "','"+crrcDepart.getDRemarks()+  "')";
			systemService.executeSql(sql1);
		}catch(Exception e){
			e.printStackTrace();
			message = "组织机构总表添加失败";
//			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 更新组织机构总表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CrrcDepartztreeEntity crrcDepartztree, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "组织机构总表更新成功";
		CrrcDepartztreeEntity t = crrcDepartztreeService.get(CrrcDepartztreeEntity.class, crrcDepartztree.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(crrcDepartztree, t);
			if(StringUtil.isEmpty(t.getDeNumber())){
				t.setDeNumber(null);
			}
			crrcDepartztreeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "组织机构总表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 组织机构总表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CrrcDepartztreeEntity crrcDepartztree, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcDepartztree.getId())) {
			crrcDepartztree = crrcDepartztreeService.getEntity(CrrcDepartztreeEntity.class, crrcDepartztree.getId());
			req.setAttribute("crrcDepartztree", crrcDepartztree);
		}
		return new ModelAndView("com/jeecg/crrc/departztree/User-add");
	}

	@RequestMapping(params = "goAdd1")
	public ModelAndView goAdd1(CrrcDepartztreeEntity crrcDepartztree, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcDepartztree.getId())) {
			crrcDepartztree = crrcDepartztreeService.getEntity(CrrcDepartztreeEntity.class, crrcDepartztree.getId());
			req.setAttribute("crrcDepartztree", crrcDepartztree);
		}
		return new ModelAndView("com/jeecg/crrc/departztree/Depart-add1");
	}

	/**
	 * 组织机构总表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CrrcDepartztreeEntity crrcDepartztree, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(crrcDepartztree.getId())) {
			crrcDepartztree = crrcDepartztreeService.getEntity(CrrcDepartztreeEntity.class, crrcDepartztree.getId());
			req.setAttribute("crrcDepartztreePage", crrcDepartztree);
		}
		return new ModelAndView("com/jeecg/crrc/departztree/crrcDepartztree-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","crrcDepartztreeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CrrcDepartztreeEntity crrcDepartztree,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CrrcDepartztreeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, crrcDepartztree, request.getParameterMap());
		List<CrrcDepartztreeEntity> crrcDepartztrees = this.crrcDepartztreeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"组织机构总表");
		modelMap.put(NormalExcelConstants.CLASS,CrrcDepartztreeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("组织机构总表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,crrcDepartztrees);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CrrcDepartztreeEntity crrcDepartztree,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"组织机构总表");
    	modelMap.put(NormalExcelConstants.CLASS,CrrcDepartztreeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("组织机构总表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CrrcDepartztreeEntity> listCrrcDepartztreeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CrrcDepartztreeEntity.class,params);
				for (CrrcDepartztreeEntity crrcDepartztree : listCrrcDepartztreeEntitys) {
					crrcDepartztreeService.save(crrcDepartztree);
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
	@RequestMapping(params = "getTreeDemoData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeDemoData(CrrcDepartztreeEntity depart, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> map1 = null;//父对象
		try {
			List<CrrcDepartztreeEntity> departList1 = new ArrayList<CrrcDepartztreeEntity>();//父对象集合
			StringBuffer hql = new StringBuffer(" from CrrcDepartztreeEntity d ");
			departList1 = this.systemService.findHql(hql.toString());
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();//所有集合
			for (CrrcDepartztreeEntity tsdepart : departList1) {
				map1 = new HashMap<String, Object>();
				map1.put("chkDisabled", false);
				map1.put("click", true);
				map1.put("id", tsdepart.getId());
				map1.put("name", tsdepart.getDeName());
				map1.put("nocheck", false);
				map1.put("struct", "TREE");
				map1.put("title", tsdepart.getDeName());

				if (tsdepart.getcrrcDepartztree() != null) {
					map1.put("parentId", tsdepart.getcrrcDepartztree().getId());
				} else {
					map1.put("parentId", "0");
				}
				dataList.add(map1);
			}
			j.setObj(dataList);
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
	public AjaxJson getTreeData(CrrcDepartztreeEntity depart, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			List<CrrcDepartztreeEntity> departList = new ArrayList<CrrcDepartztreeEntity>();
			StringBuffer hql = new StringBuffer(" from CrrcDepartztreeEntity d");
			departList = this.systemService.findHql(hql.toString());
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			for (CrrcDepartztreeEntity tsdepart : departList) {
				String sqls = null;
				Object[] paramss = null;
				map = new HashMap<String, Object>();
				map.put("id", tsdepart.getId());
				map.put("name", tsdepart.getDeName());
				map.put(tsdepart.getId(), tsdepart);
				if (tsdepart.getcrrcDepartztree() != null) {
					map.put("pId", tsdepart.getcrrcDepartztree().getId());
					map.put("open", false);
				} else {
					map.put("pId", "1");
					map.put("open", false);
				}
				sqls = "select count(1) from crrc_departztree de where de.de_number = ?";
				paramss = new Object[]{tsdepart.getId()};
				long counts = this.systemService.getCountForJdbcParam(sqls, paramss);
				if (counts > 0) {
					dataList.add(map);
				} else {
					CrrcDepartztreeEntity de = this.systemService.get(CrrcDepartztreeEntity.class, tsdepart.getId());
					if (de != null) {
						map.put("id", de.getId());
						map.put("name", de.getDeName());
						map.put(tsdepart.getId(), tsdepart);
						if (tsdepart.getcrrcDepartztree() != null) {
							map.put("pId", tsdepart.getcrrcDepartztree().getId());
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
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CrrcDepartztreeEntity depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		depart = systemService.getEntity(CrrcDepartztreeEntity.class, depart.getId());
//		Long childCount = systemService.getCountForJdbcParam("select count(1) from crrc_departztree where de_number =?", depart.getId());
		systemService.executeSql("delete from crrc_departztree where id=?", depart.getId());
//		systemService.executeSql("delete from plan_for_plus where id=?", plan.getId());
		j.setMsg("删除成功");
		return j;
	}

	@RequestMapping(params = "del2")
	@ResponseBody
	public AjaxJson del2(CrrcDepartztreeEntity depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String oriid = depart.getId();
		String id = depart.getId();
		depart = systemService.getEntity(CrrcDepartztreeEntity.class, depart.getId());
		Long childCount = systemService.getCountForJdbcParam("select count(1) from crrc_departztree where de_number = ?", depart.getId());
		StringBuffer hql = new StringBuffer(" from CrrcDepartztreeEntity where de_number='" + depart.getId() + "'");
		List<CrrcDepartztreeEntity> departlist = this.systemService.findHql(hql.toString());
		while (!departlist.isEmpty()) {

			for (int x = 0; x < departlist.size(); x++) {
				systemService.executeSql("delete from crrc_departztree where id=?", departlist.get(x).getId());
//				systemService.executeSql("delete from plan_for_plus where id=?", planlist.get(x).getId());
			}
			id = (departlist.get(0).getId());
			StringBuffer hql2 = new StringBuffer(" from CrrcDepartztreeEntity where de_number='" + id + "'");
			departlist = this.systemService.findHql(hql2.toString());
		}
		systemService.executeSql("delete from crrc_departztree where id='" + oriid + "'");
//		systemService.executeSql("delete from plan_for_plus where id='" + oriid + "'");
		j.setMsg("删除成功");
		return j;
	}
	@RequestMapping(params = "checkifhaveparent")
	@ResponseBody
	public AjaxJson checkifhaveparent(CrrcDepartztreeEntity depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
//    StringBuffer hql = new StringBuffer(" from PlanEntity where id='"+plan.getId()+"'");
//    List<PlanEntity>planlist = this.systemService.findHql(hql.toString());
//    if(null==planlist.get(0).getNumber()){
//    j.setMsg("0");System.out.println("sssssssssssssssssssssssssss"+j.getMsg());}//如果是最顶级  设置为0  表示不可删除

//    else{
		StringBuffer hql2 = new StringBuffer(" from CrrcDepartztreeEntity where de_number='" + depart.getId() + "'");
		List<CrrcDepartztreeEntity> departlist2 = this.systemService.findHql(hql2.toString());
		System.out.println(departlist2.isEmpty());
		if (departlist2.isEmpty()) {
			j.setMsg("1");//如果是最低级  设置为1  表示删除
			System.out.println("sssssssssssssssssssssssssss" + j.getMsg());
		} else {
			j.setMsg("2");//如果不是顶级也不是最低级  设置为2 表示删除其即其子节点
			System.out.println("sssssssssssssssssssssssssss" + j.getMsg());
		}
//  }
		return j;
	}

	@RequestMapping(params = "we")
	public ModelAndView we(CrrcDepartztreeEntity depart,  HttpServletRequest req) throws Exception {
		depart = systemService.getEntity(CrrcDepartztreeEntity.class, depart.getId());
		if (depart.getDeType() .equals("0") ) {
			CrrcDepartEntity depart1=null;
			depart1 = systemService.getEntity(CrrcDepartEntity.class, depart.getId());
			System.out.println("###"+depart1.getDName());
				req.setAttribute("depart1", depart1);
			return new ModelAndView("com/jeecg/crrc/departztree/project");
		} else{
			CrrcUserEntity user1=null;
			user1 = systemService.getEntity(CrrcUserEntity.class, depart.getId());
			req.setAttribute("user1", user1);
			return new ModelAndView("com/jeecg/crrc/departztree/User1");

		}
	}
	@RequestMapping(params = "rng")
	public ModelAndView rng(CrrcUserEntity user, HttpServletRequest req) {
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

		user = systemService.getEntity(CrrcUserEntity.class, user.getId());
		System.out.println("$$"+user.getUName());
		req.setAttribute("user", user);
		return new ModelAndView("com/jeecg/crrc/departztree/numbertestList1");

	}
	@RequestMapping(params = "item",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public List<WorkEntity> findItemByName(WorkEntity work,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"utf-8");
		AjaxJson j = new AjaxJson();
		List<WorkEntity> demoProjectList = new ArrayList<WorkEntity>();
		StringBuffer hql = new StringBuffer(" from WorkEntity where name = '"+name+"'");
		demoProjectList = this.systemService.findHql(hql.toString());

		System.out.println(demoProjectList);
		return demoProjectList;
	}
}

