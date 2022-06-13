package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysFactor;

public interface SysFactorMapper {

	//新增
	public Integer insertFactor(@Param("factorId")Integer factorId,@Param("factorName")String factorName
			,@Param("isPrivate")String isPrivate);
	
	//查询所有
	public List<SysFactor> findAll();
	
	//查询所有
	public List<SysFactor> findAllPro(@Param("userid")Integer userid);
	
	public List<SysFactor> findByModelId(@Param("modelId")Integer modelId);
	
}
