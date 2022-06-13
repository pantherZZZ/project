package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysUser;
import com.th.system.vo.SysCautionVo;
import com.th.system.vo.SysProjectUserVo;
//sys_user表
//用户类
public interface SysUserMapper {

	public SysCautionVo findByEquipmentId(@Param("equipmentId")Integer equipmentId,
										  @Param("cautionGrade")String cautionGrade);
	
	//新增用户
	Integer insertUser(SysUser sysUser);
	
	//用户修改
	public Integer updateUser(SysUser sysUser);
	
	//登录
	SysUser loginUser(@Param("loginName")String loginName,@Param("password")String password);
	
	//根据userid查询
	public SysUser findByUserid(@Param("userId")Integer userId);
	
	//根据userName或者loginName进行非模糊查询
	public List<SysUser> findList(@Param("userName")String userName,@Param("loginName")String loginName);
	
	public SysUser comparisonPassword(@Param("userId")Integer userId,@Param("password")String password);
		
	//根据设备id 查询用户 通知
	public SysProjectUserVo findEquipmentUser(@Param("equipmentId")Integer equipmentId);
	
	//根据userName或者loginName进行模糊查询
	public List<SysUser> findListPro(@Param("userName")String userName,@Param("loginName")String loginName);
	
	public Integer verifyUserName(@Param("userName")String userName);
	
	//查询所有用户
	public List<SysUser> findAll(@Param("page")int page,@Param("limit")int limit,
			@Param("userName")String userName,@Param("mailbox")String mailbox,
			@Param("phoneNumber")String phoneNumber
			,@Param("val")String val);
}
