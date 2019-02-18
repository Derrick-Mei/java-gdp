package com.dkm.gdpartifact;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AaGdpNameApplication
{

    private static final String EXCHANGE_NAME = "Server-Gdp";
    public static final String QUEUE_NAME = "LogQueue-Gdp";

    public static void main(String[] args)
    {
        SpringApplication.run(AaGdpNameApplication.class, args);
    }

    // make new server
    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // make new queue
    @Bean
    public Queue appQueue()
    {
        return new Queue(QUEUE_NAME);
    }

    // bind queue to server
    @Bean
    public Binding declareBinding()
    {
        return BindingBuilder.bind(appQueue()).to(appExchange()).with(QUEUE_NAME);
    }

    // Need this to work with Json and msg queue
    @Bean
    public RabbitTemplate rt(final ConnectionFactory cf)
    {
        final RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(producerJackson2JsonMessageConverter());
        return rt;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

}
