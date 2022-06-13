package com.th.system.tcp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/4/24 11:18
 * @Description： 一级
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Reture {
    private String success;
    private TwoData twoData;
    private String code;
    private String errMsg;
}
