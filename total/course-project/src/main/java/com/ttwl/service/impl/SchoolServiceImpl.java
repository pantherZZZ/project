package com.ttwl.service.impl;

import com.ttwl.dao.SchoolMapper;
import com.ttwl.pojo.School;
import com.ttwl.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 14:42
 * @Description：
 * @Version 1.0
 */
@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<School> selectSchoolList() {
         return schoolMapper.selectList(null);
    }

    @Override
    public int insertSchool(School school) {
        School resultSchool = new School();
        resultSchool.setId(school.getId());
        resultSchool.setClassId(school.getClassId());
        resultSchool.setSchoolName(school.getSchoolName());
        return schoolMapper.insert(resultSchool);
    }

    @Override
    public int updateSchool(School school) {
        School resultSchool = new School();
        resultSchool.setId(school.getId());
        resultSchool.setClassId(school.getClassId());
        resultSchool.setSchoolName(school.getSchoolName());
        return schoolMapper.updateById(resultSchool);
    }

    @Override
    public int deleteSchoolById(Integer id) {
        return schoolMapper.deleteById(id);
    }
}
