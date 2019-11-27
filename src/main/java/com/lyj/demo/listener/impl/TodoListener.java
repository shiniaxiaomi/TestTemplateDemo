package com.lyj.demo.listener;

import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/27 2:47 下午
 */

@Component
public class TodoListener implements BusListener{

    @Override
    public void listenHandler(Object o) {
        System.out.println("xiaoxi:"+o);
    }

    public static void main(String[] args) throws IOException {

        while (true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Bus.todoBus.post(reader.readLine());
        }

    }
}
