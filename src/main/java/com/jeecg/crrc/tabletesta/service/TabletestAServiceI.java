package com.jeecg.crrc.tabletesta.service;
import com.jeecg.crrc.tabletesta.entity.TabletestAEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TabletestAServiceI extends CommonService{
	
 	public void delete(TabletestAEntity entity) throws Exception;
 	
 	public Serializable save(TabletestAEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TabletestAEntity entity) throws Exception;
 	
}
