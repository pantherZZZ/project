package com.th.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.system.dao.SysDtuOneMapper;
import com.th.system.dao.SysEquipmentOneMapper;
import com.th.system.po.SysDtu;
import com.th.system.po.SysEquipment;
import com.th.system.service.SysDtuOneService;
import com.th.system.vo.SysDtuOneVo;
import com.th.system.vo.SysEquipmentNameVo;
import com.th.system.vo.SysEquipmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2021/12/20 14:17
 * @Version 1.0
 */
@Service
@Transactional
public class SysDtuOneServiceImpl implements SysDtuOneService {
    @Autowired
    private SysDtuOneMapper sysDtuOneMapper;

    @Autowired
    private SysEquipmentOneMapper sysEquipmentOneMapper;


    @Override
    public Integer updateStatus(Integer dtuId) {
        SysDtu sysDtu = sysDtuOneMapper.selectById(dtuId);
        sysDtu.setDTUid(dtuId);
        sysDtu.setStatus(2);
        return sysDtuOneMapper.updateById(sysDtu);
    }

    @Override
    public IPage findByDtuName(String pageNum, String pageSize, String dtuName, Integer modelId) {
        List<SysDtuOneVo> dtuList = sysDtuOneMapper.findByDtuName(dtuName, modelId);
        IPage<SysDtu> page = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        IPage selectPageList = null;
        if (CollectionUtil.isNotEmpty(dtuList)) {
            selectPageList = sysDtuOneMapper.selectPage(page, null);
            Long dtuCount = sysDtuOneMapper.findByDtuNameCount(dtuName, modelId);
            selectPageList.setRecords(dtuList);
            selectPageList.setTotal(dtuCount);
            return selectPageList;
        }
        return selectPageList;
    }


    @Override
    public IPage findByEquipmentName(String pageNum, String pageSize, String equipmentName, Integer model) {
        List<SysEquipmentVo> equipmentList = sysEquipmentOneMapper.findByEquipmentName(equipmentName, model);
        IPage<SysEquipment> page = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        IPage selectPageList = null;
        if (CollectionUtil.isNotEmpty(equipmentList)) {
            selectPageList = sysEquipmentOneMapper.selectPage(page, null);
            Long equipmentCount = sysEquipmentOneMapper.findEquipmentCount(equipmentName, model);
            selectPageList.setRecords(equipmentList);
            selectPageList.setTotal(equipmentCount);
            return selectPageList;
        }
        return selectPageList;
    }

    @Override
    public List<SysEquipmentNameVo> findEquipmentName(Integer modelId) {
        return sysEquipmentOneMapper.findEquipmentName(modelId);
    }

    @Override
    public long findEquipmentOffLineCount() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 2);
        return sysEquipmentOneMapper.selectCount(queryWrapper);
    }


    @Override
    public long findEquipmentOnLineCount() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 1);
        return sysEquipmentOneMapper.selectCount(queryWrapper);
    }
}
