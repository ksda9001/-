package com.trinity.service.impl;

import com.trinity.entity.Type;
import com.trinity.mapper.TypeMapper;
import com.trinity.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> findTypeAll() {
        return typeMapper.findTypeAll();
    }

    @Override
    public List<Type> findTypeByPage(Map<String, Object> map) {

        return typeMapper.findTypeByPage(map);
    }

    @Override
    public Type findTypeById(Integer id) {
        return typeMapper.findTypeById(id);
    }

    @Override
    public int addType(Type type) {
        type.setDate(new Date());
        type.setNumber(0);
        return typeMapper.insertType(type);
    }

    @Override
    public int deleteTypeById(Integer id) {
        return typeMapper.deleteTypeById(id);
    }

    @Override
    public int updateType(Type type) {
        type.setDate(new Date());
        return typeMapper.updateType(type);
    }
}
