package com.service.MainService.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String SCORING_EXCHANGE = "scoring.exchange";
    public static final String SCORING_QUEUE = "scoring.queue";
    public static final String SCORING_ROUTING_KEY = "scoring.routingKey";

    @Bean
    public Queue queue() {
        return new Queue(SCORING_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(SCORING_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SCORING_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}