package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysDetection;
import com.th.system.vo.SysDetectionVo;

public interface SysDetectionMapper {
	//新增
	public Integer insertDetection(SysDetection sysDetection);
	
	//userid 查询
	public List<SysDetectionVo> findByUserId(@Param("userId")Integer userId,@Param("detectionName")String detectionName
			,@Param("page")int page,@Param("limit")int limit);
	
	//id 查询
	public SysDetection findById(@Param("detectionId")Integer detectionId);
	
	//修改
	public Integer updateDetection(SysDetection sysDetection);
	
	//删除
	public Integer delDetection(@Param("detectionId")Integer detectionId);
}
