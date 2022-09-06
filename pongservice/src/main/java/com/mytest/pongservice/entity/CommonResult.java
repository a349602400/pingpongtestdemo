package com.mytest.pongservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
