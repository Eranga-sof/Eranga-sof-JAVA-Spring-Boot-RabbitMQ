package com.eranga.springbootrmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //queue properties
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueue;

    //exchange properties
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.jsonExchange.name}")
    private String jsonExchange;

    //routing properties
    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;
    @Value("${rabbitmq.jsonRoutingKey.name}")
    private String jsonRoutingKey;

    //spring for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    // spring for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange jsonExchange(){
        return new TopicExchange(jsonExchange);
    }

    // binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(jsonExchange()).with(jsonRoutingKey);
    }


    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
