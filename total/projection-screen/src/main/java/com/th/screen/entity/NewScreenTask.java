package com.th.screen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:23
 * @Description 屏幕任务表
 * @Version 1.0
 */
@TableName("program_task")
@Data
public class NewScreenTask {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String name;

    @TableField("status")
    private int status;

    private int programId;

    private Date startTime;

    private Date endTime;

    private Date createdAt;

    private Date updatedAt;
}
