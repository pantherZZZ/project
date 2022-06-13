package com.th.system.service;

import com.th.system.po.SysAlarmInsertion;
import com.th.system.vo.StatementVo;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/10 22:42
 * @Version 1.0
 */
public interface AlarmInsertionService {
    boolean alarmInsert(SysAlarmInsertion alarmInsertion);

    List<StatementVo> findAlarmAll();
}
