package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 传感器数据表sys_sensor_data
 * @Author zhang bao
 * @Date 2021/12/30 18:44
 * @Version 1.0
 */
@Data
@TableName("sys_sensor_data")
public class SysSensorData {

    @TableId("id")
    private int Id;

    private String typeName;

    private int typeState;  //传感器检测类型

    private int equipmentId;   //设备id

    private String measuringRange;

    private String measuringStart;

    @TableField("data")
    private String data;

    @TableField("status")
    private String status;

    @TableField("unit")
    private String unit;

    private String dataValue;  //传感器数据

}
