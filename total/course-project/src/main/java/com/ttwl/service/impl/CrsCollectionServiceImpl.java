package com.ttwl.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ttwl.dao.CrsCollectionMapper;
import com.ttwl.pojo.CrsCollection;
import com.ttwl.service.CrsCollectionService;
import com.ttwl.vo.CrsCollectionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 13:31
 * @Description： 集锦实现类
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class CrsCollectionServiceImpl implements CrsCollectionService {

    @Autowired
    private CrsCollectionMapper crsCollectionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CrsCollectionVo> getCrsCollectionList(Integer userId, Integer schoolId) {
        List<CrsCollectionVo> crsCollectionList = crsCollectionMapper.getCrsCollectionList(userId, schoolId);
        if (CollectionUtil.isEmpty(crsCollectionList)) {
            log.error(" CrsCollectionList:{} No data found is empty", crsCollectionList);
            throw new NullPointerException("此页面数据不存在");
        }
        List<CrsCollectionVo> list = new ArrayList<>();
        crsCollectionList.forEach(crsCollectionResultVo
                -> {
            CrsCollectionVo crsCollectionVo = new CrsCollectionVo();
            crsCollectionVo.setClassId(crsCollectionResultVo.getClassId());
            crsCollectionVo.setClassName(crsCollectionResultVo.getClassName());
            crsCollectionVo.setSchoolId(crsCollectionResultVo.getSchoolId());
            crsCollectionVo.setCollectionId(crsCollectionResultVo.getCollectionId());
            crsCollectionVo.setPictureId(crsCollectionResultVo.getPictureId());
            crsCollectionVo.setCollectionCrowd(crsCollectionResultVo.getCollectionCrowd());
            crsCollectionVo.setCollectionDifficulty(crsCollectionResultVo.getCollectionDifficulty());
            crsCollectionVo.setCollectionName(crsCollectionResultVo.getCollectionName());
            crsCollectionVo.setPicturePath(crsCollectionResultVo.getPicturePath());
            crsCollectionVo.setSchoolName(crsCollectionResultVo.getSchoolName());
            crsCollectionVo.setCreateTime(new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss")
                    .format(crsCollectionResultVo.getCreateTime()));
        });
        return list;
    }

    @Override
    public int insertCrsCollection(CrsCollection crsCollection) {
        CrsCollection resCrsCollection = new CrsCollection();
        resCrsCollection.setCollectionCrowd(crsCollection.getCollectionCrowd());
        resCrsCollection.setCollectionDifficulty(crsCollection.getCollectionDifficulty());
        resCrsCollection.setCollectionName(crsCollection.getCollectionName());
        resCrsCollection.setCollectionNumber(crsCollection.getCollectionNumber());
        resCrsCollection.setPictureId(crsCollection.getPictureId());
        resCrsCollection.setSchoolId(crsCollection.getSchoolId());
        resCrsCollection.setUserId(crsCollection.getUserId());
        return crsCollectionMapper.insert(resCrsCollection);
    }

    @Override
    public int updateCrsCollection(CrsCollection crsCollection) {
        CrsCollection resCrsCollection = new CrsCollection();
        resCrsCollection.setCollectionCrowd(crsCollection.getCollectionCrowd());
        resCrsCollection.setCollectionDifficulty(crsCollection.getCollectionDifficulty());
        resCrsCollection.setCollectionName(crsCollection.getCollectionName());
        resCrsCollection.setCollectionNumber(crsCollection.getCollectionNumber());
        resCrsCollection.setPictureId(crsCollection.getPictureId());
        resCrsCollection.setId(crsCollection.getId());
        return crsCollectionMapper.updateById(resCrsCollection);
    }

    @Override
    public int deleteCrsCollection(Integer crsCollectionId) {
        return crsCollectionMapper.deleteById(crsCollectionId);
    }
}
