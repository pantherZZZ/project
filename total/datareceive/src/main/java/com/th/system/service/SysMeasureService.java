package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.th.system.po.SysMeasure;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysMeasureService {

	//新增
	public Integer insertMeasure(String measureName,Integer factorId,String dataSource,Integer equipmentId,String pictureSite,Integer modelId,String label);
		
	//按照modelid查询 和名称
	public List<SysMeasure> findByModelId(Integer modelId,String measureName,int page,int limit);
		
	//按照id查询
	public SysMeasure findByMeasure(Integer measureId);
		
	//修改
	public Integer updateMeasure(Integer measureId,String measureName,Integer factorId,String dataSource,Integer equipmentId,String pictureSite,Integer modelId,String label);
		
	//删除
	public Integer dalMeasure(Integer measureId);
	
	//根据userid 查询
	public List<SysMeasure> findByUserId(HttpServletRequest request);
	
	//根据检测因素查询
	public List<SysMeasure> findByfactorId(HttpServletRequest request,Integer factorId);
	
	public String findByMeasurePro(Integer measureId,String time,String hour);

}
