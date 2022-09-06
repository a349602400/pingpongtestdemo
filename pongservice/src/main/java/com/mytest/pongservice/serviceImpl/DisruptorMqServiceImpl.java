package com.mytest.pongservice.serviceImpl;

import com.lmax.disruptor.RingBuffer;
import com.mytest.pongservice.entity.CommonResult;
import com.mytest.pongservice.event.MessageModel;
import com.mytest.pongservice.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;

import static java.nio.file.Files.readAllBytes;


@Slf4j
@Service
public class DisruptorMqServiceImpl implements DisruptorMqService {

    @Autowired
    private RingBuffer<MessageModel> messageModelRingBuffer;

    @Override
    public void sayHelloMq(String message) {
        log.info("生产消息: {}",message);
        //获取下一个Event槽的下标
        long sequence = messageModelRingBuffer.next();
        try {
            //给Event填充数据
            MessageModel event = messageModelRingBuffer.get(sequence);
            event.setMessage(message);
            log.info("往消息队列中添加消息：{}", event);
        } catch (Exception e) {
            log.error("failed to add event to messageModelRingBuffer for : e = {},{}",e,e.getMessage());
        } finally {
            //发布Event，激活观察者去消费，将sequence传递给该消费者
            //注意最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的producer
            messageModelRingBuffer.publish(sequence);
        }
    }

    @Override
    public boolean fileRead(CommonResult commonResult) {
        log.info("生产消息: {}",commonResult.toString());
        String url = commonResult.getUrl();
        //获取下一个Event槽的下标
        long sequence = messageModelRingBuffer.next();
        try {
            //给Event填充数据
            MessageModel event = messageModelRingBuffer.get(sequence);
            //event.setMessage(message);
            event.setUrl(commonResult.getUrl());
            event.setHostName(commonResult.getHostName());
            event.setTimeStamp(commonResult.getTimeStamp());
            log.info("往消息队列中添加消息：{}", event);
        } catch (Exception e) {
            log.error("failed to add event to messageModelRingBuffer for : e = {},{}",e,e.getMessage());
            return  false;
        } finally {
            //发布Event，激活观察者去消费，将sequence传递给该消费者
            //注意最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的producer
            messageModelRingBuffer.publish(sequence);
        }


        return true;
    }
}
