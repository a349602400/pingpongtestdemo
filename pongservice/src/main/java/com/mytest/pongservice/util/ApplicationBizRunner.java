package com.mytest.pongservice.util;

import com.mytest.pongservice.factory.FileListenerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@Slf4j
public class ApplicationBizRunner implements ApplicationRunner {

    @Autowired
    FileListenerFactory fileListenerFactory;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileAlterationMonitor monitor = fileListenerFactory.getMonitor();
        monitor.start();
        System.out.println("***************Monitoring starts***************");
    }
}
