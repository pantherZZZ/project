package com.th.system.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 截取传感器数据
 *
 * @Author zhang bao
 * @Date 2021/12/29 11:03
 * @Version 1.0
 */
public class InterceptDataUtil {

    public static HashMap<String, String> InterceptData(String str) {
            String replaceStr = str.replaceAll(" ", "");
            //截取传感器数据
            String substring = replaceStr.substring(6, 38);
            HashMap<String, String> map = new HashMap<>();

        DecimalFormat df = new DecimalFormat("#.0");
        df.setRoundingMode(RoundingMode.HALF_UP);  // 为舍入模式进行格式化

        String humidity = substring.substring(0, 4);
        double covert = BinaryUtil.covert(humidity);
        String humidityValue = df.format(covert);
        map.put("湿度", humidityValue);


        String temperature = substring.substring(4, 8);
        String temperatureValue = df.format(BinaryUtil.covert(temperature));
        map.put("温度", temperatureValue);

        String noise = substring.substring(8, 12);
        String noiseValue = df.format(BinaryUtil.covert(noise));
        map.put("噪声", noiseValue);

        String pm = substring.substring(12, 16);
        String pmValue = df.format(BinaryUtil.covert(pm));
        map.put("PM2.5", pmValue);

        String pmTen = substring.substring(16, 20);
        String pmTenValue = df.format(BinaryUtil.covert(pmTen));
        map.put("PM10", pmTenValue);

        String atmosphericPressure = substring.substring(20, 24);
        String atmosphericPressureValue = df.format(BinaryUtil.covert(atmosphericPressure));
        map.put("大气压", atmosphericPressureValue);

        String illumination = substring.substring(24, 32);
        String illuminationValue = df.format(BinaryUtil.covert(illumination));
        map.put("光照", illuminationValue);
        return map;
    }


    //    21-22位    00 BC    温度（*0.1，负数用补码表示）  十六进制 解析为：18.8
//    23-24位    FC D 测量值（小数点位数由35位高位决定，负数用补码表示）   十六进制  解析为：-8.13，测量值为数据值，一般使用这位；
//    25-26位    F1 3C 偏移值（小数点位数由35位高位决定，负数用补码表示）  十六进制  解析为：-37.80
//    27-28位    4B 46 传感器应变频率（×0.1）十六进制  解析为：1927.0
    public static HashMap<String, String> InterceptDataTwo(String str) {
        String replaceStr = str.replaceAll(" ", "");
        //截取传感器数据
        String substring = replaceStr.substring(40, 56);
        HashMap<String, String> map = new HashMap<>();

        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.HALF_UP);  // 为舍入模式进行格式化

        String humidity = substring.substring(0, 4);
        if (!"F".equals(humidity.substring(0,1))) { //正数
            double v = BinaryUtil.covert(humidity)*0.1;
            String humidityValue = df.format(v);
            map.put("温度", humidityValue);
        } else{
            double v = BinaryUtil.covert(humidity);//负数
            double n = -(255 - v + 1) ;
            String humidityValue = df.format(n);
            map.put("温度", humidityValue);
        }


        String Measured = substring.substring(4, 8);
        if (!"F".equals(Measured.substring(0,1))) { //正数
            double v = BinaryUtil.covert(Measured)*0.1;
            String humidityValue = df.format(v);
            map.put("测量值", humidityValue);
        } else{
            double v = BinaryUtil.covert(Measured);//负数
            double n = -(255 - v + 1) * 0.01;
            String MeasuredValue = df.format(n);
            map.put("测量值", MeasuredValue);
        }
//        double MeasuredCovert = BinaryUtil.covert(Measured);
//        String MeasuredValue = df.format(MeasuredCovert);
//        map.put("测量值", MeasuredValue);


        String Offset = substring.substring(8, 12);
        if (!"F".equals(Offset.substring(0,1))) { //正数
            double v = BinaryUtil.covert(Offset)*0.1;
            String OffsetValue = df.format(v);
            map.put("偏移值", OffsetValue);
        } else{
            double v = BinaryUtil.covert(Offset);//负数
            double n = -(255 - v + 1) * 0.01;
            String OffsetValue = df.format(n);
            map.put("偏移值", OffsetValue);
        }
//        double OffsetCovert = BinaryUtil.covert(Offset);
//        String OffsetValue = df.format(OffsetCovert);
//        map.put("偏移值", OffsetValue);


        String StrainFrequency = substring.substring(12, 16);
        double StrainFrequencyCovert = BinaryUtil.covert(StrainFrequency);
        String StrainFrequencyCovertValue = df.format(StrainFrequencyCovert);
        map.put("传感器应变频率", StrainFrequencyCovertValue);
        return map;
    }
}
