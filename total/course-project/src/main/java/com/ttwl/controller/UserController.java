package com.ttwl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttwl.pojo.User;
import com.ttwl.service.UserService;
import com.ttwl.until.JWTUtil;
import com.ttwl.until.PasswordUtil;
import com.ttwl.until.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author zhang bao
 * @Date 2022/5/25 13:24
 * @Description：
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public R login(String username, String password) {
        User user = new User();
//        user.setId(1);
//        user.setUsername("admin");
//        user.setPassword(PasswordUtil.encrypt(username, password, "asdf"));
//        user.setSalt("asdf");


        if (user == null) return R.error(204, "用户不存在!");

//        password = PasswordUtil.encrypt(username, password, user.getSalt());
        if (!user.getPassword().equals(password)) return R.error(204, "密码错误！");

        String token = JWTUtil.createToken(username);
        return R.ok().put("token",token);

    }


    /**
     * 新增用户
     *
     * @param user  用户信息
     * @return
     */
    @PostMapping("insertUser")
    public R insertUser(User user) {
        log.info("[{}] User:{} {} Role:{}", user.getId(), user.getUsername(), user.getPassword(), user.getRoleId());
        int result = userService.insertUser(user);
        if (result > 0) {
            return R.ok();
        }
        return R.error("新增失败!");
    }

    /**
     * 删除用户
     *
     * @param id  用户id
     * @return
     */
    @PostMapping("deleteUserById")
    public R deleteUserById(Integer id) {
        log.info("id:{}", id);
        int result = userService.deleteUserById(id);
        if (result > 0) {
            return R.ok();
        }
        return R.error("删除失败!");
    }

    /**
     * 修改用户
     *
     * @param user  用户信息
     * @return
     */
    @PostMapping("updateUser")
    public R updateUser(User user) {
        log.info("User：{}", user.getId());
        int result = userService.updateUser(user);
        if (result > 0) {
            return R.ok();
        }
        return R.error("更新失败!");
    }
}