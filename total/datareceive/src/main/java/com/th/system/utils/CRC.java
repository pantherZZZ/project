package com.th.system.utils;

import io.lettuce.core.codec.CRC16;

public class CRC {
	
	//截取字符串
		public static String split(String hex,int num1,int num2) {
			String specification = hex.substring(num1,num2);
			return specification;
		}
		//1应变计 2气象仪 3角度仪
		public static boolean contains(String data) {
			boolean bool = false;
			int num = 0;
			String str =  data.replaceAll(" ", "");
			if(str.contains("557A")) {
				bool = true;
				num = 1;
				String number = CRC.split(data, 12, 28);
				String instruct = CRC.split(data, 40, 44);
			}else if(str.contains("3303")){
				num = 2;
				String a = CRC.split(str, 4, 8);//噪声
				String b = CRC.split(str, 30, 34);//PM2.5
				String c = CRC.split(str, 34, 38);//PM10
				String d = CRC.split(str, 38, 42);//温度
				String e = CRC.split(str, 42, 46);//湿度
				String f = CRC.split(str, 66, 70);//光照
			}
			
			return bool;
		}
	
	public static void main(String[] args) {
		CRC.contains("33 03 20 02 08 00 00 00 00 00 00 00 00 00 00 00 11 00 14 1A 57 0C 5F 00 00 00 00 00 00 00 00 00 00 01 26 57 0A");
    }
}
