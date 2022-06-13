package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//sys_equipment表
//设备类
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_equipment")
public class SysEquipment  implements Serializable {

	@TableId("equipmentId")
	private Integer equipmentId;//设备id

	@TableField("equipmentName")
	private String equipmentName;//设备名称

	@TableField("reportedMode")
	private String reportedMode;//上报方式 1网络 2网关

	@TableField("plantId")
	private Integer plantId;//工厂id

	@TableField("typeId")
	private Integer typeId;//设备类型id

	@TableField("install")
	private String install;//是否被安装 1未安装 2已安装

	@TableField("specification")
	private String specification;//型号

	@TableField("isDTU")
	private String isDTU;//1是dtu

	@TableField("dtuId")
	private Integer dtuId;

	@TableField("modelId")
	private Integer modelId;

	@TableField("strategyId")
	private Integer strategyId;

	@TableField("time")
	private String time;


	private Integer sensorType;//传感器类型

	private String lastTime;

	@TableField("status")
	private Integer status;

}
