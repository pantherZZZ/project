package com.th.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysModel;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.SysProjectVo;
import com.th.system.vo.UserVo;

public interface SysModelService {
		//新增
		public Integer insertModel(SysModel sysModel);
		//删除
		public Integer delModel(Integer modelId);
		//根据modelId查询
		public SysModelVo findByModelId(Integer modelId);
		//根据projectId查询
		public List<SysModelVo> findByProjectId(Integer projectId,int limit,int size,String modelName,String val);
		//修改
		public Integer updateMoele(SysModel sysModel);
		//根据当前登录人查询创建所有未绑定项目
		public List<SysModel> findByBindingIsNot(Integer userId);
		//查询模型个数
		public Integer findCount();
		//查询所有模型
		public List<SysModel> findAll();
		//根据projectId查询
		public List<UserVo> findByProjectIdPro(Integer projectId);
		public SysModelVo findByModelIdPro(Integer modelId);
		//根据projectId查询
		public List<SysModelVo> findByProjectIdAir(Integer userId,int page,int limit,String modelName,String val);

		public Integer findAddress(String address);
		public String showModelId(String modelName,Integer userid,
				String address,Integer detectionTypeId,String coordinateX,String coordinateY);
}
