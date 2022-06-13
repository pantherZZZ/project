package com.th.system.service;

import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;

import java.util.List;


public interface SysEquipmentTotalService {
    List<SysFac> findAllEquipment();

    List<SysPro> findAllProduct(String plantId);

    List<SysMod> findAllModel(String typeId);
}
