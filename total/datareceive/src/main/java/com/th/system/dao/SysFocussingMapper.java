package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysFocussing;
import com.th.system.vo.SysFocussingVo;

public interface SysFocussingMapper {
   
	 //新增
	 public Integer insertFocussing(SysFocussing sysFocussing);
	 
	 //修改
	 public Integer updateFocussing(SysFocussing sysFocussing);
	 
	 //删除
	 public Integer delFocussing(@Param("focussingId")Integer focussingId);
	 
	 //按照登录人查询列表
	 public List<SysFocussingVo> findUserFocussing(@Param("userid")Integer userid,@Param("page")int page
			 ,@Param("limit")int limit);
	 
	 //按照id查询
	 public SysFocussingVo findFocussing(@Param("focussingId")Integer focussingId);
	 
}
