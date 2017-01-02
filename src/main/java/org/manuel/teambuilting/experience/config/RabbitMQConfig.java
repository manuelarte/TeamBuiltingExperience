package org.manuel.teambuilting.experience.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss.SSSZ";

    @Value("${messaging.event.amqp.exchange}")
    private String exchangeName;

    @Value("${messaging.event.amqp.queue}")
    private String teamQueueName;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    public Queue queue() {
        return new Queue(teamQueueName, true);
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2Converter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean(name = "eventMessageConverter")
    public MessageConverter messageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        // Jackson deserialization point issue
        final ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        jsonObjectMapper.setDateFormat(new SimpleDateFormat(TIMESTAMP_FORMAT));
        converter.setJsonObjectMapper(jsonObjectMapper);
        return converter;
    }


}