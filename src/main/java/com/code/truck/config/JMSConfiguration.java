package com.code.truck.config;

import jakarta.jms.ConnectionFactory;
import oracle.jakarta.jms.AQjmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.sql.DataSource;

@Configuration
public class JMSConfiguration {

    private static final String QUEUE_NAME_READ = "course_inscription_queue";

    @Autowired
    private JMSReceiver jmsReceiver;

    @Bean
    public AQjmsConnectionFactory connectionFactory(@Autowired DataSource dataSource) throws Exception {
        final AQjmsConnectionFactory connectionFactory = new AQjmsConnectionFactory();
        connectionFactory.setDatasource(dataSource);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(@Autowired AQjmsConnectionFactory connectionFactory) {
        final JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer messageListenerContainer(
            ConnectionFactory connectionFactory,
            DataSource dataSource) {

        final DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setDestinationName(QUEUE_NAME_READ);
        dmlc.setSessionTransacted(Boolean.TRUE);
        dmlc.setConnectionFactory(connectionFactory);

        final DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);

        dmlc.setTransactionManager(manager);
        dmlc.setMessageListener(jmsReceiver);

        return dmlc;
    }

}
