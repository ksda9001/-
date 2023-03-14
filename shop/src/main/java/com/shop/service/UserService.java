package com.shop.service;


import com.commons.entity.Users;

import java.util.List;

public interface UserService {
    //用户注册
    Boolean register(Users user, String sw);

    //用户登录
    Boolean login(String username, String password);

    //通过用户名选择用户
    Users selectUserByUsername(String username);

    //通过ID选择用户
    Users selectUserByID(String id);

    //更新用户信息
    Boolean updateUser(Users user);

    //查找所有用户信息
    List<Users> selectAllUsers();

    //通过ID删除用户
    Boolean deleteUserByID(String id);

    //通过用户名删除用户
    Boolean deleteUserByUsername(String username);
}
