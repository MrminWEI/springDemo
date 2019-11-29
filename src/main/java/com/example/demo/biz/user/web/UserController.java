package com.example.demo.biz.user.web;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.grcloud.base.BaseController;
import com.example.demo.biz.user.service.UserService;
import com.example.demo.biz.user.entity.User;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyy
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController<UserService,User,Integer> {
}
