package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysFocussing;
import com.th.system.vo.SysFocussingVo;

public interface SysFocussingService {

	   
		 //新增
		 public Integer insertFocussing(HttpServletRequest request,Integer factorId,String focussingType,String focussingMode
				 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
				 ,String weekEndTime,String monthStartTime,String monthEndTime,String weekStart,String weekEnd);
		 
		 //修改
		 public Integer updateFocussing(Integer focussingId,Integer factorId,String focussingType,String focussingMode
				 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
				 ,String weekEndTime,String monthStartTime,String monthEndTime,String weekStart,String weekEnd);
		 
		 //删除
		 public Integer delFocussing(Integer focussingId);
		 
		 //按照登录人查询列表
		 public List<SysFocussingVo> findUserFocussing(HttpServletRequest request,String page
				 ,String limit);
		 
		 //按照id查询
		 public SysFocussingVo findFocussing(Integer focussingId);
	
}
