package com.jeecg.crrc.papers.service;
import com.jeecg.crrc.papers.entity.PapersEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PapersServiceI extends CommonService{
	
 	public void delete(PapersEntity entity) throws Exception;
 	
 	public Serializable save(PapersEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PapersEntity entity) throws Exception;
 	
}
