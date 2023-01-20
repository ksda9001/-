package com.trinity.service;

import com.trinity.entity.Users;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Users> getAllManagers();

    List<Users> selectNSearch(Map<String, Object> search);

    int editStatus(List<String> IDList, String status);
}
