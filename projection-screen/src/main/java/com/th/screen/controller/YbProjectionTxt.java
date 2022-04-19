package com.th.screen.controller;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author zhang bao
 * @Date 2022/4/13 10:47
 * @Description
 * @Version 1.0
 */
@RestController
public class YbProjectionTxt {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("getToken")
    public JSONObject showLeaderboard(){
        String token = getToken();
        String url ="http://jgw.taiott.com/api/dtuTotal/findFactory";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token",token);
        ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<Map>(headers), String.class);
         return JSONUtil.parseObj(response.getBody());

    }


    public String getToken() {
        String url = "http://jgw.taiott.com/api/login/login?userName=admin&password=admin";
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, null, JSONObject.class);
        String token = null;
        if (responseEntity.getStatusCodeValue() == 200) {
            JSONObject body = responseEntity.getBody();
            for (String s : body.keySet()) {
                if ("data".equals(s)) {
                    Object o = body.get(s);
                    String s1 = o.toString();
                    String substring = s1.substring(0, s1.length() - 1);
                    int i = substring.indexOf(":");
                    int i1 = substring.indexOf(":", i + 1);
                    int i2 = substring.indexOf(":", i1 + 1);
                    token = substring.substring(i2+2, substring.length() - 1);
                }

            }
            System.out.println(body);
        }
        return token;
    }

    @PostMapping("/file")
    public boolean create(){
        com.alibaba.fastjson.JSONObject jsonData =new com.alibaba.fastjson.JSONObject();
        jsonData.put("first","张三");
        jsonData.put("two","李四");
        String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        String filePath ="c:/demo/demo.txt";
        try {
            File file = new File(filePath);
            // 创建上级目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // 如果文件存在，则删除文件
            if (file.exists()) {
                file.delete();
            }
            // 创建文件
            file.createNewFile();
            // 写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            write.write(content);
            write.flush();
            write.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
