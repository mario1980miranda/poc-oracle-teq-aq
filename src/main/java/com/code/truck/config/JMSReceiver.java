package com.code.truck.config;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class JMSReceiver implements SessionAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSReceiver.class);

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        final TextMessage textMessage = (TextMessage) message;
        LOGGER.info("Message received : {}", textMessage.getText());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
