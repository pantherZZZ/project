package com.th.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysEquipmentVo {

	private int equipmentId;

	private String equipmentName;

	private String typeName;

	private int typeId;

	private int plantId;

	private int modelNumberId;
	
	private String plantName;
	
	private String modelNumberName;
	
	private String dtuName;

	private int dtuId;
	
	private String strategyName;

	private int strategyId;

	private int modelId;

	private String portId;


}
