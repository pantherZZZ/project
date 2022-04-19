package com.th.screen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:39
 * @Description
 * @Version 1.0
 */
@TableName("program_media_library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramMediaLibrary {

    @TableId("id")
    private int id;

    private String fileUrl;

    @TableField("name")
    private String name;

    @TableField("sort")
    private int sort;

    @TableField("volume")
    private int volume;

    private String fileType;

    private String createdAt;

    private String updateAt;

    private String textData;

    private String textColor;

    private String textFont;
}
