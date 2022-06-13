package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.StrategyList;

public interface StrategyListMapper {

	//新增
	public Integer insertStrategy(StrategyList strategyList);
	
	//修改
	public Integer updateStrategy(StrategyList strategyList);
	
	//删除
	public Integer delStrategy(@Param("id")Integer id);
	
	//按照modelId查询
	public List<StrategyList> findStrategyByModelId(Integer modelId);
	
	//查询所有正在执行的策略
	public List<StrategyList> findStrategyBySte();
	
}
