package handler;

import com.mytest.pongservice.PongServiceApplication;
import com.mytest.pongservice.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PongServiceApplication.class)
class FileReadEventHandlerTest {

    @Test
    void onEvent() {
    }

}