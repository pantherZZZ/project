package com.ttwl.service;

import com.ttwl.pojo.Picture;

/**
 * @Author zhang bao
 * @Date 2022/5/26 14:49
 * @Description：
 * @Version 1.0
 */
public interface PictureService {
    void insertPicture(Picture picture);

    Integer getPictureByPath(String databasePath);
}
