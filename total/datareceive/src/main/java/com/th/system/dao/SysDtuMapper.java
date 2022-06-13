package com.th.system.dao;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.system.po.SysEquipment;
import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysDtu;
import com.th.system.vo.SysDtuVo;

public interface SysDtuMapper {

	//新增
	public Integer insertDTU(SysDtu sysDtu);
	
	//查询数量
	public Integer findDTUCount();
	
	//查询数量 按照时间
	public Integer findTimeCount(@Param("time")String time);
	
	//按照modelId查询
	public List<SysDtuVo> findByModelId(@Param("modelId")Integer modelId ,@Param("page")int page,@Param("limit")int limit);
	
	//按照id查询
	public SysDtuVo findById(@Param("dtuId")Integer dtuId);
	
	//修改
	public Integer updateDTU(SysDtu sysDtu);
	
	//删除
	public Integer delDTU(@Param("dtuId")Integer dtuId);

	public Integer findDTUModleCount(@Param("modelId")Integer modelId);


	List<SysDtu> findAllDtu();

	//根据number查找设备id，结构物id
	SysEquipment findByNumber(@Param("number") String number);
}
