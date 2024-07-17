package com.code.truck.controller;

import com.code.truck.service.JMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscriptions")
public class JMSController {

    @Autowired
    private JMSService jmsService;

    @PostMapping
    public void sendMessageToJmsQueue(@RequestParam final String message) {
        jmsService.sendMessageToJmsQueue(message);
    }
}
