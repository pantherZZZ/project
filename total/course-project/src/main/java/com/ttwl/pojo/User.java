package com.ttwl.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author zhang bao
 * @Date 2022/5/25 13:50
 * @Description： 用户表
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("crs_user")
public class User implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId("id")
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    private Integer userPhoneNumber;

    private Integer userStudentNumber;

    private String userBelongingSchool;

    private String pictureId;

    private Integer roleId;

    private String userAddress;

    private Timestamp createTime;

    private Timestamp updateTime;

}
