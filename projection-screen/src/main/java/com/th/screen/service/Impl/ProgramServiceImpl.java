package com.th.screen.service.Impl;

import com.th.screen.entity.Program;
import com.th.screen.mapper.ProgramMapper;
import com.th.screen.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/23 14:18
 * @Description
 * @Version 1.0
 */
@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    private ProgramMapper programMapper;

    @Override
    public List<Program> findAll(List<String> listIds) {
        return programMapper.findAll(listIds);
    }
}
