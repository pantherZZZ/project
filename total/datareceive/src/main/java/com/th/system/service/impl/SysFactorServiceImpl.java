package com.th.system.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysFactorMapper;
import com.th.system.po.SysFactor;
import com.th.system.service.SysFactorService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysEquipmentServiceImpl")
@Transactional
public class SysFactorServiceImpl implements SysFactorService{

	@Autowired
	private SysFactorMapper sysFactorMapper;

	@Override
	public List<SysFactor> findAll() {
		// TODO Auto-generated method stub
		return sysFactorMapper.findAll();
	}

	@Override
	public Integer insertFactor(Integer factorId, String factorName, String isPrivate) {
		// TODO Auto-generated method stub
		return sysFactorMapper.insertFactor(factorId, factorName, isPrivate);
	}

	@Override
	public List<SysFactor> findByModelId(Integer modelId) {
		// TODO Auto-generated method stub
		return sysFactorMapper.findByModelId(modelId);
	}

	@Override
	public List<SysFactor> findAllPro(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = "1";//(String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}
		return sysFactorMapper.findAllPro(useridPro);
	}

}
