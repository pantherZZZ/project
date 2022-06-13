package com.th.system.service;

/**
 * 测点布控页面
 * @Author zhang bao
 * @Date 2022/1/5 17:31
 * @Version 1.0
 */
public interface SysMeasuringPointService {
    Integer insertMeasure(String measureName, Integer factorId, String dataSource, String pictureSite, Integer equipmentId, Integer modelId);
}
