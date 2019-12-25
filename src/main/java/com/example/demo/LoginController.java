package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.AutoLog;
import com.example.demo.commom.FrontUser;
import com.example.demo.jwt.JwtAuthenticationRequest;
import com.example.demo.jwt.JwtAuthenticationResponse;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author : minwei
 * @date : 2019/11/18 16:42
 * @version  1.0
 */
@RestController
@RequestMapping("/customer")
public class LoginController {

    @Autowired
    LoginService loginService;


  @AutoLog
    @RequestMapping(value ="/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        System.out.println(JSONObject.toJSON(jwtAuthenticationRequest));
        JwtAuthenticationResponse token= loginService.login(jwtAuthenticationRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "/user/{token}")
    public ResponseEntity<?> getUserInfo(@PathVariable String  token) {
      FrontUser user = loginService.getUserInfo(token);
        if (user == null){
            return ResponseEntity.status(401).body(false);
        }
        else{
            return ResponseEntity.ok(user);
        }
    }
}
