package com.code.truck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JMSService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessageToJmsQueue(final String message) {
        jmsTemplate.convertAndSend("course_inscription_queue", message);
    }
}
