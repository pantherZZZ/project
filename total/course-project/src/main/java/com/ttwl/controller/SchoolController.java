package com.ttwl.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.ttwl.pojo.School;
import com.ttwl.pojo.User;
import com.ttwl.service.SchoolService;
import com.ttwl.until.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 14:38
 * @Description： 学校业务层
 * @Version 1.0
 */
@RestController
@RequestMapping("")
@Slf4j
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 查询所有学校
     *
     * @return
     */
    @GetMapping("selectSchoolList")
    public R selectSchoolList() {
        List<School> schoolList = schoolService.selectSchoolList();
        if (CollectionUtil.isNotEmpty(schoolList)) {
            return R.ok().put("data",schoolList);
        }
        return R.error();

    }

    /**
     * 新增学校
     *
     * @param school
     * @return
     */
    @PostMapping("insertSchool")
    public R insertSchool(School school) {
        int result = schoolService.insertSchool(school);
        if (result > 0) {
            return R.ok("添加成功!");
        }
        return R.error("添加失败!");
    }

    /**
     * 删除学校
     *
     * @param id
     * @return
     */
    @PostMapping("deleteSchoolById")
    public R deleteSchoolById(Integer id) {
        int result = schoolService.deleteSchoolById(id);
        if (result > 0) {
            return R.ok("删除失败!");
        }
        return R.error("删除失败!");
    }

    /**
     * 修改学校
     *
     * @param school
     * @return
     */
    @PostMapping("updateSchool")
    public R updateSchool(School school) {
        int result = schoolService.updateSchool(school);
        if (result > 0) {
            return R.ok("更新失败!");
        }
        return R.error("更新失败!");
    }
}
