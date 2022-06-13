package com.th.system.service;

import java.util.List;

import com.th.system.po.SysEquipment;
import com.th.system.vo.SysEquipmentVo;

public interface SysEquipmentService {

	//新增
	public Integer insertEquipment(SysEquipment sysEquipment);
	
	//修改
	public Integer updateEquipment(SysEquipment sysEquipment);
	
	//查询已安装设备个数
	public Integer findCount();
	
	//查询已安装的所有设备
	public List<SysEquipment> findAll();

	//删除dtu
	public Integer delEquipment(Integer equipmentId);

	public List<SysEquipmentVo> findEquipment(Integer modelId,int page,int limit,String equipmentName,String val);
	
	public List<SysEquipmentVo> findDTUEquipment(Integer modelId,
			int page,int limit,String equipmentName,String val);
	
    public SysEquipmentVo findEquipmentById(Integer equipmentId);
	
	public SysEquipmentVo findDTUEquipmentById(Integer equipmentId);
	
	public SysEquipment findAllPro(Integer equipmentId);
	
	public SysEquipment findByNumber(String number);
	
	//按照设备编号查询
	public SysEquipment findByNumberPro(String number);
	
	//查询已安装设备个数 按照时间
	public Integer findTimeCount(String time);
	
	//查询已安装设备个数
	public Integer findModelCount(Integer modelId);

	//删除设备
	Integer deleteEquipment(Integer equipmentId);

	Integer updateOnLine(Integer equipmentId);

	Integer updateOffLine(Integer equipmentId);

	Integer updateOn(Integer equipmentId);

	Integer updateOff(Integer equipmentId);

	List<SysEquipment> findAllEquipment();
}
