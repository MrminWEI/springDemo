package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.jwt.JwtAuthenticationRequest;
import com.example.demo.jwt.JwtAuthenticationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HelloController Tester.
 *
 * @author minwei
 * @version 1.0
 * @since <pre>11/18/2019</pre>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    MockMvc mvc;

    @Before
    public void before() {
        System.out.println("this is before...");
    }

    @After
    public void after() {
        System.out.println("this is after...");
    }

    /**
     * Method: login(String username, String password)
     */
    @Ignore
    public void testLogin() {
        try {
            JwtAuthenticationRequest jwtAuthenticationRequest=new JwtAuthenticationRequest();
            jwtAuthenticationRequest.setUsername("super");
            jwtAuthenticationRequest.setPassword("z");
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/user/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(jwtAuthenticationRequest));
            String responseString = mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                   // .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                  //  .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            System.out.println("result:" + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserInfo(){
        try {
            JwtAuthenticationRequest jwtAuthenticationRequest=new JwtAuthenticationRequest();
            jwtAuthenticationRequest.setUsername("super");
            jwtAuthenticationRequest.setPassword("z");
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(jwtAuthenticationRequest));
            String responseString = mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                    // .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    //  .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

         //   JwtAuthenticationResponse jwtAuthenticationResponse= JSONObject.parseObject(responseString,JwtAuthenticationResponse.class);
            JSONObject jsonObject=JSONObject.parseObject(responseString);

            System.out.println("result:" + jsonObject.get("token").toString());
           /* String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlciIsImNyZWF0ZWQiOjE1NzUwMTAxMDE2NjAsImV4cCI6MTU3NTAxNzMwMX0.Q49EFnn9ImX4NaVgzlFYg5tqudkLrgMiI1agXnANmx0-3jiCJ3AMW1CUlIQSo-zYHNR-dgPi6rVJJTLQ2Db2cQ";
            MockHttpServletRequestBuilder requestBuilder2=   MockMvcRequestBuilders.get("/customer/user/{token}",token);
             mvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().isOk())
                    // .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    //  .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                   // .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


} 
