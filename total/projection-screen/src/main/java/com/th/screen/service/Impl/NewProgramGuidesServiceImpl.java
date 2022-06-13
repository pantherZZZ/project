package com.th.screen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.th.screen.entity.ScreenTwo;
import com.th.screen.service.NewProgramGuidesService;
import com.th.screen.mapper.ScreenTwoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:21
 * @Description
 * @Version 1.0
 */
@Service
public class NewProgramGuidesServiceImpl implements NewProgramGuidesService {
    @Autowired
    private ScreenTwoMapper screenTwoMapper;


    @Override
    public ScreenTwo findScreenTypeById(int id) {
        return screenTwoMapper.selectById(id);
    }

    @Override
    public void updateOffLine(int id) {
        ScreenTwo screenTwo = screenTwoMapper.selectById(id);
        screenTwo.setId(id);
        screenTwo.setSte(0);
        screenTwoMapper.updateById(screenTwo);
    }

    @Override
    public void updateOnLine(int id) {
        ScreenTwo screenTwo = screenTwoMapper.selectById(id);
        screenTwo.setId(id);
        screenTwo.setSte(1);
        screenTwoMapper.updateById(screenTwo);
    }

    @Override
    public List<ScreenTwo> selectAll() {
        return screenTwoMapper.selectList(null);
    }
}
