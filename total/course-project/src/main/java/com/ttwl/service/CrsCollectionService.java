package com.ttwl.service;

import com.ttwl.pojo.CrsCollection;
import com.ttwl.vo.CrsCollectionVo;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 13:31
 * @Description： 集锦接口
 * @Version 1.0
 */
public interface CrsCollectionService {
    List<CrsCollectionVo> getCrsCollectionList(Integer userId, Integer schoolId);

    int insertCrsCollection(CrsCollection crsCollection);

    int updateCrsCollection(CrsCollection crsCollection);

    int deleteCrsCollection(Integer crsCollectionId);
}
