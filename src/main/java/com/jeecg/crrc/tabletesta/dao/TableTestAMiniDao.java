package com.jeecg.crrc.tabletesta.dao;

import com.jeecg.crrc.tabletesta.entity.TabletestAEntity;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

@MiniDao
public interface TableTestAMiniDao {

    @ResultType(TabletestAEntity.class)
    public MiniDaoPage<TabletestAEntity> getAllEntities(@Param("jeecgDemo") TabletestAEntity jeecgDemo, @Param("page")  int page, @Param("rows") int rows, @Param("sort")String sort, @Param("order")String order, @Param("authSql") String authSql);
}
