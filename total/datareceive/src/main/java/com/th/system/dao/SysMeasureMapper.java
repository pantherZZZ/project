package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysMeasure;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysMeasureMapper {
	//新增
	public Integer insertMeasure(SysMeasure sysMeasure);
	
	//按照modelid查询 和名称
	public List<SysMeasure> findByModelId(@Param("modelId")Integer modelId,@Param("measureName")String measureName,@Param("page")int page,@Param("limit")int limit);
	
	//按照id查询
	public SysMeasure findByMeasure(@Param("measureId")Integer measureId);
	
	//修改
	public Integer updateMeasure(SysMeasure sysMeasure);
	
	//删除
	public Integer dalMeasure(@Param("measureId")Integer measureId);
	
	//根据userid 查询
	public List<SysMeasure> findByUserId(@Param("userId")Integer userId);
	
	//根据检测因素查询
	public List<SysMeasure> findByfactorId(@Param("userid")Integer userid,@Param("factorId")Integer factorId);
	
	public String findByMeasurePro(@Param("measureId")Integer measureId,
			@Param("time")String time,
			@Param("hour")String hour);
	
	public String showMeasureId(@Param("measureName")String measureName,@Param("factorId")Integer factorId,@Param("dataSource")String dataSource,
			@Param("equipmentId")Integer equipmentId,@Param("modelId")Integer modelId,@Param("label")String label,@Param("pictureSite")String pictureSite);
}
