package com.lyj.demo.listener;

import com.google.common.eventbus.EventBus;
import com.lyj.demo.listener.impl.TodoListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息总线
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/27 3:14 下午
 */
@Component
public class Bus {
    public static EventBus todoBus = new EventBus();//todo消息总线

    @Autowired
    TodoListener todoListener;//自动注入todo消息监听

    @PostConstruct
    public void init() {
        todoBus.register(this.todoListener);//注册todo消息监听
    }
}

