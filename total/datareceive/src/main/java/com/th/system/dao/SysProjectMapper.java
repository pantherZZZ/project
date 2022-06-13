package com.th.system.dao;

import java.util.List;

import com.th.system.po.ModelNameVo;
import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysProject;
import com.th.system.po.SysUser;
import com.th.system.vo.SysProjectVo;
import com.th.system.vo.UserVo;

//sys_project表
//项目类
public interface SysProjectMapper {
	//新增项目
	public Integer insertProject(SysProject sysProject);
	
	//根据用户id 权限查询对应项目
	public List<SysProjectVo> findByUserid(@Param("userId")Integer userId
			,@Param("limit")int limit,@Param("size")int size,@Param("projectName")String projectName,@Param("val")String val);
	
	//根据用户id 权限查询对应项目
		public List<SysProject> findByUseridPro(@Param("userId")Integer userId
				,@Param("limit")int limit,@Param("size")int size,@Param("projectName")String projectName,@Param("val")String val,@Param("type")Integer type);
		
	
	//根据用户id 项目名称 权限查询对应项目
	public List<SysProjectVo> findByName(@Param("userId")Integer userId,@Param("projectName")String projectName,
			@Param("size")int size,@Param("limit")int limit,@Param("val")String val);
	
	//修改
	public Integer updateProject(SysProject sysProject);
	
	//根据id查询
	public List<UserVo> findByProjectId(@Param("projectId")Integer projectId);
	

	//根据id查询
	public UserVo findByProjectIdPro(@Param("projecfindModelByProjectIdtId")Integer projectId);
	
	//根据id查询
	public List<SysUser> findByProjectIdAir(@Param("projectId")Integer projectId);
	
	//删除
	public Integer delProject(@Param("projectId")Integer projectId);
	
	//查询项目个数
	public Integer findCount();
	
	//查询项目个数年
	public Integer findYearTime(@Param("time")String time);
		
	//查询项目个数星期
	public Integer findWeekTime(@Param("time1")String time1,@Param("time2")String time2);

	List<ModelNameVo> findModelByProjectId (@Param("projectId")Integer projectId);
}
