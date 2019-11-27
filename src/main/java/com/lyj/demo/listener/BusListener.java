package com.lyj.demo.listener;

import com.google.common.eventbus.Subscribe;

/**
 * 消息监听接口
 * @author yingjie.lu
 * @date 2019/11/27 2:26 下午
 * @version 1.0
 **/
public interface MQListener {

    @Subscribe
    void handler(Object o);

}
