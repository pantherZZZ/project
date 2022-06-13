package com.ttwl.service;

import com.ttwl.pojo.User;

/**
 * @Author zhang bao
 * @Date 2022/5/26 10:52
 * @Description：
 * @Version 1.0
 */
public interface UserService {

    int insertUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);
}
