package com.example.demo.biz.user.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.grcloud.base.BaseController;
import com.example.demo.biz.user.service.MenuService;
import com.example.demo.biz.user.entity.Menu;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/user/menu")
public class MenuController extends BaseController<MenuService,Menu,Integer> {
}
