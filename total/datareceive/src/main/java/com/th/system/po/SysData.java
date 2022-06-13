package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//sys_data表
//数据类
@TableName("sys_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysData implements Serializable {
		
	private Integer dataId;//数据id
	
	private Integer equipmentId;//设备id
	
	private Integer modelId;//模型id
	
	private String data;//数据

	private String specification;//型号
	
	private String time;//时间
	
	private String hour;//小时
	
	private String dataType;//数据类型  1应变计 2倾斜角 3噪声 4PM2.5 5PM10 6温度 7湿度 8光照

	
}
