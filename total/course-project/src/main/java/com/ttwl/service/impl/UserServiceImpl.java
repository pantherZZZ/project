package com.ttwl.service.impl;

import com.ttwl.dao.UserMapper;
import com.ttwl.pojo.User;
import com.ttwl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhang bao
 * @Date 2022/5/26 10:52
 * @Description：
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        User crsUser = new User();
        if (user.getPictureId()!=null){
            crsUser.setPictureId(user.getPictureId());
        }
        crsUser.setUsername(user.getUsername());
        crsUser.setPassword(user.getPassword());
        crsUser.setUserPhoneNumber(user.getUserPhoneNumber());
        crsUser.setUserBelongingSchool(user.getUserBelongingSchool());
        crsUser.setUserStudentNumber(user.getUserStudentNumber());
        crsUser.setUserAddress(user.getUserAddress());
        crsUser.setRoleId(user.getRoleId());
        return userMapper.insert(crsUser);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateUser(User user) {
        User crsUser = new User();
        crsUser.setUsername(user.getUsername());
        crsUser.setPassword(user.getPassword());
        crsUser.setUserPhoneNumber(user.getUserPhoneNumber());
        crsUser.setUserBelongingSchool(user.getUserBelongingSchool());
        crsUser.setUserStudentNumber(user.getUserStudentNumber());
        crsUser.setUserAddress(user.getUserAddress());
        crsUser.setRoleId(user.getRoleId());
        crsUser.setPictureId(user.getPictureId());
        return userMapper.updateById(crsUser);
    }
}
