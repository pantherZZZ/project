package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysCautionTactics;
import com.th.system.vo.SysCautionTacticsVo;

public interface SysCautionTacticsMapper {

	//修改
	public Integer updateCautionTactics(SysCautionTactics sysCautionTactics);
	
	//新增
	public Integer insertCautionTactics(SysCautionTactics sysCautionTactics);
	
	//按照登录人查询
	public List<SysCautionTacticsVo> findUserCautionTactics(@Param("userid")Integer userid
			,@Param("page")int page,@Param("limit")int limit,@Param("userName")String userName
			,@Param("modelName")String modelName,@Param("cautionGrade")String cautionGrade);
	
	//按照id查询
	public SysCautionTacticsVo findById(@Param("cautionTacticsId")Integer cautionTacticsId);
	
	//删除
	public Integer delCautionTactics(@Param("cautionTacticsId")Integer cautionTacticsId);
}
