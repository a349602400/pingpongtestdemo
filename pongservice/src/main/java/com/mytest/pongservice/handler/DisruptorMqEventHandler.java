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
            //log.info("Consumer processing message started");
            if (event != null) {
                log.info("Consumer consumption information is：{}",event);
                try {
                    String str =  new String(readAllBytes(Paths.get(event.getPath())));
                    long epochMilliNOW  = Instant.now().toEpochMilli();
                    long epochMilliNOWReduce = epochMilliNOW - event.getTimeStamp();
                    System.out.println("File name："+event.getPath()
                            +"；The content of the file read by the server is："+str
                            +"The difference between time stamps is:"+epochMilliNOWReduce);
                    log.info("The file name and content read are：{}",event.getPath()+"--"+str);
                    log.info("The time difference from generation to reading is:{}",epochMilliNOWReduce);
                } catch (IOException e) {
                    System.out.println("Read error");
                }
            }
        } catch (Exception e) {
            log.info("Consumer failed to process message");
        }
        log.info("End of consumer processing message");
    }
}
