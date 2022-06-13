package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

/**
 * @author zhangbao
 * @date 2021-12-16
 */
@TableName("sys_equipment_total")
@Data
public class SysEquipmentTotal {
    private Integer equipmentId;

    private Integer plantId;

    private Integer typeId;

    private Integer modelNumberId;

    @TableField(exist = false)
    private String plantName;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String modelNumberName;


}
