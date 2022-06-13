package com.th.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.system.po.SysProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhang bao
 * @Date 2022/1/12 10:26
 * @Version 1.0
 */
public interface ProjectMapper extends BaseMapper<SysProject> {
    SysProject selectProject(@Param("projectId") int projectId);
}
