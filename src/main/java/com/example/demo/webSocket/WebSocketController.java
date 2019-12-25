package com.example.demo.webSocket;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocketController
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/16
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

  /**
   * 群发消息内容
   */
  @RequestMapping(value = "/sendAll", method = RequestMethod.GET)
  public String sendAllMessage(@RequestParam(required = true) String message) {
    try {
      WebSocketServer.BroadCastInfo(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "ok";
  }

  /**
   * 指定会话ID发消息
   *
   * @param message 消息内容
   * @param id 连接会话ID
   */
  @RequestMapping(value = "/sendOne", method = RequestMethod.GET)
  public String sendOneMessage(@RequestParam(required = true) String message,
      @RequestParam(required = true) String id) {
    try {
      WebSocketServer.SendMessage(message, id);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "ok";
  }
}
