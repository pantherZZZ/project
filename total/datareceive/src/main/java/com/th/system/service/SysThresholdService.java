package com.th.system.service;

import com.th.system.po.Threshold;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/10 21:25
 * @Version 1.0
 */
public interface SysThresholdService {
    List<Threshold> findThreshold(String number);
}
