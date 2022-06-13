package com.th.login.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.th.system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysUser;
import com.th.system.service.SysUserService;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.utils.MD5;


@RestController
@RequestMapping("/login")
public class LoginController extends HttpClient {

//	private static final long EXPIRE_TIME = 180 * 60 * 1000;

//	private static final String TOKEN_SECRET = "thefirsttoken123";

    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = "/loginOut", produces = "application/json;charset=UTF-8")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userid");
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("result", "OK");
        data.put("code", 200);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "成功");
        return JsonUtil.writeAsString(message);
    }

    //登录
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request, String userName, String password) {
        SysUser user = sysUserService.loginUser(userName, MD5.getMD5(password));
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        String token = null;
        if (user != null) {
//			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//			Algorithm algorithm;
            try {
                token = JwtUtil.createToken(String.valueOf(user.getUserId()));
                Integer userId = JwtUtil.parseToken(token);

                HttpSession session = request.getSession();
                session.setAttribute("userid", userId);
                session.setAttribute("role", user.getRole());
                session.setMaxInactiveInterval(24 * 60 * 60);
                data.put("code", 200);
                data.put("role", user.getRole());
                data.put("token", token);


                message.put("data", data);
                message.put("status", 200);
                message.put("message", "成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            data.put("code", 201);
            data.put("token", token);
            message.put("data", data);
            message.put("status", 200);
            message.put("message", "登录密码或用户名错误!");
        }
        return JsonUtil.writeAsString(message);
    }
}
