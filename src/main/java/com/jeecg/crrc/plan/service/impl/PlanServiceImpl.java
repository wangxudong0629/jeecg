package com.jeecg.crrc.plan.service.impl;

import com.jeecg.crrc.plan.service.PlanServiceI;
import com.jeecg.crrc.plan.entity.PlanEntity;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("planService")
@Transactional
public class PlanServiceImpl extends CommonServiceImpl implements PlanServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(PlanEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(PlanEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PlanEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}