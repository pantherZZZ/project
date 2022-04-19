package com.th.screen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author zhang bao
 * @Date 2022/3/16 11:51
 * @Description
 * @Version 1.0
 */
public interface ScreenTypeService {

    JSONObject projectionScreen(String screenId,String mediaId);

    JSONObject closeScreen(String screenId);
}
