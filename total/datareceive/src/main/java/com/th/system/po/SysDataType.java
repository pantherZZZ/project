package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 传感器数据表sys_data_type
 * @Author zhang bao
 * @Date 2022/1/7 10:14
 * @Version 1.0
 */
@Data
@TableName("sys_data_type")
public class SysDataType {

    @TableId("id")
    private int Id;

    @TableField("type")
    private int type;   //1湿度 2温度 3噪声 4PM2.5  5PM10 6大气压 7光照

    private int equipmentId;   //设备id

    private String createTime;   //创建数据时间

    private String dataValue;   //传感器数据值
}
