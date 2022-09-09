package com.mytest.pongservice.listener

import com.mytest.pongservice.entity.CommonResult
import junit.framework.TestListener
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.Instant

@SpringBootTest
@Slf4j
class FileListenerTest extends Specification {

    def "OnFileCreateTest:#testCase"() {
        expect:"init file"
        //Generate test files
        def epochMilli  = Instant.now().toEpochMilli()
        def epochMilliName  = epochMilli+".txt"
        def f = new File(path, epochMilliName)
        f.append(message)
        //call disruptorMqService
        CommonResult commonResult=new CommonResult();
        commonResult.setPath(path);
        commonResult.setFileName(epochMilliName);
        commonResult.setTimeStamp(epochMilli);
        disruptorMqService.fileRead(commonResult)

        where:"testCase:#,path:#path,message:#message"
        testCase                 || path          || message
        "normal"                 || "D:\\test\\"  || "hello"
        "Folder does not exist"  || "D:\\test1\\" || "hello"
        "File content is empty"  || "D:\\test\\"  ||  null
        "The file has a newline" || "D:\\test\\"  || "he\tllo"
    }
}
