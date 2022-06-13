package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysUserMapper;
import com.th.system.po.SysUser;
import com.th.system.service.SysUserService;
import com.th.system.vo.SysCautionVo;
import com.th.system.vo.SysProjectUserVo;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public Integer insertUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return sysUserMapper.insertUser(sysUser);
	}

	@Override
	public SysUser loginUser(String loginName, String password) {
		// TODO Auto-generated method stub
		return sysUserMapper.loginUser(loginName, password);
	}

	@Override
	public SysUser findByUserid(Integer userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.findByUserid(userId);
	}


	@Override
	public Integer updateUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return sysUserMapper.updateUser(sysUser);
	}

	@Override
	public List<SysUser> findList(String userName,String loginName) {
		// TODO Auto-generated method stub
		return sysUserMapper.findList(userName,loginName);
	}

	@Override
	public List<SysUser> findListPro(String userName,String loginName) {
		// TODO Auto-generated method stub
		return sysUserMapper.findListPro(userName,loginName);
	}

	@Override
	public List<SysUser> findAll(HttpServletRequest request,int page,int limit,String userName,String mailbox,String phoneNumber,String val) {
		HttpSession session = request.getSession();
		String role = "1";//(String) session.getAttribute("role");
		List<SysUser> list = new ArrayList<SysUser>();
		if(role.equals("1")) {
			list = sysUserMapper.findAll(page,limit,userName,mailbox,phoneNumber,val);
		}else {
			Integer userid = (Integer) session.getAttribute("userid");
			SysUser user = sysUserMapper.findByUserid(userid);
			list.add(user);
		}
		return list;
	}

	@Override
	public SysUser comparisonPassword(Integer userId, String password) {
		// TODO Auto-generated method stub
		return sysUserMapper.comparisonPassword(userId, password);
	}

	@Override
	public SysProjectUserVo findEquipmentUser(Integer equipmentId) {
		// TODO Auto-generated method stub
		return sysUserMapper.findEquipmentUser(equipmentId);
	}

	@Override
	public SysCautionVo findByEquipmentId(Integer equipmentId, String cautionGrade) {
		// TODO Auto-generated method stub
		return sysUserMapper.findByEquipmentId(equipmentId, cautionGrade);
	}

	@Override
	public Integer verifyUserName(String userName) {
		// TODO Auto-generated method stub
		return sysUserMapper.verifyUserName(userName);
	}


	
}
