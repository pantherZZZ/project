package com.ttwl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author zhang bao
 * @Date 2022/5/26 13:51
 * @Description： 系统图片表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId
    private Integer id;

    private String pictureName;

    private String picturePath;

    private Timestamp createTime;

    private Timestamp updateTime;

}
