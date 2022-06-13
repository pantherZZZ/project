package com.th.system.service.impl;

import com.th.system.dao.SysMeasuringPointMapper;
import com.th.system.po.SysMeasure;
import com.th.system.service.SysMeasuringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhang bao
 * @Date 2022/1/5 17:42
 * @Version 1.0
 */
@Service
@Transactional
public class SysMeasuringPointServiceImpl implements SysMeasuringPointService {

    @Autowired
    private SysMeasuringPointMapper sysMeasuringPointMapper;
    @Override
    public Integer insertMeasure(String measureName, Integer factorId, String dataSource, String pictureSite, Integer equipmentId, Integer modelId) {
        SysMeasure sysMeasure = new SysMeasure();
        try {
            sysMeasure.setMeasureName(measureName);
            sysMeasure.setEquipmentId(equipmentId);
            sysMeasure.setFactorId(factorId);
            sysMeasure.setPictureSite(pictureSite);
            sysMeasure.setModelId(modelId);
            sysMeasure.setDataSource(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysMeasuringPointMapper.insert(sysMeasure);
    }
}
