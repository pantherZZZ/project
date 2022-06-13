package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysFactor;

public interface SysFactorService {
	
	//新增
	public Integer insertFactor(Integer factorId,String factorName,String isPrivate);
	//查询所有
	public List<SysFactor> findAll();
	
	public List<SysFactor> findByModelId(Integer modelId);
	
	//查询所有
	public List<SysFactor> findAllPro(HttpServletRequest request);
}
