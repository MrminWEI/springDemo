package com.example.demo.schedule;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * SaticScheduleTask
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/18
 */
@Configuration
@EnableScheduling
public class SaticScheduleTask {


  @Scheduled(cron = "0/5 * * * * ?")
  private void configureTasks() {
    System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
  }
}
