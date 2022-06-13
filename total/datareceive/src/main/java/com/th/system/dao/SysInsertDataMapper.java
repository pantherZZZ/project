package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysSensorData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2021/12/30 18:43
 * @Version 1.0
 */
public interface SysInsertDataMapper extends BaseMapper<SysSensorData> {

    List<SysSensorData> findSensorDataTwo(@Param("equipmentId") Integer equipmentId, @Param("typeState") Integer typeState);

}
