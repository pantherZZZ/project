package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysEquipmentTotal;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021-12-16
 */
public interface SysEquipmentTotalMapper extends BaseMapper<SysEquipmentTotal> {
    List<SysFac> findAllDtu();

    List<SysPro> findAllProduct(@Param("plantId") String plantId);

    List<SysMod> findAllModel(@Param("typeId") String typeId);
}
