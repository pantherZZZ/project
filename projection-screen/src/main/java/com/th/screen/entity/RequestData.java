package com.th.screen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhang bao
 * @Date 2022/3/31 15:37
 * @Description
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestData {
    public String type="loadUrl";
    public String url="www.sogou.com";
    public boolean persistent=true;
}
