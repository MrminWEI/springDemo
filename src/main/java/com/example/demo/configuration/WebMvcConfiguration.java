package com.example.demo.configuration;

import com.example.demo.filter.CustomerAuthFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * WebMvcConfiguration
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/23
 */
@Configuration
public class WebMvcConfiguration {

  @Bean
  public Filter customerAuthFilter() {
    return new CustomerAuthFilter();
  }

  @Bean
  public FilterRegistrationBean filterRegistrationCustomer() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new DelegatingFilterProxy("customerAuthFilter"));
    registrationBean.addUrlPatterns("/system/*");
    registrationBean.setName("customerAuthFilter");
    return registrationBean;
  }
}
