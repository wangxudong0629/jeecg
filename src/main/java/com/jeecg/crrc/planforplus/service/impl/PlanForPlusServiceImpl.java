package com.jeecg.crrc.planforplus.service.impl;
import com.jeecg.crrc.planforplus.service.PlanForPlusServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.crrc.planforplus.entity.PlanForPlusEntity;
import com.jeecg.crrc.planforplus.entity.PlanForPlusChildEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("planForPlusService")
@Transactional
public class PlanForPlusServiceImpl extends CommonServiceImpl implements PlanForPlusServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(PlanForPlusEntity entity) throws Exception{
 		super.delete(entity);
 	}
	public void addMain(PlanForPlusEntity planForPlus,
	        List<PlanForPlusChildEntity> planForPlusChildList) throws Exception{
			//保存主信息
			this.save(planForPlus);
		
			/**保存-添加人员活动信息*/
			for(PlanForPlusChildEntity planForPlusChild:planForPlusChildList){
				//外键设置
				planForPlusChild.setPid(planForPlus.getId());
				this.save(planForPlusChild);
			}
	}

	public void updateMain(PlanForPlusEntity planForPlus,
	        List<PlanForPlusChildEntity> planForPlusChildList) throws Exception {
		//保存主表信息
		if(StringUtil.isNotEmpty(planForPlus.getId())){
			try {
				PlanForPlusEntity temp = findUniqueByProperty(PlanForPlusEntity.class, "id", planForPlus.getId());
				MyBeanUtils.copyBeanNotNull2Bean(planForPlus, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(planForPlus);
		}
		//===================================================================================
		//获取参数
		Object id0 = planForPlus.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-添加人员活动信息
	    String hql0 = "from PlanForPlusChildEntity where 1 = 1 AND pid = ? ";
	    List<PlanForPlusChildEntity> planForPlusChildOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-添加人员活动信息
		if(planForPlusChildList!=null&&planForPlusChildList.size()>0){
		for(PlanForPlusChildEntity oldE:planForPlusChildOldList){
			boolean isUpdate = false;
				for(PlanForPlusChildEntity sendE:planForPlusChildList){
					//需要更新的明细数据-添加人员活动信息
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-添加人员活动信息
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-添加人员活动信息
			for(PlanForPlusChildEntity planForPlusChild:planForPlusChildList){
				if(oConvertUtils.isEmpty(planForPlusChild.getId())){
					//外键设置
					planForPlusChild.setPid(planForPlus.getId());
					this.save(planForPlusChild);
				}
			}
		}
	}

	public void delMain(PlanForPlusEntity planForPlus) throws Exception{
		//删除主表信息
		this.delete(planForPlus);
		//===================================================================================
		//获取参数
		Object id0 = planForPlus.getId();
		//===================================================================================
		//删除-添加人员活动信息
	    String hql0 = "from PlanForPlusChildEntity where 1 = 1 AND pid = ? ";
	    List<PlanForPlusChildEntity> planForPlusChildOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(planForPlusChildOldList);
		
	}
 	
}