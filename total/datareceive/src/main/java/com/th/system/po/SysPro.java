package com.th.system.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_dtu_type")
public class SysPro {
    private Integer typeId;
    private String typeName;

}
