package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * sensor表
 * @Author zhang bao
 * @Date 2022/1/11 15:52
 * @Version 1.0
 */
@TableName("sensor")
@Data
public class SensorDataTwo {
    @TableId("id")
    private Integer id;

    @TableField("type")
    private Integer type;

    @TableField("time")
    private Integer time;

    @TableField("ste")
    private Integer ste;

    @TableField("value")
    private String value;

    private String deviceId;


}
