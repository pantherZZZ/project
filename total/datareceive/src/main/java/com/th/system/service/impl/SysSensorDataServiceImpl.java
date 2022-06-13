package com.th.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.th.system.dao.SysInsertDataMapper;
import com.th.system.dao.SysInsertDataTimeMapper;
import com.th.system.dao.SysSensorDataTwoMapper;
import com.th.system.po.SensorDataTwo;
import com.th.system.po.SysDataType;
import com.th.system.po.SysSensorData;
import com.th.system.service.SysSensorDataService;
import com.th.system.service.SysSensorDataTwoService;
import com.th.system.utils.AnalysisUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zhang bao
 * @Date 2022/1/7 13:37
 * @Version 1.0
 */
@Service
@Transactional
public class SysSensorDataServiceImpl implements SysSensorDataService {

    @Autowired
    private SysInsertDataMapper sysInsertDataMapper;

    @Autowired
    private SysInsertDataTimeMapper sysInsertDataTimeMapper;

    @Autowired
    private SysSensorDataTwoService sysSensorDataTwoService;

    @Autowired
    private SysSensorDataTwoMapper sysSensorDataTwoMapper;

    @Autowired
    private AnalysisUtil analysisUtil;

    @Override
    public List<SysDataType> findSensorDataOne(Integer equipmentId, Integer type) {
        List<SysDataType> sysDataTypeList = sysInsertDataTimeMapper.findSensorDataOne(equipmentId, type);
        for (SysDataType sysDataType : sysDataTypeList) {
            sysDataType.getCreateTime();
            return sysDataTypeList;
        }
        return sysDataTypeList;

    }

    @Override
    public SysSensorData findSensorDataTwo(Integer equipmentId, Integer typeState) {
        List<SysSensorData> sensorDataTwoList = sysInsertDataMapper.findSensorDataTwo(equipmentId, typeState);
        return sensorDataTwoList.get(sensorDataTwoList.size() - 1);
    }

    @Override
    public long findSensorDataCount() {
        Long count = sysInsertDataTimeMapper.selectCount(null);
        Long aLong = sysSensorDataTwoService.selectSensorDataTwo();
        return count + aLong;
    }

    @Override
    public List<Long> findDataCount() {
        Map<String, String> todayTimeMap = findTodayTime();
        String todayStartTime = null;
        String todayEndTime = null;
        for (String s : todayTimeMap.keySet()) {
            if ("startDate".contains(s)) {
                todayStartTime = todayTimeMap.get(s);
            } else {
                todayEndTime = todayTimeMap.get(s);
            }
        }
        List<Long> todayTimeList = dataList(todayStartTime, todayEndTime);

        return todayTimeList;
    }


    //本周和上周星期一到星期日数据
    public List<Long> dataList(String todayStartTime, String todayEndTime) {
//        //周二到周六
//        String tuesday = getTime(monday);
//        String wednesday = getTime(tuesday);
//        String thursday = getTime(wednesday);
//        String friday = getTime(thursday);
//        String saturday = getTime(friday);
//        String endDay = getTime(sunday);
//        开始时间和结束时间
        List<String> timeOne = getTime(todayStartTime, todayEndTime);//前一天
        List<String> timeTwo = getTime(timeOne.get(0), timeOne.get(1));//前两天
        List<String> timeThree = getTime(timeTwo.get(0), timeTwo.get(1));//前三天
        List<String> timeFour = getTime(timeThree.get(0), timeThree.get(1));//前四天
        List<String> timeFive = getTime(timeFour.get(0), timeFour.get(1));//前五天
        List<String> timeSix = getTime(timeFive.get(0), timeFive.get(1));//前六天


        List<Long> list = null;
        try {
            ArrayList<String> mondayData = findData(todayStartTime, todayEndTime);//当天
            String mondayStartTime = mondayData.get(0);
            String mondayEndTime = mondayData.get(1);
            long mondayCount = selectData(mondayStartTime, mondayEndTime);


            ArrayList<String> tuesdayData = findData(timeOne.get(0), timeOne.get(1));//前一天
            String tuesdayStartTime = tuesdayData.get(0);
            String tuesdayEndTime = tuesdayData.get(1);
            long tuesdayCount = selectData(tuesdayStartTime, tuesdayEndTime);


            ArrayList<String> wednesdayData = findData(timeTwo.get(0), timeTwo.get(1));//前两天
            String wednesdayStartTime = wednesdayData.get(0);
            String wednesdayEndTime = wednesdayData.get(1);
            long wednesdayCount = selectData(wednesdayStartTime, wednesdayEndTime);


            ArrayList<String> thursdayData = findData(timeThree.get(0), timeThree.get(1));//前三天
            String thursdayStartTime = thursdayData.get(0);
            String thursdayEndTime = thursdayData.get(1);
            long thursdayCount = selectData(thursdayStartTime, thursdayEndTime);


            ArrayList<String> fridayData = findData(timeFour.get(0), timeFour.get(1));//前四天
            String fridayStartTime = fridayData.get(0);
            String fridayEndTime = fridayData.get(1);
            long fridayCount = selectData(fridayStartTime, fridayEndTime);


            ArrayList<String> saturdayData = findData(timeFive.get(0), timeFive.get(1));//前五天
            String saturdayStartTime = saturdayData.get(0);
            String saturdayEndTime = saturdayData.get(1);
            long saturdayCount = selectData(saturdayStartTime, saturdayEndTime);


            ArrayList<String> sundayData = findData(timeSix.get(0), timeSix.get(1));//前六天
            String sundayStartTime = sundayData.get(0);
            String sundayEndTime = sundayData.get(1);
            long sundayCount = selectData(sundayStartTime, sundayEndTime);


            list = new ArrayList<>();
            list.add(mondayCount);
            list.add(tuesdayCount);
            list.add(wednesdayCount);
            list.add(thursdayCount);
            list.add(fridayCount);
            list.add(saturdayCount);
            list.add(sundayCount);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    //查传感器数据量
    public long selectData(String start, String end) {
        QueryWrapper<SysDataType> wrapperOne = new QueryWrapper<>();
        wrapperOne.between("create_time", start, end);
        Long countOne = sysInsertDataTimeMapper.selectCount(wrapperOne);
        //对接数据
        QueryWrapper<SensorDataTwo> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.between("time", start, end);
        Long countTwo = sysSensorDataTwoMapper.selectCount(wrapperTwo);
        return countOne + countTwo;
    }

    //获取前一天的开始时间和结束时间
    public List<String> getTime(String todayStartTime, String todayEndTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<String> list = null;
        try {
            Date dd = df.parse(todayStartTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, -1);//减一天
            String start = df.format(calendar.getTime());
            Date ddEnd = df.parse(todayEndTime);
            calendar = Calendar.getInstance();
            calendar.setTime(ddEnd);
            calendar.add(Calendar.DAY_OF_MONTH, -1);//减一天
            String end = df.format(calendar.getTime());
            list = new ArrayList<>();
            list.add(start);
            list.add(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ArrayList<String> findData(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = String.valueOf(sdf.parse(start).getTime() / 1000);//星期一
        String endTime = String.valueOf(sdf.parse(end).getTime() / 1000);//星期日
        ArrayList<String> list = new ArrayList<>();
        list.add(startTime);
        list.add(endTime);
        return list;
    }

    //获取当天的开始时间和结束时间
    public Map<String, String> findTodayTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map condition = new HashedMap();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        condition.put("endDate", df.format(calendar.getTime()));
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        condition.put("startDate", df.format(calendar.getTime()));
        return condition;
    }
}