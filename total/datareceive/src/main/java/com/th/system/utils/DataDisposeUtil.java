package com.th.system.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.th.system.po.SysEquipment;
import com.th.system.service.SysCautionService;
import com.th.system.service.SysDataService;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysThresholdValueService;
import com.th.system.service.SysUserService;
import com.th.system.vo.SysProjectUserVo;
import com.th.system.vo.SysThresholdValueVo;

public class DataDisposeUtil {
	
	@Autowired
	private SysDataService sysDataService;
	
	@Autowired
	private SysEquipmentService sysEquipmentService;
	
	@Autowired
	private SysThresholdValueService sysThresholdValueService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysCautionService sysCautionService;
	
	AnalysisUtil util = new AnalysisUtil();
	
	
	
}
