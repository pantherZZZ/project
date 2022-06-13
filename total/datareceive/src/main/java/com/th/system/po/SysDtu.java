package com.th.system.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("sys_dtu")
@AllArgsConstructor
@NoArgsConstructor
public class SysDtu implements Serializable {

    @TableId("DTUid")
    private Integer DTUid;//dtu id

    @TableField("dtuName")
    private String dtuName;

    @TableField("plantId")
    private Integer plantId;//工厂id

    @TableField("modelId")
    private Integer modelId;//结构物id

    @TableField(value = "equipmentId",exist = false)
    private Integer equipmentId;//设备id

    @TableField("typeId")
    private Integer typeId;

    @TableField("modelNumberId")
    private Integer modelNumberId;//型号id

    @TableField("time")
    private String time;

    @TableField("postId")
    private String postId;

    @TableField("status")
    private Integer status;


}
