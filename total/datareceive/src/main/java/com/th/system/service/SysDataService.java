package com.th.system.service;

import java.net.Socket;
import java.util.List;

import com.th.system.po.SysData;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDataService {
	
	
	public Integer insertTextData(String data, String time);
	//新增
	public Integer insertData(Integer equipmentId,Integer modelId,String data
			,String specification,String time,String hour);
	
	//按照设备id查询
	public SysData findByEquipment(Integer equipmentId);
	
	public void showData(String instruct);
	
	//查询数量
	public Integer findDataCount();
	
	public SysData findHourData(Integer equipmentId
			,String time
			,String hour);
}
