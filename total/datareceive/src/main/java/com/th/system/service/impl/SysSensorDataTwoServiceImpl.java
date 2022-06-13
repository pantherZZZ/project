package com.th.system.service.impl;

import com.th.system.dao.SysSensorDataTwoMapper;
import com.th.system.service.SysSensorDataTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhang bao
 * @Date 2022/1/11 15:50
 * @Version 1.0
 */
@Service
@Transactional
public class SysSensorDataTwoServiceImpl implements SysSensorDataTwoService {

    @Autowired
    private SysSensorDataTwoMapper sysSensorDataTwoMapper;

    @Override
    public Long selectSensorDataTwo() {
        return sysSensorDataTwoMapper.selectCount(null);
    }
}
