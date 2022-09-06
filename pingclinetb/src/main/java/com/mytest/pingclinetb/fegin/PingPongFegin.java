package com.mytest.pingclinetb.fegin;

import com.mytest.pingclinetb.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "pong-service")
public interface PingPongFegin {
    @PostMapping("notifServer")
    public String notifServer(CommonResult commonResult);
}
