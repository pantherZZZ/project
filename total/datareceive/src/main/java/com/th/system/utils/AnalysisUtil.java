package com.th.system.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AnalysisUtil {

	
	// 16进制转换成为string类型字符串
	public String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	//十六进制转ASCLL码
	public String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
			temp.append(decimal);
		}
		return sb.toString();
	}
	
	//截取字符串
	public String split(String hex,int num1,int num2) {
		String str = hex.replaceAll(" ", "");
		String specification = str.substring(num1,num2);
		return specification;
	}
	
	//截取字符串
	public String[] splitPro(String hex,int num1,int num2) {
		String[] str = hex.split(" ");
		
		
		return null;
	}
	//1应变计 2气象仪 4角度仪
	public boolean contains(String data) {
		boolean bool = false;
		int num = 0;
		String val1 =  this.split(data, 0, 5);
		String val2 =  this.split(data, 0, 3);
		if(val1.contains("557A")) {
			bool = true;
			num = 1;
			String number = this.split(data, 12, 28);
			String instruct = this.split(data, 40, 44);
		}else if(val1.contains("FF03")){
			bool = true;
			num = 2;
			this.split(data, 4, 8);
			this.split(data, 40, 44);
		}else if(val2.contains("36")) {
			bool = true;
			num = 3;
		}
		return bool;
	}
	
	//十六进制转十进制
	public double covert(String content){
        int number=0;
        String [] HighLetter = {"A","B","C","D","E","F"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number*0.1;
    }
	
	//获取 yyyy-MM-dd HH:mm:ss
	public String getTimeAll() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return time.format(date);
	}
	
	//获取 yyyy
	public String getYear() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy");
		return time.format(date);
	}
	
	//获取 去年
	public String getLastYear() {
		SimpleDateFormat format  = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		return year;
	}
	
	//获取今天是第几个星期
	public int weekOfMonth() {
		String dateString = getTime();
		int weekOfMonth = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
			calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周的第一天。
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return weekOfMonth;
	}
	

	//计算上一周日期
	public Map<String,String> getTimeInterval() { 
		 Map<String,String> map = new HashMap<>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Calendar calendar1 = Calendar.getInstance();  
         Calendar calendar2 = Calendar.getInstance();  
         int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
         int offset1 = 1 - dayOfWeek;
         int offset2 = 7 - dayOfWeek;
         calendar1.add(Calendar.DATE, offset1 - 7);
         calendar2.add(Calendar.DATE, offset2 - 7);
         String lastBeginDate = sdf.format(calendar1.getTime());  
         String lastEndDate = sdf.format(calendar2.getTime()); 
         map.put("lastBeginDate", lastBeginDate);//上一周开始
	     map.put("lastEndDate", lastEndDate);//上一周结束
         return map;  
   }

	//计算上一周日期
	public Map<String,String> getTimeIntervalNew() {
		Map<String,String> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) +6;
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		String lastBeginDate = sdf.format(calendar1.getTime());
		String lastEndDate = sdf.format(calendar2.getTime());
		map.put("lastBeginDate", lastBeginDate);//上一周开始
		map.put("lastEndDate", lastEndDate);//上一周结束
		return map;
	}

	//计算这个星期日期
	 public static Map<String,String> getWeekDate() {
		 Map<String,String> map = new HashMap<String, String>();
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     Calendar cal = Calendar.getInstance();
	     cal.setFirstDayOfWeek(Calendar.MONDAY);
	     // 获得当前日期是一个星期的第几天
	     int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
	     if(dayWeek==1){
	          dayWeek = 8;
	     }
	     cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
	     Date mondayDate = cal.getTime();
	     String weekBegin = sdf.format(mondayDate);
	     cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
	     Date sundayDate = cal.getTime();
	     String weekEnd = sdf.format(sundayDate);
	     map.put("mondayDate", weekBegin);//这一周开始
	     map.put("sundayDate", weekEnd);//这一周结束
	     return map;
	 }
	
	//获取 yyyy-MM-dd
	public String getTime() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		return time.format(date);
	}
		
	//获取 HH
	public String getHour() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("HH");
		return time.format(date);
	}
}
