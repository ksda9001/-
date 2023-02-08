package com.trinity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
    private String userID;
    private String userName;
    private String userSex;
    private String password;
    private String userhand;
    private String userAddress;
    private String userPhone;
    private String userQQ;
    private String userEmail;
    private String userCollection;
    private String userPayCard;
    private String userStatus;
    private String userLoginTime;
    private String userIP;
    private String userPayPassword;
    private String userRMB;
    //安全校验，加盐
    private String salt;
    //安全验证问题
    private String safeQuestion1;
    private String answer1;
    private String safeQuestion2;
    private String answer2;
    //用户权限
    private String role;
    private Date userBirthday;
}
