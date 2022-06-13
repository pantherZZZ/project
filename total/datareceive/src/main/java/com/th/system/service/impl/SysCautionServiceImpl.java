package com.th.system.service.impl;

import java.util.List;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysCautionMapper;
import com.th.system.po.SysCaution;
import com.th.system.service.SysCautionService;
import com.th.system.vo.SysCantionVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysCautionServiceImpl")
@Transactional
public class SysCautionServiceImpl implements SysCautionService{

	@Autowired
	private SysCautionMapper sysCautionMapper;

	@Override
	public Integer insertCaution(Integer modelId,Integer cautionSource
			,String cautionGrade,String cautionMessage,String produceTime,String updateTime) {
		SysCaution caution = new SysCaution();
		caution.setSituation("0");
		caution.setModelId(modelId);
		caution.setCautionSource(cautionSource);
		caution.setCautionGrade(cautionGrade);
		caution.setCautionMessage(cautionMessage);
		caution.setProduceTime(produceTime);
		caution.setUpdateTime(updateTime);
		caution.setCount("1");
		return sysCautionMapper.insertCaution(caution);
	}

	@Override
	public Integer updateCaution(Integer cautionId) {
		// TODO Auto-generated method stub
		return sysCautionMapper.updateCaution(cautionId);
	}

	@Override
	public List<SysCantionVo> findByUserCantion(Integer userid,int page,
			int limit,String modelName,String measureName,String cautionGrade) {
		// TODO Auto-generated method stub
		return sysCautionMapper.findByUserCantion(userid,page,
				limit,modelName,measureName,cautionGrade);
	}

	@Override
	public Integer delCantion(Integer cautionId) {
		// TODO Auto-generated method stub
		return sysCautionMapper.delCantion(cautionId);
	}

	@Override
	public List<SysCantionVo> findCautionDate() {
		// TODO Auto-generated method stub
		return sysCautionMapper.findCautionDate();
	}

	@Override
	public Integer findCount() {
		// TODO Auto-generated method stub
		return sysCautionMapper.findCount();
	}

	@Override
	public List<SysCaution> findCautionTime(String produceTime) {
		// TODO Auto-generated method stub
		return sysCautionMapper.findCautionTime(produceTime);
	}

	@Override
	public Integer findModelCount(Integer modelId) {
		// TODO Auto-generated method stub
		return sysCautionMapper.findModelCount(modelId);
	}
	
}
