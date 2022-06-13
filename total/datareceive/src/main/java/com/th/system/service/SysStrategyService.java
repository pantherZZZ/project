package com.th.system.service;

import java.util.List;

import com.th.system.po.SysStrategy;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysStrategyService {

	public Integer insertStrategy(String strategyName,Integer modelId,String way
			,String cycle,String startTime,String endTime,String describe);
	
	public List<SysStrategy> findByModelId(Integer modelId,int page,int limit,String val);
	
	//修改
	public Integer updateStrategy(Integer strategyId,String strategyName,String way
			,String cycle,String startTime,String endTime,String describe);
	//删除
	public Integer delStrategy(Integer strategyId);
	
	//按照策略id查询
	public SysStrategy findByStrategyId(Integer strategyId);
}
