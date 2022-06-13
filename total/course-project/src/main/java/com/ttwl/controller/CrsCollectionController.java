package com.ttwl.controller;

import com.ttwl.pojo.CrsCollection;
import com.ttwl.service.CrsCollectionService;
import com.ttwl.until.R;
import com.ttwl.vo.CrsCollectionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 9:28
 * @Description： 课程集锦
 * @Version 1.0
 */
@RestController
@RequestMapping("crsCollection")
@Slf4j
public class CrsCollectionController {

    @Autowired
    private CrsCollectionService crsCollectionService;


    /**
     * 查询当前学校的所有课程集锦信息
     *
     * @param userId   用户id
     * @param schoolId 学校id
     * @return 当前学校课程集锦信息
     */
    @PostMapping("selectCrsCollectionList")
    public R getCrsCollectionList(Integer userId, Integer schoolId) {
        log.info("CrsCollection userId:{} schoolId:{}",userId,schoolId);
        List<CrsCollectionVo> crsCollectionList = crsCollectionService.getCrsCollectionList(userId, schoolId);
        return R.ok().put("data", crsCollectionList);
    }

    /**
     * 新增课程集锦
     *
     * @param crsCollection 传入需新增的集锦对象
     * @return 结果信息
     */
    @RequestMapping("insertCrsCollection")
    public R insertCrsCollection(CrsCollection crsCollection) {
        int result = crsCollectionService.insertCrsCollection(crsCollection);
        if (result > 0) {
            R.ok("添加成功!");
        }
        return R.error("添加失败!");
    }

    /**
     * 更新课程集锦
     *
     * @param crsCollection 传入需更新的集锦对象
     * @return 结果信息
     */
    @RequestMapping("updateCrsCollection")
    public R updateCrsCollection(CrsCollection crsCollection) {
        int result = crsCollectionService.updateCrsCollection(crsCollection);
        if (result > 0) {
            R.ok("更新成功!");
        }
        return R.error("更新失败!");
    }

    /**
     * 删除课程集锦
     *
     * @param crsCollectionId 当前集锦id
     * @return 结果信息
     */
    @RequestMapping("deleteCrsCollection")
    public R deleteCrsCollection(Integer crsCollectionId) {
        int result = crsCollectionService.deleteCrsCollection(crsCollectionId);
        if (result > 0) {
            R.ok("删除成功!");
        }
        return R.error("删除失败!");
    }
}
