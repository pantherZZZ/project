package com.th.system.utils;

public class Weekutil {

	public String week(String str) {
		String str2 = null;
		if(str.equals("1")) {
			str2 = "周一";
		}else if(str.equals("2")){
			str2 = "周一";
		}else if(str.equals("3")){
			str2 = "周一";
		}else if(str.equals("4")){
			str2 = "周四";	
		}else if(str.equals("5")){
			str2 = "周五";		
		}else if(str.equals("6")){
			str2 = "周六";			
		}else if(str.equals("7")){
			str2 = "周日";				
		}
		return str2;
	}
	
}
