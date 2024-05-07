package com.example.controller;

import com.example.model.User;
import com.example.publisher.Producer2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {
    private Producer2 producer;

    public JsonMessageController(Producer2 producer) {
        this.producer = producer;
    }

    @PostMapping("/publish2")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        producer.sendJsonMessage(user);

        return ResponseEntity.ok("Json message sent to RabbitMq");

    }
}
