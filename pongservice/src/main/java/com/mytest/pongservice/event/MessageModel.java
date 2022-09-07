package com.mytest.pongservice.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.security.PublicKey;

@Getter
@Setter
@ToString
public class MessageModel {
    private  String url;
    private  String hostName;
    private  String message;
    private long timeStamp;
}
