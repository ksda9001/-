package com.trinity.mapper;

import com.commons.entity.Users;
import org.apache.ibatis.annotations.Mapper;

//由于需要连表查询，不使用mybatis-plus
@Mapper
public interface UserMapper {
    Users getUserByUsername(String username);

    Boolean addUser(Users user);

    Boolean EditUser(Users user);

    Users getUserByID(String userID);
}
