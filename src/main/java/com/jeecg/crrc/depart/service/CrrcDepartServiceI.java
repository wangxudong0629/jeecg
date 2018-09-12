package com.jeecg.crrc.depart.service;
import com.jeecg.crrc.depart.entity.CrrcDepartEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CrrcDepartServiceI extends CommonService{
	
 	public void delete(CrrcDepartEntity entity) throws Exception;
 	
 	public Serializable save(CrrcDepartEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CrrcDepartEntity entity) throws Exception;
 	
}
