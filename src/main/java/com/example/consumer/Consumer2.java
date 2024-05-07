package com.example.consumer;

import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer2 {
    private static final Logger LOGGER= LoggerFactory.getLogger(Consumer2.class);

    @RabbitListener(queues = "demo_queue2")
    public void consume(User user){
        LOGGER.info(String.format("Received JSON message -> %s",user.toString()));

    }

}
