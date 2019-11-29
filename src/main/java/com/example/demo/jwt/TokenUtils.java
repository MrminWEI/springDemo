package com.example.demo.jwt;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.demo.biz.user.entity.User;
import com.example.demo.biz.user.service.UserService;
import com.example.demo.utils.SessionCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tokenUtils
 *
 * @author minwei
 * @version 1.0
 * @since 2019/11/18
 */
@Component
public class TokenUtils implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    @Autowired
    UserService userService;

    @Autowired
    private SessionCache sessionCache;

    public TokenUtils() {
    }

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${gate.jwt.secret}")
    private String secret;

    @Value("${gate.jwt.expiration}")
    private Long expiration;

    /**
     * 获取token
     * @author : minwei
     * @date : 2019/11/18 16:01
     * @param user :
     * @return : java.lang.String
     */
    public String getToken(User user){
        String token=sessionCache.getToken(user.getUserName());
        if (token == null) {
            Map<String, Object> claims = new HashMap<String, Object>(16);
            claims.put(CLAIM_KEY_USERNAME, user.getUserName());
            claims.put(CLAIM_KEY_CREATED, new Date());
            token = generateToken(claims);
            sessionCache.setToken(user.getUserName(), token);
        }else{
            log.info("get old token");
        }
        return token;
    }

    public String generateToken(Map<String, Object> claims) {
        log.info("create  new token");
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public User getUsernameFromToken(String token) {
        User user = new User();
        try {
            final Claims claims = getClaimsFromToken(token);
            if(claims!=null){
                String  username = claims.getSubject();
                Wrapper<User> condition = Condition.<User>wrapper();
                condition.eq("USER_STATUS", "1");
                condition.and("USER_NAME={0}", username);
                List<User> userList = userService.selectList(condition);
                if (userList != null && userList.size()>0) {
                    user=  userList.get(0);
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                throw (ExpiredJwtException) e;
            } else {
                claims = null;
            }

        }
        return claims;
    }

    /**
     * 验证用户和token是否一致
     * @author : minwei
     * @date : 2019/11/18 16:17 
     * @param token : 
 * @param info : 
     * @return : java.lang.Boolean
     */
    public Boolean validateToken(String token, User info) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        String existToken = sessionCache.getToken(info.getUserName());
        if (token.equals(existToken)) {
            User user= getUsernameFromToken(token);
            return (user.getUserName().equals(info.getUserName()));
        } else {
            return false;
        }
    }


}
