package com.th.screen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:20
 * @Description 屏幕实体
 * @Version 1.0
 */
@TableName("screen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenTwo {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("number")
    private String number;

    @TableField("ste")
    private int ste;

    @TableField("pattern")
    private int pattern;

    @TableField("uid")
    private int uid;

    @TableField("name")
    private String name;

    private int createdAt;

    private String deviceAddress;

    private int lampId;

    private int screenType;

    private String programId;

    private int fileId;



}
