package com.th.system.service.impl;


import com.th.system.dao.ProjectMapper;
import com.th.system.po.SysProject;
import com.th.system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhang bao
 * @Date 2022/1/12 10:31
 * @Version 1.0
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public SysProject selectProject(int projectId) {
        SysProject sysProject = projectMapper.selectProject(projectId);
        return sysProject;
    }
}
