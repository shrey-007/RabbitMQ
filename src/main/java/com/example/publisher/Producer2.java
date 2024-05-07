package com.example.publisher;

import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer2 {
    private String exchange="demo_exchange";
    private String routingKey="routing2";

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER= LoggerFactory.getLogger(Producer2.class);

    public Producer2(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        // we will use rabbitTemplate to send message and this rabbitTemplate will internaly use the message convertor
        // to convert the user class to json and vice versa, we have provided message convertor to rabbit template in amqpTemplate method
        LOGGER.info(String.format("Json message sent -> %s",user.toString()));

        // rabbit template will send the user to the exchange and exchange will send it to the respective queue
        rabbitTemplate.convertAndSend(exchange,routingKey,user);
    }
}
