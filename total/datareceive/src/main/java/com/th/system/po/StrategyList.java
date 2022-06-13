package com.th.system.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StrategyList {
	
	private Integer id;
	
	private String title;
	
	private Integer ste;
	
	private String time;
	
	private String light;
	
	private Integer modelId;

	
}
