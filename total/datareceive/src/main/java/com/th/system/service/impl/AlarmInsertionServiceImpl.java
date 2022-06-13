package com.th.system.service.impl;

import com.th.system.dao.AlarmInsertionMapper;
import com.th.system.po.SysAlarmInsertion;
import com.th.system.service.AlarmInsertionService;
import com.th.system.vo.StatementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/10 22:43
 * @Version 1.0
 */
@Service
@Transactional
public class AlarmInsertionServiceImpl implements AlarmInsertionService {

    @Autowired
    private AlarmInsertionMapper alarmInsertionMapper;



    @Override
    public boolean alarmInsert(SysAlarmInsertion alarmInsertion) {
        if (alarmInsertion == null) {
            return false;
        }
        alarmInsertionMapper.insert(alarmInsertion);
        return true;
    }

    @Override
    public List<StatementVo> findAlarmAll() {
        List<SysAlarmInsertion> list = alarmInsertionMapper.selectList(null);
        StatementVo statement = new StatementVo();
        List<StatementVo> alarmInsertionArrayList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (SysAlarmInsertion alarmInsertion : list) {
                statement.setEquipmentId(Integer.parseInt(alarmInsertion.getDeviceId()));
                statement.setDistrict(findType(alarmInsertion.getType()));
                statement.setStatementMessage(alarmInsertion.getRemark());
                statement.setStatementTime(alarmInsertion.getCreatedAt());
                alarmInsertionArrayList.add(statement);
            }
            return alarmInsertionArrayList;
        }
        return Collections.emptyList();
    }

    public String findType(int type) {
        if (type==1){
            return "湿度";
        }else if (type==2){
            return "温度";
        }else if (type==3){
            return "噪音";
        }else if (type==4){
            return "PM2.5";
        }else if (type==5){
            return "PM1.0";
        }else if (type==6){
            return "光照";
        }else if (type==7){
            return "温度";
        }else if (type==8){
            return "测量值";
        }else if (type==9){
            return "偏移值";
        }else
            return "传感器应变频率";
    }
}
