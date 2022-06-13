package com.th.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import com.th.system.service.SysEquipmentTotalService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新增设备中选择厂商，产品，型号
 */
@CrossOrigin
@RestController
@RequestMapping("/equipmentTotal")
public class SysEquipmentTotalController {
    @Autowired
    private SysEquipmentTotalService sysEquipmentTotalService;

    EncapsulationUtil en = new EncapsulationUtil();

    /**
     * 查所有设备厂商
     * @return
     */
    @PostMapping(value = "/findFactory", produces="application/json;charset=UTF-8")
    public String findAllDtu() {
        List<SysFac> list = sysEquipmentTotalService.findAllEquipment();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 根据设备厂商查设备产品
     * @param plantId
     * @return
     */
    @PostMapping(value = "/findProduct", produces="application/json;charset=UTF-8")
    public String findAllProduct(String plantId) {
        List<SysPro> list = sysEquipmentTotalService.findAllProduct(plantId);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 根据设备产品查设备型号
     * @param typeId
     * @return
     */
    @PostMapping(value = "/findModel", produces="application/json;charset=UTF-8")
    public String findAllModel(String typeId) {
        List<SysMod> allModel = sysEquipmentTotalService.findAllModel(typeId);
        JSONObject json = en.encapsulationListJson(allModel);
        return JsonUtil.writeAsString(json);
    }
}
