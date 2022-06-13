package com.th.system.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.system.dao.SysDtuOneMapper;
import com.th.system.dao.SysEquipmentOneMapper;
import com.th.system.po.SysDtu;
import com.th.system.service.*;
import com.th.system.vo.SysDtuOneVo;
import com.th.system.vo.SysFindAllDtuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysEquipment;
import com.th.system.po.SysModelAndDetectionFacility;
import com.th.system.po.SysStrategy;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysDtuVo;
import com.th.system.vo.SysEquipmentVo;

/**
 * 结构物配置
 */
@CrossOrigin
@RestController
@RequestMapping("/equipment")
public class SysEquipmentController extends HttpClient {

    @Autowired
    private SysEquipmentService sysEquipmentService;

    @Autowired
    private SysModelAndDetectionFacilityService sysModelAndDetectionFacilityService;

    @Autowired
    private SysStrategyService sysStrategyService;

    @Autowired
    private SysDtuSetvice sysDtuSetvice;

    @Autowired
    private SysDtuOneService sysDtuOneService;

    @Autowired
    private SysEquipmentOneMapper sysEquipmentOneMapper;

    EncapsulationUtil en = new EncapsulationUtil();

    //查询模型 的策略
    @PostMapping(value = "/findByStrategyModelId", produces = "application/json;charset=UTF-8")
    public String findByStrategyModelId(Integer modelId) {
        List<SysStrategy> list = sysStrategyService.findByModelId(modelId, 0, 0, null);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //查询所有已安装的设备数量
    @GetMapping(value = "/findEquipmentCount", produces = "application/json;charset=UTF-8")
    public String findEquipmentCount() {
        Integer count = sysEquipmentService.findCount();
        JSONObject json = en.encapsulationCountJson(count + 3);
        return JsonUtil.writeAsString(json);
    }

    //    //dtu 根据modelId查询
//    @PostMapping(value = "/findDTUEquipmentAll", produces = "application/json;charset=UTF-8")
//    public String findDTUEquipmentAll(HttpServletRequest request, Integer modelId, String page, String limit) {
//        int pages = Integer.parseInt(page) - 1;
//        List<SysDtuVo> list = sysDtuSetvice.findByModelId(modelId, pages, Integer.parseInt(limit));
//        List<SysDtuVo> list2 = sysDtuSetvice.findByModelId(modelId, 0, 1000000);
//        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
//        return JsonUtil.writeAsString(json);
//    }


    /**
     * 查询结构物下dtu列表+输入框根据dtu名字模糊查询
     *
     * @param equipmentName
     * @param page
     * @param limit
     * @param modelId
     * @return
     */
    @GetMapping(value = "/findDTUEquipmentAll", produces = "application/json;charset=UTF-8")
    public String findDTUEquipmentAll(String equipmentName, String page, String limit, Integer modelId) {
        IPage<SysDtuOneVo> listPage = sysDtuOneService.findByDtuName(page, limit, equipmentName, modelId);
        if (listPage == null) {
            JSONObject json = en.encapsulationlimitJson(Collections.emptyList(), "1", "20", 0);
            return JsonUtil.writeAsString(json);
        }
        JSONObject json = en.encapsulationlimitJson(listPage.getRecords()
                , String.valueOf(listPage.getCurrent())
                , String.valueOf(listPage.getSize())
                , (int) listPage.getTotal());
        return JsonUtil.writeAsString(json);
    }

    /**
     * 查询结构物下设备列表+输入框根据设备名字模糊查询
     *
     * @param page
     * @param limit
     * @param modelId
     * @param equipmentName
     * @return
     */
    @GetMapping(value = "findEquipmentAll", produces = "application/json;charset=UTF-8")
    public String findEquipmentAll(String page, String limit, Integer modelId, String equipmentName) {
        IPage<SysEquipmentVo> listPage = sysDtuOneService.findByEquipmentName(page, limit, equipmentName, modelId);
        if (listPage == null) {
            JSONObject json = en.encapsulationlimitJson(Collections.emptyList(), "1", "20", 0);
            return JsonUtil.writeAsString(json);
        }
        JSONObject json = en.encapsulationlimitJson(listPage.getRecords()
                , String.valueOf(listPage.getCurrent())
                , String.valueOf(listPage.getSize())
                , (int) listPage.getTotal());
        return JsonUtil.writeAsString(json);
    }
//    //查询结构物下的设备
//    @PostMapping(value = "/findEquipmentAll", produces = "application/json;charset=UTF-8")
//    public String findEquipmentAll(Integer modelId, String page, String limit, String equipmentName) {
//        int pages = Integer.parseInt(page) - 1;
//        List<SysEquipmentVo> listPro = new ArrayList<>();
//        List<SysEquipmentVo> list = sysEquipmentService.findEquipment(modelId, pages, Integer.parseInt(limit), equipmentName, "1");
//        List<SysEquipmentVo> list2 = sysEquipmentService.findEquipment(modelId, pages, 1000000, equipmentName, null);
//        for (int i = 0; i < list.size(); i++) {
//            SysEquipmentVo equipment = list.get(i);
//            if (equipment.getDtuId() + "" != "" && equipment.getDtuId() + "" != "null" && equipment.getDtuId() + "" != null) {
//                SysEquipmentVo DTU = sysEquipmentService.findDTUEquipmentById(equipment.getDtuId());
//                if (DTU != null) {
//                    equipment.setDtuName(DTU.getEquipmentName());
//                }
//            }
//            listPro.add(equipment);
//        }
//        JSONObject json = en.encapsulationlimitJson(listPro, page, limit, list2.size());
//        return JsonUtil.writeAsString(json);
//    }


//	//查询结构物下的DTU设备
//	@PostMapping(value = "/findDTUEquipmentAll", produces="application/json;charset=UTF-8")
//	public String findDTUEquipmentAll(HttpServletRequest request,String modelId,String page,String limit,String equipmentName) {
//		int pages = Integer.parseInt(page)-1;
//		List<SysEquipmentVo> list = sysEquipmentService.findDTUEquipment(modelId,pages,Integer.parseInt(limit),equipmentName,"1");
//		List<SysEquipmentVo> list2 = sysEquipmentService.findDTUEquipment(modelId,pages,Integer.parseInt(limit),equipmentName,null);
//		JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
//		return JsonUtil.writeAsString(json);
//	}
//
//    //dtu 根据modelId查询
//    @PostMapping(value = "/findDTUEquipmentAllPro", produces = "application/json;charset=UTF-8")
//    public String findDTUEquipmentAllPro() {
//        Integer integer = sysDtuOneService.updateStatus(2);
//        JSONObject json = en.encapsulationCountJson(integer);
//        return JsonUtil.writeAsString(json);
//    }


    //dtu 根据id查询
    @PostMapping(value = "/findDTUEquipmentById", produces = "application/json;charset=UTF-8")
    public String findDTUEquipmentById(HttpServletRequest request, Integer dtuId) {
        SysDtuVo dtu = sysDtuSetvice.findById(dtuId);
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("result", dtu);
        data.put("code", code);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "成功");
        return JsonUtil.writeAsString(message);
    }


