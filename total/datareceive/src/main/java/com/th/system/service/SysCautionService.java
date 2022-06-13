package com.th.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysCaution;
import com.th.system.vo.SysCantionVo;

public interface SysCautionService {
	
	    //获取数量
		public Integer findModelCount(Integer modelId);
	
	    //新增
		public Integer insertCaution(Integer modelId,Integer cautionSource
				,String cautionGrade,String cautionMessage,String produceTime,String updateTime);
		
		//修改
		public Integer updateCaution(Integer cautionId);
		
		//查询
		public List<SysCantionVo> findByUserCantion(Integer userid,int page,
				int limit,String modelName,String measureName,String cautionGrade);
		
		//删除
		public Integer delCantion(Integer cautionId);
		
		public List<SysCantionVo> findCautionDate();
		
		//获取数量
		public Integer findCount();
		
		//按照时间查询
		public List<SysCaution> findCautionTime(String produceTime);
}
