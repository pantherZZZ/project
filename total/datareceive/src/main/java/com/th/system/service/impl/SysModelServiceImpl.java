package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysModelMapper;
import com.th.system.po.SysModel;
import com.th.system.service.SysModelService;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysModelServiceImpl")
@Transactional
public class SysModelServiceImpl implements SysModelService{
	
	@Autowired
	private SysModelMapper sysModelMapper;
	
	@Override
	public Integer insertModel(SysModel sysModel) {
		// TODO Auto-generated method stub
		return sysModelMapper.insertModel(sysModel);
	}

	@Override
	public Integer delModel(Integer modelId) {
		// TODO Auto-generated method stub
		return sysModelMapper.delModel(modelId);
	}

	@Override
	public SysModelVo findByModelId(Integer modelId) {
		// TODO Auto-generated method stub
		return sysModelMapper.findByModelId(modelId);
	}

	@Override
	public List<SysModelVo> findByProjectId(Integer projectId,int limit,int size,String modelName,String val) {
		// TODO Auto-generated method stub
		return sysModelMapper.findByProjectId(projectId,limit,size,modelName,val);
	}

	@Override
	public Integer updateMoele(SysModel sysModel) {
		// TODO Auto-generated method stub
		return sysModelMapper.updateMoele(sysModel);
	}

	@Override
	public List<SysModel> findByBindingIsNot(Integer userId) {
		// TODO Auto-generated method stub
		return sysModelMapper.findByBindingIsNot(userId);
	}

	@Override
	public Integer findCount() {
		// TODO Auto-generated method stub
		return sysModelMapper.findCount();
	}

	@Override
	public List<SysModel> findAll() {
		// TODO Auto-generated method stub
		return sysModelMapper.findAll();
	}

	@Override
	public List<UserVo> findByProjectIdPro(Integer projectId) {
		// TODO Auto-generated method stub
		return sysModelMapper.findByProjectIdPro(projectId);
	}

	@Override
	public List<SysModelVo> findByProjectIdAir(Integer userId,int page, int limit, String modelName, String val) {
		// TODO Auto-generated method stub
		List<SysModelVo> list = sysModelMapper.findByProjectIdAir(userId, page, limit, modelName, val);
		List<String> arrayList = new ArrayList<>();
		arrayList.add("118.77307916969056");
		arrayList.add("31.827994075774843");
		for (SysModelVo sysModelVo : list) {
			if (sysModelVo.getModelId()==1){
				sysModelVo.setAlnglat(arrayList);
			}
		}
		return list;
	}

	@Override
	public SysModelVo findByModelIdPro(Integer modelId) {
		// TODO Auto-generated method stub
		return sysModelMapper.findByModelIdPro(modelId);
	}

	@Override
	public Integer findAddress(String address) {
		// TODO Auto-generated method stub
		return sysModelMapper.findAddress(address);
	}

	@Override
	public String showModelId(String modelName, Integer userid,String address, Integer detectionTypeId,
			String coordinateX, String coordinateY) {
		// TODO Auto-generated method stub
		return sysModelMapper.showModelId(modelName, userid,address, detectionTypeId, coordinateX, coordinateY);
	}

}
