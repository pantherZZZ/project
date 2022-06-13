package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysDtuTotal;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021-12-15
 */
public interface SysDtuTotalMapper extends BaseMapper<SysDtuTotal> {

    List<SysFac> findAllDtu();

    List<SysPro> findAllProduct(@Param("plantId") String plantId);

    List<SysMod> findAllModel(@Param("typeId") String typeId);
}
