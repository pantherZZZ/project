package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/1/10 22:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_alarm")
public class SysAlarmInsertion {

    @TableId("id")
    private Integer id;

    @TableField("type")
    private Integer type;

    private String deviceId;

    private String createdAt;

    @TableField("status")
    private Integer status;

    @TableField("remark")
    private String remark;
}
