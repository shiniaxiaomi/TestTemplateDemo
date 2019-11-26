package com.lyj.demo.service;


import com.lyj.demo.domain.User;
import com.lyj.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {


    @Autowired
    UserMapper userMapper;

    public void test(){
        User user = new User();
        user.setName("java");
        user.setAge(1);

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }


    public List<User> selectByUserName(String name) {
        List<User> users = userMapper.selectByUserName(name);
        return users;
    }
}
