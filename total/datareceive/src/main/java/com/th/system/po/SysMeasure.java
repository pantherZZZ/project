package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//sys_measure
@TableName("sys_measure")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMeasure implements Serializable {

	@TableId("measureId")
	private int measureId;//测点id

	@TableField("measureName")
	private String measureName;//测点名称

	@TableField("factorId")
	private int factorId;//检测因素id

	@TableField("dataSource")
	private String dataSource;//数据来源 1设备采集 2人工上传

	@TableField("equipmentId")
	private int equipmentId;//设备

	@TableField("pictureSite")
	private String pictureSite;//图片地址

	@TableField("modelId")
	private int modelId;//结构物id

	@TableField("label")
	private String label;//标签

	@TableField("show")
	private String show;//2显示 1不显示

}
