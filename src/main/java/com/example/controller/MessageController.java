package com.example.controller;

import com.example.publisher.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private Producer producer;

    public MessageController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    // message is comming in url parameter
    // localhost:8080/api/v1/publish?message=hello
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

}
