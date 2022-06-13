package com.th.screen.controller;

import com.alibaba.fastjson.JSONObject;
import com.th.screen.entity.ScreenTwo;
import com.th.screen.service.ScreenTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhang bao
 * @Date 2022/3/10 10:09
 * @Description  投屏/
 * @Version 1.0
 */
@RestController
@RequestMapping("screen")
public class ScreenTypeController {

     @Autowired
     private ScreenTypeService screenTypeService;

     @PostMapping("projectionScreen")
     public JSONObject projectionScreen(String screenId,String mediaId){
          return screenTypeService.projectionScreen(screenId,mediaId);
     }

     @PostMapping("closeScreen")
     public JSONObject closeScreen(String screenId){
          return screenTypeService.closeScreen(screenId);
     }
}
