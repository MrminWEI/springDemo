package com.example.demo.biz.user.web;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.grcloud.base.BaseController;
import com.example.demo.biz.user.service.RoleService;
import com.example.demo.biz.user.entity.Role;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/user/sRole")
public class RoleController extends BaseController<RoleService,Role,Integer> {
}
