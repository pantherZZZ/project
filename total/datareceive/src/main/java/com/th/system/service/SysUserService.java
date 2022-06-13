package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysUser;
import com.th.system.vo.SysCautionVo;
import com.th.system.vo.SysProjectUserVo;

public interface SysUserService {
	
		public Integer verifyUserName(String userName);
	
		//新增用户
		public Integer insertUser(SysUser sysUser);
		
		//登录
		public SysUser loginUser(String loginName,String password);
		
		//根据userid查询
		public SysUser findByUserid(Integer userId);
		
		//根据userName或者loginName进行非模糊查询
		public List<SysUser> findList(String userName,String loginName);
		
		//根据userName或者loginName进行模糊查询
		public List<SysUser> findListPro(String userName,String loginName);
		
		//用户修改
		public Integer updateUser(SysUser sysUser);
		
		//查询所有用户
		public List<SysUser> findAll(HttpServletRequest request,int page,int limit,String userName,String mailbox,String phoneNumber,String val);
		
		public SysUser comparisonPassword(Integer userId,String password);
		
		//根据设备id 查询用户 通知
		public SysProjectUserVo findEquipmentUser(Integer equipmentId);
		
		public SysCautionVo findByEquipmentId(Integer equipmentId,String cautionGrade);
}
