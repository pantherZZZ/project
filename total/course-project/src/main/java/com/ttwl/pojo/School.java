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
 * @Date 2022/5/27 14:35
 * @Description： 学校总表
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("school")
public class School implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId
    private Integer id;

    private String schoolName;

    private Integer classId;

    private Timestamp createTime;

    private Timestamp updateTime;

}
