package com.lyj.demo.listener.impl;

import com.alibaba.fastjson.JSON;
import com.lyj.demo.listener.BusListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * todo消息监听
 * @author yingjie.lu
 * @date 2019/11/28 9:58 上午
 * @version 1.0
 **/
@Component
public class TodoListener implements BusListener {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //同步消息队列，存储用于发往mq的消息
    public static Queue todoQueue = new ConcurrentLinkedQueue();

    public void listenHandler(Object o) {
        todoQueue.add(o);
    }

    @PostConstruct
    public void sendToMQ() {
        (new Thread(() -> {
            while(true) {
                if (todoQueue.size() != 0) {
                    Object poll = todoQueue.poll();
                    this.rabbitTemplate.convertSendAndReceive("test", JSON.toJSONString(poll));
                }
            }
        })).start();
    }
}
