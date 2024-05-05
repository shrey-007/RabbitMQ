package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "demo_queue")
    // whenever a new message comes into demo_queue queue then this consume methos will run
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s",message));


    }
}
