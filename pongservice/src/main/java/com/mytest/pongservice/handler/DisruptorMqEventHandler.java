package com.mytest.pongservice.handler;

import com.lmax.disruptor.EventHandler;
import com.mytest.pongservice.event.MessageModel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;

import static java.nio.file.Files.readAllBytes;

@Slf4j
public class DisruptorMqEventHandler implements EventHandler<MessageModel> {

    @Override
    public void onEvent(MessageModel event, long sequence, boolean endOfBatch) {
        try {
            //这里停止1000ms是为了确定消费消息是异步的
            //Thread.sleep(1000);
            log.info("消费者处理消息开始");
            if (event != null) {
                log.info("消费者消费的信息是：{}",event);
                try {
                    String str =  new String(readAllBytes(Paths.get(event.getUrl())));
                    long epochMilliNOW  = Instant.now().toEpochMilli();
                    long epochMilliNOWReduce = epochMilliNOW - event.getTimeStamp();
                    System.out.println("数据来自："+event.getHostName()+";文件名为："+event.getUrl()
                            +"；服务端读取文件内容为："+str +"时间戳之差为:"+epochMilliNOWReduce);
                } catch (IOException e) {
                    System.out.println("读取错误");
                }
            }
        } catch (Exception e) {
            log.info("消费者处理消息失败");
        }
        log.info("消费者处理消息结束");
    }
}
