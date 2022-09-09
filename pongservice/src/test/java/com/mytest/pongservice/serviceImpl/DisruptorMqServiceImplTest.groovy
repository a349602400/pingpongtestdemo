package com.mytest.pongservice.serviceImpl

import com.mytest.pongservice.entity.CommonResult
import com.mytest.pongservice.service.DisruptorMqService
import lombok.extern.slf4j.Slf4j
import org.apache.juli.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Instant

@SpringBootTest
@Slf4j
class DisruptorMqServiceImplTest extends Specification {

    @Autowired
    private DisruptorMqService disruptorMqService

    @Unroll
    @Rollback
    def "testFileRead:#testCase"() {
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
