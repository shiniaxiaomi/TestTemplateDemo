package com.lyj.demo.listener;

import com.google.common.eventbus.Subscribe;

/**
 * 消息监听接口
 * @author yingjie.lu
 * @date 2019/11/27 2:26 下午
 * @version 1.0
 **/
public interface BusListener {

    @Subscribe
    void listenHandler(Object o);//监听到消息后的处理函数

}
