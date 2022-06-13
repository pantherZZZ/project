package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 总dtu表
 * @author zhangbao
 * @date 2021-12-15
 */
@TableName("sys_dtu_total")
@Data
public class SysDtuTotal {
    private Integer totalId;

    private Integer plantId;

    private Integer typeId;

    private Integer modelNumberId;

    @TableField(exist = false)
    private String plantName;

    @TableField(value = "typeName",exist = false)
    private String typeName;

    @TableField(exist = false)
    private String modelNumberName;
}
