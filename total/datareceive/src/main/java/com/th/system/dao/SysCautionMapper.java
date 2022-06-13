package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysCaution;
import com.th.system.vo.SysCantionVo;

public interface SysCautionMapper {
	
	//新增
	public Integer insertCaution(SysCaution caution);
	
	//修改
	public Integer updateCaution(@Param("cautionId")Integer cautionId);
	
	//查询
	public List<SysCantionVo> findByUserCantion(@Param("userid")Integer userid,@Param("page")int page,
			@Param("limit")int limit,@Param("modelName")String modelName,@Param("measureName")String measureName,
			@Param("cautionGrade")String cautionGrade);
	
	//删除
	public Integer delCantion(@Param("cautionId")Integer cautionId);
	
	public List<SysCantionVo> findCautionDate();
	
	//获取数量
	public Integer findCount();
	
	//获取数量
	public Integer findModelCount(@Param("modelId")Integer modelId);
	
	//按照时间查询
	public List<SysCaution> findCautionTime(@Param("produceTime")String produceTime);
}
