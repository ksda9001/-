package com.trinity.config;

import com.commons.entity.Users;
import com.trinity.service.UserService;
import com.trinity.util.CONF_PASSWORD;
import com.trinity.util.XMLUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Realm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        Users userEntity = userService.selectUserByUsername(username);
        //查询用户的角色
        //TODO 可能存在设计失误
        String role = userEntity.getRole();
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add(role);
        info.setStringPermissions(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        //判断是否存在用户
        Users userEntity = userService.selectUserByUsername(username);
        if (userEntity == null) {
            return null;
        }
        String pass = userEntity.getPassword();
        //传入密码与数据库校验
        CONF_PASSWORD conf_password = new CONF_PASSWORD();
        XMLUtil xmlUtil = new XMLUtil();
        //此处进行了二次密码检验
        if (conf_password.getPassword(password, userEntity.getSalt(), xmlUtil.getText()).equals(pass)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        return null;
    }
}