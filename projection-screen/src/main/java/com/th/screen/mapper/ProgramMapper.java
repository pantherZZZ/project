package com.th.screen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.th.screen.entity.Program;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/23 14:19
 * @Description
 * @Version 1.0
 */
@Repository
public interface ProgramMapper extends BaseMapper<Program> {
    List<Program> findAll(@Param("ids") List<String> ids);
}
