package com.th.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.th.system.dao.SysDtuTotalMapper;
import com.th.system.po.SysDtuTotal;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import com.th.system.service.SysDtuTotalService;
import com.th.system.utils.ListRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SysDtuTotalServiceImpl implements SysDtuTotalService {
    @Autowired
    private SysDtuTotalMapper sysDtuTotalMapper;

    @Override
    public List<SysFac> findAllDtu() {
        List<SysFac> allDtu = sysDtuTotalMapper.findAllDtu();
        List<SysFac> newList = new ArrayList<>();
        allDtu.stream().filter(ListRemove.distinctByKey(p -> (p.getPlantId())))
                .forEach(newList::add);
        return newList;
    }



    @Override
    public List<SysPro> findAllProduct(String plantId) {
        List<SysPro> proList = sysDtuTotalMapper.findAllProduct(plantId);
        List<SysPro> newList = new ArrayList<>();
        proList.stream().filter(ListRemove.distinctByKey(p -> (p.getTypeId())))
                .forEach(newList::add);
        return newList;
    }

    @Override
    public List<SysMod> findAllModel(String typeId) {
       return sysDtuTotalMapper.findAllModel(typeId);
    }
}