    //查询按照id查询设备
    @PostMapping(value = "/findEquipmentById", produces = "application/json;charset=UTF-8")
    public String findEquipmentById(Integer equipmentId) {
        SysEquipmentVo equipment = sysEquipmentService.findEquipmentById(equipmentId);
        if (equipment.getDtuId() + "" != "" && equipment.getDtuId() + "" != "null" && equipment.getDtuId() + "" != null) {
            SysDtuVo dtu = sysDtuSetvice.findById(equipment.getDtuId());
            if (dtu != null) {
                equipment.setDtuName(dtu.getDtuName());
            }
        }
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("result", equipment);
        data.put("code", 200);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "成功");
        return JsonUtil.writeAsString(message);
    }

    /**
     * 删除dtu
     *
     * @param equipmentId
     * @return
     */
    @PostMapping(value = "/delEquipment", produces = "application/json;charset=UTF-8")
    public String delEquipment(Integer equipmentId) {
        Integer count = sysEquipmentService.delEquipment(equipmentId);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 修改dtu
     *
     * @param equipmentId
     * @param plantId
     * @param typeId
     * @param modelNumberId
     * @param portId
     * @param equipmentName
     * @return
     */
    @PostMapping(value = "/updateDtuEquipment", produces = "application/json;charset=UTF-8")
    public String updateDtuEquipment(Integer equipmentId, Integer modelId, Integer plantId, Integer typeId, Integer modelNumberId,
                                     String portId, String equipmentName) {
        String port = portId.replaceAll(" ", "");
        Integer number = sysDtuSetvice.updateDTU(equipmentId, modelId, plantId, typeId, modelNumberId, port, equipmentName);
        JSONObject json = en.encapsulationStrJson(number, number);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 修改设备
     *
     * @param equipmentId
     * @param equipmentName
     * @param plantId
     * @param typeId
     * @param modelNumberId
     * @param strategyId
     * @param dtuId
     * @param modelId
     * @param portId
     * @return
     */
    @PostMapping(value = "/updateEquipment", produces = "application/json;charset=UTF-8")
    public String updateEquipment(Integer equipmentId
            , String equipmentName, Integer plantId, Integer typeId, Integer modelNumberId,
                                  Integer strategyId, Integer dtuId, Integer modelId, String portId) {
        SysEquipment sysEquipmentNew = sysEquipmentOneMapper.selectById(equipmentId);
        SysEquipment equipment = new SysEquipment();
        equipment.setEquipmentId(equipmentId);
        equipment.setEquipmentName(equipmentName);
        equipment.setPlantId(plantId);
        equipment.setTypeId(typeId);
        equipment.setDtuId(dtuId);
        equipment.setModelId(modelId);
        equipment.setStrategyId(strategyId);
        equipment.setSpecification(modelNumberId + "");
        String str = portId.replaceAll(" ", "");
        equipment.setSensorType(Integer.parseInt(str));
        equipment.setStatus(sysEquipmentNew.getStatus());
        equipment.setInstall(sysEquipmentNew.getInstall());
        Integer count = sysEquipmentService.updateEquipment(equipment);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //    /**
//     * 删除设备
//     *
//     * @param equipmentId
//     * @return
//     */
//    @PostMapping(value = "/updata", produces = "application/json;charset=UTF-8")
//    public String updata(Integer equipmentId) {
//        Integer count = sysEquipmentService.updata(equipmentId);
//        JSONObject json = en.encapsulationStrJson(count, count);
//        return JsonUtil.writeAsString(json);
//    }

    /**
     * 设备在线数
     *
     * @return
     */
    @GetMapping(value = "/findEquipmentOnLineCount", produces = "application/json;charset=UTF-8")
    public String findEquipmentOnLineCount() {
        long equipmentOnLineCount = sysDtuOneService.findEquipmentOnLineCount();
        JSONObject json = en.encapsulationCountJson(equipmentOnLineCount + 3);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 设备离线数
     *
     * @return
     */
    @GetMapping(value = "/findEquipmentOffLineCount", produces = "application/json;charset=UTF-8")
    public String findEquipmentOffLineCount() {
        long equipmentOffLineCount = sysDtuOneService.findEquipmentOffLineCount();
        JSONObject json = en.encapsulationCountJson(equipmentOffLineCount);
        return JsonUtil.writeAsString(json);
    }


    /**
     * 删除设备
     *
     * @param equipmentId
     * @return
     */
    @PostMapping(value = "/deleteEquipment", produces = "application/json;charset=UTF-8")
    public String deleteEquipment(Integer equipmentId) {
        Integer count = sysEquipmentService.deleteEquipment(equipmentId);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }


    /**
     * 新增dtu
     *
     * @param plantId
     * @param modelId
     * @param typeId
     * @param modelNumberId
     * @param postId
     * @param dtuName
     * @return
     */
    @PostMapping(value = "/insertDTU", produces = "application/json;charset=UTF-8")
    public String insertEquipmentDTU(Integer plantId, Integer modelId, Integer typeId, Integer modelNumberId, @RequestParam("portId") String postId, @RequestParam("equipmentName") String dtuName) {
        String str = postId.replaceAll(" ", "");
        Integer number = sysDtuSetvice.insertDTU(plantId, modelId, str, dtuName, typeId, modelNumberId);
        JSONObject json = en.encapsulationStrJson(number, number);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 查找所有项目里面新增的dtu(新增设备下拉框关联dtu)
     *
     * @return
     */
    @GetMapping("/findAllDtu")
    public String findAllDtu() {
        List<SysFindAllDtuVo> allDtu = sysDtuSetvice.findAllDtu();
        JSONObject json = en.encapsulationListJson(allDtu);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 新增设备
     *
     * @param equipmentName
     * @param plantId
     * @param typeId
     * @param modelNumberId
     * @param dtuId
     * @param modelId
     * @param strategyId
     * @return
     */
    @PostMapping(value = "/insertEquipment", produces = "application/json;charset=UTF-8")
    public String insertEquipment(String equipmentName, Integer plantId, Integer typeId, Integer modelNumberId,
                                  Integer dtuId, Integer modelId, Integer strategyId, String portId) {
        SysEquipment equipment = new SysEquipment();
        equipment.setEquipmentName(equipmentName);
        equipment.setPlantId(plantId);
        equipment.setTypeId(typeId);
        equipment.setIsDTU("2");
        equipment.setInstall("2");
        equipment.setDtuId(dtuId);
        equipment.setModelId(modelId);
        equipment.setStrategyId(strategyId);
        equipment.setSensorType(Integer.parseInt(portId));
        equipment.setSpecification(modelNumberId + "");
        equipment.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer count = sysEquipmentService.insertEquipment(equipment);
        sysDtuOneService.updateStatus(dtuId);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //安装设备
    @PostMapping(value = "/installEquipment", produces = "application/json;charset=UTF-8")
    public String installEquipment(HttpServletRequest request, Integer equipmentId, Integer modelId) {
        SysEquipment equipment = new SysEquipment();
        equipment.setEquipmentId(equipmentId);
        equipment.setInstall("2");
        Integer num = sysEquipmentService.updateEquipment(equipment);
        if (num == 1) {
            SysModelAndDetectionFacility detectionFacility = new SysModelAndDetectionFacility();
            detectionFacility.setDetectionFacilityId(equipmentId);
            detectionFacility.setModelId(modelId);
            sysModelAndDetectionFacilityService.insert(detectionFacility);
        }
        JSONObject json = en.encapsulationStrJson(num, num);
        return JsonUtil.writeAsString(json);
    }
}
