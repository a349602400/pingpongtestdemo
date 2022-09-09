package com.mytest.pongservice;

import com.mytest.pongservice.entity.CommonResult;
import com.mytest.pongservice.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spock.lang.Specification;
import spock.lang.Subject;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PongServiceApplication.class)
class PongserviceApplicationTests  {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @Test
    void fileRead(){
        CommonResult commonResult=new CommonResult();
        commonResult.setPath("D:\\test\\1662462808014.txt");
        commonResult.setTimeStamp(1662462808014L);
        commonResult.setFileName("1662462808014.txt");
        disruptorMqService.fileRead(commonResult);
    }

}
