package com.th.system.service;

import java.util.HashMap;

/**
 * 实时监测存、取
 *
 * @Author zhang bao
 * @Date 2021/12/30 18:32
 * @Version 1.0
 */
public interface SysInsertData {
    //    存入传感器数据
    boolean insertData(String number, HashMap<String, String> map);


    //存入时间搓和传感器数据
    boolean insertTimeData(String number, HashMap<String, String> map);


    boolean insertDataTwo(String number, HashMap<String, String> map);

    boolean insertTimeDataTwo(String number, HashMap<String, String> map);
}
