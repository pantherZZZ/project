package com.ttwl.service;

import com.ttwl.pojo.School;
import com.ttwl.pojo.User;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 14:41
 * @Description：
 * @Version 1.0
 */
public interface SchoolService {
    List<School> selectSchoolList();

    int insertSchool(School school);

    int updateSchool(School school);

    int deleteSchoolById(Integer id);
}
