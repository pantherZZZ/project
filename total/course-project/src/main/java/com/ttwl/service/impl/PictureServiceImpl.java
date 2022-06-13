package com.ttwl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttwl.dao.PictureMapper;
import com.ttwl.pojo.Picture;
import com.ttwl.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhang bao
 * @Date 2022/5/26 14:49
 * @Description：
 * @Version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public void insertPicture(Picture picture) {
        pictureMapper.insert(picture);
    }

    @Override
    public Integer getPictureByPath(String databasePath) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<Picture>().eq("picture_path", databasePath);
        Picture picture = pictureMapper.selectOne(queryWrapper);
        return picture.getId();
    }
}
