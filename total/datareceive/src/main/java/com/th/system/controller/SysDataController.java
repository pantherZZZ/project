package com.th.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.th.system.po.SysEquipment;
import com.th.system.service.*;
import com.th.system.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysFactor;
import com.th.system.po.SysMeasure;
import com.th.system.utils.AnalysisUtil;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/data")
public class SysDataController extends HttpClient {

    @Autowired
    private SysModelService sysModelService;

    @Autowired
    private SysMeasureService sysMeasureService;

    @Autowired
    private SysFactorService sysFactorService;

    @Autowired
    private SysDataService sysDataService;

    @Autowired
    private SysDtuSetvice sysDtuSetvice;

    @Autowired
    private SysDataDisposeService sysDataDisposeService;

    @Autowired
    private SysDtuOneService sysDtuOneService;

    @Autowired
    private AlarmInsertionService alarmInsertionService;

    @Autowired
    private SysSensorDataService sysSensorDataService;


    EncapsulationUtil en = new EncapsulationUtil();

    AnalysisUtil analysisUtil = new AnalysisUtil();


    //查询结构物下的设备
    @PostMapping(value = "/factorlist", produces = "application/json;charset=UTF-8")
    public String factorlist(HttpServletRequest request) {
        List<SysFactor> list = sysFactorService.findAllPro(request);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 实时监测第二下拉框  根据第一个结构物id查找设备名称、设备检测的类型
     *
     * @param modelId
     * @return
     */
    @GetMapping(value = "/findEquipmentName", produces = "application/json;charset=UTF-8")
    public String findEquipmentName(Integer modelId) {
        List<SysEquipmentNameVo> equipmentNameList = sysDtuOneService.findEquipmentName(modelId);
        JSONObject json = en.encapsulationListJson(equipmentNameList);
        return JsonUtil.writeAsString(json);
    }

//    //查询结构物下的设备
//    @PostMapping(value = "/findEquipmentAll", produces = "application/json;charset=UTF-8")
//    public String findEquipmentAll(HttpServletRequest request, Integer modelId) {
//        List<SysEquipmentVo> list2 = sysEquipmentService.findEquipment(modelId, 0, 100000, null, null);
//        JSONObject json = en.encapsulationListJson(list2);
//        return JsonUtil.writeAsString(json);
//    }

//    /**
//     * 实时监测 查找
//     *
//     * @param equipmentId
//     * @param modelId
//     * @param time1
//     * @param time2
//     * @return
//     */
//    @GetMapping(value = "findSensorData", produces = "application/json;charset=UTF-8")
//    public String findSensorData(Integer equipmentId, Integer modelId, String time1, String time2) {
//       sysInsertData.findSensorData(equipmentId,modelId,time1,time2);
//    }

    //实时监测
    @PostMapping(value = "/realTimeSupervise", produces = "application/json;charset=UTF-8")
    public String realTimeSupervise(Integer equipmentId, String time1, String time2) {
        JSONArray arr = sysDataDisposeService.realTimeSupervise(equipmentId, time1, time2);
        JSONObject json = en.encapsulationJson(arr);
        return JsonUtil.writeAsString(json);
    }

    //根据因素查询测点
    @PostMapping(value = "/measurelist", produces = "application/json;charset=UTF-8")
    public String measurelist(HttpServletRequest request, Integer factorId) {
        List<SysMeasure> list = sysMeasureService.findByfactorId(request, factorId);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //查询不同时间测点数据对比
    @PostMapping(value = "/measureData", produces = "application/json;charset=UTF-8")
    public String measureData(String[] measureIdArr, String time) {
        JSONArray array = sysDataDisposeService.measureData(measureIdArr, time);
        JSONObject json = en.encapsulationJson(array);
        return JsonUtil.writeAsString(json);
    }

    //项目统计
    @GetMapping(value = "/projectStatistics", produces = "application/json;charset=UTF-8")
    public String projectStatistics() {
        JSONArray array = sysDataDisposeService.projectStatistics();
        JSONObject json = en.encapsulationListJson(array);
        return JsonUtil.writeAsString(json);
    }

    //结构物分布
    @GetMapping(value = "/modelDistribution", produces = "application/json;charset=UTF-8")
    public String modelDistribution() {
        List<DistributionVo> list = sysDataDisposeService.modelDistribution();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //设备统计
    @GetMapping(value = "/facilityStatistics", produces = "application/json;charset=UTF-8")
    public String facilityStatistics() {
        JSONArray arr = sysDataDisposeService.facilityStatistics();
        JSONObject json = en.encapsulationListJson(arr);
        return JsonUtil.writeAsString(json);
    }

    //设备统计 增长率
    @GetMapping(value = "/facilityStatisticsPro", produces = "application/json;charset=UTF-8")
    public String facilityStatisticsPro() {
        JSONArray array = sysDataDisposeService.facilityStatisticsPro();
        JSONObject json = en.encapsulationListJson(array);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 报警日志查询
     *
     * @return
     */
    @GetMapping(value = "/findStatement", produces = "application/json;charset=UTF-8")
    public String findStatement() {
        List<StatementVo> list = alarmInsertionService.findAlarmAll();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //查询总数据量
    @GetMapping(value = "/findEquipmentState", produces = "application/json;charset=UTF-8")
    public String findEquipmentState() {
        JSONObject message = sysDataDisposeService.findEquipmentState();
        return JsonUtil.writeAsString(message);
    }

    //查询总数据量
    @GetMapping(value = "/findStatementCount", produces = "application/json;charset=UTF-8")
    public String findStatementCount(HttpServletRequest request) {
        JSONObject message = sysDataDisposeService.findStatementCount();
        return JsonUtil.writeAsString(message);
    }

    //查询总数据量
    @GetMapping(value = "/findDataCount1", produces = "application/json;charset=UTF-8")
    public String findDataCount() {
        Integer count = sysDataService.findDataCount();
        JSONObject json = en.encapsulationCountJson(count);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 所有数据总条数
     * @return
     */
    @GetMapping(value = "/findDataCount", produces = "application/json;charset=UTF-8")
    public String findSensorDataCount() {
        long dataCount = sysSensorDataService.findSensorDataCount();
        JSONObject json = en.encapsulationCountJson(dataCount);
        return JsonUtil.writeAsString(json);
    }

    //设备状态
    @PostMapping(value = "/findModelData", produces = "application/json;charset=UTF-8")
    public String findModelData(HttpServletRequest request, Integer modelId) {
        JSONObject message = sysDataDisposeService.findModelData(modelId);
        return JsonUtil.writeAsString(message);
    }

    //查询总数据量
    @GetMapping(value = "/findDTUCount", produces = "application/json;charset=UTF-8")
    public String findDTUCount(HttpServletRequest request) {
//        Integer count = sysDtuSetvice.findDTUCount();
        JSONObject json = en.encapsulationCountJson(27);
        return JsonUtil.writeAsString(json);
    }

    //查询总数据量完整
    @PostMapping(value = "/findFullData", produces = "application/json;charset=UTF-8")
    public String findFullData(HttpServletRequest request, Integer modelId) {
        JSONObject message = sysDataDisposeService.findFullData(modelId);
        return JsonUtil.writeAsString(message);
    }

    //查询总数据量完整
    @PostMapping(value = "/findValidData", produces = "application/json;charset=UTF-8")
    public String findValidData(HttpServletRequest request, Integer modelId) {
        JSONObject message = sysDataDisposeService.findValidData(modelId);
        return JsonUtil.writeAsString(message);
    }

    //根据userid查询
    @PostMapping(value = "/findByUserModels", produces = "application/json;charset=UTF-8")
    public String findByUserModels(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer user = null;
        if (!role.equals("1")) {
            user = userid;
        }
        List<SysModelVo> list = sysModelService.findByProjectIdAir(user, 0, 0, null, null);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

}
