package com.th.system.service.impl;

import com.th.system.dao.SysEquipmentTotalMapper;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import com.th.system.service.SysEquipmentTotalService;
import com.th.system.utils.ListRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbao
 * @date 2021-12-16
 */
@Service
@Transactional
public class SysEquipmentTotalServiceImpl implements SysEquipmentTotalService {

    @Autowired
    private SysEquipmentTotalMapper sysEquipmentTotalMapper;

    @Override
    public List<SysFac> findAllEquipment() {
        List<SysFac> allDtu = sysEquipmentTotalMapper.findAllDtu();
        List<SysFac> newList = new ArrayList<>();
        allDtu.stream().filter(ListRemove.distinctByKey(p -> (p.getPlantId())))
                .forEach(newList::add);
        return newList;
    }

    @Override
    public List<SysPro> findAllProduct(String plantId) {
        List<SysPro> proList = sysEquipmentTotalMapper.findAllProduct(plantId);
        List<SysPro> newList = new ArrayList<>();
        proList.stream().filter(ListRemove.distinctByKey(p -> (p.getTypeId())))
                .forEach(newList::add);
        return newList;
    }

    @Override
    public List<SysMod> findAllModel(String typeId) {
        return sysEquipmentTotalMapper.findAllModel(typeId);
    }
}
