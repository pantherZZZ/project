package com.th.system.tcp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/4/26 10:26
 * @Description：
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DataTwo {
    private int id;               //序号
    private String locus;         //路线
    private String speed;         //速度
    private String time;         //完成时间
    private String calorie;     //卡路里
}
