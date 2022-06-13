package com.th.system.service;


import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface SysDtuTotalService  {
    List<SysFac>  findAllDtu();

    List<SysPro> findAllProduct(String plantId);

    List<SysMod> findAllModel(String typeId);
}
