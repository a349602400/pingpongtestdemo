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
    public boolean fileRead(CommonResult commonResult) {
        if(null==commonResult) return  false;
        log.info("Production news: {}",commonResult.toString());
        //Get the subscript of the next event slot
        long sequence = messageModelRingBuffer.next();
        try {
            //Fill event with data
            MessageModel event = messageModelRingBuffer.get(sequence);
            event.setPath(commonResult.getPath());
            event.setTimeStamp(commonResult.getTimeStamp());
            log.info("Add message to message queue...");
        } catch (Exception e) {
            log.error("failed to add event to messageModelRingBuffer for : e = {},{}",e,e.getMessage());
            return  false;
        } finally {
            //Issue an event, activate the observer to consume, and pass the sequence to the consumer
            //If a requested sequence is not submitted, subsequent publishing operations or other producers will be blocked
             messageModelRingBuffer.publish(sequence);
        }
        return true;
    }
}
