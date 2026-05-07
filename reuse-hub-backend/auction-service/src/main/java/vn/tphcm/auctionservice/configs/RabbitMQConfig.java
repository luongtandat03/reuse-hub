/*
 * @ (#) RabbitMQConfig.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.configs;
/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchanges.bid-exchange}")
    private String bidExchange;

    @Value("${rabbitmq.exchanges.notification-exchange}")
    private String notificationExchange;

    @Value("${rabbitmq.queues.notification-queue}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.notification}")
    private String routingKey;

    @Bean
    public FanoutExchange auctionUpdateExchange() {
        return new FanoutExchange(bidExchange);
    }

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(notificationExchange, true, false);
    }

    @Bean
    public Queue anonymouseQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange auctionUpdateExchange, Queue anonymouseQueue) {
        return BindingBuilder.bind(anonymouseQueue).to(auctionUpdateExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueue);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);

        return rabbitTemplate;
    }
}
