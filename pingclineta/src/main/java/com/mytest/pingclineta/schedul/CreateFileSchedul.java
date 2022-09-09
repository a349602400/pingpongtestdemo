package com.mytest.pingclineta.schedul;


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


    //Seconds, minutes, hours, days, months, years, weeks / / 0-7 (every day)
    @Scheduled(cron = "*/1 * * * * ?")
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
            System.out.println("write error");
        }
    }

}
