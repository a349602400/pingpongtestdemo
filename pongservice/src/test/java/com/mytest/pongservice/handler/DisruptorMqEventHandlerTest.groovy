package com.mytest.pongservice.handler

import com.mytest.pongservice.event.MessageModel
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Instant
@SpringBootTest
class DisruptorMqEventHandlerTest extends Specification {

    @Unroll
    @Rollback
    def "OnEventTest"() {
        expect:"init file"
        //Generate test files
        def epochMilli  = Instant.now().toEpochMilli()
        def epochMilliName  = epochMilli+".txt"
        def f = new File(path, epochMilliName)
        f.append(message)

        DisruptorMqEventHandler  handler=new DisruptorMqEventHandler()
        MessageModel event=new MessageModel()
        event.setPath(f.getPath())
        event.setTimeStamp(epochMilli)
        handler.onEvent(event,1L,true)
        where:"testCase:#,path:#path,message:#message"
        testCase                 || path          || message
        "normal"                 || "D:\\test\\"  || "hello"
        "Folder does not exist"  || "D:\\test1\\" || "hello1"
        "File content is empty"  || "D:\\test\\"  ||  null
        "The file has a newline" || "D:\\test\\"  || "he\tllo"

    }
}
