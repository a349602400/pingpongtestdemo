package com.mytest.pingclinetc.controller;

import com.mytest.pingclinetc.fegin.PingPongFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @Autowired
    PingPongFegin pingPongFegin;

    @GetMapping("pingPong")
    public String pingPong(){
        return "pingPong测试";
    }
}
