package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysThresholdValue;
import com.th.system.vo.SysThresholdValueVo;

public interface SysThresholdValueMapper {
	//新增
	public Integer updateThreshold(SysThresholdValue sysThresholdValue);
	//修改
	public Integer insertThreshold(SysThresholdValue sysThresholdValue);
	
	//删除
	public Integer delThresholdValue(@Param("thresholdValueId")Integer thresholdValueId);
	
	//按照modelId查询
	public List<SysThresholdValueVo> findThresholdValueModelId(@Param("modelId")Integer modelId,@Param("page")int page,@Param("limit")int limit,@Param("measureName")String measureName);
	
	//按照id查询
	public SysThresholdValueVo findByThresholdValueId(@Param("thresholdValueId")Integer thresholdValueId);

	//查询所有阀值 一级警报
	public List<SysThresholdValue> oneWarning(@Param("value")String value,@Param("equipmentId")Integer equipmentId);

	//查询所有阀值 二级警报
	public List<SysThresholdValue> twoWarning(@Param("value")String value,@Param("equipmentId")Integer equipmentId);
		
	//查询所有阀值 三级警报
	public List<SysThresholdValue> threeWarning(@Param("value")String value,@Param("equipmentId")Integer equipmentId);
}
