package com.th.system.service.impl;

import com.th.system.dao.SysInsertDataMapper;
import com.th.system.dao.SysInsertDataTimeMapper;
import com.th.system.po.*;
import com.th.system.service.AlarmInsertionService;
import com.th.system.service.SysDtuSetvice;
import com.th.system.service.SysInsertData;
import com.th.system.service.SysThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhang bao
 * @Date 2021/12/30 18:34
 * @Version 1.0
 */
@Service
@Transactional
public class SysInsertDataImpl implements SysInsertData {

    @Autowired
    private SysInsertDataMapper sysInsertDataMapper;

    @Autowired
    private SysInsertDataTimeMapper sysInsertDataTimeMapper;

    @Autowired
    private SysDtuSetvice sysDtuSetvice;

    @Autowired
    private AlarmInsertionService alarmInsertionService;

    @Autowired
    private SysThresholdService sysThresholdService;


//    public void threshold(List<Threshold> list) {
//        for (Threshold threshold : list) {
//            if (1 == threshold.getType() && Integer.parseInt(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
//
//            }
//        }
//    }

    /**
     * 存入传感器数据
     *
     * @param number dtu唯一标识
     * @param map
     */
    @Override
    public boolean insertData(String number, HashMap<String, String> map) {
        SysEquipment sysDtu = sysDtuSetvice.findByNumber(number);
        if (sysDtu == null) {
            return false;
        }
        //查出出属于这个传感器的检测类型和阈值（温度 50）
        List<Threshold> list = sysThresholdService.findThreshold(number);

        //取map中传感器数据，每分钟存六条  大气压不存    气象百叶箱
        //1-湿度 2-温度 3-噪声 4-PM2.5 5-PM10  6-光照
        SysSensorData sysSensorData = new SysSensorData();
        try {
            for (String mapKey : map.keySet()) {
                if ("湿度".contains(mapKey)) {
                    //数据插入前先与阈值表对比，超过就报警，把报警的传感器数据和信息存入报警表
                    for (Threshold threshold : list) {
                        if (1 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(1);
                            alarmInsertion.setRemark("湿度异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("湿度");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("99");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("5,95");
                    sysSensorData.setUnit("%RH");
                    sysSensorData.setTypeState(1);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("温度".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (2 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(2);
                            alarmInsertion.setRemark("温度异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("温度");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("120");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("30,38");
                    sysSensorData.setUnit("°C");
                    sysSensorData.setTypeState(2);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("噪声".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (3 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(3);
                            alarmInsertion.setRemark("噪声异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("噪声");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("120");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("60,90");
                    sysSensorData.setUnit("db");
                    sysSensorData.setTypeState(3);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("PM2.5".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (4 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(4);
                            alarmInsertion.setRemark("PM2.5异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("PM2.5");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("1000");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("75,150");
                    sysSensorData.setUnit("μg/m3");
                    sysSensorData.setTypeState(4);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("PM10".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (5 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(5);
                            alarmInsertion.setRemark("PM10异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("PM10");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("1000");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("75,150");
                    sysSensorData.setUnit("μg/m3");
                    sysSensorData.setTypeState(5);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("光照".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (6 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(6);
                            alarmInsertion.setRemark("PM10异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("光照");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("2000");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("75,150");
                    sysSensorData.setUnit("lux");
                    sysSensorData.setTypeState(6);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 存入传感器值和时间搓
     *
     * @param number
     * @param map
     * @return
     */
    @Override
    public boolean insertTimeData(String number, HashMap<String, String> map) {
        SysEquipment sysDtu = sysDtuSetvice.findByNumber(number);
        if (sysDtu == null) {
            return false;
        }
        //秒时间搓
        long dateTime = Instant.now().getEpochSecond();

        //存入数据
        SysDataType sysDataType = new SysDataType();
        try {
            for (String mapKey : map.keySet()) {
                if ("湿度".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(1);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("温度".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(2);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("噪声".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(3);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("PM2.5".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(4);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("PM10".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(5);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("光照".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(6);
                    sysInsertDataTimeMapper.insert(sysDataType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertDataTwo(String number, HashMap<String, String> map) {
        SysEquipment sysDtu = sysDtuSetvice.findByNumber(number);
        if (sysDtu == null) {
            return false;
        }
        //查出出属于这个传感器的检测类型和阈值（温度 50）
        List<Threshold> list = sysThresholdService.findThreshold(number);

        // 取map中传感器数据，每分钟存四条      表面应变计
        // 1-温度 2-测量值 3-偏移值 4-传感器应变频率
        SysSensorData sysSensorData = new SysSensorData();
        try {
            for (String mapKey : map.keySet()) {
                if ("温度".contains(mapKey)) {

                    //数据插入前先与阈值表对比，超过就报警，把报警的传感器数据和信息存入报警表
                    for (Threshold threshold : list) {
                        if (7 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(7);
                            alarmInsertion.setRemark("温度异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("温度");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("120");
                    sysSensorData.setMeasuringStart("0");
                    sysSensorData.setStatus("30,38");
                    sysSensorData.setUnit("°C");
                    sysSensorData.setTypeState(7);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("测量值".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (8 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(8);
                            alarmInsertion.setRemark("测量值异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("测量值");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("100");
                    sysSensorData.setMeasuringStart("-100");
                    sysSensorData.setStatus("-50,50");
                    sysSensorData.setUnit("°");
                    sysSensorData.setTypeState(8);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("偏移值".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (9 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(9);
                            alarmInsertion.setRemark("偏移值异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("噪声");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("100");
                    sysSensorData.setMeasuringStart("-100");
                    sysSensorData.setStatus("-50,50");
                    sysSensorData.setUnit("°");
                    sysSensorData.setTypeState(9);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                } else if ("传感器应变频率".contains(mapKey)) {

                    for (Threshold threshold : list) {
                        if (10 == threshold.getType() && Double.valueOf(map.get(mapKey)) > Integer.parseInt(threshold.getThresholdValue())) {
                            SysAlarmInsertion alarmInsertion = new SysAlarmInsertion();
                            alarmInsertion.setDeviceId(String.valueOf(sysDtu.getEquipmentId()));
                            alarmInsertion.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            alarmInsertion.setType(10);
                            alarmInsertion.setRemark("传感器应变频率异常值-" + map.get(mapKey));
                            alarmInsertionService.alarmInsert(alarmInsertion);
                        }
                    }
                    sysSensorData.setEquipmentId(sysDtu.getEquipmentId());
                    sysSensorData.setTypeName("传感器应变频率");
                    sysSensorData.setDataValue(map.get(mapKey));
                    sysSensorData.setMeasuringRange("100");
                    sysSensorData.setMeasuringStart("-100");
                    sysSensorData.setStatus("-50,50");
                    sysSensorData.setUnit("°");
                    sysSensorData.setTypeState(10);
                    sysSensorData.setData("#a3f263,#ffce49,#ff4949");
                    sysInsertDataMapper.insert(sysSensorData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean insertTimeDataTwo(String number, HashMap<String, String> map) {
        SysEquipment sysDtu = sysDtuSetvice.findByNumber(number);
        if (sysDtu == null) {
            return false;
        }

        //秒时间搓
        long dateTime = Instant.now().getEpochSecond();

        SysDataType sysDataType = new SysDataType();
        try {
            for (String mapKey : map.keySet()) {
                if ("温度".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(7);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("测量值".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(8);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("偏移值".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(9);
                    sysInsertDataTimeMapper.insert(sysDataType);
                } else if ("传感器应变频率".contains(mapKey)) {
                    sysDataType.setEquipmentId(sysDtu.getEquipmentId());
                    sysDataType.setCreateTime(String.valueOf(dateTime));
                    sysDataType.setDataValue(map.get(mapKey));
                    sysDataType.setType(10);
                    sysInsertDataTimeMapper.insert(sysDataType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
