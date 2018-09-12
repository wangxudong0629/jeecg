package com.jeecg.crrc.money.service;
import com.jeecg.crrc.money.entity.MoneyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface MoneyServiceI extends CommonService{
	
 	public void delete(MoneyEntity entity) throws Exception;
 	
 	public Serializable save(MoneyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(MoneyEntity entity) throws Exception;
 	
}
