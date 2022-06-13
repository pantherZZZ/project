package com.th.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.system.po.SysEquipment;
import com.th.system.vo.SysFindAllDtuVo;
import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysDtu;
import com.th.system.vo.SysDtuVo;

public interface SysDtuSetvice {

	//查询数量
	public Integer findDTUCount();
	
	//新增
	public Integer insertDTU(Integer plantId,Integer modelId,String postId,String dtuName,Integer typeId,Integer modelNumberId);
		
	//按照modelId查询
	public List<SysDtuVo> findByModelId(Integer modelId,int page,int limit);
		
	//按照id查询
	public SysDtuVo findById(Integer dtuId);
		
	//修改
	public Integer updateDTU(Integer dtuId,Integer modelId,Integer plantId, Integer typeId,Integer modelNumberId,
							 String postId,String dtuName);
		
	//删除
	public Integer delDTU(Integer dtuId);
	
	//查询数量 按照时间
	public Integer findTimeCount(String time);
	
	public Integer findDTUModleCount(Integer modelId);


	List<SysFindAllDtuVo> findAllDtu();

	SysEquipment findByNumber(String number);
}
