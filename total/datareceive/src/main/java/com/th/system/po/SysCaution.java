package com.th.system.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

//sys_caution表
//警告类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysCaution implements Serializable {

	private Integer cautionId;//id
	
	private Integer modelId;//模型id
	
	private Integer cautionSource;//警告源
	
	private String cautionGrade;//警告等级
	
	private String count;//发生次数
	
	private String cautionMessage;//警告信息
	
	private String produceTime;//产生时间
	
	private String updateTime;//更新时间
	
	private String situation;//处理情况 1已处理 0未处理

	
}
