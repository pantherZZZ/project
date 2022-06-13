package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysDtu;
import com.th.system.vo.SysDtuOneVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2021/12/20 14:20
 * @Version 1.0
 */
public interface SysDtuOneMapper extends BaseMapper<SysDtu> {
    List<SysDtuOneVo> findByDtuName(@Param("dtuName") String dtuName, @Param("modelId") Integer modelId);

    Long findByDtuNameCount(@Param("dtuName") String dtuName,@Param("modelId") Integer modelId);
}
