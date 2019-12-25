package com.example.demo.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MyRabbitmqController
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
//@RestController
//@RequestMapping("/rabbitmq")
public class MyRabbitmqController {

  @Autowired
  Sender sender;

  @RequestMapping("/sender")
  @ResponseBody
  public String sender() {
    System.out.println("send string:hello world");
    sender.send("hello world");
    return "sending...";
  }
}
