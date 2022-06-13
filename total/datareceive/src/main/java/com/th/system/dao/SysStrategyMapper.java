package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysStrategy;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysStrategyMapper {
	//新增
	public Integer insertStrategy(SysStrategy sysStrategy);
	//按照模型id查询
	public List<SysStrategy> findByModelId(@Param("modelId")Integer modelId,@Param("page")int page,
			@Param("limit")int limit,@Param("val")String val);
	//修改
	public Integer updateStrategy(SysStrategy sysStrategy);
	//删除
	public Integer delStrategy(@Param("strategyId")Integer strategyId);
	
	//按照策略id查询
	public SysStrategy findByStrategyId(@Param("strategyId")Integer strategyId);
}
