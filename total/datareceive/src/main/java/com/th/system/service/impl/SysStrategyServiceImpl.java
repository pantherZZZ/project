package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysStrategyMapper;
import com.th.system.po.SysStrategy;
import com.th.system.service.SysStrategyService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysStrategyServiceImpl")
@Transactional
public class SysStrategyServiceImpl implements SysStrategyService{

	@Autowired
	private SysStrategyMapper sysStrategyMapper;
	
	@Override
	public Integer insertStrategy(String strategyName,Integer modelId,String way
			,String cycle,String startTime,String endTime,String describe) {
		SysStrategy strategy = new SysStrategy();
//		String strategyId = UUID.randomUUID().toString().replace("-", "");
//		strategy.setStrategyId(strategyId);
		strategy.setStrategyName(strategyName);
		strategy.setModelId(modelId);
		strategy.setWay(way);
		strategy.setCycle(cycle);
		strategy.setStartTime(startTime);
		strategy.setEndTime(endTime);
		strategy.setDescribe(describe);
		return sysStrategyMapper.insertStrategy(strategy);
	}

	@Override
	public List<SysStrategy> findByModelId(Integer modelId,int page,int limit,String val) {
		// TODO Auto-generated method stub
		return sysStrategyMapper.findByModelId(modelId,page,limit,val);
	}

	@Override
	public Integer updateStrategy(Integer strategyId, String strategyName, String way, String cycle, String startTime,
			String endTime, String describe) {
		SysStrategy strategy = new SysStrategy();
		strategy.setStrategyId(strategyId);
		strategy.setStrategyName(strategyName);
		strategy.setWay(way);
		strategy.setCycle(cycle);
		strategy.setStartTime(startTime);
		strategy.setEndTime(endTime);
		strategy.setDescribe(describe);
		return sysStrategyMapper.updateStrategy(strategy);
	}

	@Override
	public Integer delStrategy(Integer strategyId) {
		// TODO Auto-generated method stub
		return sysStrategyMapper.delStrategy(strategyId);
	}

	@Override
	public SysStrategy findByStrategyId(Integer strategyId) {
		// TODO Auto-generated method stub
		return sysStrategyMapper.findByStrategyId(strategyId);
	}

}
