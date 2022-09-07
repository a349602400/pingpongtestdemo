package com.mytest.pingclineta.entity;

import lombok.*;

@Getter
@Setter
@ToString
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
