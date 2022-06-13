package com.ttwl.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author zhang bao
 * @Date 2022/5/30 9:17
 * @Description： 班级表
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("class")
public class CrsClass implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId
    private Integer id;

    private String className;

    private Timestamp createTime;

    private Timestamp updateTime;

}
