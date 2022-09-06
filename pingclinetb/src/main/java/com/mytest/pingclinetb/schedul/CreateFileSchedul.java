package com.mytest.pingclinetb.schedul;

import com.mytest.pingclinetb.entity.CommonResult;
import com.mytest.pingclinetb.fegin.PingPongFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class CreateFileSchedul {

    @Autowired
    PingPongFegin pingPongFegin;

    //秒 分 时 日 月 年 周几  //0-7 每一天
    @Scheduled(cron = "*/1 * * * * ?")  //什么时候执行
    public void createFile(){
        String fileName = "D:\\test\\";
        File file=new File(fileName);
        if(!file.exists()){
            file.mkdir();
        }
        long epochMilli  = Instant.now().toEpochMilli();
        String url= fileName+epochMilli +".txt";
        Path path = Paths.get(url);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("Hello");
        }catch (Exception e){
            System.out.println("写入错误");
        }

        try {
            CommonResult result=new CommonResult();
            result.setState("success");
            result.setHostName("ping-service-b");
            result.setUrl(url);
            result.setTimeStamp(epochMilli);
            String ret = pingPongFegin.notifServer(result);
        }catch (Exception e){
            System.out.println("通讯出错");
        }
    }

}
