package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysEquipment;
import com.th.system.vo.SysEquipmentNameVo;
import com.th.system.vo.SysEquipmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2021/12/22 16:30
 * @Version 1.0
 */
public interface SysEquipmentOneMapper extends BaseMapper<SysEquipment> {

    List<SysEquipmentVo> findByEquipmentName(@Param("equipmentName") String equipmentName, @Param("modelId") Integer modelId);

    Long findEquipmentCount(@Param("equipmentName") String equipmentName,@Param("modelId") Integer modelId);

    List<SysEquipmentNameVo> findEquipmentName(@Param("modelId") Integer modelId);
}
