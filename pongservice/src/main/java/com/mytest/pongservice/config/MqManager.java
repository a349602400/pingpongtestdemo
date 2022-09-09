package com.mytest.pongservice.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.mytest.pongservice.event.MessageModel;
import com.mytest.pongservice.factory.DisruptorMqEventFactory;
import com.mytest.pongservice.handler.DisruptorMqEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class MqManager {

    @Bean("messageModel")
    public RingBuffer<MessageModel> messageModelRingBuffer() {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        //Specify event factory
        DisruptorMqEventFactory factory = new DisruptorMqEventFactory();

        //The specified ringbuffer byte size must be the nth power of 2 (modular operation can be converted to bit operation to improve efficiency), otherwise efficiency will be affected
        int bufferSize = 1024 * 256;

        //Single thread mode for extra performance
        Disruptor<MessageModel> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());

        //Set event business processor consumer
        disruptor.handleEventsWith(new DisruptorMqEventHandler());

        // Start the disruptor thread
        disruptor.start();

        //Obtain ringbuffer ring, which is used to receive events produced by the producer
        RingBuffer<MessageModel> ringBuffer = disruptor.getRingBuffer();

        return ringBuffer;
    }
}

