package com.mytest.pongservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommonResult {
    private String message;
    private String path;
    private String fileName;
    private long timeStamp;
}
