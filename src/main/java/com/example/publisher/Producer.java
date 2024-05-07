package com.example.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private String exchange="demo_exchange";

    private String rountingKey="routing";

    // this object provides logs to identify bug
    private static final Logger LOGGER= LoggerFactory.getLogger(Producer.class);

    // This object is required to send message to the Exchange with the routing key
    private RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s ",message));

        // we passed exchange,routingKey and message object so that exchange can route the message to specific queue
        rabbitTemplate.convertAndSend(exchange,rountingKey,message);

    }



}
