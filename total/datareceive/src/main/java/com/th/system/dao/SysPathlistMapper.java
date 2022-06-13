package com.th.system.dao;

import com.th.system.po.SysPathlist;

public interface SysPathlistMapper {
	
	//新增图片
	public Integer insertPath(SysPathlist sysPathlist);
	
	public String showPictureId(String size);
}
