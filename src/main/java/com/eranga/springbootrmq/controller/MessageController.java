package com.eranga.springbootrmq.controller;

import com.eranga.springbootrmq.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMQProducer rabbitMQProducer;
    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }


    @GetMapping("/publisher")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return  ResponseEntity.ok("Message Sent to RabbitMQ Successfully");
    }

}
