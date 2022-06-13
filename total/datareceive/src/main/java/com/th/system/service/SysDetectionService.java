package com.th.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysDetection;
import com.th.system.vo.SysDetectionVo;

public interface SysDetectionService {

	//新增
	public Integer insertDetection(String detectionName,
			Integer factorId,Integer detectionTypeId,Integer userId);
		
	//userid 查询
	public List<SysDetectionVo> findByUserId(Integer userId,String detectionName
			,int page,int limit);
		
	//id 查询
	public SysDetection findById(Integer detectionId);
		
	//修改
	public Integer updateDetection(Integer detectionId,String detectionName,
			Integer factorId,Integer detectionTypeId);
		
	//删除
	public Integer delDetection(Integer detectionId);
	
}
