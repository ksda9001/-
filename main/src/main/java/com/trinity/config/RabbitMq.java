package com.trinity.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMq {

    @Bean("A")
    public Queue queue1() {
        return new Queue("A");
    }

    @Bean("B")
    public Queue queue2() {
        return new Queue("B");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("chatExchange");
    }


    @Bean
    public Binding bindA(@Qualifier("A") Queue queue, DirectExchange e) {
        return BindingBuilder.bind(queue).to(e).with("A");
    }

    @Bean
    public Binding bindB(@Qualifier("B") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("B");
    }
}
