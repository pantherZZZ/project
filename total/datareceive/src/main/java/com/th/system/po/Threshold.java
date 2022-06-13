package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/1/10 21:30
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_threshold_value")
public class Threshold {
    @TableId("id")
    private int id;
    @TableField("name")
    private String name;

    private int equipmentId;

    private String thresholdValue;

    @TableField("type")
    private int type;
}
