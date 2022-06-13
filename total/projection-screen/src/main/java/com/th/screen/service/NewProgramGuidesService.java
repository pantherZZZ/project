package com.th.screen.service;

import com.th.screen.entity.ScreenTwo;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:18
 * @Description
 * @Version 1.0
 */
public interface NewProgramGuidesService {

    ScreenTwo findScreenTypeById(int id);

    void updateOffLine(int id);

    void updateOnLine(int id);

    List<ScreenTwo> selectAll();
}
