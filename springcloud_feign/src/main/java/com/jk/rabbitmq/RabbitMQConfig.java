package com.jk.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //标识为RabbitMQ配置文件类
public class RabbitMQConfig {

    @Bean
    public Queue queueMessage(){
        // 参数为自定义队列名称
        return new Queue("1906-wyh");
    }
}
