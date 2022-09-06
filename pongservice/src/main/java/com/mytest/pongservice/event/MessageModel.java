package com.mytest.pongservice.event;

import lombok.Data;

import java.security.PublicKey;

@Data
public class MessageModel {
    private  String url;
    private  String hostName;
    private  String message;
    private long timeStamp;
}
