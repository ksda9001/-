package com.trinity.service.impl;

import com.commons.entity.Users;
import com.trinity.mapper.UserMapper;
import com.trinity.service.UserService;
import com.trinity.util.CONF_PASSWORD;
import com.trinity.util.SaltRandom;
import com.trinity.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //注册
    @Override
    public Boolean register(Users user, String sw) {
        //后端校验
        //确保 姓名，密码，电话，邮箱已输入
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserEmail()) || StringUtils.isEmpty(user.getUserPhone())) {
            return false;
        }
        //防止用户名重复
        //Empty != NULL
        //Controller层已进行相同校验，此处用于防止终止SQL造成出错
        //若为用户修改则不进行校验
        if (!sw.equals("adminEdit")) {
            if (userMapper.getUserByUsername(user.getUserName()) != null) {
                return false;
            }
        }
        //判断是否加盐，如没有则进行加盐
        if (user.getSalt() == null || user.getSalt().equals("")) {
            SaltRandom saltRandom = new SaltRandom();
            CONF_PASSWORD conf_password = new CONF_PASSWORD();
            XMLUtil xmlUtil = new XMLUtil();
            String salt = saltRandom.getRandomSalt();
            //设置加盐
            user.setSalt(salt);
            String password = conf_password.getPassword(user.getPassword(), user.getSalt(), xmlUtil.getText());
            //设置密码
            user.setPassword(password);
        }
        //数据预处理
        //设置用户权限,判断请求来源（注册则默认为用户）
        if (sw.equals("register")) {
            user.setRole("user");
        }
        if (sw.equals("adminEdit")) {
            try {
                Boolean bo = userMapper.EditUser(user);
                if (bo) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        //设置启停状态
        user.setUserStatus("enable");
        try {
            Boolean booleann = userMapper.addUser(user);
            if (booleann) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //登录
    @Override
    public Boolean login(String username, String password) {
        Users user = userMapper.getUserByUsername(username);
        //判断账户启停情况
        if (StringUtils.isEmpty(user) || user.getUserStatus().equals("disable")) {
            return false;
        }
        CONF_PASSWORD conf_password = new CONF_PASSWORD();
        //获取配置文件中的迭代次数
        XMLUtil xmlUtil = new XMLUtil();
        String realpassword = conf_password.getPassword(password, user.getSalt(), xmlUtil.getText());
        if (realpassword.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public Users selectUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Users selectUserByID(String userID) {
        return userMapper.getUserByID(userID);
    }

    @Override
    public Boolean updateUser(Users user) {
        return null;
    }

    @Override
    public List<Users> selectAllUsers() {
        return null;
    }

    @Override
    public Boolean deleteUserByID(String id) {
        return null;
    }

    @Override
    public Boolean deleteUserByUsername(String username) {
        return null;
    }
}
