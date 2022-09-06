package com.mytest.pongservice.controller;

import com.mytest.pongservice.entity.CommonResult;
import com.mytest.pongservice.service.DisruptorMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

import static java.nio.file.Files.readAllBytes;

@RestController
public class RecFileController {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @PostMapping("notifServer")
    public String notifServer(@RequestBody CommonResult commonResult){
        disruptorMqService.fileRead(commonResult);
        return "success";
    }
}
