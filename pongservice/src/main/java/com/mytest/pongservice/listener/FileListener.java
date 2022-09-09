package com.mytest.pongservice.listener;


import com.mytest.pongservice.entity.CommonResult;
import com.mytest.pongservice.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;


@Slf4j
@Component
public class FileListener extends FileAlterationListenerAdaptor {
    @Autowired
    DisruptorMqService disruptorMqService;

    /**
     * File creation execution
     */
    public void onFileCreate(File file) {
        log.info("[newly build]:" + file.getAbsolutePath());
        // Execute business logic
        CommonResult commonResult = new CommonResult();
        try {
            String path = file.getPath();
            String getAFileName = path.substring(path.lastIndexOf("\\") + 1);
            Long timeStamp = Long.parseLong(getAFileName.substring(0, getAFileName.lastIndexOf(".")));
            commonResult.setPath(path);
            commonResult.setFileName(getAFileName);
            commonResult.setTimeStamp(timeStamp);
            disruptorMqService.fileRead(commonResult);
            log.info("New write complete\t");
        }catch (Exception e){
            e.printStackTrace();
            //noting todo
        }
    }

    /**
     * File creation and modification
     */
    public void onFileChange(File file) {
        log.info("[modify]:" + file.getAbsolutePath());
    }

}