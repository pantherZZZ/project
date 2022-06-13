package com.th.system.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysEquipment;
import com.th.system.vo.SysEquipmentVo;

//sys_equipment表
//设备类
public interface SysEquipmentMapper extends BaseMapper<SysEquipment> {
	
	//查询已安装设备个数
	public Integer findCount();
	
	//查询已安装设备个数
	public Integer findModelCount(@Param("modelId")Integer modelId);
	
	//查询已安装设备个数 按照时间
	public Integer findTimeCount(@Param("time")String time);	
	
	//查询已安装的所有设备
	public List<SysEquipment> findAll();
	
	public SysEquipment findAllPro(@Param("equipmentId")Integer equipmentId);
	
	//按照设备编号查询
	public SysEquipment findByNumber(@Param("number")String number);
	
	//按照设备编号查询
	public SysEquipment findByNumberPro(@Param("number")String number);
	
	//新增 
	public Integer insertEquipment(SysEquipment sysEquipment);
	
	//修改
	public Integer updateEquipment(SysEquipment sysEquipment);
	
	public Integer delEquipment(@Param("equipmentId")Integer equipmentId);

	public List<SysEquipmentVo> findEquipment(@Param("modelId")Integer modelId,@Param("page")int page,
			@Param("limit")int limit,@Param("equipmentName")String equipmentName,@Param("val")String val);
	
	public List<SysEquipmentVo> findDTUEquipment(@Param("modelId")Integer modelId,@Param("page")int page,@Param("limit")int limit
			,@Param("equipmentName")String equipmentName,@Param("val")String val);

	public SysEquipmentVo findEquipmentById(@Param("equipmentId")Integer equipmentId);
	
	public SysEquipmentVo findDTUEquipmentById(@Param("equipmentId")Integer equipmentId);
}
