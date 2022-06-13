package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysCautionTacticsMapper;
import com.th.system.po.SysCautionTactics;
import com.th.system.service.SysCautionTacticsSerice;
import com.th.system.vo.SysCautionTacticsVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysCautionTacticsServiceImpl")
@Transactional
public class SysCautionTacticsServiceImpl implements SysCautionTacticsSerice{
 
	@Autowired
	private SysCautionTacticsMapper sysCautionTacticsMapper;

	@Override
	public Integer updateCautionTactics(Integer cautionTacticsId,Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden) {
		SysCautionTactics cautionTactics = new SysCautionTactics();
		cautionTactics.setCautionTacticsId(cautionTacticsId);
		cautionTactics.setUserId(userId);
		cautionTactics.setCautionType(cautionType);
		cautionTactics.setProjectId(projectId);
		cautionTactics.setModelId(modelId);
		cautionTactics.setIsMailbox(isMailbox);
		cautionTactics.setIsNote(isNote);
		cautionTactics.setCautionGrade(cautionGrade);
		cautionTactics.setForbidden(forbidden);
		return sysCautionTacticsMapper.updateCautionTactics(cautionTactics);
	}

	@Override
	public Integer insertCautionTactics(Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden) {
		SysCautionTactics cautionTactics = new SysCautionTactics();
		cautionTactics.setUserId(userId);
		cautionTactics.setCautionType(cautionType);
		cautionTactics.setProjectId(projectId);
		cautionTactics.setModelId(modelId);
		cautionTactics.setIsMailbox(isMailbox);
		cautionTactics.setIsNote(isNote);
		cautionTactics.setCautionGrade(cautionGrade);
		cautionTactics.setForbidden(forbidden);
		return sysCautionTacticsMapper.insertCautionTactics(cautionTactics);
	}

	@Override
	public List<SysCautionTacticsVo> findUserCautionTactics(Integer userid, int page, int limit, String userName,
			String modelName,String cautionGrade) {
		// TODO Auto-generated method stub
		return sysCautionTacticsMapper.findUserCautionTactics(userid, page, limit, userName, modelName,cautionGrade);
	}

	@Override
	public SysCautionTacticsVo findById(Integer cautionTacticsId) {
		// TODO Auto-generated method stub
		return sysCautionTacticsMapper.findById(cautionTacticsId);
	}

	@Override
	public Integer delCautionTactics(Integer cautionTacticsId) {
		// TODO Auto-generated method stub
		return sysCautionTacticsMapper.delCautionTactics(cautionTacticsId);
	}
	
}
