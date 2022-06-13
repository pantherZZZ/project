package com.th.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysPathlistMapper;
import com.th.system.po.SysPathlist;
import com.th.system.service.SysPathlistService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysPathlistServiceImpl")
@Transactional
public class SysPathlistServiceImpl implements SysPathlistService{

	@Autowired
	private SysPathlistMapper sysPathlistMapper;
	
	@Override
	public Integer insertPath(SysPathlist sysPathlist) {
		// TODO Auto-generated method stub
		return sysPathlistMapper.insertPath(sysPathlist);
	}

	@Override
	public String showPictureId(String size) {
		// TODO Auto-generated method stub
		return sysPathlistMapper.showPictureId(size);
	}

}
