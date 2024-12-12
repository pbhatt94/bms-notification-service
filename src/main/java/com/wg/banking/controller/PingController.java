package com.wg.banking.controller;


import com.wg.banking.service.SQSMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;


@RestController
@EnableWebMvc
public class PingController {
    @Autowired
    private SQSMessageConsumer consumer;
    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public Map<String, String> ping() throws InterruptedException {
        Map<String, String> pong = new HashMap<>();
        pong.put("pong", "Hello, World!");
        consumer.consumeMessages();
        return pong;
    }
}
