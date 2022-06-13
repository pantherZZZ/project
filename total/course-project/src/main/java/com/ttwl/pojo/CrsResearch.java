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
 * @Date 2022/5/31 14:31
 * @Description： 研学表
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("crs_research")
public class CrsResearch implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    @TableId
    private Integer id;
    private Integer userId;
    private String researchName;
    private String researchTitle;
    private String researchStatus;
    private Double researchPrice;
    private String researchLocation;
    private Timestamp researchOpeningTime;
    private Integer picturesId;
    private Timestamp createTime;
    private Timestamp updateTime;
}
