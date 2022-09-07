package com.mytest.pongservice.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    private String state;
    private String url;
    private String fileName;
    private String hostName;
    private long timeStamp;
}
