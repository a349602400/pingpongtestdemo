package com.mytest.pongservice

import com.mytest.pongservice.entity.CommonResult
import com.mytest.pongservice.service.DisruptorMqService
import com.mytest.pongservice.serviceImpl.DisruptorMqServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback;
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@SpringBootTest
class DemoSpec extends Specification{

    //     @Subject
    @Autowired
    private DisruptorMqService disruptorMqService

    @Unroll
    @Rollback
    def "method6"() {
        given:
        CommonResult commonResult=new CommonResult();
        commonResult.setMessage(Message);
        commonResult.setHostName(HostName);
        commonResult.setUrl(Url);
        commonResult.setTimeStamp(TimeStamp);//Long.getLong(TimeStamp)
        when:
        commonResult !=null

        then:
        boolean ret =disruptorMqService.fileRead(commonResult);

        where:
        Message << ["你好", "你好", "你好"]
        HostName << ["张三", "李四", "赵五"]
        Url << ["D:\\test\\1662462808014.txt", "123", "D:\\test\\1662462834010.txt"]
        TimeStamp << [1662462808014, 123, 1662462834010]
    }

}
