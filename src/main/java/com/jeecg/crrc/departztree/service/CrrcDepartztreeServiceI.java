package com.jeecg.crrc.departztree.service;
import com.jeecg.crrc.departztree.entity.CrrcDepartztreeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CrrcDepartztreeServiceI extends CommonService{
	
 	public void delete(CrrcDepartztreeEntity entity) throws Exception;
 	
 	public Serializable save(CrrcDepartztreeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CrrcDepartztreeEntity entity) throws Exception;
 	
}
