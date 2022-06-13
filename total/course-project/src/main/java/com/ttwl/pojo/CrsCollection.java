package com.ttwl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author zhang bao
 * @Date 2022/5/26 16:28
 * @Description： 课程集锦表
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("crs_collection")
public class CrsCollection implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId
    private Integer id;

    private String collectionName;

    private String collectionDifficulty;

    private Integer pictureId;

    private String collectionCrowd;

    private String collectionNumber;

    private Integer userId;

    private Integer schoolId;

    private Timestamp createTime;

    private Timestamp updateTime;

}
