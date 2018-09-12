package com.jeecg.crrc.planforplus.service;
import com.jeecg.crrc.planforplus.entity.PlanForPlusEntity;
import com.jeecg.crrc.planforplus.entity.PlanForPlusChildEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface PlanForPlusServiceI extends CommonService{
 	public void delete(PlanForPlusEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(PlanForPlusEntity planForPlus,
	        List<PlanForPlusChildEntity> planForPlusChildList) throws Exception;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(PlanForPlusEntity planForPlus,
	        List<PlanForPlusChildEntity> planForPlusChildList) throws Exception;
	        
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (PlanForPlusEntity planForPlus) throws Exception;
}
