package com.example.demo.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.biz.user.entity.User;
import com.example.demo.jwt.TokenErrorResponse;
import com.example.demo.service.LoginService;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


// 定义filterName 和过滤的url
//@WebFilter(filterName = "customerAuthFilter" ,urlPatterns = "/system/*")
public class CustomerAuthFilter implements Filter {

    Set<String> noUrl = Sets.newHashSet("/customer/login/");
    @Autowired
    private LoginService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("---------------- customer authFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        String curUrl = req.getRequestURI();

        boolean isFilter = true;
        for(String url : noUrl){
            if(StringUtils.contains(curUrl, url)){
                isFilter = false;
                break;
            }
        }
        if(StringUtils.equalsIgnoreCase("OPTIONS", req.getMethod())){

            isFilter = false;
        }
        if(isFilter){
            User customer=authService.getSession(req);

            if (customer == null) {
                HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");

                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                try {
                    out = httpResponse.getWriter();
                    out.append(JSON.toJSONString(new TokenErrorResponse("Token Error or Token Expired!")));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
                return;
            } else {
                request.setAttribute("SYS_USER_INFO", customer);
                chain.doFilter(req, response);
            }
        } else {
            chain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {

    }
}
