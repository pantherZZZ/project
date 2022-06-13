package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysModel;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.UserVo;

//sys_model表
//模型类
public interface SysModelMapper {
	//新增
	public Integer insertModel(SysModel sysModel);
	//删除
	public Integer delModel(@Param("modelId")Integer modelId);
	//根据modelId查询
	public SysModelVo findByModelId(@Param("modelId")Integer modelId);
	//根据modelId查询
	public SysModelVo findByModelIdPro(@Param("modelId")Integer modelId);
	//根据projectId查询
	public List<SysModelVo> findByProjectId(@Param("projectId")Integer projectId,@Param("limit")int limit,@Param("size")int size,@Param("modelName")String modelName,@Param("val")String val);
	//修改
	public Integer updateMoele(SysModel sysModel);
	//根据当前登录人查询创建所有未绑定项目
	public List<SysModel> findByBindingIsNot(@Param("userId")Integer userId);
	//查询模型个数
	public Integer findCount();
	//查询所有模型
	public List<SysModel> findAll();
	//根据projectId查询
	public List<UserVo> findByProjectIdPro(@Param("projectId")Integer projectId);
	public Integer findAddress(@Param("address")String address);
	//根据projectId查询
	public List<SysModelVo> findByProjectIdAir(@Param("userId")Integer userId,@Param("page")int page,@Param("limit")int limit,@Param("modelName")String modelName,@Param("val")String val);
	
	public String showModelId(@Param("modelName")String modelName,@Param("userid")Integer userid,
			@Param("address")String address,@Param("detectionTypeId")Integer detectionTypeId,@Param("coordinateX")String coordinateX,@Param("coordinateY")String coordinateY);
}
