package com.th.system.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_dtu_plant")
public class SysFac {
    private Integer plantId;
    private String plantName;

}
