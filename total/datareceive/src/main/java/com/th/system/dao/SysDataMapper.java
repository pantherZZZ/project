package com.th.system.dao;

import com.th.system.po.SysData;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDataMapper {
	
	public Integer insertTextData(@Param("data")String data,@Param("time")String time);
	
	//新增
	public Integer insertData(SysData sysData);
	
	//按照设备id查询
	public SysData findByEquipment(@Param("equipmentId")Integer equipmentId);
	
	//查询数量
	public Integer findDataCount();
	
	public SysData findHourData(@Param("equipmentId")Integer equipmentId
			,@Param("time")String time
			,@Param("hour")String hour);
}
