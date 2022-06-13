package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysDataType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/1/7 11:19
 * @Version 1.0
 */
public interface SysInsertDataTimeMapper extends BaseMapper<SysDataType> {

    List<SysDataType> findSensorDataOne(@Param("equipmentId") Integer equipmentId, @Param("type") Integer type);
}
