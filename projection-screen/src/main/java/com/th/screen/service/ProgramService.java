package com.th.screen.service;

import com.th.screen.entity.Program;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/23 14:18
 * @Description
 * @Version 1.0
 */
public interface ProgramService {
    List<Program> findAll(List<String> listIds);
}
