package com.example.demo.biz.user.service.impl;

import com.example.demo.biz.user.entity.User;
import com.example.demo.biz.user.mapper.UserMapper;
import com.example.demo.biz.user.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2019-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
