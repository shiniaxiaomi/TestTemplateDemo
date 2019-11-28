package com.lyj.demo.service;


import com.lyj.demo.domain.User;
import com.lyj.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
    @Autowired
    UserMapper userMapper;

    public TestService() {
    }

    @Transactional
    public void test() {
        User user = new User();
        user.setName("java");
        user.setAge(1);
        user.setId(1);
        this.userMapper.updateByPrimaryKey(user);
    }

    @Transactional
    public void insert() {
        User user = new User();
        user.setName("java");
        user.setAge(1);
        user.setId(1);
        this.userMapper.insert(user);
    }

    public List<User> selectByUserName(String name) {
        List<User> users = this.userMapper.selectByUserName(name);
        return users;
    }

    @Transactional
    public void detele(String id) {
        this.userMapper.deleteByPrimaryKey(id);
    }
}

