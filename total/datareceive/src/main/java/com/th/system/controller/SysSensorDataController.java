package com.th.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysDataType;
import com.th.system.po.SysSensorData;
import com.th.system.service.SysSensorDataService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时监测界面数据获取
 *
 * @Author zhang bao
 * @Date 2022/1/7 13:29
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("device")
public class SysSensorDataController {

    private final EncapsulationUtil en = new EncapsulationUtil();

    @Autowired
    private SysSensorDataService sysSensorDataService;

    /**
     * 查询传感器数据
     *
     * @param equipmentId
     * @paGram type
     * @return (返回时间搓和传感器数据值)
     */
    @GetMapping(value = "getSensor", produces = "application/json;charset=UTF-8")
    public String findSensorDataOne(Integer equipmentId, Integer type) {
        List<SysDataType> sysDataTypeList = sysSensorDataService.findSensorDataOne(equipmentId, type);

        JSONObject json = en.encapsulationListJson(sysDataTypeList);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 获取折线图警告线及线颜色()
     *
     * @param equipmentId
     * @param type
     * @return
     */
    @GetMapping(value = "findSensor", produces = "application/json;charset=UTF-8")
    public String findSensorDataTwo(Integer equipmentId, Integer type) {
        SysSensorData sensorDataTwo = sysSensorDataService.findSensorDataTwo(equipmentId, type);
        JSONObject json = en.encapsulationObjJson(sensorDataTwo);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 所有数据总条数
     * @return
     */
    @GetMapping(value = "findSensorDataCount", produces = "application/json;charset=UTF-8")
    public String findSensorDataCount() {
        long dataCount = sysSensorDataService.findSensorDataCount();
        JSONObject json = en.encapsulationCountJson(dataCount);
        return JsonUtil.writeAsString(json);
    }


    /**
     * 走势图 上周和本周星期1到星期日数据条数
     * @return
     */
    @GetMapping(value = "findDataCount", produces = "application/json;charset=UTF-8")
    public String findDataCount() {
        List<Long> list = sysSensorDataService.findDataCount();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

}
