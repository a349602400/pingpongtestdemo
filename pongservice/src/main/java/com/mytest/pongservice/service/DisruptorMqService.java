package com.mytest.pongservice.service;

import com.mytest.pongservice.entity.CommonResult;

public interface DisruptorMqService {
    /**
     * 消息
     * @param message
     */
    void sayHelloMq(String message);
    boolean fileRead(CommonResult commonResult);
}
