package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysDetectionMapper;
import com.th.system.po.SysDetection;
import com.th.system.service.SysDetectionService;
import com.th.system.vo.SysDetectionVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysDetectionServiceImpl")
@Transactional
public class SysDetectionServiceImpl implements SysDetectionService{

	@Autowired
	private SysDetectionMapper sysDetectionMapper;
	
	@Override
	public Integer insertDetection(String detectionName,
			Integer factorId,Integer detectionTypeId,Integer userId) {
		SysDetection detection = new SysDetection();
//		String detectionId = UUID.randomUUID().toString().replace("-", "");
//		detection.setDetectionId(detectionId);
		detection.setDetectionName(detectionName);
		detection.setDetectionTypeId(detectionTypeId);
		detection.setFactorId(factorId);
		detection.setUserId(userId);
		return sysDetectionMapper.insertDetection(detection);
	}

	@Override
	public List<SysDetectionVo> findByUserId(Integer userId,String detectionName
			,int page,int limit) {
		// TODO Auto-generated method stub
		return sysDetectionMapper.findByUserId(userId,detectionName,page,limit);
	}

	@Override
	public SysDetection findById(Integer detectionId) {
		// TODO Auto-generated method stub
		return sysDetectionMapper.findById(detectionId);
	}

	@Override
	public Integer updateDetection(Integer detectionId,String detectionName,
			Integer factorId,Integer detectionTypeId) {
		SysDetection detection = new SysDetection();
		detection.setDetectionId(detectionId);
		detection.setDetectionName(detectionName);
		detection.setDetectionTypeId(detectionTypeId);
		detection.setFactorId(factorId);
		return sysDetectionMapper.updateDetection(detection);
	}

	@Override
	public Integer delDetection(Integer detectionId) {
		// TODO Auto-generated method stub
		return sysDetectionMapper.delDetection(detectionId);
	}

}
