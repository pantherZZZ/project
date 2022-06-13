package com.th.system.service;

import com.th.system.po.SysDataType;
import com.th.system.po.SysSensorData;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/7 13:36
 * @Version 1.0
 */
public interface SysSensorDataService {

    List<SysDataType> findSensorDataOne(Integer equipmentId,Integer type);

    SysSensorData findSensorDataTwo(Integer equipmentId, Integer typeState);

    long findSensorDataCount();

    List<Long> findDataCount();
}
