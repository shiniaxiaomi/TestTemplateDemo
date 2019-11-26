package com.lyj.demo.controller;


import com.lyj.demo.domain.User;
import com.lyj.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class TestController {

    @Autowired
    TestService testService;


    @RequestMapping("test")
    @ResponseBody
    public String test(){
        testService.test();
        return "success";
    }

    @RequestMapping("test2")
    @ResponseBody
    public List<User> test2(String name){
        List<User> users = testService.selectByUserName(name);
        return users;
    }

}
