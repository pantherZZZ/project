package com.ttwl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttwl.pojo.CrsCollection;
import com.ttwl.vo.CrsCollectionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 13:32
 * @Description： 集锦数据层接口
 * @Version 1.0
 */
@Repository
public interface CrsCollectionMapper extends BaseMapper<CrsCollection> {
    List<CrsCollectionVo> getCrsCollectionList(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId);
}
