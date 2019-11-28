package com.lyj.demo.controller;


import com.lyj.demo.domain.User;
import com.lyj.demo.listener.Bus;
import com.lyj.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用于测试的控制层
 * @author yingjie.lu
 * @date 2019/11/28 9:56 上午
 * @version 1.0
 **/
@Controller
@RequestMapping
public class TestController {
    @Autowired
    TestService testService;

    public TestController() {
    }

    @RequestMapping({"test"})
    @ResponseBody
    public String test() {
        this.testService.test();
        return "success";
    }

    @RequestMapping({"test2"})
    @ResponseBody
    public List<User> test2(String name) {
        List<User> users = this.testService.selectByUserName(name);
        return users;
    }

    @RequestMapping({"busTest"})
    @ResponseBody
    public String busTest(String name) {
        Bus.todoBus.post(name);
        return "success";
    }

    /**
     * 测试增加待办
     * @param name
     * @return
     */
    @RequestMapping({"testInsert"})
    @ResponseBody
    public String testInsert(String name) {
        this.testService.insert();
        return "success";
    }

    /**
     * 测试删除待办
     * @param id 待办id
     * @return
     */
    @RequestMapping({"testDelete"})
    @ResponseBody
    public String testDelete(String id) {
        this.testService.detele(id);
        return "success";
    }
}