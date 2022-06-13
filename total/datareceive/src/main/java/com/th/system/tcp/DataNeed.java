package com.th.system.tcp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/4/24 10:53
 * @Description： 三级
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DataNeed {
    private String id;               //序号
    private String locus;         //路线
    private String speed;         //速度
    private String distance;     //距离
    private String time;         //完成时间
    private String calorie;     //卡路里
}

//对象里面4参  -> data里面 20参 -> list需要 17参  navigatepageNums-> 导航页面号码