package com.th.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.system.po.SysDtu;
import com.th.system.vo.SysEquipmentNameVo;


import java.util.List;

/**
 * @Author zhang bao
 * @Date 2021/12/20 14:16
 * @Version 1.0
 */
public interface SysDtuOneService {

    long findEquipmentOnLineCount();

    IPage findByDtuName(String dtuName, String page, String limit, Integer modelId);

    IPage findByEquipmentName(String pageNum, String pageSize, String equipmentName, Integer model);

    List<SysEquipmentNameVo> findEquipmentName(Integer modelId);

    long findEquipmentOffLineCount();

    Integer updateStatus(Integer dtuId);
}
