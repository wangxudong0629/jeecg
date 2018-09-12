package com.jeecg.crrc.plan.service;
import com.jeecg.crrc.plan.entity.PlanEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PlanServiceI extends CommonService{
	
 	public void delete(PlanEntity entity) throws Exception;
 	
 	public Serializable save(PlanEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PlanEntity entity) throws Exception;
 	
}
