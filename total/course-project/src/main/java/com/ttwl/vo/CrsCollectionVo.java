package com.ttwl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/5/30 9:23
 * @Description： 课程集锦展示返回
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrsCollectionVo {
    private Integer classId;
    private String className;
    private String schoolName;
    private Integer schoolId;
    private Integer collectionId;
    private String collectionName;
    private String collectionDifficulty;
    private String collectionCrowd;
    private String createTime;
    private Integer pictureId;
    private String picturePath;
}
