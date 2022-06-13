package com.th.system.service.impl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.th.system.dao.SysDtuOneMapper;
import com.th.system.dao.SysEquipmentOneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysEquipmentMapper;
import com.th.system.po.SysEquipment;
import com.th.system.service.SysEquipmentService;
import com.th.system.utils.AnalysisUtil;
import com.th.system.vo.SysEquipmentVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysEquipmentServiceImpl")
@Transactional
public class SysEquipmentServiceImpl implements SysEquipmentService {

    @Autowired
    private SysEquipmentMapper sysEquipmentMapper;

    @Autowired
    private SysDtuOneMapper sysDtuOneMapper;

    @Autowired
    private SysEquipmentOneMapper sysEquipmentOneMapper;

    AnalysisUtil analysisUtil = new AnalysisUtil();

    @Override
    public Integer insertEquipment(SysEquipment sysEquipment) {
        return sysEquipmentMapper.insertEquipment(sysEquipment);

    }

    @Override
    public Integer updateEquipment(SysEquipment sysEquipment) {
        return sysEquipmentOneMapper.updateById(sysEquipment);
    }

    @Override
    public Integer findCount() {
        return sysEquipmentMapper.findCount();
    }

    @Override
    public List<SysEquipment> findAll() {
        return sysEquipmentMapper.findAll();
    }

    @Override
    public Integer delEquipment(Integer equipmentId) {
        return sysDtuOneMapper.deleteById(equipmentId);
    }

    @Override
    public List<SysEquipmentVo> findEquipment(Integer modelId, int page, int limit, String equipmentName, String val) {
        return sysEquipmentMapper.findEquipment(modelId, page, limit, equipmentName, val);
    }

    @Override
    public List<SysEquipmentVo> findDTUEquipment(Integer modelId, int page, int limit, String equipmentName, String val) {
        return sysEquipmentMapper.findDTUEquipment(modelId, page, limit, equipmentName, val);
    }

    @Override
    public SysEquipmentVo findEquipmentById(Integer equipmentId) {
        return sysEquipmentMapper.findEquipmentById(equipmentId);
    }

    @Override
    public SysEquipmentVo findDTUEquipmentById(Integer equipmentId) {
        return sysEquipmentMapper.findDTUEquipmentById(equipmentId);
    }

    @Override
    public SysEquipment findAllPro(Integer equipmentId) {
        return sysEquipmentMapper.findAllPro(equipmentId);
    }

    @Override
    public SysEquipment findByNumber(String number) {
        return sysEquipmentMapper.findByNumber(number);
    }

    @Override
    public Integer findTimeCount(String time) {
        return sysEquipmentMapper.findTimeCount(time);
    }

    @Override
    public SysEquipment findByNumberPro(String number) {
        return sysEquipmentMapper.findByNumberPro(number);
    }

    @Override
    public Integer findModelCount(Integer modelId) {
        return sysEquipmentMapper.findModelCount(modelId);
    }

    @Override
    public Integer deleteEquipment(Integer equipmentId) {
        return sysEquipmentOneMapper.deleteById(equipmentId);
    }

    @Override
    public Integer updateOnLine(Integer equipmentId) {
        SysEquipment sysEquipment = sysEquipmentOneMapper.selectById(equipmentId);
        sysEquipment.setEquipmentId(equipmentId);
        sysEquipment.setLastTime(( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        sysEquipment.setStatus(1);
        return sysEquipmentOneMapper.updateById(sysEquipment);
    }

    @Override
    public Integer updateOffLine(Integer equipmentId) {
        SysEquipment sysEquipment=sysEquipmentOneMapper.selectById(equipmentId);
        sysEquipment.setEquipmentId(equipmentId);
        sysEquipment.setLastTime(( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        sysEquipment.setStatus(2);
        return sysEquipmentOneMapper.updateById(sysEquipment);
    }

    @Override
    public Integer updateOn(Integer equipmentId) {
//        SysEquipment sysEquipment=sysEquipmentOneMapper.selectById(equipmentId);
        SysEquipment sysEquipment = new SysEquipment();
        sysEquipment.setEquipmentId(equipmentId);
        sysEquipment.setStatus(1);
        return sysEquipmentOneMapper.updateById(sysEquipment);
    }

    @Override
    public Integer updateOff(Integer equipmentId) {
//        SysEquipment sysEquipment=sysEquipmentOneMapper.selectById(equipmentId);
        SysEquipment sysEquipment = new SysEquipment();
        sysEquipment.setEquipmentId(equipmentId);
        sysEquipment.setStatus(2);
        return sysEquipmentOneMapper.updateById(sysEquipment);
    }


    @Override
    public List<SysEquipment> findAllEquipment() {
        return sysEquipmentOneMapper.selectList(null);
    }

}
