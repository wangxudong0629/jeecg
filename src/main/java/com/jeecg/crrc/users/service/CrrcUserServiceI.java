package com.jeecg.crrc.users.service;
import com.jeecg.crrc.users.entity.CrrcUserEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CrrcUserServiceI extends CommonService{
	
 	public void delete(CrrcUserEntity entity) throws Exception;
 	
 	public Serializable save(CrrcUserEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CrrcUserEntity entity) throws Exception;
 	
}
