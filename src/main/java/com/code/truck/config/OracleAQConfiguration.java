package com.code.truck.config;

import jakarta.jms.JMSException;
import jakarta.jms.QueueConnectionFactory;
import oracle.jakarta.jms.AQjmsFactory;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class OracleAQConfiguration {

    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pass;
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setUser(user);
        ds.setPassword(pass);
        ds.setURL(dbUrl);
        ds.setImplicitCachingEnabled(Boolean.TRUE);
        return ds;
    }

    @Bean
    public QueueConnectionFactory queueConnectionFactory(DataSource dataSource) throws JMSException {
        return AQjmsFactory.getQueueConnectionFactory(dataSource);
    }
}
