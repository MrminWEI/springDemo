package com.example.demo.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * DirectRabbitConfig
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
//@Configuration
public class DirectRabbitConfig {

  @Bean
  public Queue TestDirectQueue() {
    return new Queue("TestDirectQueue", true);
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("direct", false, false);//交换器名称、是否持久化、是否自动删除
  }

  @Bean
  Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("direct");
  }

}
