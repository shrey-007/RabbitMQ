package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // spring bean for RabbitMQ queue(queue handling String messages)
    @Bean
    public Queue queue(){
        return new Queue("demo_queue");
    }

    // spring bean for RabbitMQ queue(queue handling Json messages)
    @Bean
    public Queue queue2(){
        return new Queue("demo_queue2");
    }

    // spring bean for RabbitMQ exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("demo_exchange");
    }

    @Bean
    // spring bean for binding between queue and exchange, using routing key "routing"
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with("routing");
    }

    @Bean
    // spring bean for binding between queue2 and exchange, using routing key "routing2"
    public Binding binding2(){
        return BindingBuilder.bind(queue2())
                .to(exchange())
                .with("routing2");
    }

    // Intially we were sending only String messages, but now we are sending JSON message so we need to configure these
    // amqpTemplate and MessageConverter
    // It converts User class to Json and vice versa
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
