package com.trinity.service.impl;

import com.trinity.entity.Users;
import com.trinity.mapper.AdminMapper;
import com.trinity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Users> getAllManagers() {
        return adminMapper.getAllManagers();
    }

    @Override
    public List<Users> selectNSearch(Map<String, Object> search) {
        return adminMapper.selectNSearch(search);
    }

    @Override
    public int editStatus(List<String> IDList, String status) {
        return adminMapper.editStatus(IDList, status);
    }
}
