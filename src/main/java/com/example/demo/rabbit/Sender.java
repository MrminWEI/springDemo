package com.example.demo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Sender
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
//@Component
public class Sender {

  @Autowired
  AmqpTemplate amqpTemplate;

  public void send(String message) {
    System.out.println("发送消息：" + message);
    amqpTemplate.convertAndSend("direct", message);
  }
}
