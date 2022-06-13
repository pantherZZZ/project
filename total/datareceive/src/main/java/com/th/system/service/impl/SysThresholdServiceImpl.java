package com.th.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.th.system.dao.SysThresholdMappe;
import com.th.system.po.SysEquipment;
import com.th.system.po.Threshold;
import com.th.system.service.SysDtuSetvice;
import com.th.system.service.SysThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/10 21:28
 * @Version 1.0
 */
@Service
@Transactional
public class SysThresholdServiceImpl implements SysThresholdService {

    @Autowired
    private SysThresholdMappe sysThresholdMappe;

    @Autowired
    private SysDtuSetvice sysDtuSetvice;

    @Override
    public List<Threshold> findThreshold(String number) {
        SysEquipment sysEquipment = sysDtuSetvice.findByNumber(number);
        QueryWrapper<Threshold> wrapper = new QueryWrapper<>();
        wrapper.eq("equipment_id",sysEquipment.getEquipmentId());
        return sysThresholdMappe.selectList(wrapper);
    }
}
