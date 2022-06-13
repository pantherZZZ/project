package com.th.system.service;

import com.th.system.po.SysPathlist;

public interface SysPathlistService {

	//新增图片
	public Integer insertPath(SysPathlist sysPathlist);
	
	public String showPictureId(String size);
}
