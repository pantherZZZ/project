package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.th.system.dao.SysDtuOneMapper;
import com.th.system.po.SysEquipment;
import com.th.system.vo.SysFindAllDtuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysDtuMapper;
import com.th.system.po.SysDtu;
import com.th.system.service.SysDtuSetvice;
import com.th.system.utils.AnalysisUtil;
import com.th.system.vo.SysDtuVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysDtuSetviceImpl")
@Transactional
public class SysDtuSetviceImpl implements SysDtuSetvice {

    @Autowired
    private SysDtuMapper sysDtuMapper;

    @Autowired
    private SysDtuOneMapper sysDtuOneMapper;

    AnalysisUtil analysisUtil = new AnalysisUtil();

    @Override
    public Integer insertDTU(Integer plantId, Integer modelId, String postId, String dtuName, Integer typeId, Integer modelNumberId) {
        SysDtu dtu = new SysDtu();
//		String DTUid = UUID.randomUUID().toString().replace("-", "");
//		dtu.setDTUid(DTUid);
        dtu.setPlantId(plantId);
        dtu.setModelNumberId(modelNumberId);
        dtu.setTypeId(typeId);
        dtu.setModelId(modelId);
        dtu.setPostId(postId);
        dtu.setDtuName(dtuName);
        String time = analysisUtil.getTime();
        dtu.setTime(time);
        return sysDtuMapper.insertDTU(dtu);
    }

    @Override
    public List<SysDtuVo> findByModelId(Integer modelId, int page, int limit) {

        // TODO Auto-generated method stub
        return sysDtuMapper.findByModelId(modelId, page, limit);
    }

    @Override
    public SysDtuVo findById(Integer dtuId) {
        // TODO Auto-generated method stub
        return sysDtuMapper.findById(dtuId);
    }

    @Override
    public Integer updateDTU(Integer dtuId, Integer modelId, Integer plantId, Integer typeId, Integer modelNumberId,
                             String postId, String dtuName) {
        SysDtu sysDtu = sysDtuOneMapper.selectById(dtuId);
        SysDtu dtu = new SysDtu();
        dtu.setDTUid(dtuId);
        dtu.setModelId(modelId);
        dtu.setPlantId(plantId);
        dtu.setTypeId(typeId);
        dtu.setModelNumberId(modelNumberId);
        dtu.setPostId(postId);
        dtu.setDtuName(dtuName);
        dtu.setStatus(sysDtu.getStatus());
        return sysDtuOneMapper.updateById(dtu);
    }

    @Override
    public Integer delDTU(Integer DTUid) {
        // TODO Auto-generated method stub
        return sysDtuMapper.delDTU(DTUid);
    }

    @Override
    public Integer findDTUCount() {
        // TODO Auto-generated method stub
        return sysDtuMapper.findDTUCount();
    }

    @Override
    public Integer findTimeCount(String time) {
        // TODO Auto-generated method stub
        return sysDtuMapper.findTimeCount(time);
    }

    @Override
    public Integer findDTUModleCount(Integer modelId) {
        // TODO Auto-generated method stub
        return sysDtuMapper.findDTUModleCount(modelId);
    }

    @Override
    public List<SysFindAllDtuVo> findAllDtu() {
        List<SysDtu> allDtu = sysDtuMapper.findAllDtu();
        List<SysFindAllDtuVo> list = new ArrayList<>();
        if (allDtu != null && allDtu.size() > 0) {
            for (SysDtu sysDtu : allDtu) {
                SysFindAllDtuVo sysFindAllDtuVo = new SysFindAllDtuVo();
                sysFindAllDtuVo.setDTUid(sysDtu.getDTUid());
                sysFindAllDtuVo.setDtuName(sysDtu.getDtuName());
                list.add(sysFindAllDtuVo);
            }
            return list;
        }
        return list;
    }

    @Override
    public SysEquipment findByNumber(String number) {
        return sysDtuMapper.findByNumber(number);
    }


}
