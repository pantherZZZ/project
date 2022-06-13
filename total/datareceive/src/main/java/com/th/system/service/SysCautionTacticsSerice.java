package com.th.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysCautionTactics;
import com.th.system.vo.SysCautionTacticsVo;

public interface SysCautionTacticsSerice {

	//修改
	public Integer updateCautionTactics(Integer cautionTacticsId,Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden);
		
	//新增
	public Integer insertCautionTactics(Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden);
		
	//按照登录人查询
	public List<SysCautionTacticsVo> findUserCautionTactics(Integer userid
			,int page,int limit,String userName
			,String modelName,String cautionGrade);
		
	//按照id查询
	public SysCautionTacticsVo findById(Integer cautionTacticsId);
		
	//删除
	public Integer delCautionTactics(Integer cautionTacticsId);
	
}
