package com.jeecg.crrc.tabletestb.service;
import com.jeecg.crrc.tabletestb.entity.TabletestBEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TabletestBServiceI extends CommonService{
	
 	public void delete(TabletestBEntity entity) throws Exception;
 	
 	public Serializable save(TabletestBEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TabletestBEntity entity) throws Exception;
 	
}
