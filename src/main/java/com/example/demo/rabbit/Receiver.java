package com.example.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * Receiver
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
//@Component
//@RabbitListener(queues = "direct")
public class Receiver {

  @RabbitHandler
  public void handler(String message) {
    System.out.println("接收消息：" + message);
  }
}
