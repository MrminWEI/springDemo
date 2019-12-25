package com.example.demo.webSocket;

import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocketConfig
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/16
 */
//@Configuration
public class WebSocketConfig {

  // @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }

}
