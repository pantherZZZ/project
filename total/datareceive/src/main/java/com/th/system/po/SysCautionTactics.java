package com.th.system.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysCautionTactics implements Serializable {
	
	private Integer cautionTacticsId;//id
	
	private Integer userId;//用户id
	
	private Integer projectId;//项目id
	
	private Integer modelId;//结构物id
	
	private String cautionType;//告警类型
	
	private String isMailbox;//邮箱通知 1通知 2不通知
	
	private String isNote;//短信通知 1通知 2不通知
	
	private String cautionGrade;//告警级别 1 2 3
	
	private String forbidden;//禁用 1禁用 2不禁用


}
