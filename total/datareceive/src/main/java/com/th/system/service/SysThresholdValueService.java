package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysThresholdValue;
import com.th.system.vo.SysThresholdValueVo;

public interface SysThresholdValueService {

	//修改
	public Integer updateThreshold(SysThresholdValue sysThresholdValue);
	//新增
	public Integer insertThreshold(SysThresholdValue sysThresholdValue);
	
	//新增
	public int insertThresholdPro(HttpServletRequest request,Integer thresholdValueId,
			Integer measureId,String thresholdValue1,String thresholdValue2,String thresholdValue3);
	
	
	//按照modelId查询
	public List<SysThresholdValueVo> findThresholdValueModelId(Integer modelId,int page,int limit,String measureName);
		
	
	//查询所有阀值 一级警报
	public List<SysThresholdValue> oneWarning(String value,Integer equipmentId);

	//查询所有阀值 二级警报
	public List<SysThresholdValue> twoWarning(String value,Integer equipmentId);
			
	//查询所有阀值 三级警报
	public List<SysThresholdValue> threeWarning(String value,Integer equipmentId);
	
	//删除
	public Integer delThresholdValue(Integer thresholdValueId);
	
	//按照id查询
	public String findByThresholdValueId(Integer thresholdValueId);
	
	public String findThresholdValue(Integer modelId,String page,String limit,String measureName);
	
}
