package com.jeecg.crrc.work.service;
import com.jeecg.crrc.work.entity.WorkEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WorkServiceI extends CommonService{
	
 	public void delete(WorkEntity entity) throws Exception;
 	
 	public Serializable save(WorkEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WorkEntity entity) throws Exception;
 	
}
