package com.th.system.tcp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/4/26 10:54
 * @Description：
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DataOne {
    private int id;               //序号
    private String locus;         //路线
    private String distance;     //距离
    private String time;         //完成时间
    private String calorie;     //卡路里
}
