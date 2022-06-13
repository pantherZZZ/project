package com.th.system.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysFocussingMapper;
import com.th.system.po.SysFocussing;
import com.th.system.service.SysFocussingService;
import com.th.system.vo.SysFocussingVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysFocussingServiceImpl")
@Transactional
public class SysFocussingServiceImpl implements SysFocussingService{

	@Autowired
	private SysFocussingMapper sysFocussingMapper;
	
	@Override
	public Integer insertFocussing(HttpServletRequest request,Integer factorId,String focussingType,String focussingMode
			 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
			 ,String weekEndTime,String monthStartTime,String monthEndTime,String weekStart,String weekEnd) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		SysFocussing focussing = new SysFocussing();
		focussing.setFactorId(factorId);
		focussing.setFocussingType(focussingType);
		focussing.setFocussingMode(focussingMode);
		focussing.setState(state);
		focussing.setDaysStartTime(daysStartTime);
		focussing.setDaysEndTime(daysEndTime);
		focussing.setWeekStartTime(weekStartTime);
		focussing.setWeekEndTime(weekEndTime);
		focussing.setMonthStartTime(monthStartTime);
		focussing.setMonthEndTime(monthEndTime);
		focussing.setUserId(userid);
		focussing.setWeekStart(weekStart);
		focussing.setWeekEnd(weekEnd);
		return sysFocussingMapper.insertFocussing(focussing);
	}

	@Override
	public Integer updateFocussing(Integer focussingId,Integer factorId,String focussingType,String focussingMode
			 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
			 ,String weekEndTime,String monthStartTime,String monthEndTime,String weekStart,String weekEnd) {
		SysFocussing focussing = new SysFocussing();
		focussing.setFocussingId(focussingId);
		focussing.setFactorId(factorId);
		focussing.setFocussingType(focussingType);
		focussing.setFocussingMode(focussingMode);
		focussing.setState(state);
		focussing.setDaysStartTime(daysStartTime);
		focussing.setDaysEndTime(daysEndTime);
		focussing.setWeekStartTime(weekStartTime);
		focussing.setWeekEndTime(weekEndTime);
		focussing.setMonthStartTime(monthStartTime);
		focussing.setMonthEndTime(monthEndTime);
		focussing.setWeekStart(weekStart);
		focussing.setWeekEnd(weekEnd);
		return sysFocussingMapper.updateFocussing(focussing);
	}

	@Override
	public Integer delFocussing(Integer focussingId) {
		// TODO Auto-generated method stub
		return sysFocussingMapper.delFocussing(focussingId);
	}

	@Override
	public List<SysFocussingVo> findUserFocussing(HttpServletRequest request,String page
			 ,String limit) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		int pages = Integer.parseInt(page)-1;
		return sysFocussingMapper.findUserFocussing(userid,pages,Integer.parseInt(limit));
	}

	@Override
	public SysFocussingVo findFocussing(Integer focussingId) {
		// TODO Auto-generated method stub
		return sysFocussingMapper.findFocussing(focussingId);
	}

}
