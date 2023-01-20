package com.trinity.mapper;

import com.trinity.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {
    List<Users> getAllManagers();

    List<Users> selectNSearch(Map<String, Object> search);

    int editStatus(List<String> IDList, String status);
}
