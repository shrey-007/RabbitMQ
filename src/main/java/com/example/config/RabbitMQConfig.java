package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // spring bean for RabbitMQ queue
    @Bean
    public Queue queue(){
        return new Queue("demo_queue");
    }

    // spring bean for RabbitMQ exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("demo_exchange");
    }

    @Bean
    // spring bean for binding between queue and exchange, using routing key
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with("routing");
    }
}
