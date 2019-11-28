package com.lyj.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试消费mq的test队列
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/27 4:48 下午
 */
@Component
public class TestConsumer {

    @RabbitListener(queues = "test")
    public void processMessage(String content) {
        System.out.println(content);
    }
}