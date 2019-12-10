package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.demo.biz.user.entity.Menu;
import com.example.demo.biz.user.entity.User;
import com.example.demo.biz.user.service.MenuService;
import com.example.demo.biz.user.service.UserService;
import com.example.demo.commom.FrontUser;
import com.example.demo.jwt.JwtAuthenticationRequest;
import com.example.demo.jwt.JwtAuthenticationResponse;
import com.example.demo.jwt.TokenUtils;
import com.example.demo.utils.MD5Encoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * LoginService
 *
 * @author minwei
 * @version 1.0
 * @since 2019/11/18
 */
@Service
public class LoginService {

    @Value("${gate:jwt:header}")
    private String headerToken;

    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;


    @Autowired
    TokenUtils tokenUtils;

    public JwtAuthenticationResponse login(JwtAuthenticationRequest jwtAuthenticationRequest) {
        String token = "";
        String msg = "";
        if (jwtAuthenticationRequest == null) {
            return new JwtAuthenticationResponse("", "登录失败");
        } else {
            Wrapper<User> condition = Condition.<User>wrapper();
            condition.eq("USER_STATUS", "1");
            condition.and("USER_NAME={0}", jwtAuthenticationRequest.getUsername());
            List<User> userList = userService.selectList(condition);
            if (userList != null && userList.size()>0) {
                User info = userList.get(0);
                if (MD5Encoder.generatePassword(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword()).equals(info.getUserPwd())) {
                    token = tokenUtils.getToken(info);
                }
                if ("".equals(token)) {
                    msg = "用户名或密码错误";
                }
            } else {
                msg = "账户不存在";
            }
            return new JwtAuthenticationResponse(token, msg);
        }

    }

    public User getUserNameByToken(String token) {
        return tokenUtils.getUsernameFromToken(token);
    }


    public User getUser(String userName) {
        return userService.selectOne(Condition.<User>wrapper().eq("USER_NAME", userName).eq("CUSTOMER_STATUS", "1"));
    }

    public FrontUser getUserInfo(String token) {
        User user = tokenUtils.getUsernameFromToken(token);
        if (user == null) {
            return null;
        } else {
            FrontUser frontUser = new FrontUser();
            BeanUtils.copyProperties(user, frontUser);
            List<Menu> menuList = menuService
                .getPermissionByUserId(Integer.valueOf(user.getUserId()));
            List<Menu> menus = new ArrayList<>();
            for (Menu per : menuList) {
                if (per.getMenuLevel() < 3) {
                    menus.add(per);
                }
            }
            frontUser.setMenus(menus);
            return frontUser;
        }
    }

    public User getSession(HttpServletRequest req) {
        String authToken = req.getHeader(this.headerToken);
        if (authToken != null) {
            User user = tokenUtils.getUsernameFromToken(authToken);
            if (user != null) {
                if (tokenUtils.validateToken(authToken, user)) {
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
