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
 * @Date 2022/3/23 14:19
 * @Description
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("program")
public class Program {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("status")
    private int status;

    private String programName;

    private String programTime;

    private String fileId;

    private String deviceCode;

    private int programType;

    private int screenType;

    private String createdAt;

    private String updatedAt;
}
