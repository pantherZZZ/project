package com.th.system.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.th.system.vo.DistributionVo;
import com.th.system.vo.StatementVo;

public interface SysDataDisposeService {

	public JSONArray realTimeSupervise(Integer equipmentId,String time1,String time2);
	
	public JSONArray measureData(String[] measureIdArr,String time);
	
	public JSONArray projectStatistics();
	
	public List<DistributionVo> modelDistribution();
	
	public JSONArray facilityStatistics();
	
	public JSONArray facilityStatisticsPro();
	
	public List<StatementVo> findStatement();
	
	public JSONObject findEquipmentState();
	
	public JSONObject findStatementCount();
	
	public JSONObject findModelData(Integer modelId);
	
	public JSONObject findFullData(Integer modelId);
	
	public JSONObject findValidData(Integer modelId);
}
