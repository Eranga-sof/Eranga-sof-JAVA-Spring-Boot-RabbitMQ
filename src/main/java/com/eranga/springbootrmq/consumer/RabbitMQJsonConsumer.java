package com.eranga.springbootrmq.consumer;

import com.eranga.springbootrmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);


    @RabbitListener(queues = {"${rabbitmq.jsonQueue.name}"})
    public void jsonConsumer(User user){
        LOGGER.info(String.format("Read Json Message -> %s", user.toString()));
    }
}